package in.co.companyname.controllers;

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
import in.co.companyname.service.adminfunction.AdminFunctionService;
import in.co.companyname.service.pdfgen.PdfGenService;
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

public class AdminFunctionControllerTest {

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
    private AdminFunctionService adminFunctionService;

    @Mock
    private RequestService requestService;
    
    @Mock
    private PdfGenService pdfGenService;


    @InjectMocks
    private AdminFunctionController adminFunctionController = 
    new AdminFunctionController();

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
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminFunctionController).build();
        
            
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
    public void testGetForm() throws Exception {
        mockMvc.perform(get("/admin_addbook.html"))

        .andExpect(view().name("addbook"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("addbook"));
    }

    @Test
    public void testListBooks() throws Exception {
        mockMvc.perform(get("/admin_booklist.html"))

        .andExpect(view().name("booklist"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("booklist"));
    }


    /*    @Test
    public void testPaginationBooks() throws Exception {
        mockMvc.perform(get("/admin_BooksPagination")
                .param("draw", "1")
                .param("start", "start")
                .param("search[value]", "start")
                .param("order[0][column]", "start")
                .param("order[0][dir]", "start")
                .param("  length", "start")

                )

                .andExpect(status().is(400))
               .andExpect(view().name("gson.toJsonTree(requestMap)"));


        when( adminFunctionService.getTotalRecordsOfBooks()).thenReturn(1);
        when( adminFunctionService.getAllBooks(1,2,"start",
                1, "")).thenReturn(books);

        }
     */





    /*    @Test
    public void testInsertBook() throws Exception {
        mockMvc.perform(get("/admin_bookinsert.html")
                .session(session))


                .andExpect(view().name("addbook"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("addbook"));
        when( adminFunctionService.insertBook(bookSearch,request)).thenReturn("Added or Updated Successfully");
    }
     */
    @Test
    public void testEditBook() throws Exception {
        mockMvc.perform(get("/admin_bookedit.html")
                .param("bookId", "bookId"))
                .andExpect(view().name("updateBook"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("updateBook"));
        when( adminFunctionService.getBookById("bookId")).thenReturn(bookSearch);
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(get("/admin_bookdelete.html")
                .param("bookId", "bookId")
                .session(session))
                .andExpect(view().name("redirect: admin_booklist.html"))
                .andExpect(status().is(302));
        when( adminFunctionService.deleteBook("bookId")).thenReturn("Success");
    }

    @Test
    public void testDeliveryRequests() throws Exception {
        mockMvc.perform(get("/admin_View_Delivery_Requests.html"))
        .andExpect(view().name("deliveryRequest"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("deliveryRequest"));
        when( adminFunctionService
                .showActiveDeliveryRequests()).thenReturn(reqBookList);
    }

    @Test
    public void testReturnRequests() throws Exception {
        mockMvc.perform(get("/admin_View_Return_Requests.html"))
        .andExpect(view().name("returnRequest"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("returnRequest"));
        when( adminFunctionService
                .showActiveReturnRequests()).thenReturn(reqBookList);
    }

    /*  @Test
    public void testAcceptDeliveryRequests() throws Exception {
        mockMvc.perform(post("/admin_Accept_Delivery_Requests")
                .session(session))
                .andExpect(view().name("redirect: admin_booklist.html"))
                .andExpect(status().is(302));
        when(request.getParameter("bookId")).thenReturn("B01");
        when(request.getParameter("button")).thenReturn(" Accept Request");
        when(request.getParameter("requestId")).thenReturn("1");




        when( adminFunctionService.deleteBook("bookId")).thenReturn("Success");
        when(  requestService
                .getRequestedBookByReqId(1)).thenReturn(requestBook);
        when(  registerService.getUserById("userName")).thenReturn(user);
        when(  requestService.checkSubscription(user
                .getUserName())).thenReturn(subscription);




    }*/

    @Test
    public void testAcceptReturnRequests() throws Exception  {
    
            mockMvc.perform(post("/admin_Accept_Return_Requests")
                    .param("bookId", "bookId")
                    .param("requestId", "1")
                    .session(session))
                    .andExpect(view().name("redirect:admin_View_Return_Requests.html"))
                    .andExpect(status().is(302));
            when( adminFunctionService.acceptReturnRequest(1, "bookId")).thenReturn("success");
      
         }

      @Test
    public void testViewActiveUsers() throws Exception {
    
            mockMvc.perform(get("/admin_View_Active_Users.html"))
              .andExpect(view().name("activeUser"))
              .andExpect(status().isOk())
              .andExpect(forwardedUrl("activeUser"));
      
          when( adminFunctionService.viewActiveUsers()).thenReturn(subsList);
    }

        @Test
    public void testAddSubscriptoinXML() throws Exception {
            mockMvc.perform(get("/admin_addSubscriptionXML.html"))
            .andExpect(view().name("addSubscriptions"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("addSubscriptions"));
    
        when( subscriptionPlanService.getPlansList()).thenReturn(plansList);
    }

   /*     @Test
    public void testAddSubscription() {
        fail("Not yet implemented");
    }
*/
        @Test
    public void testShowReports() throws Exception {
            mockMvc.perform(get("/ admin_Reports.html"))
            .andExpect(view().name("reports"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("reports"));
    
        when( pdfGenService.getDistinctAuth()).thenReturn(books);
        when( pdfGenService.getDistinctCat()).thenReturn(books);
       
    }

     /*  @Test
    public void testGetReports() {
        fail("Not yet implemented");
    }

    @Test
    public void testGeneratePdf() {
        fail("Not yet implemented");
    }*/

/*    @Test
    public void testUpdateBook() throws Exception {

        when( adminFunctionService.insertBook(bookSearch, request)).thenReturn("Added or Updated Successfully");
        
        mockMvc.perform(get("/admin_updatebook.html"))
        .andExpect(view().name("updateBook"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("updateBook"));

    }*/

/*    @Test
    public void testAjaxCustomerDetails() {
        fail("Not yet implemented");
    }

    @Test
    public void testAjaxRequestsCount() {
        fail("Not yet implemented");
    }
     */
}
