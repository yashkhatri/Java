/*
 *  */
package in.co.companyname.controllers;

import static in.co.companyname.constants.Constants.ADDED_SUCCESSFULLY;
import static in.co.companyname.constants.Constants.SAME_BOOKID;
import static in.co.companyname.constants.Constants.SUCCESS;
import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.Plans;
import in.co.companyname.db.model.RequestBook;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.db.model.Users;
import in.co.companyname.exceptionhandling.SystemException;
import in.co.companyname.mailsender.MailSender;
import in.co.companyname.pdfgenerator.PdfGenerator;
import in.co.companyname.service.adminfunction.AdminFunctionService;
import in.co.companyname.service.pdfgen.PdfGenService;
import in.co.companyname.service.registration.RegisterService;
import in.co.companyname.service.request.RequestService;
import in.co.companyname.service.subscriptionplan.SubscriptionPlanService;
import in.co.companyname.service.xmlparser.XmlParserService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminFunctionController.
 * This Class deal with all the functionalities which are provided to the admin
 */
/**
 * @author yash.khatri
 * 
 */
@Controller
public class AdminFunctionController {

    /** The admin function service. */
    @Autowired
    private AdminFunctionService adminFunctionService;

    /** The pdf gen service. */
    @Autowired
    private PdfGenService pdfGenService;

    /** The register service. */
    @Autowired
    private RegisterService registerService;

    /** The request service. */
    @Autowired
    private RequestService requestService;

    /** The subscription plan service. */
    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    /** The xml parser service. */
    @Autowired
    private XmlParserService xmlParserService;
    


    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AdminFunctionController.class);

    /** The reports. */
    private static List<Object[]> reports = null;

    /** The send mail. */
    @Autowired
    private MailSender sendMail = new MailSender();

    /** The authors. */
    private static List<BookSearch> authors = null;

    /** The categories. */
    private static List<BookSearch> categories = null;

    /** The Constant ADDSUBSCRIPTIONS. */
    private static final String ADDSUBSCRIPTIONS = "addSubscriptions";
    
    /** The Constant MSG. */
    private static final String MSG = "msg";

    /*-------------	Add Book -----------------------------------------*/

    /**
     * Gets the form.
     * 
     * @return the form
     */
    @RequestMapping(value = "admin_addbook.html", method = RequestMethod.GET)
    public ModelAndView getForm() {
        LOGGER.info("Request to get the add book form");
        return new ModelAndView("addbook");
    }

    /*-------------	View BookList -----------------------------------------
    /**
     * List books.
     *
     * @return the model and view
     */
    /**
     * List books.
     * 
     * @return the model and view
     */
    @RequestMapping("admin_booklist.html")
    public ModelAndView listBooks() {
        LOGGER.info("Request to get the list of the books");
        return new ModelAndView("booklist");

    }

    /**
     * Pagination books.
     *
     * @param draw the draw
     * @param start the start
     * @param search the search
     * @param columnNum the column num
     * @param sortOrder the sort order
     * @param pageSize the page size
     * @return the string
     */
    @RequestMapping("/admin_BooksPagination")
    @ResponseBody
    String paginationBooks(@RequestParam("draw") int draw,
            @RequestParam("start") int start,
            @RequestParam("search[value]") String search,
            @RequestParam("order[0][column]") int columnNum,
            @RequestParam("order[0][dir]") String sortOrder,
            @RequestParam("length") int pageSize) {

        LOGGER.info("--> {} {} {} <--", draw, start, pageSize);
        LOGGER.info("Request to get the books list paginated");
        int totalRecords = adminFunctionService.getTotalRecordsOfBooks();

        List<BookSearch> booksList = adminFunctionService.getAllBooks(start,
                pageSize, search, columnNum, sortOrder);

        Gson gson = new Gson();

        Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("draw", draw);
        requestMap.put("recordsTotal", totalRecords);
        requestMap.put("recordsFiltered", totalRecords);
        requestMap.put("data", booksList);

        LOGGER.info(gson.toJsonTree(requestMap).toString());
        LOGGER.info("Books list received successfully now sending to the JSP");
        return gson.toJsonTree(requestMap).toString();
    }

    /*-------------Insert Books -----------------------------------------*/

    /**
     * Insert book.
     *
     * @param books the books
     * @param request            the request
     * @return the model and view
     */
    @RequestMapping("admin_bookinsert.html")
    public ModelAndView insertBook(@ModelAttribute BookSearch books,
            HttpServletRequest request) {

        LOGGER.info("Trying to add the book");

        ModelAndView model = new ModelAndView("addbook");

        String result = SAME_BOOKID;
        try {
            result = adminFunctionService.insertBook(books, request);
        } catch (Exception e) {
            LOGGER.info("Caught Exception in AdminFunction Service");
        }
        if (result.equals(ADDED_SUCCESSFULLY)) {
            model.addObject("Msg", result);
        } else {
            model.addObject("Msg1", result);
        }
        LOGGER.info("Book Add Code successfully completed now sending model");
        return model;

    }

    /*-------------	Edit Books -----------------------------------------*/

    /**
     * Edits the book.
     * 
     * @param bookId
     *            the book id
     * @return the model and view
     */
    @RequestMapping("admin_bookedit.html")
    public ModelAndView editBook(@RequestParam String bookId) {
        LOGGER.info("Request to edit the book");
        LOGGER.info("bookId reached" + bookId);
        BookSearch book = adminFunctionService.getBookById(bookId);
        LOGGER.info("Request Completed now sending model");
        return new ModelAndView("updateBook", "book", book);
    }

    /**
     * Delete user.
     *
     * @param bookId            the book id
     * @param session the session
     * @return the model and view
     */
    @RequestMapping(value = "admin_bookdelete.html", method = RequestMethod.GET)
    public ModelAndView deleteBook(@RequestParam String bookId,
            HttpSession session) {
        LOGGER.info("Request to delete the book");
        String deleteResult = adminFunctionService.deleteBook(bookId);
        LOGGER.info("Delete Result {}", deleteResult);
        session.setAttribute("deleteResult", deleteResult);
        LOGGER.info("Code completed succesfully now sending model and view");
        return new ModelAndView("redirect: admin_booklist.html");
    }

    /*-------------	View Delivery _Requests -----------------------------------------*/

    /**
     * Delivery requests.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param response
     *            the response
     * @return the string
     */
    @RequestMapping("admin_View_Delivery_Requests.html")
    public String deliveryRequests(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Request to see the delivery requests");
        List<RequestBook> requests = adminFunctionService
                .showActiveDeliveryRequests();
        model.put("requests", requests);
        LOGGER.info("Delivery Requests fetched successully now returning model"
                + " and view ");
        return "deliveryRequest";
    }

    /*-------------	View_Return_Requests -----------------------------------------*/

    /**
     * Return requests.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param response
     *            the response
     * @return the string
     */
    @RequestMapping("admin_View_Return_Requests.html")
    public String returnRequests(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Request to see the Return Requests");
        List<RequestBook> requests = adminFunctionService
                .showActiveReturnRequests();

        model.put("requests", requests);
        LOGGER.info("Return Requests feteched successfully now returning model"
                + " and view");
        return "returnRequest";
    }

    /*-------------	Accept_Delivery_Requests -----------------------------------------*/

    /**
     * Accept delivery requests.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param response
     *            the response
     * @return the string
     * @throws SystemException 
     */
    @RequestMapping(value = "admin_Accept_Delivery_Requests", method = RequestMethod.POST)
    public String acceptDeliveryRequests(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) throws SystemException {

        LOGGER.info("Accepting or Denying Delivery Requests");

        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String bookId = request.getParameter("bookId");

        String buttonClicked = request.getParameter("button");
        RequestBook requestBook = requestService
                .getRequestedBookByReqId(requestId);
        String userName = requestBook.getUserName();
        Users user = registerService.getUserById(userName);
        Subscription subscription = requestService.checkSubscription(user
                .getUserName());

        if (buttonClicked.equalsIgnoreCase("Accept Request")) {

            LOGGER.info("Request to accept the delivery request ");

            String acceptDeliveryResult = adminFunctionService
                    .acceptDeliveryRequest(requestId, "Delivered", bookId);

            if (acceptDeliveryResult.equalsIgnoreCase(SUCCESS)) {
                try {

                    LOGGER.info("Sending Mail of Acceptance");
                    sendMail.shootMail("ACCEPT DELIVERY REQUEST", user,
                            subscription, requestBook);
                } catch (Exception e) {
                    LOGGER.info("Exception in Send MAil at register" + e);
                }
            }

        } else {
            if (buttonClicked.equalsIgnoreCase("Deny Request")) {

                LOGGER.info("Request to Denying the Delivery Request");

                String acceptDeliveryResult = adminFunctionService
                        .acceptDeliveryRequest(requestId, "Denied", bookId);

                if (acceptDeliveryResult.equalsIgnoreCase(SUCCESS)) {
                    try {
                        LOGGER.info("Sending Mail if Denied delivery request");
                        sendMail.shootMail("DENY DELIVERY REQUEST", user,
                                subscription, requestBook);
                    } catch (Exception e) {
                        LOGGER.info("Exception in Send MAil at register" + e);
                    }
                }
            }
        }

        return "redirect:admin_View_Delivery_Requests.html";
    }

    /*-------------	Accept_Return_Requests -----------------------------------------*/

    /**
     * Accept return requests.
     *
     * @param model            the model
     * @param request            the request
     * @param response            the response
     * @param bookId the book id
     * @param requestId the request id
     * @return the string
     */
    @RequestMapping(value = "admin_Accept_Return_Requests", method = RequestMethod.POST)
    public String acceptReturnRequests(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam("bookId") String bookId, @RequestParam("requestId")
    int requestId) {

        LOGGER.info("Request to accept the return requests");

        adminFunctionService.acceptReturnRequest(requestId, bookId);

        LOGGER.info("Return Request accepted now sending model and view");

        return "redirect:admin_View_Return_Requests.html";
    }

    /*---------------	View Active Users -----------------------------------------*/
    /**
     * View active users.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param response
     *            the response
     * @return the string
     */
    @RequestMapping(value = "admin_View_Active_Users.html")
    public String viewActiveUsers(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) {

        LOGGER.info("Request to view the active users");

        List<Subscription> usersList = adminFunctionService.viewActiveUsers();
        model.put("results", usersList);

        LOGGER.info("List of active users fetched successfully now sending model"
                + "and view");

        return "activeUser";
    }

    /*---------------	add Subscription via XML -----------------------------------------*/
    /**
     * Adds the subscriptoin xml.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param response
     *            the response
     * @return the string
     */
    @RequestMapping(value = "admin_addSubscriptionXML.html")
    public String addSubscriptoinXML(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) {

        LOGGER.info("Request to view plans list by admin");

        List<Plans> plansList = subscriptionPlanService.getPlansList();
        LOGGER.info("Plans at Controller ->{}", plansList);

        model.put("plansList", plansList);

        LOGGER.info("List fetched successfully now sending model and view");

        return ADDSUBSCRIPTIONS;
    }

    /*---------------	add-Update Subscription via XML -----------------------------------------*/
    /**
     * Adds the subscription.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param subscriptionXMLFile
     *            the subscription xml file
     * @return the model and view
     */
    @RequestMapping(value = "admin_addSubscriptionXML1.html", method = RequestMethod.POST)
    public ModelAndView addSubscription(Model model,
            HttpServletRequest request,
            @RequestParam("file") MultipartFile subscriptionXMLFile) {

        LOGGER.info("Request to add subscription plan via uploading XML file");

        ModelAndView modelAndView = new ModelAndView(ADDSUBSCRIPTIONS);
        try {
            ServletContext context = request.getServletContext();
            String path = context.getRealPath("/");
            LOGGER.info("XML PATH ->" + path);

            String res = null;

            String buttonClicked = request.getParameter("button");

            LOGGER.info("button clicked is " + buttonClicked);

            if (buttonClicked.equalsIgnoreCase("Add or Update")) {

                LOGGER.info("Requested to add plans");

                res = xmlParserService.addOrUpdateSubscription(path,
                        subscriptionXMLFile);

                if (res.equalsIgnoreCase("success")) {
                    modelAndView = new ModelAndView(ADDSUBSCRIPTIONS, MSG,
                            "Plans Added Succesfully");
                } else {
                    modelAndView = new ModelAndView(ADDSUBSCRIPTIONS, MSG, res);
                }
                if (res.equalsIgnoreCase("fileempty")) {
                    modelAndView = new ModelAndView(ADDSUBSCRIPTIONS, MSG,
                            "Please select a file first");
                }

            } else {

                LOGGER.info("Requested to delete plans");

                if (buttonClicked.equalsIgnoreCase("Delete")) {

                    res = xmlParserService.deleteSubscriptions(path,
                            subscriptionXMLFile);
                    if (res.equalsIgnoreCase("Success")) {
                        modelAndView = new ModelAndView(ADDSUBSCRIPTIONS, MSG,
                                "Plans Deleted Succesfully");
                    } else {
                        modelAndView = new ModelAndView(ADDSUBSCRIPTIONS, MSG,
                                "System Failure");
                    }
                    if (res.equalsIgnoreCase("fileempty")) {
                        modelAndView = new ModelAndView(ADDSUBSCRIPTIONS, MSG,
                                "Please select a file first");
                    }

                }

            }

        } catch (Exception ex) {

            modelAndView = new ModelAndView(ADDSUBSCRIPTIONS, MSG,
                    ex.getMessage());
            LOGGER.error("in Adding Plans" + ex);

        }

        List<Plans> plansList = subscriptionPlanService.getPlansList();

        modelAndView.addObject("plansList", plansList);

        LOGGER.info("Action to add or delete plans performed successfully "
                + "now sending model and view");

        return modelAndView;

    }

    /*---------------	Reports View -----------------------------------------*/

    /**
     * Show reports.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param response
     *            the response
     * @return the string
     */
    @RequestMapping(value = "admin_Reports.html", method = RequestMethod.GET)
    public String showReports(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) {

        LOGGER.info("Request to view the reports page");

        authors = pdfGenService.getDistinctAuth();
        categories = pdfGenService.getDistinctCat();

        model.put("Authors", authors);
        model.put("Categories", categories);

        LOGGER.info("authors and categories fetched successfully now sending "
                + "model and view");

        return "reports";
    }

    /*---------------	Getting Reports -----------------------------------------*/

    /**
     * Gets the reports.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param response
     *            the response
     * @return the reports
     */
    @RequestMapping(value = "admin_Reports.html", method = RequestMethod.POST)
    public String getReports(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) {

        LOGGER.info("Posted query to get the reports");

        String author = request.getParameter("author");
        String category = request.getParameter("category");
        String from = request.getParameter("from");
        String to = request.getParameter("to");

        LOGGER.info("Author {}", author);
        LOGGER.info("Category {}", category);

        Date dateFrom = null;
        Date dateTo = null;

        try {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            df.setTimeZone(TimeZone.getDefault());
            Date date = new java.sql.Date(df.parse(from).getTime());

            DateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd",
                    Locale.ENGLISH);
            outputDateFormat.setTimeZone(TimeZone.getDefault());
            LOGGER.info("Output date is = " + outputDateFormat.format(date));
            String dateString1 = outputDateFormat.format(date);
            dateFrom = new java.sql.Date(outputDateFormat.parse(dateString1)
                    .getTime());

            Date date1 = new java.sql.Date(df.parse(to).getTime());
            outputDateFormat.setTimeZone(TimeZone.getDefault());
            LOGGER.info("Output date is = " + outputDateFormat.format(date1));
            String dateString2 = outputDateFormat.format(date1);
            dateTo = new java.sql.Date(outputDateFormat.parse(dateString2)
                    .getTime());

            reports = pdfGenService.getReports(author, category, dateFrom,
                    dateTo);

        } catch (Exception e) {
            LOGGER.info("Exception in converting date" + e);
            model.put("Msg", "");
            return "reports";
        }

        model.put("Authors", authors);
        model.put("Categories", categories);

        model.put("report", reports);

        LOGGER.info("Reports fetched successfuly now sending model and view");

        return "reports";
    }

    /*---------------	Generate PDF -----------------------------------------*/
    /**
     * Generate pdf.
     * 
     * @param model
     *            the model
     * @param request
     *            the request
     * @param response
     *            the response
     */
    @RequestMapping(value = "admin_GeneratePdf.pdf")
    public void generatePdf(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) {

        LOGGER.info("Request to generate pdf");

        PdfGenerator pdfGen = new PdfGenerator();

        response.setContentType("application/pdf");
        com.itextpdf.text.Document d = new com.itextpdf.text.Document(
                PageSize.LETTER.rotate());
        try {
            PdfWriter.getInstance(d, response.getOutputStream());
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            LOGGER.info("Document Exception" + e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LOGGER.info("IO Exception" + e);
        }

        pdfGen.generate(reports, d);

        LOGGER.info("PDF generated successfully");

    }

    /**
     * Update book.
     *
     * @param books the books
     * @param request            the request
     * @return the model and view
     */
    @RequestMapping("admin_updatebook.html")
    public ModelAndView updateBook(@ModelAttribute BookSearch books,
            HttpServletRequest request) {

        LOGGER.info("Request to update the books");

        ModelAndView model = new ModelAndView("updateBook");

        String result = adminFunctionService.insertBook(books, request);
        
        if (result.equals(ADDED_SUCCESSFULLY)) {
            model.addObject("Msg", result);
        } else {
            model.addObject("Msg1", result);
        }

        LOGGER.info("Request to add books completed successfully "
                + "now returning model and view");
        return model;

    }

    /**
     * Ajax customer details.
     *
     * @param model the model
     * @param userName the user name
     * @param response the response
     * @return the string
     */
    @RequestMapping(value = "admin_customerDetails.htm", method = RequestMethod.POST)
    @ResponseBody
    public String ajaxCustomerDetails(Model model,
            @RequestParam("userName") String userName, ServletResponse response) {

        LOGGER.info("Request to Fetch customer details by admin");

        LOGGER.info("userName:" + userName);
        Users user = registerService.getUserById(userName);
        model.addAttribute("user", user);

        Gson gson = new Gson();

        Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("data", user);

        LOGGER.info(gson.toJsonTree(requestMap).toString());

        LOGGER.info("Request fetched successfully now returning GSON");

        return gson.toJsonTree(requestMap).toString();

    }

}
