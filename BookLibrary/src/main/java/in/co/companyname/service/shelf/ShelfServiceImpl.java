/**
 * 
 *
 * 
 */
package in.co.companyname.service.shelf;

import in.co.companyname.db.dao.ShelfDAO;
import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.RequestBook;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.exceptionhandling.SystemException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc

/**
 * The Class ShelfServiceImpl.
 * 
 * @author yash.khatri
 */

@Service("shelfService")
public class ShelfServiceImpl implements ShelfService {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ShelfServiceImpl.class);

    /** The shelf dao. */
    @Autowired
    private ShelfDAO shelfDAO;

    /**
     * Sets the shelf dao.
     * 
     * @param shelfDAO
     *            the new shelf dao
     */
    public void setShelfDAO(ShelfDAO shelfDAO) {
        this.shelfDAO = shelfDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.shelf.ShelfService#addToShelf(java.lang.String,
     * java.lang.String)
     */
    @Override
    @Transactional
    public String addToShelf(String bookId, String userId) {
        return shelfDAO.addToShelf(bookId, userId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see in.co.companyname.service.shelf.ShelfService#showShelf(java.lang.String)
     */
    @Override
    public List<BookSearch> showShelf(String userId) throws SystemException {
        return (List<BookSearch>) shelfDAO.showShelf(userId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.shelf.ShelfService#removeFromShelf(java.lang.String
     * , java.lang.String)
     */
    @Override
    @Transactional
    public String removeFromShelf(String userId, String bookId) {
        LOGGER.info("service" + userId);
        LOGGER.info(bookId);
        return shelfDAO.removeFromShelf(userId, bookId);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.shelf.ShelfService#showRequestedBooks(java.lang
     * .String)
     */
    @Override
    public List<RequestBook> showRequestedBooks(String uid) throws SystemException {
        return shelfDAO.showRequestedBooks(uid);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.shelf.ShelfService#showBooksHolding(java.lang.String
     * )
     */
    @Override
    public List<RequestBook> showBooksHolding(String uid) throws SystemException {
        // TODO Auto-generated method stub

        return shelfDAO.showBooksHolding(uid);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.shelf.ShelfService#getSubscription(java.lang.String
     * )
     */
    @Override
    public List<Subscription> getSubscription(String userId) {
        // TODO Auto-generated method stub
        return shelfDAO.getSubscription(userId);
    }

}
