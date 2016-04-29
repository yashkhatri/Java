/*
 * 
 */
package in.co.companyname.db.dao;

import static org.junit.Assert.assertTrue;
import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.RequestBook;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class RequestDAOImplTest.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/java/resources/applicationContext.xml"})
public class RequestDAOImplTest {

	/** The request dao impl. */
	@Autowired
	private RequestDAOImpl requestDAOImpl;


	/**
	 * Test count requested books.
	 */
	@Test
	public void testCountRequestedBooks() {
		try{
			requestDAOImpl.countRequestedBooks("yash.khatri@companyname.co.in");
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test count holding books.
	 */
	@Test
	public void testCountHoldingBooks() {
		try{
			requestDAOImpl.countHoldingBooks("yash.khatri@companyname.co.in");
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test check subscription.
	 */
	@Test
	public void testCheckSubscription() {
		try{
			requestDAOImpl.checkSubscription("yash.khatri@companyname.co.in");
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test cancel delivery request.
	 */
	@Test
	public void testCancelDeliveryRequest() {
		try{
		    RequestBook requestBook = new RequestBook();
			requestDAOImpl.cancelDeliveryRequest(requestBook);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test return book request.
	 */
	@Test
	public void testReturnBookRequest() {
		try{
			requestDAOImpl.returnBookRequest(344, "Dhar");
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test cancel return request.
	 */
	@Test
	public void testCancelReturnRequest() {
		try{
			requestDAOImpl.cancelReturnRequest(344);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test request book.
	 */
	@Test
	public void testRequestBook() {
		try{
			requestDAOImpl.requestBook("yash.khatri@companyname.co.in", "B50", "Dhar");
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test change availablity.
	 */
	@Test
	public void testChangeAvailablity() {
		try{
		    BookSearch book = new BookSearch();
            book.setBookAuthor("bookAuthor");
            book.setBookAvailablity(10);
            book.setBookCategory("bookCategory");
            book.setBookDescription("bookDescription");
            book.setBookId("bookId");
            book.setBookImage("bookImage");
            book.setBookPublisher("bookPublisher");
            book.setBookTitle("bookTitle");
			requestDAOImpl.changeAvailablity(book);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test get book by request id.
	 */
	@Test
	public void testGetBookByRequestId() {
		try{
			requestDAOImpl.getBookByRequestId(344);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test check if already requested.
	 */
	@Test
	public void testCheckIfAlreadyRequested() {
		try{
		    BookSearch book = new BookSearch();
            book.setBookAuthor("bookAuthor");
            book.setBookAvailablity(10);
            book.setBookCategory("bookCategory");
            book.setBookDescription("bookDescription");
            book.setBookId("bookId");
            book.setBookImage("bookImage");
            book.setBookPublisher("bookPublisher");
            book.setBookTitle("bookTitle");
			requestDAOImpl.changeAvailablity(book);
			requestDAOImpl.checkIfAlreadyRequested("yash.khatri@companyname.co.in", book);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test get requested book.
	 */
	@Test
	public void testGetRequestedBook() {
		try{
		    BookSearch book = new BookSearch();
            book.setBookAuthor("bookAuthor");
            book.setBookAvailablity(10);
            book.setBookCategory("bookCategory");
            book.setBookDescription("bookDescription");
            book.setBookId("bookId");
            book.setBookImage("bookImage");
            book.setBookPublisher("bookPublisher");
            book.setBookTitle("bookTitle");
			requestDAOImpl.getRequestedBook("yash.khatri@companyname.co.in", book);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	
	
}
