/**
 * 
 *
 * 
 */

package in.co.companyname.db.dao;

import in.co.companyname.db.model.BookSearch;
import in.co.companyname.exceptionhandling.SystemException;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface BookSearchDAO.
 *
 * @author yash.khatri
 */
/**
 * 
 * This interface deals with Books table has a method for searching the books
 * requested by the user.
 * */
public interface BookSearchDAO {

    /**
     * Search books.
     * 
     * @param searchCriteria
     *            the search criteria
     * @return the list
     * @throws SystemException 
     */
    List<BookSearch> searchBooks(String searchCriteria) throws SystemException;

    /**
     * Gets the book by id.
     * 
     * @param bookId
     *            the book id
     * @return the book by id
     * @throws SystemException 
     */
    BookSearch getBookById(String bookId) throws SystemException;

    /**
     * Gets the recent books list.
     * 
     * @return the recent books list
     * @throws SystemException 
     */
    List<BookSearch> getRecentBooksList() throws SystemException;

}
