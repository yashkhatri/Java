/**
 * 
 *
 * 
 */

package in.co.companyname.db.dao;

import in.co.companyname.db.model.BookSearch;
import in.co.companyname.exceptionhandling.SystemException;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class BookSearchDAOImpl.
 *
 * @author yash.khatri
 */

/**
 * 
 * This is an Implementation Class of BookSearchDAO Interface
 * 
 */
@Repository("bookSearchDAO")
public class BookSearchDAOImpl implements BookSearchDAO {

    /** The Constant FIVE. */
    private static final int FIVE=5;
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(BookSearchDAOImpl.class);

    /** The session factory. */
    @Autowired
    private SessionFactory sessionFactory;

    // Searching Requested Books From Books
    // Table********************************
    /*
     * (non-Javadoc)
     * 
     * @see in.co.companyname.db.dao.BookSearchDAO#searchBooks(java.lang.String)
     */
    @Override
    public List<BookSearch> searchBooks(String searchCriteria) throws SystemException {
        Session session = sessionFactory.getCurrentSession();
        try {
            //
            LOGGER.info("inside booksearch impl..:" + searchCriteria);

            Criteria criteria = session.createCriteria(BookSearch.class);

            Criterion title = Restrictions.like("bookTitle", searchCriteria,
                    MatchMode.ANYWHERE).ignoreCase();

            Criterion author = Restrictions.like("bookAuthor", searchCriteria,
                    MatchMode.ANYWHERE).ignoreCase();

            Criterion category = Restrictions.like("bookCategory",
                    searchCriteria, MatchMode.ANYWHERE).ignoreCase();

            criteria.add(Restrictions.or(title, author, category));

            @SuppressWarnings("unchecked")
            List<BookSearch> resultRecords = criteria.list();

            return resultRecords;

        }catch(HibernateException e){
            throw new SystemException("EXCEPTION CAUGHT IN SEARCHING BOOKS :"+e);

        } 
    }

    // Searching Ends
    // ***********************************************************

    /*
     * (non-Javadoc)
     * 
     * @see in.co.companyname.db.dao.BookSearchDAO#getBookById(java.lang.String)
     */
    @Override
    public BookSearch getBookById(String bookId) throws SystemException {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        try {
            //

            Criteria criteria = session.createCriteria(BookSearch.class);

            Criterion rest1 = Restrictions.eq("bookId", bookId);

            criteria.add(rest1);

            BookSearch resultRecords = (BookSearch) criteria.uniqueResult();

            return resultRecords;

        }catch(HibernateException e){
            throw new SystemException("EXCEPTION CAUGHT IN GETTING BOOKS BY"
                    + "ID :"+e);

        } 
    }

    /*
     * (non-Javadoc)
     * 
     * @see in.co.companyname.db.dao.BookSearchDAO#getRecentBooksList()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<BookSearch> getRecentBooksList() throws SystemException {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        try {
            Query query = session
                    .createQuery("from BookSearch b order by b.sno desc");

            query.setFirstResult(0);

            query.setMaxResults(FIVE);
            return (List<BookSearch>) query.list();
        } catch(HibernateException e){
            throw new SystemException("EXCEPTION CAUGHT IN SEARCHING RECENT"
                    + " BOOKS :"+e);

        } 

    }

}
