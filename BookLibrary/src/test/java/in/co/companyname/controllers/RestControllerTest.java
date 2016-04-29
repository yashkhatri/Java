package in.co.companyname.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.Plans;
import in.co.companyname.db.model.RequestBook;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.db.model.Users;
import in.co.companyname.service.registration.RegisterService;
import in.co.companyname.service.search.BookSearchService;
import in.co.companyname.service.shelf.ShelfService;
import in.co.companyname.service.subscriptionplan.SubscriptionPlanService;
import in.co.companyname.service.userfunction.UserFunctionService;

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

public class RestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookSearchService bookSearchService;

    @InjectMocks
    private RestController restController = new RestController();

    @Mock
    MockHttpSession session;

    @Mock
    MockHttpServletRequest request;

    private BookSearch bookSearch = new BookSearch();
    private List<BookSearch> books = new ArrayList<BookSearch>();


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(restController).build();


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

    }

    @Test
    public void testGetBook() throws Exception {

        mockMvc.perform(get("/searchBookRest"))
                .andExpect(status().isOk());

        when(bookSearchService.getRecentBooksList()).thenReturn(books);
    }

}
