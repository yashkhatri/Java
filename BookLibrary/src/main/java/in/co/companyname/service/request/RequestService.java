/*
 * 
 */
package in.co.companyname.service.request;

import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.RequestBook;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.exceptionhandling.SystemException;

// TODO: Auto-generated Javadoc
/**
 * The Interface RequestService.
 */
public interface RequestService {

    /**
     * Cancel delivery request.
     * 
     * @param requestID
     *            the request id
     * @return the string
     */
    String cancelDeliveryRequest(int requestID);

    /**
     * Return book request.
     * 
     * @param requestId
     *            the request id
     * @param returnAddress
     *            the return address
     * @return the string
     */
    String returnBookRequest(int requestId, String returnAddress);

    /**
     * Cancel return request.
     * 
     * @param requestId
     *            the request id
     * @return the string
     */
    String cancelReturnRequest(int requestId);

    /**
     * Request book.
     * 
     * @param uid
     *            the uid
     * @param bid
     *            the bid
     * @param dt
     *            the dt
     * @param deliveryAddress
     *            the delivery address
     * @return the string
     * @throws SystemException 
     */
    String requestBook(String uid, String bid, String dt, String deliveryAddress) throws SystemException;

    /**
     * Check subscription.
     * 
     * @param userName
     *            the user name
     * @return the subscription
     */
    Subscription checkSubscription(String userName);

    /**
     * Gets the requested book by book id.
     * 
     * @param userName
     *            the user name
     * @param book
     *            the book
     * @return the requested book by book id
     */
    RequestBook getRequestedBookByBookId(String userName, BookSearch book);

    /**
     * Gets the requested book by req id.
     * 
     * @param requestId
     *            the request id
     * @return the requested book by req id
     */
    RequestBook getRequestedBookByReqId(int requestId);

}
