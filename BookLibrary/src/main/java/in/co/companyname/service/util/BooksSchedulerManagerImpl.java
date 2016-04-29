/*
 * 
 */
package in.co.companyname.service.util;

import org.springframework.transaction.annotation.Transactional;

import in.co.companyname.db.dao.BooksSchedulerDAOInterface;
import in.co.companyname.exceptionhandling.SystemException;
import in.co.companyname.service.util.BooksSchedulerManager;

// TODO: Auto-generated Javadoc
/**
 * The Class BooksSchedulerManagerImpl.
 */
public class BooksSchedulerManagerImpl implements BooksSchedulerManager {

    /** The books scheduler dao. */
    private BooksSchedulerDAOInterface booksSchedulerDAO;

    /**
     * Sets the books scheduler dao.
     * 
     * @param booksSchedulerDAO
     *            the new books scheduler dao
     */
    public void setBooksSchedulerDAO(
            BooksSchedulerDAOInterface booksSchedulerDAO) {
        this.booksSchedulerDAO = booksSchedulerDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see in.co.companyname.service.util.BooksSchedulerManager#addBooks()
     */
    @Override
    @Transactional
    public void addBooks() throws SystemException {
        booksSchedulerDAO.addBooks();

    }

    /*
     * (non-Javadoc)
     * 
     * @see in.co.companyname.service.util.BooksSchedulerManager#deleteBooks()
     */
    @Override
    @Transactional
    public void deleteBooks() throws SystemException {
        booksSchedulerDAO.deleteBooks();

    }

}
