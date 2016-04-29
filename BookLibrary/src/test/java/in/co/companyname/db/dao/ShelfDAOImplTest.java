/*
 * 
 */
package in.co.companyname.db.dao;

import static org.junit.Assert.assertTrue;
import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.RequestBook;
import in.co.companyname.exceptionhandling.SystemException;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class ShelfDAOImplTest.
 *
 * @author yash.khatri
 */

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/java/resources/applicationContext.xml"})
public class ShelfDAOImplTest {


	/** The shelf dao impl. */
	@Autowired
	private ShelfDAOImpl shelfDAOImpl;



	@Test
	public void testAddToShelf() {

		try{
			shelfDAOImpl.addToShelf("B21", "yash.khatri@companyname.co.in");
			assertTrue(true);
		}
		catch(Exception e)
		{
			assertTrue(false);

		}

	}
	
	   @Test(expected = SystemException.class)
	    public void testAddToShelfException() throws SystemException {
	    
	       shelfDAOImpl.addToShelf("B21", "yash.khatri@companyname.co.in");
	       
	    }


	@Test
	public void testShowShelf() {
		try{
			List<BookSearch> shelf = shelfDAOImpl.showShelf("yash.khatri@companyname.co.in");
			assertTrue(true);

		}
		catch(Exception e){
			assertTrue(false);


		}
	}


	@Test
	public void testRemoveFromShelf() {
		try{
			shelfDAOImpl.removeFromShelf("yash.khatri@companyname.co.in", "N02");
			assertTrue(true);

		}catch(Exception e)
		{
			assertTrue(false);

		}

	}


	@Test
	public void testShowRequestedBooks() {
		try{
			List<RequestBook> rB = shelfDAOImpl.showRequestedBooks("yash.khatri@companyname.co.in");
			assertTrue(true);

		}
		catch(Exception e)
		{
			assertTrue(false);

		}


	}

	/**
	 * Test show books holding.
	 */
	@Test
	public void testShowBooksHolding() {
		try{

			shelfDAOImpl.showBooksHolding("yash.khatri@companyname.co.in");
			assertTrue(true);

		}catch(Exception e){
			assertTrue(false);

		}
	}



	/**
	 * Test get subscription.
	 */
	@Test
	public void testGetSubscription() {
		try{

			shelfDAOImpl.getSubscription("yash.khatri@companyname.co.in");
			assertTrue(true);

		}catch(Exception e){
			assertTrue(false);

		}

	}


	/**
	 * Test show recommendation.
	 */
	@Test
	public void testShowRecommendation() {
		try{

			shelfDAOImpl.showRecommendation("yash.khatri@companyname.co.in");
			assertTrue(true);

		}catch(Exception e){
			assertTrue(false);

		}
	}

	/**
	 * Test get max occuring string.
	 */
	@Test
	public void testGetMaxOccuringString() {
		try{

			shelfDAOImpl.getMaxOccuringString("yash.khatri@companyname.co.in");
			assertTrue(true);

		}catch(Exception e){
			assertTrue(false);

		}
	}

	
	

}
