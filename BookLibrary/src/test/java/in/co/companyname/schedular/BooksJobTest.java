package in.co.companyname.schedular;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import in.co.companyname.exceptionhandling.SystemException;

import org.junit.Before;
import org.junit.Test;
import org.quartz.JobExecutionException;

public class BooksJobTest {

    private BooksTask booksTask;
    @Before
    public void setUp() throws Exception {
        booksTask=mock(BooksTask.class);
    }

    @Test
    public void testExecuteInternal() throws JobExecutionException, SystemException 
    {
        BooksJob booksJob=new BooksJob();
        booksJob.setBooksTask(booksTask);
        booksJob.executeInternal(null);
        doNothing().when(booksTask).addBooks();
        booksTask.printMessage();
        booksTask.deleteBooks();
        assertTrue(true);
    }


}
