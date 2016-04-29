/*
 * 
 */
package in.co.companyname.service.subscriptionplan;

import in.co.companyname.db.dao.SubscriptionPlansDao;
import in.co.companyname.db.model.Plans;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class SubscriptionPlanServiceImpl.
 */
@Repository
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService

{

    /** The subscription plans dao. */
    @Autowired
    private SubscriptionPlansDao subscriptionPlansDao;

    
    /**
     * Sets the subscription plans dao.
     * 
     * @param subscriptionPlansDao
     *            the new subscription plans dao
     */
    public void setSubscriptionPlansDao(
            SubscriptionPlansDao subscriptionPlansDao) {
        this.subscriptionPlansDao = subscriptionPlansDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.subscriptionplan.SubscriptionPlanService#insertRow
     * (in.co.companyname.db.model.Plans)
     */
    @Override
    @Transactional
    public void insertPlans(Plans plans) {
        // TODO Auto-generated method stub
        subscriptionPlansDao.insertPlan(plans);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.subscriptionplan.SubscriptionPlanService#getPlansList
     * ()
     */
    @Override
    public List<Plans> getPlansList() {
        // TODO Auto-generated method stub
        return subscriptionPlansDao.getPlansList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.subscriptionplan.SubscriptionPlanService#getRowById
     * (java.lang.Integer)
     */
    @Override
    public Plans getPlanById(Integer planId) {
        // TODO Auto-generated method stub
        return subscriptionPlansDao.getPlanById(planId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.subscriptionplan.SubscriptionPlanService#deleteRow
     * (java.lang.Integer)
     */
    @Override
    @Transactional
    public void deletePlan(Integer planId) {
        // TODO Auto-generated method stub
        subscriptionPlansDao.deletePlan(planId);

    }

}
