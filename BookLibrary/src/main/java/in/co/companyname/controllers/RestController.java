/*
 * 
 */
package in.co.companyname.controllers;

import in.co.companyname.db.model.BookSearch;
import in.co.companyname.exceptionhandling.SystemException;
import in.co.companyname.service.search.BookSearchService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

// TODO: Auto-generated Javadoc
/**
 * The Class RestController.
 * This controller handles the rest request.
 */
@Controller
@RequestMapping(value = "searchBookRest")
public class RestController {
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RestController.class);

    
    /** The book search service. */
    @Autowired
    private BookSearchService bookSearchService;

    /**
     * Gets the book.
     * 
     * @return the book
     * @throws SystemException 
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<BookSearch> getBook() throws SystemException {

        LOGGER.info("Rest request to get recent books");

        return bookSearchService.getRecentBooksList();
    }
}
