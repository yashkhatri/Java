package in.co.companyname.controllers;

import in.co.companyname.exceptionhandling.SystemException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/java/resources/applicationContext.xml"})
public class ExceptionHandlerControllerTest {
    
    private static final Exception Exception = null;

    private MockMvc mockMvc;

    //@InjectMocks
    private ExceptionHandlerController exceptionHandlerController =
    new ExceptionHandlerController();


   

    @Test
    public void testSqlGrammerException() throws Exception {
            
        exceptionHandlerController.sqlGrammerException(Exception);
    }

   @Test
    public void testGenericJdbcException() {
       exceptionHandlerController.genericJdbcException(Exception);
    }

    @Test
    public void testSqlException() {
        exceptionHandlerController.sqlException(Exception);
    }

    @Test
    public void testSessionException() {
        exceptionHandlerController.sessionException(Exception);
    }

    @Test
    public void testNullPointerException() {
        exceptionHandlerController.nullPointerException(Exception);
    }

    @Test
    public void testMySqlSyntaxException() {
        exceptionHandlerController.mySqlSyntaxException(Exception);
    }

    @Test
    public void testConstraintViolationException() {
        exceptionHandlerController.constraintViolationException(Exception);
    }

    @Test
    public void testJdbcException() {
        exceptionHandlerController.jdbcException(Exception);
    }

    @Test
    public void testHibernateException() {
        exceptionHandlerController.hibernateException(Exception);
    }

    @Test
    public void testException() {
        exceptionHandlerController.exception(Exception);
    }
    
    @Test
    public void testSystemException() {
        exceptionHandlerController.systemException(new SystemException("Exception"));
    }

}
