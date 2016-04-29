package in.co.companyname.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import in.co.companyname.db.dao.RegisterDao;
import in.co.companyname.db.dao.ShelfDAO;
import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.Recommendation;
import in.co.companyname.db.model.Users;
import in.co.companyname.service.userfunction.UserFunctionServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;



public class UserFunctionServiceImplTest {

private RegisterDao registerDao;
private ShelfDAO shelfDAO;

	@Before
	public void setUp() throws Exception
	{
		registerDao=mock(RegisterDao.class);
		shelfDAO=mock(ShelfDAO.class);
	}

	@Test
	public void testShowRecommendedBooks()
	{
		UserFunctionServiceImpl userFunctionServiceImpl=new UserFunctionServiceImpl();
		userFunctionServiceImpl.setShelfDAO(shelfDAO);
		
		List<BookSearch> blist=new ArrayList<>();
		BookSearch book1=new BookSearch();
		Users users=new Users();
		users.setUserName("xyz");
		book1.setBookId("a");
		book1.setBookAuthor("a");
		book1.setBookAvailablity(0);
		book1.setBookCategory("b");
		book1.setBookDescription("b");
		book1.setBookId("a01");
		book1.setBookTitle("xxx");
		book1.setBookPublisher("abc");
		book1.setBookImage("abcd.jpeg");
		blist.add(book1);
		
		when(shelfDAO.showRecommendation("xyz")).thenReturn(blist);
		List<BookSearch> actual=(List<BookSearch>) userFunctionServiceImpl.showRecommendedBooks("xyz");
		assertEquals(blist, actual);
	
	}
	
	@Test
	public void testShowProfile() 
	{

		UserFunctionServiceImpl userFunctionServiceImpl=new UserFunctionServiceImpl();
		userFunctionServiceImpl.setRegisterDao(registerDao);

		
		Users users=new Users();
		users.setFirstName("a");
		users.setLastName("b");
		users.setUserName("c");
		
		when(registerDao.getUserById("c")).thenReturn(users);
		assertEquals(users,userFunctionServiceImpl.showProfile("c"));
	}

	@Test
	public void testSaveSearchCriteria()
	{
		try
		{
		UserFunctionServiceImpl userFunctionServiceImpl=new UserFunctionServiceImpl();
		userFunctionServiceImpl.setRegisterDao(registerDao);
		
		Users users=new Users();
		users.setUserName("a");
		Recommendation recommendation = new Recommendation();
		recommendation.setSearchCriteria("xyz");
  
		userFunctionServiceImpl.saveSearchCriteria("a", "xyz");
		assertTrue(true);
		}
		catch(Exception e){
			assertTrue(false);
	}

}
}
