/*
 * 
 */
package in.co.companyname.service.search;

import in.co.companyname.db.model.BookSearch;
import in.co.companyname.exceptionhandling.SystemException;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface BookSearchService.
 * 
 * @author yash.khatri
 */

public interface BookSearchService {

    /**
     * Searchbooks.
     * 
     * @param search
     *            the search
     * @return the list
     * @throws SystemException 
     */
    List<BookSearch> searchbooks(String search) throws SystemException;

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
