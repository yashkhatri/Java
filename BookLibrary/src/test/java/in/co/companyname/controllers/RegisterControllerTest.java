package in.co.companyname.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.Plans;
import in.co.companyname.db.model.RequestBook;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.db.model.Users;
import in.co.companyname.service.registration.RegisterService;
import in.co.companyname.service.request.RequestService;
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
import org.junit.Test;

public class RegisterControllerTest {

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

    @Mock
    private RequestService requestService;

    @InjectMocks
    private RegisterController registerController = new RegisterController();

    @Mock
    MockHttpSession session;

    @Mock
    MockHttpServletRequest request;

    private BookSearch bookSearch = new BookSearch();
    private List<BookSearch> books = new ArrayList<BookSearch>();
    private RequestBook requestBook = new RequestBook();
    private List<RequestBook> reqBookList = new ArrayList<RequestBook>();
    private Users user = new Users();
    private List<Users> userList = new ArrayList<Users>();
    private Subscription subscription = new Subscription();
    private List<Subscription> subsList = new ArrayList<Subscription>();
    private Plans plans = new Plans();
    private List<Plans> plansList = new ArrayList<Plans>();
    private Users users = new Users();


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();


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


        users.setAddress("address");
        users.setCity("city");
        users.setEnabled(true);
        users.setFirstName("firstName");
        users.setLastName("lastName");
        users.setMobile("mobile");
        users.setPincode(10);
        users.setPlanId(10);
        users.setPrimaryLanguage("primaryLanguage");
        users.setSecondaryLanguage("secondaryLanguage");
        users.setState("state");
        users.setUserName("userName");
        users.setUserPassword("userPassword");


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
    public void testGetForm() throws Exception {
        mockMvc.perform(get("/registrationForm.html")
                .session(session))
                .andExpect(view().name("registrationForm"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("registrationForm"));

        when(subscriptionPlanService.getPlansList()).thenReturn(plansList);
    }

    @Test
    public void testListUsers() throws Exception {

        mockMvc.perform(get("/userList.htm")
                .session(session))
                .andExpect(view().name("userList"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("userList"));

        when(registerService.getUsersList()).thenReturn(userList);

    }

    /* @Test
    public void testInsertUser() throws Exception {
       mockMvc.perform(get("/Register.htm")
               .session(session))
               .andExpect(view().name("redirect:/loginform.html"))
               .andExpect(status().is(302));

       when(registerService.getUsersList()).thenReturn(userList);
       doNothing().when( registerService).insertUser(user);
       when(requestService.checkSubscription(users.getUserName()).thenReturn(subscription);



    }*/

    @Test
    public void testUpdateUser() throws Exception {
        mockMvc.perform(get("/user_updateUser.htm")
                .session(session))
                .andExpect(view().name("redirect:/user_ViewProfile"))
                .andExpect(status().is(302));
        doNothing().when( registerService).updateUser(user);
    }

     /* @Test
    public void testChangePassword() throws Exception {
          mockMvc.perform(get("/admin_changePassword.htm")
                  .session(session))
                  .andExpect(status().is(302));
          doNothing().when( registerService).updateUser(user);
          when(registerService.getUserById("userName")).thenReturn(user);

          
    }*/

      @Test
    public void testEditUser() throws Exception {
          mockMvc.perform(get("/user_userEdit.htm")
                  .param("userName", "Yash")
                  .session(session))
                  .andExpect(view().name("editProfile"))
                  .andExpect(status().isOk())
                  .andExpect(forwardedUrl("editProfile"));

          when(registerService.getUserById("userName")).thenReturn(user);
    }

     @Test
    public void testDeleteUser() throws Exception {
         mockMvc.perform(get("/userDelete.htm")
                 .param("userName", "Yash")
                 .session(session))
                 .andExpect(view().name("redirect:/user_userList.htm"))
                 .andExpect(status().is(302));
         when(registerService.getUserById("userName")).thenReturn(user);


    }

     /* @Test
    public void testAjaxCheckAvalability() throws Exception {
          mockMvc.perform(post("/checkavailability.htm")
                  .param("userName", "Yash")
                  .session(session))
                  .andExpect(view().name("false"))
                  .andExpect(status().isOk());
          when(registerService.checkAvailability("userName")).thenReturn(true);


    }*/

}
