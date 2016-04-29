/*
 * 
 */
package in.co.companyname.schedular;

import in.co.companyname.db.dao.AdminFunctionDaoImpl;
import in.co.companyname.exceptionhandling.SystemException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BooksJob.
 */
public class BooksJob extends QuartzJobBean {

    /** The books task. */
    private BooksTask booksTask;
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(BooksJob.class);

    /**
     * Sets the books task.
     * 
     * @param booksTask
     *            the new books task
     */
    public void setBooksTask(BooksTask booksTask) {
        this.booksTask = booksTask;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org
     * .quartz.JobExecutionContext)
     */
    @Override
    protected void executeInternal(JobExecutionContext arg0)
            throws JobExecutionException {

        booksTask.printMessage();
        try {
            booksTask.addBooks();
            booksTask.deleteBooks();
        } catch (SystemException e) {
            LOGGER.error("EXCEPTION CAUGHT IN CALLING BOOKS TASKS "+e);
        }
       

    }

}
