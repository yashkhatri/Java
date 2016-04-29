package in.co.companyname.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
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

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookSearchService bookSearchService;

    @Mock
    private UserFunctionService userFunctionService;

    @Mock
    private ShelfService shelfService;


    /** The Subscription plan service. */
    @Mock
    private SubscriptionPlanService subscriptionPlanService;

    /** The register service. */
    @Mock
    private RegisterService registerService;

    @InjectMocks
    private UserController userController = new UserController();

    @Mock
    MockHttpSession session;

    @Mock
    MockHttpServletRequest request;

    private BookSearch bookSearch = new BookSearch();
    private List<BookSearch> books = new ArrayList<BookSearch>();
    private RequestBook requestBook = new RequestBook();
    private List<RequestBook> reqBookList = new ArrayList<RequestBook>();
    private Users user = new Users();
    private Subscription subscription = new Subscription();
    private List<Subscription> subsList = new ArrayList<Subscription>();
    private Plans plans = new Plans();
    private List<Plans> plansList = new ArrayList<Plans>();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();


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

        user.setAddress("address");
        user.setCity("city");
        user.setEnabled(true);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setMobile("mobile");
        user.setPincode(10);
        user.setPlanId(10);
        user.setPrimaryLanguage("primaryLanguage");
        user.setSecondaryLanguage("secondaryLanguage");
        user.setState("state");
        user.setUserName("userName");
        user.setUserPassword("userPassword");

        subscription.setEndDate(date);
        subscription.setIsActive(true);
        subscription.setMaxBooks(10);
        subscription.setMaxDays(10);
        subscription.setPlan(10);
        subscription.setPlanName("planName");
        subscription.setPrice(10);
        subscription.setStartDate(date);
        subscription.setSubscriptionId(10);
        subscription.setUserName("userName");

        subsList.add(subscription);
        
        plans.setMaxBooks(10);
        plans.setMaxDays(10);
        plans.setPlanId(10);
        plans.setPlanName("planName");
        plans.setPrice(10);
        
        plansList.add(plans);
    }

    @Test
    public void testViewProfile() throws Exception {
        session.setAttribute("uname", "yash.s.khatri@gmail.com");

        mockMvc.perform(get("/user_ViewProfile")
                .session(session))
                .andExpect(view().name("viewProfile"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("viewProfile"));

        when(userFunctionService.showProfile("userName")).thenReturn(user);
    }

    @Test
    public void testViewHistory() throws Exception {
        session.setAttribute("uname", "yash.s.khatri@gmail.com");

        mockMvc.perform(get("/user_ViewHistory")
                .session(session))
                .andExpect(view().name("returnedHistory"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("returnedHistory"));

        when(shelfService
                .showRequestedBooks("userName")).thenReturn(reqBookList);
    }

    @Test
    public void testSubscriptionHistory() throws Exception {
        session.setAttribute("uname", "yash.s.khatri@gmail.com");

        mockMvc.perform(get("/user_SubscriptionHistory")
                .session(session))
                .andExpect(view().name("subscriptionHistory"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("subscriptionHistory"));

        when(shelfService
                .getSubscription("userName")).thenReturn(subsList);
    }

     @Test
    public void testGetForm() throws Exception {
         session.setAttribute("uname", "yash.s.khatri@gmail.com");

         mockMvc.perform(get("/user_showuserhome")
                 .session(session))
                 .andExpect(view().name("userHome"))
                 .andExpect(status().isOk())
                 .andExpect(forwardedUrl("userHome"));

         when(bookSearchService
                 .getRecentBooksList()).thenReturn(books);
    }

    @Test
    public void testListplans() throws Exception {
        session.setAttribute("uname", "yash.s.khatri@gmail.com");

        mockMvc.perform(get("/user_upgradeplan.htm")
                .session(session))
                .andExpect(view().name("upgradePlan"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("upgradePlan"));

        when(subscriptionPlanService.getPlansList()).thenReturn(plansList);
        
    }
/*
 @Test
    public void testEnrolNewPlan() throws Exception {
     session.setAttribute("uname", "yash.s.khatri@gmail.com");

     mockMvc.perform(post("/user_enrolNewPlan")
             .param("planId", "1")
             .session(session))
             .andExpect(view().name("upgradePlan"))
             .andExpect(status().isOk())
             .andExpect(forwardedUrl("upgradePlan"));

     when(registerService.upgradeSubscription("userName",
             1)).thenReturn("added");
     when(subscriptionPlanService.getPlansList()).thenReturn(plansList);
     
    }*/

   @Test
    public void testShowRecommendations() throws Exception {
       session.setAttribute("uname", "yash.s.khatri@gmail.com");

       mockMvc.perform(get("/user_Recommendations.html")
               .session(session))
               .andExpect(view().name("recommendedBooks"))
               .andExpect(status().isOk())
               .andExpect(forwardedUrl("recommendedBooks"));

       when(userFunctionService
               .showRecommendedBooks("userName")).thenReturn(books);  
    }
     
}
