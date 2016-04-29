package in.co.companyname.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import in.co.companyname.db.dao.BookSearchDAO;
import in.co.companyname.db.model.BookSearch;
import in.co.companyname.exceptionhandling.SystemException;
import in.co.companyname.service.search.BookSearchServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BookSearchServiceImplTest 

{
    private BookSearchDAO bookSearchDAO;

    @Before
    public void setUp() 
    {
        bookSearchDAO=mock(BookSearchDAO.class);
    }

    @Test
    public void getRecentBooksList() throws SystemException
    {
        BookSearchServiceImpl bookSearchServiceImpl=new BookSearchServiceImpl() ;
        bookSearchServiceImpl.setBooksearchDAO(bookSearchDAO);

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
        when(bookSearchDAO.getRecentBooksList()).thenReturn(blist);
        List<BookSearch> actual=(List<BookSearch>) bookSearchServiceImpl.getRecentBooksList();
        assertEquals(blist, actual);
    }

    @Test
    public void getsearchbooks()
    {
        BookSearchServiceImpl bookSearchServiceImpl=new BookSearchServiceImpl() ;
        bookSearchServiceImpl.setBooksearchDAO(bookSearchDAO);

        List<BookSearch> blist=new ArrayList<>();
        BookSearch book1 = new BookSearch();
        book1.setBookAuthor("Noah's Law");
        book1.setBookAvailablity(10);
        book1.setBookCategory("bookCategory");
        book1.setBookDescription("bookDescription");
        book1.setBookId("B01");
        book1.setBookImage("bookImage");
        book1.setBookPublisher("bookPublisher");
        book1.setBookTitle("bookTitle");

        BookSearch book2 = new BookSearch();
        book2.setBookAuthor("Noah's Law");
        book2.setBookAvailablity(10);
        book2.setBookCategory("bookCategory");
        book2.setBookDescription("bookDescription");
        book2.setBookId("B02");
        book2.setBookImage("bookImage");
        book2.setBookPublisher("bookPublisher");
        book2.setBookTitle("bookTitle");


        blist.add(book1);
        blist.add(book2);
        List<BookSearch> actual = new ArrayList<BookSearch>();
        try {
            when(bookSearchDAO.searchBooks("Noah's Law")).thenReturn(blist);
            actual = (List<BookSearch>) bookSearchServiceImpl.searchbooks("Noah's Law");
            assertEquals(blist,actual);
        } catch (SystemException e) {
            // TODO Auto-generated catch block
            assertTrue(false);
        }
     

    }

    @Test
    public void getBookById() throws SystemException
    {
        BookSearchServiceImpl bookSearchServiceImpl=new BookSearchServiceImpl() ;
        bookSearchServiceImpl.setBooksearchDAO(bookSearchDAO);

        BookSearch book1 = new BookSearch();
        book1.setBookAuthor("Noah's Law");
        book1.setBookAvailablity(10);
        book1.setBookCategory("bookCategory");
        book1.setBookDescription("bookDescription");
        book1.setBookId("B01");
        book1.setBookImage("bookImage");
        book1.setBookPublisher("bookPublisher");
        book1.setBookTitle("bookTitle");

        when(bookSearchDAO.getBookById("B01")).thenReturn(book1);
        assertEquals(book1, bookSearchServiceImpl.getBookById("B01"));
    }

}
