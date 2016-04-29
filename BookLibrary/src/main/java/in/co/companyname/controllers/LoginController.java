/**
 * 
 *
 * 
 */

package in.co.companyname.controllers;

import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.Users;
import in.co.companyname.exceptionhandling.SystemException;
import in.co.companyname.mailsender.MailSender;
import in.co.companyname.service.registration.RegisterService;
import in.co.companyname.service.search.BookSearchService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// TODO: Auto-generated Javadoc


/**
 * The Class LoginController.
 *This class handles the login request by the USERS.  
 * @author yash.khatri
 */

@Controller
// @RequestMapping(value = "loginform.html")
public class LoginController {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(LoginController.class);

    /** The register service. */
    @Autowired
    private RegisterService registerService;

    /** The book search service. */
    @Autowired
    private BookSearchService bookSearchService;

    /** The send mail. */
    @Autowired
    private MailSender sendMail = new MailSender();

    /** The recent books list. */
    private static List<BookSearch> recentBooksList = null;
    
    private static final String RECENTBOOKLIST = "recentBooksList";
    
    /** The Constant LOGINFORM. */
    private static final String LOGINFORM = "loginform";
           

    /**
     * Show form.
     * 
     * @param model
     *            the model
     * @return This method is used for showing the Login Form
     */

    @RequestMapping(value = "/")
    public String showHome(Map<Object, Object> model) {
        LOGGER.info("Index page hitted");
        return "index";
    }

    /**
     * Show form.
     * 
     * @param model
     *            the model
     * @param session
     *            the session
     * @return the string
     * @throws SystemException 
     */
    @RequestMapping(value = "loginform.html", method = RequestMethod.GET)
    public String showForm(Map<Object, Object> model, HttpSession session) throws SystemException {
        session.invalidate();
        model.put("LoginForm", true);

        LOGGER.info("In Login Page, User is requested to login");

        recentBooksList = bookSearchService.getRecentBooksList();

        model.put(RECENTBOOKLIST, recentBooksList);
        
        LOGGER.info("Recent books fetched successfully, now sending model and "
                + "view");

        return LOGINFORM;
    }

    /**
     * Login verify.
     * 
     * @param request
     *            the request
     * @param model
     *            the model
     * @param authfailed
     *            the authfailed
     * @param logout
     *            the logout
     * @param denied
     *            the denied
     * @return This is a loginVerify Method. Used to Verify the login
     *         credentials
     * @throws SystemException 
     */
    @RequestMapping("login")
    public ModelAndView getLoginForm(HttpServletRequest request, Model model,
            @RequestParam(required = false) String authfailed, String logout,
            String denied) throws SystemException {
        String message = "";
        if (authfailed != null) {
            LOGGER.info("Authentication Failed");
            message = "Invalid username or password, try again !";
        } else if (logout != null) {
            LOGGER.info("Logged Out Successfully"); 
            HttpSession session = request.getSession();
            session.invalidate();
            message = "Logged out successfully, login again to continue !";
        } else if (denied != null) {
            LOGGER.info("Access Denied");
            HttpSession session = request.getSession();
            session.invalidate();
            message = "Access denied for this user !";
        }
        model.addAttribute("LoginForm", true);
        recentBooksList = bookSearchService.getRecentBooksList();

        model.addAttribute(RECENTBOOKLIST, recentBooksList);
        
        LOGGER.info("Recent Books fetched successfully now sending model and "
                + "view");
        return new ModelAndView(LOGINFORM, "message", message);
    }

    /**
     * Ge user page.
     * 
     * @param request
     *            the request
     * @param model
     *            the model
     * @return the string
     */
    @RequestMapping("user")
    public String geUserPage(HttpServletRequest request, Model model) {
        
        LOGGER.info("Requested to See User_Home page");
        
        String userName = request.getUserPrincipal().getName();
               
        LOGGER.info("UserName" + userName);
        HttpSession session = request.getSession(true);

        Users user = registerService.getUserById(userName);
        String firstName = user.getFirstName();
        session.setAttribute("firstname", firstName);

        String completeAddress = user.getAddress().trim().concat(" , ")
                .concat(user.getCity()).concat(" , ").concat(user.getState());

        session.setAttribute("uname", userName);
        session.setAttribute("address", completeAddress.trim());

        LOGGER.info("Login Successful. Redirecting to User's home page");

        model.addAttribute(RECENTBOOKLIST, recentBooksList);
        
        LOGGER.info("Recent Books Fetched now sending model and view");

        return "userHome";
    }

    /**
     * Ge admin page.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @return the string
     */
    @RequestMapping("admin")
    public String geAdminPage(Model model, HttpServletRequest request) {

        LOGGER.info("Requested to see admin home page");
        
        HttpSession session = request.getSession(true);
        String userName = request.getUserPrincipal().getName();

        Users user = registerService.getUserById(userName);
        String firstName = user.getFirstName();
        session.setAttribute("firstname", firstName);
        session.setAttribute("uname", userName);
        
        LOGGER.info("Request completed to see admin home page now sending model"
                + "and view");

        return "adminHomeNew";
    }

  
  

    /* *************** Show Recover Page *************************** */
    /**
     * Recover.
     * 
     * @param model
     *            the model
     * @param session
     *            the session
     * @return the string
     * @throws SystemException 
     */
    @RequestMapping(value = "Recover.html", method = RequestMethod.GET)
    public String recover(Map<Object, Object> model, HttpSession session) throws SystemException {

        LOGGER.info("Requested to see recover password page");
        session.invalidate();
        
       recentBooksList = bookSearchService
                .getRecentBooksList();
        
        model.put("Recover", true);
        model.put(RECENTBOOKLIST, recentBooksList);
        
        LOGGER.info("Request to see recover page completed. Now sending model "
                + "and view");
        
        return LOGINFORM;
    }

    /* *************** Show Recover Continue Page *************************** */
    /**
     * Recover continue.
     * 
     * @param model
     *            the model
     * @param session
     *            the session
     * @param request
     *            the request
     * @return the model and view
     */
    @RequestMapping(value = "RecoverMail.html", method = RequestMethod.POST)
    public ModelAndView recoverContinue(Map<Object, Object> model,
            HttpSession session, HttpServletRequest request) {
        
        LOGGER.info("Request to recover the password");
        
        session.invalidate();
        String userId = (String) request.getParameter("recoverEmail");
        Users user = registerService.getUserById(userId);
        ModelAndView modelAndView = new ModelAndView(LOGINFORM);

        try {
            if (user != null) {

                sendMail.shootMail("USER_PASSWORD_RECOVERY", user, null, null);
                modelAndView.addObject("Msg", "The Password has been sent to"
                        + "your registered email id.");
            } else {
                modelAndView.addObject("Msg", "The Username doesnot found."
                        + "Please Register to continue");
            }

        } catch (Exception e) {
            LOGGER.info("Exception in Send MAil at register" + e);
        }

        model.put(RECENTBOOKLIST, recentBooksList);
        model.put("RecoverContinue", true);

        LOGGER.info("Password recovery request completed successfully now"
                + "sending mail and returning model and view");
        
        return modelAndView;
    }

   
}