/*
 * 
 */
package in.co.companyname.controllers;

import java.util.List;

import in.co.companyname.db.model.Plans;
import in.co.companyname.service.subscriptionplan.SubscriptionPlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// TODO: Auto-generated Javadoc
/**
 * The Class SubscriptionPlansController.
 * This class deals with handling the requests for deletion and addition of the
 * subscriptions plans.
 */
/**
 * @author yash.khatri
 * 
 */
@Controller
public class SubscriptionPlansController {


    /** The Subscription plan service. */
    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    /**
     * Gets the form.
     * 
     * @return the form
     */
    @RequestMapping(value = "addSubscriptionPlans.htm", method = RequestMethod.GET)
    public ModelAndView getForm() {
        return new ModelAndView("addSubscriptionPlans");
    }

    /**
     * Listplans.
     * 
     * @return the model and view
     */
    @RequestMapping("subscriptionPlansList.htm")
    public ModelAndView listplans() {

        List<Plans> plansList = subscriptionPlanService.getPlansList();
        ModelAndView modelAndView = new ModelAndView("subscriptionPlansList");
        modelAndView.addObject("plansList", plansList);
        return modelAndView;

    }

    /**
     * Insert user.
     * 
     * @param plans
     *            the plans
     * @return the model and view
     */
    @RequestMapping("subsriptionPlansInsert.htm")
    public ModelAndView insertPlans(@ModelAttribute Plans plans) {
        subscriptionPlanService.insertPlans(plans);
        return new ModelAndView("redirect:subscriptionPlansList.htm");

    }

    /**
     * Edits the plans.
     * 
     * @param planId
     *            the plan id
     * @return the model and view
     */
    @RequestMapping("subsriptionPlansEdit.htm")
    public ModelAndView editPlans(@RequestParam("planId") int planId) {
        Plans plans = subscriptionPlanService.getPlanById(planId);
        return new ModelAndView("addSubscriptionPlans", "plans", plans);
    }

    /**
     * Delete user.
     * 
     * @param planId
     *            the plan id
     * @return the model and view
     */
    @RequestMapping(value = "subsriptionPlansDelete.htm", method = RequestMethod.GET)
    public ModelAndView deletePlans(@RequestParam int planId) {
        subscriptionPlanService.deletePlan(planId);
        return new ModelAndView("redirect:subscriptionPlansList.htm");
    }

}
