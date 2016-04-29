package in.co.companyname.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.RequestBook;
import in.co.companyname.service.search.BookSearchService;
import in.co.companyname.service.shelf.ShelfService;
import in.co.companyname.service.userfunction.UserFunctionService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;




public class BookSearchControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookSearchService bookSearchService;

    @Mock
    private UserFunctionService userFunctionService;

    @Mock
    private ShelfService shelfService;

    @InjectMocks
    private BookSearchController bookSearchController = new BookSearchController();

    @Mock
    MockHttpSession session;

    @Mock
    MockHttpServletRequest request;

    private BookSearch bookSearch = new BookSearch();
    private List<BookSearch> books = new ArrayList<BookSearch>();
    private RequestBook requestBook = new RequestBook();
    private List<RequestBook> reqBookList = new ArrayList<RequestBook>();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookSearchController).build();


        bookSearch.setBookAuthor("bookAuthor");
        bookSearch.setBookAvailablity(10);
        bookSearch.setBookCategory("bookCategory");
        bookSearch.setBookDescription("bookDescription");
        bookSearch.setBookId("bookId");
        bookSearch.setBookImage("bookImage");
        bookSearch.setBookPublisher("bookPublisher");
        bookSearch.setBookTitle("bookTitle");
        bookSearch.setSno("sno");
        MultipartFile file = null;
        bookSearch.setFile(file);


        books.add(bookSearch);

        Date date = new Date(new java.util.Date().getTime());

        requestBook.setBookSearch(bookSearch);
        requestBook.setDeliveryAddress("deliveryAddress");
        requestBook.setDeliveryDate(date);
        requestBook.setDeliveryRequestDate(date);
        requestBook.setDeliveryStatus("deliveryStatus");
        requestBook.setDelReqCancelDate(date);
        requestBook.setRequestId(10);
        requestBook.setRetReqCancelDate(date);
        requestBook.setReturnAddress("returnAddress");
        requestBook.setReturnDate(date);
        requestBook.setReturnRequestDate(date);
        requestBook.setReturnStatus("returnStatus");
        requestBook.setUserName("userName");


        reqBookList.add(requestBook);

    }


    @Test
    public void testBookSearchHelper() throws Exception {

        mockMvc.perform(get("/BookSearchHelper")
                .param("searchCriteria","title")
                .session(session))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:BookSearch"));
    }

    @Test
    public void testBookSearch() throws Exception {

        session.setAttribute("uname", "yash.s.khatri@gmail.com");
        session.setAttribute("searchCriteria", "novels");
        String searchCriteria = "novels";

        when(bookSearchService.searchbooks(searchCriteria)).thenReturn((ArrayList<BookSearch>) books);

        mockMvc.perform(get("/BookSearch")
                .param("searchCriteria","title")
                .session(session))
                .andExpect(forwardedUrl("searchedBooks"))
                .andExpect(view().name("searchedBooks"))
                .andExpect(status().isOk());

        doNothing().when( userFunctionService).saveSearchCriteria("userName", "novels");

    }




    @Test
    public void testAddBooks() throws Exception {

        session.setAttribute("uname", "yash.s.khatri@gmail.com");

        mockMvc.perform(post("/user_addBook")
                .param("bid","title")
                .session(session))
                .andExpect(view().name("redirect:BookSearch"))
                .andExpect(status().is(302))
                ;

        String bookId = "B01";
        String userId = "yash.khatri@companyname.co.in";

        when(shelfService.addToShelf(bookId, userId)).thenReturn("Added");


    }

    @Test
    public void testGetShelf() throws Exception {
        session.setAttribute("uname", "yash.s.khatri@gmail.com");

        mockMvc.perform(get("/user_showBookShelf")
                .session(session))
                .andExpect(view().name("showShelf"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("showShelf"));

        when(shelfService.showShelf("userId")).thenReturn(books);

    }

    @Test
    public void testAnonymousSearch() throws Exception {
        session.setAttribute("uname", "yash.s.khatri@gmail.com");


        mockMvc.perform(get("/AnonymousSearch")
                .param("searchCriteria","title")
                .session(session))
                .andExpect(view().name("anonymousSearch"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("anonymousSearch"));

        when(bookSearchService
                .searchbooks("searchCriteria")).thenReturn(books);

    }

    @Test
    public void testShowRequestedBooks() throws Exception {
        session.setAttribute("uname", "yash.s.khatri@gmail.com");

        mockMvc.perform(get("/user_showRequestedBooks")
                .session(session))
                .andExpect(view().name("requestedBooks"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("requestedBooks"));

        when(shelfService
                .showRequestedBooks("userId")).thenReturn(reqBookList);
    }

    @Test
    public void testShowHoldingBooks() throws Exception {
        session.setAttribute("uname", "yash.s.khatri@gmail.com");

        mockMvc.perform(get("/user_showHoldingBooks")
                .session(session))
                .andExpect(view().name("holdingBooks"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("holdingBooks"));

        when(shelfService
                .showBooksHolding("userId")).thenReturn(reqBookList);
    }

    @Test
    public void testAjaxAddToShelf() throws Exception {
        session.setAttribute("uname", "yash.s.khatri@gmail.com");

        mockMvc.perform(post("/user_addToShelf")
                .param("bookId","title")
                .session(session));
               
        String bookId = "B01";
        String userId = "yash.khatri@companyname.co.in";

        when(shelfService.addToShelf(bookId, userId)).thenReturn("Added");
    }

}
