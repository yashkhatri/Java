/*
 * 
 */
package in.co.companyname.controllers;

import in.co.companyname.db.model.Plans;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.db.model.Users;
import in.co.companyname.mailsender.MailSender;
import in.co.companyname.service.registration.RegisterService;
import in.co.companyname.service.request.RequestService;
import in.co.companyname.service.subscriptionplan.SubscriptionPlanService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterController.
 * This class contains all the code related to the registration of the
 * unregistered user.
 */
/**
 * 
 * @author yash.khatri
 *
 */
@Controller
public class RegisterController {

    /** The register service. */
    @Autowired
    private RegisterService registerService;

    /** The request service. */
    @Autowired
    private RequestService requestService;

    /** The SubscriptionPlan service. */
    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RegisterController.class);

    /** The send mail. */
    @Autowired
    private MailSender sendMail = new MailSender();

    /**
     * Gets the form.
     * 
     * @return the form
     */
    @RequestMapping(value = "registrationForm.html", method = RequestMethod.GET)
    public ModelAndView getForm() {

        LOGGER.info("Request to see the registration form");

        List<Plans> plansList = subscriptionPlanService.getPlansList();
        ModelAndView model = new ModelAndView("registrationForm");
        model.addObject("plansList", plansList);
        
        LOGGER.info("Plans list fetched now sending model and view of register "
                + "page");
        
        return model;
    }

    /**
     * List users.
     * 
     * @return the model and view
     */
    @RequestMapping("userList.htm")
    public ModelAndView listUsers() {
        LOGGER.info("Requested to see the complete users list");
        LOGGER.info("in register controller method catching list");
        List<Users> usersList = registerService.getUsersList();
        ModelAndView modelAndView = new ModelAndView("userList");
        modelAndView.addObject("usersList", usersList);
        LOGGER.info("");  
        return modelAndView;
    }

    /**
     * Insert user.
     * 
     * @param users
     *            the users
     * @return the model and view
     */
    @RequestMapping("Register.htm")
    public ModelAndView insertUser(@ModelAttribute Users users) {

        LOGGER.info("Inserting User Details during Registration");

        registerService.insertUser(users);

        Users user = registerService.getUserById(users.getUserName());
        Subscription subscription = requestService.checkSubscription(users
                .getUserName());

        try {
            sendMail.shootMail("REGISTRATION", user, subscription, null);
        } catch (Exception e) {
            LOGGER.info("Exception in Send MAil at register" + e);
        }
        
        LOGGER.info("Request to insert registration details completed"
                + "now redirecting to login page");
        
        return new ModelAndView("redirect:/loginform.html");

    }

    /**
     * Update user.
     * 
     * @param users
     *            the users
     * @return the model and view
     */
    @RequestMapping("user_updateUser.htm")
    public ModelAndView updateUser(@ModelAttribute Users users) {
        
        LOGGER.info("Request to update user record");
        
        registerService.updateUser(users);
        
        LOGGER.info("Request to update record completed, now redirecting to"
                + "user profile");
        
        return new ModelAndView("redirect:/user_ViewProfile");

    }

    /**
     * Change password.
     * 
     * @param request
     *            the request
     * @param map
     *            the map
     */
    @RequestMapping("admin_changePassword.htm")
    public void changePassword(HttpServletRequest request,
            Map<String, String> map) {
       
        LOGGER.info("Request to change the admin password");
        
        HttpSession session = request.getSession(false);
        String newPassword = request.getParameter("newPassword");
        String userName = (String) session.getAttribute("uname");
        Users user = registerService.getUserById(userName);

        user.setUserPassword(newPassword);            
        registerService.updateUser(user);
        LOGGER.info("Request to update admin password Completed");
    }

    /**
     * Edits the user.
     * 
     * @param userName
     *            the user name
     * @return the model and view
     */
    @RequestMapping("user_userEdit.htm")
    public ModelAndView editUser(@RequestParam String userName) {
        LOGGER.info("Request to edit User");
        Users user = registerService.getUserById(userName);
        LOGGER.info("Request completed, now sending model and view");
        return new ModelAndView("editProfile", "user", user);
    }

    /**
     * Delete user.
     * 
     * @param userName
     *            the user name
     * @return the model and view
     */
    @RequestMapping("userDelete.htm")
    public ModelAndView deleteUser(@RequestParam String userName) {
        LOGGER.info("Request to delete the user");
        registerService.deleteUser(userName);
        LOGGER.info("Request completed, now redirecting to users list");
        return new ModelAndView("redirect:/user_userList.htm");
    }

    /**
     * Ajax check avalability.
     * 
     * @param model
     *            the model
     * @param userName
     *            the user name
     * @return the string
     */
    @RequestMapping(value = "checkavailability.htm", method = RequestMethod.POST)
    @ResponseBody
    public String ajaxCheckAvalability(Model model,
            @RequestParam("userName") String userName) {
        LOGGER.info("Ajax request to check the user name availability");

        Boolean flag = registerService.checkAvailability(userName);
        if (flag) {
            LOGGER.info("User Name Avaiable, returning true");
            return "true";
        } else {
            LOGGER.info("User Name Not Available, returnign false");
            return "false";
        }
    }
}
