/*
 * 
 */
package in.co.companyname.service.pdfgen;

import in.co.companyname.db.model.BookSearch;

import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface PdfGenService.
 */
public interface PdfGenService {

    /**
     * Gets the reports.
     * 
     * @param author
     *            the author
     * @param category
     *            the category
     * @param from
     *            the from
     * @param to
     *            the to
     * @return the reports
     */
    List<Object[]> getReports(String author, String category, Date from, Date to);

    /**
     * Gets the distinct auth.
     * 
     * @return the distinct auth
     */
    List<BookSearch> getDistinctAuth();

    /**
     * Gets the distinct cat.
     * 
     * @return the distinct cat
     */
    List<BookSearch> getDistinctCat();

}