/*
 * 
 */
package in.co.companyname.service.request;

import static in.co.companyname.constants.Constants.ALREADY_REQUESTED;
import static in.co.companyname.constants.Constants.CANCELLED_SUCCESS;
import static in.co.companyname.constants.Constants.FAILURE;
import static in.co.companyname.constants.Constants.IMPENDING_EXPIRY;
import static in.co.companyname.constants.Constants.PLAN_EXPIRED;
import static in.co.companyname.constants.Constants.REQUESTED_SUCCESSFULLY;
import static in.co.companyname.constants.Constants.UPGRADE_PLAN;
import in.co.companyname.db.dao.BookSearchDAO;
import in.co.companyname.db.dao.RequestDAO;
import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.RequestBook;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.exceptionhandling.SystemException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class RequestServiceImpl.
 */
@Service("requestService")
public class RequestServiceImpl implements RequestService {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RequestServiceImpl.class);

    

    /** The request dao. */
    @Autowired
    private RequestDAO requestDAO;

    /** The book search dao. */
    @Autowired
    private BookSearchDAO bookSearchDAO;


    /**
     * Sets the request dao.
     * 
     * @param requestDAO
     *            the new request dao
     */
    public void setRequestDAO(RequestDAO requestDAO) {
        this.requestDAO = requestDAO;
    }

    /**
     * Sets the book search dao.
     * 
     * @param bookSearchDAO
     *            the new book search dao
     */
    public void setBookSearchDAO(BookSearchDAO bookSearchDAO) {
        this.bookSearchDAO = bookSearchDAO;
    }




    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.request.RequestService#requestBook(java.lang.String
     * , java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public String requestBook(String userId, String bookId, String dt,
            String deliveryAddress) throws SystemException {

        int requestedBooks = requestDAO.countRequestedBooks(userId);
        LOGGER.info("in service requested Books"
                + Integer.toString(requestedBooks));

        int holdingBooks = requestDAO.countHoldingBooks(userId);
        LOGGER.info("in Service holding books" + Integer.toString(holdingBooks));

        Subscription subscription = requestDAO.checkSubscription(userId);

        int maxBooks = 0;
       
        boolean impendingExpiry =true;

        if (subscription != null) {
            maxBooks = subscription.getMaxBooks();
           
        }

        int total = requestedBooks + holdingBooks;

        BookSearch book = bookSearchDAO.getBookById(bookId);

        Integer bookAvailablity = book.getBookAvailablity();

      

        if (subscription != null) {

            if(impendingExpiry)         {

                if (bookAvailablity > 0) {
                    /* Checking Availability */
               
                        if (total < maxBooks) {
                            /* checking if max books reached */
                            String requestResult = requestDAO.requestBook(userId,
                                    bookId, deliveryAddress);
                            if (requestResult.equals(REQUESTED_SUCCESSFULLY)) {
                                bookAvailablity = bookAvailablity - 1;
                                /* Decreasing Book quantity */
                                book.setBookAvailablity(bookAvailablity);
                                requestDAO.changeAvailablity(book);

                                return requestResult;
                            } else {
                                if (requestResult.equals(ALREADY_REQUESTED)) {
                                    return ALREADY_REQUESTED;
                                }
                            }
                        } else {
                            return UPGRADE_PLAN;
                        }
                   
                } else {
                    return FAILURE;
                }

            }else
            {
                LOGGER.info("impendingExpiry");
                return IMPENDING_EXPIRY;
            }
        }else {
            return PLAN_EXPIRED;
        }
        
        
        return FAILURE;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.request.RequestService#cancelDeliveryRequest(int)
     */
    @Override
    @Transactional
    public String cancelDeliveryRequest(int requestId) {
        // TODO Auto-generated method stub

        RequestBook requestedBook = requestDAO.getBookByRequestId(requestId);
        BookSearch book = requestedBook.getBookSearch();

        int bookAvailablity = book.getBookAvailablity();
        bookAvailablity = bookAvailablity + 1;
        book.setBookAvailablity(bookAvailablity);

        String cancelResult = requestDAO.cancelDeliveryRequest(requestedBook);

        if (cancelResult == CANCELLED_SUCCESS) {
            requestDAO.changeAvailablity(book);
        }

        return cancelResult;
    }

    /*
     * (non-Javadoc)
     * 
     * @see in.co.companyname.service.request.RequestService#returnBookRequest(int,
     * java.lang.String)
     */
    @Override
    @Transactional
    public String returnBookRequest(int requestId, String returnAddress) {
        // TODO Auto-generated method stub

        return requestDAO.returnBookRequest(requestId, returnAddress);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.request.RequestService#cancelReturnRequest(int)
     */
    @Override
    @Transactional
    public String cancelReturnRequest(int requestId) {
        // TODO Auto-generated method stub

        return requestDAO.cancelReturnRequest(requestId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.request.RequestService#checkSubscription(java.lang
     * .String)
     */
    @Override
    public Subscription checkSubscription(String userName) {
        // TODO Auto-generated method stub
        return requestDAO.checkSubscription(userName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.request.RequestService#getRequestedBookByBookId
     * (java.lang.String, in.co.companyname.db.model.BookSearch)
     */
    @Override
    public RequestBook getRequestedBookByBookId(String userName, BookSearch book) {
        // TODO Auto-generated method stub
        return requestDAO.getRequestedBook(userName, book);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.request.RequestService#getRequestedBookByReqId(int)
     */
    @Override
    public RequestBook getRequestedBookByReqId(int requestId) {
        // TODO Auto-generated method stub
        return requestDAO.getBookByRequestId(requestId);
    }

}
