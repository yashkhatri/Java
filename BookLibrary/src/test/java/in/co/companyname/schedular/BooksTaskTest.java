package in.co.companyname.schedular;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import in.co.companyname.exceptionhandling.SystemException;
import in.co.companyname.service.util.BooksSchedulerManager;

import org.junit.Before;
import org.junit.Test;


public class BooksTaskTest {
 private BooksSchedulerManager booksSchedulerManager;
	@Before
	public void setUp() throws Exception {
		booksSchedulerManager=mock(BooksSchedulerManager.class);
	}

	@Test
	public void testAddBooks() throws SystemException 
	{
		BooksTask booksTask=new BooksTask();
		booksTask.setBooksSchedulerManager(booksSchedulerManager);
		doNothing().when(booksSchedulerManager).addBooks();
         booksTask.addBooks();
 		assertTrue(true);

}

	@Test
	public void testDeleteBooks() throws SystemException {
		BooksTask booksTask=new BooksTask();
		booksTask.setBooksSchedulerManager(booksSchedulerManager);
		doNothing().when(booksSchedulerManager).deleteBooks();
		booksTask.deleteBooks();
		assertTrue(true);

	
	}

	@Test
	public void testPrintMessage() 
	{
		
		BooksTask booksTask=new BooksTask();
        booksTask.printMessage();
        assertTrue(true);
	}

}
