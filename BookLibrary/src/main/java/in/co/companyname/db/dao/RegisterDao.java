/*
 * 
 */
package in.co.companyname.db.dao;

import in.co.companyname.db.model.Plans;
import in.co.companyname.db.model.Roles;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.db.model.Users;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface RegisterDao.
 */
public interface RegisterDao {

    /**
     * Insert row.
     * 
     * @param users
     *            the users
     */
    void insertUser(Users users);

    /**
     * Gets the users list.
     * 
     * @return the users list
     */
    List<Users> getUsersList();

    /**
     * Gets the user by id.
     * 
     * @param userName
     *            the user name
     * @return the user by id
     */
    Users getUserById(String userName);

    /**
     * Delete row.
     * 
     * @param id
     *            the id
     */
    void deleteUser(String id);

    /**
     * Insert subscription.
     * 
     * @param subscription
     *            the subscription
     */
    void insertSubscription(Subscription subscription);

    /**
     * Gets the plan by id.
     * 
     * @param planId
     *            the plan id
     * @return the plan by id
     */
    Plans getPlanById(int planId);

    /**
     * Save search criteria.
     * 
     * @param userName
     *            the user name
     * @param searchCriteria
     *            the search criteria
     */
    void saveSearchCriteria(String userName, String searchCriteria);

    /**
     * Check availability.
     * 
     * @param userName
     *            the user name
     * @return the boolean
     */
    Boolean checkAvailability(String userName);

    /**
     * Insert roles.
     * 
     * @param roles
     *            the roles
     */
    void insertRoles(Roles roles);

}
