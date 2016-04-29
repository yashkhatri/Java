/*
 * 
 */
package in.co.companyname.service.adminfunction;

import static in.co.companyname.constants.Constants.SUCCESS;
import in.co.companyname.db.dao.AdminFunctionDao;
import in.co.companyname.db.dao.BookSearchDAO;
import in.co.companyname.db.dao.RequestDAO;
import in.co.companyname.db.model.BookSearch;
import in.co.companyname.db.model.RequestBook;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.exceptionhandling.SystemException;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminFunctionServiceImpl.
 */
@Repository
public class AdminFunctionServiceImpl implements AdminFunctionService {
    
    

    /** The admin function dao. */
    @Autowired
    private AdminFunctionDao adminFunctionDao;

    /** The request dao. */
    @Autowired
    private RequestDAO requestDAO;

    /** The book search dao. */
    @Autowired
    private BookSearchDAO bookSearchDAO;
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AdminFunctionServiceImpl.class);


    /**
     * Sets the admin function dao.
     *
     * @param adminFunctionDao the new admin function dao
     */
    public void setAdminFunctionDao(AdminFunctionDao adminFunctionDao) {
        this.adminFunctionDao = adminFunctionDao;
    }


    /**
     * Sets the book search dao.
     *
     * @param bookSearchDAO the new book search dao
     */
    public void setBookSearchDAO(BookSearchDAO bookSearchDAO) {
        this.bookSearchDAO = bookSearchDAO;
    }

    /**
     * Sets the request dao.
     *
     * @param requestDAO the new request dao
     */
    public void setRequestDAO(RequestDAO requestDAO) {
        this.requestDAO = requestDAO;
    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.adminfunction.AdminFunctionService#insertRow(in
     * .co.companyname.db.model.BookSearch)
     */
    @Override
    @Transactional
    public String insertBook(BookSearch books,HttpServletRequest request) {
        
        String fileName = "";

        if (!books.getFile().isEmpty()) {
            try {
                byte[] bytes = books.getFile().getBytes();
                String name = books.getFile().getOriginalFilename();
                LOGGER.info("Name : " + name);
                fileName = name;
                @SuppressWarnings("deprecation")
                String rpath = request.getRealPath("/");
                LOGGER.info(rpath);
                File file = new File(rpath, "images");
                if (!file.exists()) {
                    file.mkdirs();
                }

                File temp = new File(file, fileName);
                LOGGER.info("Path : " + temp);

                FileOutputStream fos = new FileOutputStream(temp);
                fos.write(bytes);
                fos.close();

            } catch (Exception ex) {
                LOGGER.info("Exception in File Upload" + ex);
            }
            books.setBookImage(fileName);

        }
        return adminFunctionDao.insertBook(books);
        
       
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.adminfunction.AdminFunctionService#getRowById(java
     * .lang.String)
     */
    @Override
    public BookSearch getBookById(String bookId) {
        return adminFunctionDao.getBookById(bookId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.adminfunction.AdminFunctionService#deleteRow(java
     * .lang.String)
     */
    @Override
    @Transactional
    public String deleteBook(String bookId) {
        return adminFunctionDao.deleteBook(bookId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see in.co.companyname.service.adminfunction.AdminFunctionService#
     * showActiveDeliveryRequests()
     */
    @Override
    public List<RequestBook> showActiveDeliveryRequests() {
        // TODO Auto-generated method stub

        return adminFunctionDao.showActiveDeliveryRequests();
    }

    /*
     * (non-Javadoc)
     * 
     * @see in.co.companyname.service.adminfunction.AdminFunctionService#
     * showActiveReturnRequests()
     */
    @Override
    public List<RequestBook> showActiveReturnRequests() {
        // TODO Auto-generated method stub
        return adminFunctionDao.showActiveReturnRequests();
    }

    /*
     * (non-Javadoc)
     * 
     * @see in.co.companyname.service.adminfunction.AdminFunctionService#
     * acceptDeliveryRequest(int, java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public String acceptDeliveryRequest(int requestId, String action,
            String bookId) throws SystemException {
        // TODO Auto-generated method stub



        if (action.equalsIgnoreCase("Denied")) {       
            BookSearch book = bookSearchDAO.getBookById(bookId);
            int bookAvailiblity = book.getBookAvailablity();
            bookAvailiblity = bookAvailiblity + 1;
            book.setBookAvailablity(bookAvailiblity);
            requestDAO.changeAvailablity(book);
            return adminFunctionDao.acceptDeliveryRequest(requestId, action);
        } else {
            return adminFunctionDao.acceptDeliveryRequest(requestId, action);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.adminfunction.AdminFunctionService#acceptReturnRequest
     * (int, java.lang.String)
     */
    @Override
    @Transactional
    public String acceptReturnRequest(int requestId, String bookId) {
        // TODO Auto-generated method stub


        RequestBook requestedBook = requestDAO.getBookByRequestId(requestId);
        BookSearch book = requestedBook.getBookSearch();
        int bookAvailiblity = book.getBookAvailablity();
        String result = adminFunctionDao.acceptReturnRequest(requestedBook);
        if (result.equalsIgnoreCase(SUCCESS)) {

            bookAvailiblity = bookAvailiblity + 1;
            book.setBookAvailablity(bookAvailiblity);
            requestDAO.changeAvailablity(book);

        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.co.companyname.service.adminfunction.AdminFunctionService#viewActiveUsers
     * ()
     */
    @Override
    public List<Subscription> viewActiveUsers() {
        // TODO Auto-generated method stub
        List<Subscription> users = adminFunctionDao.viewActiveUsers();

        return users;
    }


    /* (non-Javadoc)
     * @see in.co.companyname.service.adminfunction.AdminFunctionService#getAllBooks(int, int, java.lang.String, int, java.lang.String)
     */
    @Override
    @Transactional
    public List<BookSearch> getAllBooks(int start, int pageSize,  String search,
            int columnNum, String sortOrder) {
        // TODO Auto-generated method stub
        return adminFunctionDao.getAllBooks(start, pageSize, search, columnNum, sortOrder);
    }

    // **********************************************************************************************************************


    /* (non-Javadoc)
     * @see in.co.companyname.service.adminfunction.AdminFunctionService#getTotalRecordsOfRequest()
     */
    @Override
    @Transactional
    public int getTotalRecordsOfBooks() {
        // TODO Auto-generated method stub
        return adminFunctionDao.getTotalRecordsOfBooks();
    }

}
