/*
 * 
 */
package in.co.companyname.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorHandlerController.
 * This class contains the exception handler code.
 * It handles all the exception at the controller level which ever is generated
 * in the project.
 */

/**
 * 
 * @author yash.khatri
 *
 */
@Controller
public class ErrorHandlerController {

    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ErrorHandlerController.class);

    
    /**
     * Ge403denied.
     * 
     * @return the string
     */
    @RequestMapping("403page")
    public String ge403denied() {
        return "redirect:login?denied";
        }
    
    /**
     * Error404.
     * 
     * @param request
     *            the request
     * @return the string
     */
    @RequestMapping(value = "404")
    public String error404(HttpServletRequest request) {
        LOGGER.error("Error, Request URL :: " + request.getRequestURL());
        return "pageNotFound";
    }


    /**
     * Error400.
     * 
     * @param request
     *            the request
     * @return the string
     */
    @RequestMapping(value = "400")
    public String error400(HttpServletRequest request) {
        LOGGER.error("Error, Request URL :: " + request.getRequestURL());
        return "errorPage";
    }



    /**
     * Error405.
     * 
     * @param request
     *            the request
     * @return the string
     */
    @RequestMapping(value = "405")
    public String error405(HttpServletRequest request) {
        LOGGER.error("Error, Request URL :: " + request.getRequestURL());
        return "errorPage";
    }
    
}
