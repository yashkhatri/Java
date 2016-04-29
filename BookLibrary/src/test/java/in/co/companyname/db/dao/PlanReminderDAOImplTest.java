/*
 * 
 */
package in.co.companyname.db.dao;

import static org.junit.Assert.assertTrue;
import in.co.companyname.db.model.Subscription;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class PlanReminderDAOImplTest.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/java/resources/applicationContext.xml"})
public class PlanReminderDAOImplTest {

	

	/** The plan reminder dao impl. */
	@Autowired
	private PlanReminderDAOImpl planReminderDAOImpl;
	
	/**
	 * Test get users by month.
	 */
	@Test
	public void testGetUsersByMonth() {
		try{
			planReminderDAOImpl.getUsersByMonth();
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test get users by week.
	 */
	@Test
	public void testGetUsersByWeek() {
		try{
			planReminderDAOImpl.getUsersByWeek();
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test get users by days.
	 */
	@Test
	public void testGetUsersByDays() {
		try{
			planReminderDAOImpl.getUsersByDays();
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test get user subscriptions.
	 */
	@Test
	public void testGetUserSubscriptions() {
		try{
			planReminderDAOImpl.getUserSubscriptions();
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test update subscription.
	 */
	@Test
	public void testUpdateSubscription() {
		try{
			Date date = new Date(new java.util.Date().getTime());
			Subscription subscription = new Subscription();
			subscription.setEndDate(date);
			subscription.setIsActive(false);
			subscription.setMaxBooks(10);
			subscription.setMaxDays(10);
			subscription.setPlan(10);
			subscription.setPlanName("Test");
			subscription.setPrice(10);
			subscription.setStartDate(date);
			subscription.setUserName("as@as.com");
			planReminderDAOImpl.updateSubscription(subscription);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}
	
	
}
