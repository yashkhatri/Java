/**
 * 
 *
 * 
 */
package in.co.companyname.db.dao;

import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.RequestBook;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.exceptionhandling.SystemException;

import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Interface ShelfDAO.
 * 
 * @author yash.khatri
 */

public interface ShelfDAO {

    /**
     * Adds the to shelf.
     * 
     * @param bid
     *            the bid
     * @param uid
     *            the uid
     * @return the string
     */
    String addToShelf(String bid, String uid);

    /**
     * Show shelf.
     * 
     * @param uid
     *            the uid
     * @return the list
     * @throws SystemException 
     */
    List<BookSearch> showShelf(String uid) throws SystemException;

    /**
     * Removes the from shelf.
     * 
     * @param uid
     *            the uid
     * @param bid
     *            the bid
     * @return the string
     */
    String removeFromShelf(String uid, String bid);

    /**
     * Show requested books.
     * 
     * @param uid
     *            the uid
     * @return the list
     * @throws SystemException 
     */
    List<RequestBook> showRequestedBooks(String uid) throws SystemException;

    /**
     * Show books holding.
     * 
     * @param uid
     *            the uid
     * @return the list
     * @throws SystemException 
     */
    List<RequestBook> showBooksHolding(String uid) throws SystemException;

    /**
     * Gets the subscription.
     * 
     * @param userId
     *            the user id
     * @return the subscription
     */
    List<Subscription> getSubscription(String userId);

    /**
     * Show recommendation.
     * 
     * @param uid
     *            the uid
     * @return the list
     */
    List<BookSearch> showRecommendation(String uid);

}
