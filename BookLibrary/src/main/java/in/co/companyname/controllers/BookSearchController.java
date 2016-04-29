/**
 * 
 *
 * 
 */
package in.co.companyname.controllers;

import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.RequestBook;
import in.co.companyname.exceptionhandling.SystemException;
import in.co.companyname.service.search.BookSearchService;
import in.co.companyname.service.shelf.ShelfService;
import in.co.companyname.service.userfunction.UserFunctionService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// TODO: Auto-generated Javadoc

/**
 * The Class BookSearchController.
 * This class deals with handling the request 
 * for searching and inserting books into the database.
 * 
 * @author yash.khatri
 */

@Controller
public class BookSearchController {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(BookSearchController.class);

    /** The book search service. */
    @Autowired
    private BookSearchService bookSearchService;

    /** The shelf service. */
    @Autowired
    private ShelfService shelfService;

    /** The user function service. */
    @Autowired
    private UserFunctionService userFunctionService;
    
    /** The Constant USERNAME. */
    private static final String USERNAME ="uname";
    
    private static final String SEARCHCRITERIA ="searchCriteria";

    /**
     * * @param model.
     *
     * @param searchCriteria            the search criteria
     * @param response            the response
     * @param request            the request
     * @return This method deals with calling the searchBooks method from the
     *         bookSearchService
     */
    @RequestMapping(value = "BookSearchHelper", method = RequestMethod.GET)
    public String bookSearchHelper(
            @RequestParam(SEARCHCRITERIA) String searchCriteria,
            HttpServletResponse response, HttpServletRequest request) {
        
        LOGGER.info("Search Criteria reached Properly");
        
        HttpSession session = request.getSession(false);

        session.setAttribute(SEARCHCRITERIA, searchCriteria);
        
        return "redirect:BookSearch";
    }

    /**
     * Book search.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param response
     *            the response
     * @param session
     *            the session
     * @return the string
     * @throws SystemException 
     */
    @RequestMapping(value = "BookSearch", method = RequestMethod.GET)
    public String bookSearch(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response,
            HttpSession session) throws SystemException {

        LOGGER.info("Requested to search books");
        
        String searchCriteria = (String) session.getAttribute(SEARCHCRITERIA);
        String userName = (String) session.getAttribute(USERNAME);

        LOGGER.info("in controller***********" + searchCriteria);
        List<BookSearch> bResult = bookSearchService
                .searchbooks(searchCriteria);
        if (!bResult.isEmpty()) {
            userFunctionService.saveSearchCriteria(userName, searchCriteria);
        }
        model.put("bookresults", bResult);
        model.put("showSearchedBooks", true);
        
        LOGGER.info("Searched books fetched. Returning model & view");
        
        return "searchedBooks";
    }

    /**
     * This method deals with calling the addToShelf method from the
     * bookSearchService to addBooks into the users personal shelf.
     * 
     * @param request
     *            the request
     * @param response
     *            the response
     * @param m
     *            the m
     */

    @RequestMapping(value = "/user_addBook", method = RequestMethod.POST)
    public String addBooks(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("bid") String bookId) {

        LOGGER.info("Requested to add books in the shelf from searched books");
        
        HttpSession session = request.getSession(false);

        String userId = (String) session.getAttribute(USERNAME);
        String addResult = shelfService.addToShelf(bookId, userId);

        session.setAttribute("addresult", addResult);
       
        return "redirect:BookSearch";

    } 

    /**
     * * @param model.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param response
     *            the response
     * @return This method deals with calling the showShelf method from the
     *         bookSearchService to Show users personal shelf
     * @throws SystemException 
     */

    @RequestMapping(value = "/user_showBookShelf", method = RequestMethod.GET)
    public String getShelf(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) throws SystemException {
       
        LOGGER.info("Requested to see the shelf");
        
        HttpSession session = request.getSession(false);

        String userId = (String) session.getAttribute(USERNAME);

        LOGGER.info(userId);
        List<BookSearch> shelf = shelfService.showShelf(userId);

        model.put("shelfresults", shelf);
        model.put("showShelf", true);

        model.put("showMessage", true);

        return "showShelf";

    }

    /**
     * Anonymous search.
     * 
     * @param model
     *            the model
     * @param searchCriteria
     *            the search criteria
     * @param request
     *            the request
     * @param response
     *            the response
     * @param session
     *            the session
     * @return the string
     * @throws SystemException 
     */
    @RequestMapping(value = "AnonymousSearch", method = RequestMethod.GET)
    public String anonymousSearch(Map<String, Object> model,
            @RequestParam(SEARCHCRITERIA) String searchCriteria,
            HttpServletRequest request, HttpServletResponse response,
            HttpSession session) throws SystemException {

        LOGGER.info("Anonymous user requested to search the books");
        
        List<BookSearch> bResult = bookSearchService
                .searchbooks(searchCriteria);

        model.put("bookresults", bResult);
        model.put("showSearchedBooks", true);
        
        LOGGER.info("Search records fetched successfully, now returning model"
                + "and view");
        
        return "anonymousSearch";
    }

    /**
     * Show requested books.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param response
     *            the response
     * @return This method calls showRequestedBooks method of service and pass
     *         userId as the parameter.
     * @throws SystemException 
     */
    @RequestMapping(value = "/user_showRequestedBooks", method = RequestMethod.GET)
    public String showRequestedBooks(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) throws SystemException {
        
        LOGGER.info("Requested to show the Requested books");
        
        HttpSession session = request.getSession(false);

        String userId = (String) session.getAttribute(USERNAME);

        LOGGER.info(userId);
        List<RequestBook> requestedBooks = shelfService
                .showRequestedBooks(userId);
        model.put("requestedBooks", requestedBooks);
        model.put("showRequestedBooks", true);

        LOGGER.info("Requested books fetched successfully now returning"
                + "model and view ");
        
        return "requestedBooks";

    }

    /**
     * Show holding books.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param response
     *            the response
     * @return This method calls showHoldingBooks method of the service and sets
     *         userId as a parameter.
     * @throws SystemException 
     */
    @RequestMapping(value = "/user_showHoldingBooks", method = RequestMethod.GET)
    public String showHoldingBooks(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) throws SystemException {
       
        LOGGER.info("Requested to show holding books");
        
        HttpSession session = request.getSession(false);

        String userId = (String) session.getAttribute(USERNAME);

        LOGGER.info(userId);
        List<RequestBook> requestedBooks = shelfService
                .showBooksHolding(userId);
        model.put("requestedBooks", requestedBooks);
        model.put("showHoldingBooks", true);

        LOGGER.info("Holding books fetched sucessfully, now returning model and"
                + "view");
        
        return "holdingBooks";

    }
    
    /**
     * Ajax add to shelf.
     *
     * @param request the request
     * @param response the response
     * @param m the m
     * @return the string
     */
    @RequestMapping(value = "/user_addToShelf", method = RequestMethod.POST)
    @ResponseBody
    public String ajaxAddToShelf(HttpServletRequest request,
            HttpServletResponse response, Map<String, Object> m, 
            @RequestParam("bookId") String bookId) {

        LOGGER.info("Requested to add books in the shelf from latest arrivals"
                + "or recommendation page");
        
        HttpSession session = request.getSession(false);
       
        LOGGER.info(bookId);
        String userId = (String) session.getAttribute(USERNAME);
        String addResult = shelfService.addToShelf(bookId, userId);
        
        LOGGER.info("Request to add books completed successfully now returning"
                + "model and view ");

        return addResult;
    }

}
