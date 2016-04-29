/*
 * 
 */
package in.co.companyname.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestParam;

import static org.mockito.Mockito.when;
import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.Plans;
import in.co.companyname.db.model.Recommendation;
import in.co.companyname.db.model.RequestBook;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.db.model.Users;


import in.co.companyname.db.dao.AdminFunctionDao;
import in.co.companyname.db.dao.AdminFunctionDaoImpl;
import in.co.companyname.db.dao.BookSearchDAO;
import in.co.companyname.db.dao.RequestDAO;
import in.co.companyname.exceptionhandling.SystemException;
import in.co.companyname.service.adminfunction.AdminFunctionServiceImpl;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;






import javax.annotation.Resource;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminFunctionServiceImplTest.
 */
public class AdminFunctionServiceImplTest

{

/** The admin function dao. */
private AdminFunctionDao adminFunctionDao;
private BookSearchDAO bookSearchDAO;
private RequestDAO requestDAO;
private MultipartFile mockMultipartFile;
private HttpServletRequest request;

/**
 * Sets the up.
 */
@Before
public void setUp() 
{
adminFunctionDao=mock(AdminFunctionDao.class);
bookSearchDAO=mock(BookSearchDAO.class);
requestDAO=mock(RequestDAO.class);
request= mock(HttpServletRequest.class);
mockMultipartFile = new MockMultipartFile("test.jpg","Hello World".getBytes());
}



/**
 * Gets the booksearch list.
 *
 * @return the booksearch list
 */
@Test
public void  getBooksearchList() 
{
AdminFunctionServiceImpl adminFunctionServiceImpl=new AdminFunctionServiceImpl();
adminFunctionServiceImpl.setAdminFunctionDao(adminFunctionDao);

List<BookSearch> blist=new ArrayList<>();
BookSearch book = new BookSearch();
book.setBookAuthor("bookAuthor");
book.setBookAvailablity(10);
book.setBookCategory("bookCategory");
book.setBookDescription("bookDescription");
book.setBookId("B01");
book.setBookImage("bookImage");
book.setBookPublisher("bookPublisher");
book.setBookTitle("bookTitle");

blist.add(book);

 int start =0;
 String search =null;
 int columnNum=2;
 String sortOrder ="asc";
 int pageSize=10;

when(adminFunctionDao.getAllBooks(start, pageSize, search, columnNum, sortOrder)).thenReturn(blist);
List<BookSearch> actual=(List<BookSearch>) adminFunctionServiceImpl.getAllBooks(start, pageSize, search, columnNum, sortOrder);
assertEquals(blist, actual);

}


@Test
public void showActiveDeliveryRequests()
{
	List<RequestBook> reqlist=new ArrayList<>();
	AdminFunctionServiceImpl adminFunctionServiceImpl=new AdminFunctionServiceImpl();
	adminFunctionServiceImpl.setAdminFunctionDao(adminFunctionDao);
	
	RequestBook req1= new RequestBook();
	req1.setDeliveryStatus("pending");
	
	reqlist.add(req1);
	when(adminFunctionDao.showActiveDeliveryRequests()).thenReturn(reqlist);
	List<RequestBook> actual=(List<RequestBook>) adminFunctionServiceImpl.showActiveDeliveryRequests();
	assertEquals(reqlist, actual);
}

/**
 * Show active return requests.
 */
@Test
public void showActiveReturnRequests()
{
	List<RequestBook> reqlist=new ArrayList<>();

	AdminFunctionServiceImpl adminFunctionServiceImpl=new AdminFunctionServiceImpl();
	adminFunctionServiceImpl.setAdminFunctionDao(adminFunctionDao);
	

RequestBook req2= new RequestBook();

req2.setReturnStatus("Cancelled");
reqlist.add(req2);

when(adminFunctionDao.showActiveReturnRequests()).thenReturn(reqlist);
List<RequestBook> actual=(List<RequestBook>) adminFunctionServiceImpl.showActiveReturnRequests();
assertEquals(reqlist, actual);
	
}

/**
 * View active users.
 */
@Test
public void viewActiveUsers()
{
	AdminFunctionServiceImpl adminFunctionServiceImpl=new AdminFunctionServiceImpl();
	adminFunctionServiceImpl.setAdminFunctionDao(adminFunctionDao);
	
	List<Subscription> subsList=new ArrayList<>();
	Subscription subs=new Subscription();
	subs.setPlan(1);
	subs.setPlanName("abc");
	
	subsList.add(subs);
	
	
when(adminFunctionDao.viewActiveUsers()).thenReturn(subsList);
List<Subscription> actual=(List<Subscription>) adminFunctionServiceImpl.viewActiveUsers();
assertEquals(subsList, actual);

}


/**
 * Gets the row by id.
 *
 * @return the row by id
 */
@Test
public void getBookById()
{
	AdminFunctionServiceImpl adminFunctionServiceImpl=new AdminFunctionServiceImpl();
	adminFunctionServiceImpl.setAdminFunctionDao(adminFunctionDao);
	
	 BookSearch book = new BookSearch();
     book.setBookAuthor("bookAuthor");
     book.setBookAvailablity(10);
     book.setBookCategory("bookCategory");
     book.setBookDescription("bookDescription");
     book.setBookId("B01");
     book.setBookImage("bookImage");
     book.setBookPublisher("bookPublisher");
     book.setBookTitle("bookTitle");
	
	when(adminFunctionDao.getBookById("B01")).thenReturn(book);
	assertEquals(book, adminFunctionServiceImpl.getBookById("B01"));
}

/**
 * Accept delivery request.
 * @throws SystemException 
 */
@Test
public void acceptDeliveryRequest() throws SystemException
{
    AdminFunctionServiceImpl adminFunctionServiceImpl=new AdminFunctionServiceImpl();
    adminFunctionServiceImpl.setAdminFunctionDao(adminFunctionDao);
    
    int requestId=2;
    String action="pending";
    String bookId="B01";
    String expected="true";
    
    when(adminFunctionDao.acceptDeliveryRequest(requestId, action)).thenReturn(expected);
    assertEquals(expected, adminFunctionServiceImpl.acceptDeliveryRequest(requestId, action, bookId));
}



@Test
public void acceptDeliveryRequestSuccess() throws SystemException
{
    AdminFunctionServiceImpl adminFunctionServiceImpl=new AdminFunctionServiceImpl();
    adminFunctionServiceImpl.setAdminFunctionDao(adminFunctionDao);
    adminFunctionServiceImpl.setRequestDAO(requestDAO);
    adminFunctionServiceImpl.setBookSearchDAO(bookSearchDAO);
    BookSearch book = new BookSearch();

    int requestId=2;
   

String action="Denied";
    String bookId="B01";
    String expected="true";
    when(bookSearchDAO.getBookById(bookId)).thenReturn(book);
     book.setBookAvailablity(7);    
    when(adminFunctionDao.acceptDeliveryRequest(requestId, action)).thenReturn(expected);
    assertEquals(expected, adminFunctionServiceImpl.acceptDeliveryRequest(requestId, action, bookId));
}



/**
 * Accept return request.
 */
@Test
public void acceptReturnRequest()
{   

AdminFunctionServiceImpl adminFunctionServiceImpl=new AdminFunctionServiceImpl();
adminFunctionServiceImpl.setAdminFunctionDao(adminFunctionDao);
adminFunctionServiceImpl.setBookSearchDAO(bookSearchDAO);
adminFunctionServiceImpl.setRequestDAO(requestDAO);

BookSearch books= new BookSearch();
RequestBook requestBook = new RequestBook();
books.setBookAvailablity(10);
requestBook.setBookSearch(books);

int requestId=2;
String bookId="B01";
String expected="true";

when(requestDAO.getBookByRequestId(requestId)).thenReturn(requestBook);
when(adminFunctionDao.acceptReturnRequest(requestBook)).thenReturn(expected);

assertEquals(expected,adminFunctionServiceImpl.acceptReturnRequest(requestId, bookId));
};


@Test
public void acceptReturnRequestSucess()
{   

AdminFunctionServiceImpl adminFunctionServiceImpl=new AdminFunctionServiceImpl();
adminFunctionServiceImpl.setAdminFunctionDao(adminFunctionDao);
adminFunctionServiceImpl.setBookSearchDAO(bookSearchDAO);
adminFunctionServiceImpl.setRequestDAO(requestDAO);


BookSearch books= new BookSearch();
RequestBook requestBook = new RequestBook();

int requestId=2;
String bookId="B01";
String expected="Successful";
books.setBookAvailablity(10);

requestBook.setBookSearch(books);

when(requestDAO.getBookByRequestId(requestId)).thenReturn(requestBook);
when(requestDAO.changeAvailablity(books)).thenReturn(true);
when(adminFunctionDao.acceptReturnRequest(requestBook)).thenReturn(expected);
assertEquals(expected,adminFunctionServiceImpl.acceptReturnRequest(requestId, bookId));
};
/**
 * Insert row.
 */
@Test
public void insertBook()
{
    AdminFunctionServiceImpl adminFunctionServiceImpl=new AdminFunctionServiceImpl();
    adminFunctionServiceImpl.setAdminFunctionDao(adminFunctionDao);
    BookSearch books=new BookSearch();
    books.setBookId("b01");
    books.setBookAuthor("a");
    books.setBookAvailablity(5);
    books.setBookCategory("b");
    books.setBookDescription("c");
    books.setBookId("s01");
    books.setBookImage("abc.jpeg");
    books.setBookPublisher("d");
    books.setBookTitle("e");
    books.setFile(mockMultipartFile);
    String expected="true";
    when(adminFunctionDao.insertBook(books)).thenReturn(expected);
    String actual=adminFunctionServiceImpl.insertBook(books, request);
    assertEquals(expected, actual);
    
}

@Test
public void deleteRow()
{
	try{
		
		AdminFunctionServiceImpl adminFunctionServiceImpl=new AdminFunctionServiceImpl();
		adminFunctionServiceImpl.setAdminFunctionDao(adminFunctionDao);
		
		BookSearch books=new BookSearch();
		books.setBookId("b11");

		adminFunctionServiceImpl.deleteBook("b11");
		assertTrue(true);
}
	catch(Exception e){
		assertTrue(false);
	
}

}

@Test
public void getTotalRecordsOfBooks()
{
    AdminFunctionServiceImpl adminFunctionServiceImpl=new AdminFunctionServiceImpl();
    adminFunctionServiceImpl.setAdminFunctionDao(adminFunctionDao);
    
    int expected=1;
    when(adminFunctionDao.getTotalRecordsOfBooks()).thenReturn(expected);
    assertEquals(expected, adminFunctionServiceImpl.getTotalRecordsOfBooks());
}

}