package in.co.companyname.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import in.co.companyname.db.dao.BooksSchedulerDAOInterface;
import in.co.companyname.exceptionhandling.SystemException;
import in.co.companyname.service.util.BooksSchedulerManagerImpl;

import org.junit.Before;
import org.junit.Test;

public class BooksSchedulerManagerImplTest
{
	private BooksSchedulerDAOInterface booksSchedulerDAOInterface;

	@Before
	public void setUp() throws Exception
	{
		booksSchedulerDAOInterface=mock(BooksSchedulerDAOInterface.class);
	}

	@Test
	public void testAddBooks() throws SystemException 
	{
		BooksSchedulerManagerImpl booksSchedulerManagerImpl=new BooksSchedulerManagerImpl();
		booksSchedulerManagerImpl.setBooksSchedulerDAO(booksSchedulerDAOInterface);
		doNothing().when(booksSchedulerDAOInterface).addBooks();
		booksSchedulerManagerImpl.addBooks();
		assertTrue(true);
		
	}

	@Test
	public void testDeleteBooks() throws SystemException 
	{
		BooksSchedulerManagerImpl booksSchedulerManagerImpl=new BooksSchedulerManagerImpl();
		booksSchedulerManagerImpl.setBooksSchedulerDAO(booksSchedulerDAOInterface);
		doNothing().when(booksSchedulerDAOInterface).deleteBooks();
		booksSchedulerManagerImpl.deleteBooks();
		assertTrue(true);

	}

}
