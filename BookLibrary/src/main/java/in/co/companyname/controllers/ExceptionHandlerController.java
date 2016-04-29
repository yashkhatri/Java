/*
 * 
 */
package in.co.companyname.controllers;

import in.co.companyname.exceptionhandling.SystemException;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.SessionException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

// TODO: Auto-generated Javadoc
/**
 * The Class ExceptionHandlerController.
 */
@ControllerAdvice
public class ExceptionHandlerController {
    /** The Constant LOGGER. */

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ExceptionHandlerController.class);

    private static final String ERROR_PAGE  = "errorPage";

    /**
     * Sql grammer exception.
     * 
     * @param e
     *            the e
     * @return the string
     */
    @ExceptionHandler(SQLGrammarException.class)
    public String sqlGrammerException(Exception e) {
        LOGGER.error("SQL Grammar Exception.\nException ::\n" + e);
        return ERROR_PAGE;
    }

    /**
     * Generic jdbc exception.
     * 
     * @param e
     *            the e
     * @return the string
     */
    @ExceptionHandler(GenericJDBCException.class)
    public String genericJdbcException(Exception e) {
        LOGGER.error("Generic JDBC Exception.\nException ::\n" + e);
        return ERROR_PAGE;
    }

    /**
     * SQ lexception.
     * 
     * @param e
     *            the e
     * @return the string
     */
    @ExceptionHandler(CannotCreateTransactionException.class)
    public String sqlException(Exception e) {
        LOGGER.error("Error connecting Database.\nException ::\n" + e);
        return ERROR_PAGE;
    }

    /**
     * Session exception.
     * 
     * @param e
     *            the e
     * @return the string
     */
    @ExceptionHandler(SessionException.class)
    public String sessionException(Exception e) {
        LOGGER.error("Session Exception.\nException ::\n" + e);
        return ERROR_PAGE;
    }

    /**
     * Null pointer exception.
     * 
     * @param e
     *            the e
     * @return the string
     */
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerException(Exception e) {
        LOGGER.error("Null Pointer Exception.\nException ::\n" + e);
        return ERROR_PAGE;
    }

    /**
     * My sql syntax exception.
     * 
     * @param e
     *            the e
     * @return the string
     */
    @ExceptionHandler(MySQLSyntaxErrorException.class)
    public String mySqlSyntaxException(Exception e) {
        LOGGER.error("(MySQLSyntaxErrorException.\nException ::\n" + e);
        return ERROR_PAGE;
    }

    /**
     * Constraint violation exception exception.
     *
     * @param e the e
     * @return the string
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public String constraintViolationException(Exception e) {
        LOGGER.error("(constraintViolationException.\nException ::\n" + e);
        return ERROR_PAGE;
    }

    /**
     * Jdbc exception.
     *
     * @param e the e
     * @return the string
     */
    @ExceptionHandler( JDBCException.class)
    public String jdbcException(Exception e) {
        LOGGER.error("(jdbcException.\nException ::\n" + e);
        return ERROR_PAGE;
    }

    @ExceptionHandler(HibernateException.class)
    public String hibernateException(Exception e) {
        LOGGER.error("(hibernateException.\nException ::\n" + e);
        return ERROR_PAGE;
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception e) {
        LOGGER.error("(Generic Exception .\nException ::\n" + e);
        return ERROR_PAGE;
    }


    @ExceptionHandler(SystemException.class)
    public String systemException(SystemException e) {
        LOGGER.error("SYSTEM EXCEPTION CAUGHT:\n" + e);
        return ERROR_PAGE;
    }


}
