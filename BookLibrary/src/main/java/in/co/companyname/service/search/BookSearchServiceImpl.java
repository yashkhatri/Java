/**
 * 
 *
 * 
 */
package in.co.companyname.service.search;

import in.co.companyname.db.dao.BookSearchDAO;
import in.co.companyname.db.model.BookSearch;
import in.co.companyname.exceptionhandling.SystemException;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class BookSearchServiceImpl.
 * 
 * @author yash.khatri
 */

@Service("bookSearchService")
@Transactional
public class BookSearchServiceImpl implements BookSearchService {

    /**
     * Instantiates a new book search service impl.
     */
    public BookSearchServiceImpl() {
    }

    /** The book search dao. */
    @Autowired
    private BookSearchDAO bookSearchDAO;

     /**
     * Sets the booksearch dao.
     * 
     * @param bookSearchDAO
     *            the new booksearch dao
     */
    public void setBooksearchDAO(BookSearchDAO bookSearchDAO) {
        this.bookSearchDAO = bookSearchDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.search.BookSearchService#searchbooks(java.lang.
     * String)
     */
    public List<BookSearch> searchbooks(String searchCriteria) throws SystemException {

        List<BookSearch> bookList = bookSearchDAO.searchBooks(searchCriteria);

        return bookList;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.search.BookSearchService#getBookById(java.lang.
     * String)
     */
    @Override
    public BookSearch getBookById(String bookId) throws SystemException {
        // TODO Auto-generated method stub
        return bookSearchDAO.getBookById(bookId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see in.co.companyname.service.search.BookSearchService#getRecentBooksList()
     */
    @Override
    public List<BookSearch> getRecentBooksList() throws SystemException {

        return bookSearchDAO.getRecentBooksList();
    }
}
