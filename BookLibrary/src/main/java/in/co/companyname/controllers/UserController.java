/*
 * 
 */
package in.co.companyname.controllers;

import static in.co.companyname.constants.Constants.PLAN_UPGRADED;
import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.Plans;
import in.co.companyname.db.model.RequestBook;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.db.model.Users;
import in.co.companyname.exceptionhandling.SystemException;
import in.co.companyname.service.registration.RegisterService;
import in.co.companyname.service.search.BookSearchService;
import in.co.companyname.service.shelf.ShelfService;
import in.co.companyname.service.subscriptionplan.SubscriptionPlanService;
import in.co.companyname.service.userfunction.UserFunctionService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// TODO: Auto-generated Javadoc

/**
 * The Class UserController.
 * This class is responsible for handling all the request from the users 
 * related to his/her profile or subscription plans. 
 * 
 * @author yash.khatri
 */

@Controller
public class UserController {

    /** The user function service. */
    @Autowired
    private UserFunctionService userFunctionService;

    /** The shelf service. */
    @Autowired
    private ShelfService shelfService;

    /** The book search service. */
    @Autowired
    private BookSearchService bookSearchService;

    /** The Subscription plan service. */
    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    /** The register service. */
    @Autowired
    private RegisterService registerService;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(UserController.class);

    /** The Constant USERNAME. */
    private static final String USERNAME ="uname";

    /**
     * View profile.
     * 
     * @param request
     *            the request
     * @param response
     *            the response
     * @param m
     *            the m
     * @return the string
     */
    @RequestMapping(value = "user_ViewProfile", method = RequestMethod.GET)
    public String viewProfile(HttpServletRequest request,
            HttpServletResponse response, Map<String, Object> m) {

        LOGGER.info("Requested to see user profile");
        
        HttpSession session = request.getSession(false);
        String userName = (String) session.getAttribute(USERNAME);
        Users user = userFunctionService.showProfile(userName);

        m.put("myProfile", user);
        m.put("showProfile", true);
        
        LOGGER.info("Data Fetched successfully now returning model and view");
        
        return "viewProfile";
    }

    /**
     * View history.
     * 
     * @param request
     *            the request
     * @param response
     *            the response
     * @param map
     *            the map
     * @return the string
     * @throws SystemException 
     */
    @RequestMapping(value = "user_ViewHistory", method = RequestMethod.GET)
    public String viewHistory(HttpServletRequest request,
            HttpServletResponse response, Map<String, Object> map) throws SystemException {
        
        LOGGER.info("Requested to see History");
        
        HttpSession session = request.getSession(false);
        String userName = (String) session.getAttribute(USERNAME);
        List<RequestBook> requestedBooks = shelfService
                .showRequestedBooks(userName);
        map.put("requestedBooks", requestedBooks);
        map.put("showHistory", true);
        
        LOGGER.info("History data fetched successfully, now returning model & "
                + "view");
        
        return "returnedHistory";
    }

    /**
     * Subscription history.
     * 
     * @param request
     *            the request
     * @param response
     *            the response
     * @param map
     *            the map
     * @return the string
     */
    @RequestMapping(value = "user_SubscriptionHistory", method = RequestMethod.GET)
    public String subscriptionHistory(HttpServletRequest request,
            HttpServletResponse response, Map<String, Object> map) {
        
        LOGGER.info("Requested to see subscription history");
        
        HttpSession session = request.getSession(false);
        String userName = (String) session.getAttribute(USERNAME);
        List<Subscription> subscriptions = shelfService
                .getSubscription(userName);
        map.put("subscriptionhistory", subscriptions);
        map.put("showSubscriptionHistory", true);
        
        LOGGER.info("Subscription history's Data fetched successfully"
                + "now returning model & view");
        
        return "subscriptionHistory";
    }

    /**
     * Gets the form.
     * 
     * @param model
     *            the model
     * @return the form
     * @throws SystemException 
     */
    @RequestMapping(value = "user_showuserhome", method = RequestMethod.GET)
    public ModelAndView getForm(Map<Object, Object> model) throws SystemException {
        
        LOGGER.info("Requested to get User Home");
        
        List<BookSearch> recentBooksList = bookSearchService
                .getRecentBooksList();

        model.put("recentBooksList", recentBooksList);
        
        LOGGER.info("Recent Books fetched successfully now returning model"
                + "& view");
        
        return new ModelAndView("userHome");
    }

    /**
     * Listplans.
     * 
     * @return the model and view
     */
    @RequestMapping("user_upgradeplan.htm")
    public ModelAndView listplans() {
        
        LOGGER.info("Requested to upgrade plan");
        
        List<Plans> plansList = subscriptionPlanService.getPlansList();
        ModelAndView modelAndView = new ModelAndView("upgradePlan");
        modelAndView.addObject("plansList", plansList);
        
        LOGGER.info("Plans List fetched successfully, now returning model &"
                + "view");
        
        return modelAndView;

    }

    /**
     * Enrol new plan.
     * 
     * @param request
     *            the request
     * @param response
     *            the response
     * @param map
     *            the map
     * @return the string
     */
    @RequestMapping(value = "user_enrolNewPlan", method = RequestMethod.POST)
    public String enrolNewPlan(HttpServletRequest request,
            HttpServletResponse response, Map<String, Object> map) {
        
        LOGGER.info("requested to enrol to new plan");
        
        HttpSession session = request.getSession(false);
        String userName = (String) session.getAttribute(USERNAME);
        int planId = (Integer.parseInt(request.getParameter("planId")));
        String upgradeResult = registerService.upgradeSubscription(userName,
                planId);
        if (upgradeResult.equals(PLAN_UPGRADED)) {
            map.put("upgradeSuccess", upgradeResult);
        } else {
            map.put("upgradeFailure", upgradeResult);
        }
        map.put("upgradeResult", upgradeResult);
        List<Plans> plansList = subscriptionPlanService.getPlansList();
        map.put("plansList", plansList);
        
        LOGGER.info("Request to enrol a new plan completed successfully"
                + "now returning model & view");
        
        return "upgradePlan";
    }

    /**
     * Show recommendations.
     *
     * @param request            the request
     * @param response            the response
     * @param map            the map
     * @return the model and view
     */
    @RequestMapping("user_Recommendations.html")
    public ModelAndView showRecommendations(HttpServletRequest request,
            HttpServletResponse response, Map<String, Object> map) {
        
        LOGGER.info("Request to view recommended books");
        
        HttpSession session = request.getSession(false);
        String userName = (String) session.getAttribute(USERNAME);
        List<BookSearch> books = userFunctionService
                .showRecommendedBooks(userName);
        LOGGER.info("Recommended  books" + books);

        ModelAndView modelAndView = new ModelAndView("recommendedBooks");

        modelAndView.addObject("recommendedBooks", books);
        
        LOGGER.info("Recommeded books fetched successfully. Now returning model"
                + " & view");
        
        return modelAndView;

    }

}
