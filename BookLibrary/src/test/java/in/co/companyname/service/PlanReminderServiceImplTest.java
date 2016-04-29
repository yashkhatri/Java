/**
 * 
 */
package in.co.companyname.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import in.co.companyname.db.dao.PlanReminderDAO;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.service.planreminder.PlanReminderServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
/**
 * @author yash.khatri
 *
 */
public class PlanReminderServiceImplTest {

	/**
	 * @throws java.lang.Exception
	 */
	private static PlanReminderDAO planReminderDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		planReminderDAO=mock(PlanReminderDAO.class);
	}

	@Test
	public void testGetUsersByMonth() {

		PlanReminderServiceImpl planReminderServiceImpl=new PlanReminderServiceImpl();
		planReminderServiceImpl.setPlanReminderDAO(planReminderDAO);
		
	List<Subscription> slist=new ArrayList<>();
	Subscription subs=new Subscription();
	subs.setMaxBooks(5);
	subs.setMaxDays(60);
	slist.add(subs);
	when(planReminderDAO.getUsersByMonth()).thenReturn(slist);
	List<Subscription> actual=(List<Subscription>)planReminderServiceImpl.getUsersByMonth();
	assertEquals(slist, actual);
	
	}

	
	@Test
	public void testGetUsersByWeek()
	{
	
		PlanReminderServiceImpl planReminderServiceImpl=new PlanReminderServiceImpl();
		planReminderServiceImpl.setPlanReminderDAO(planReminderDAO);
		
		List<Subscription> slist=new ArrayList<>();
		Subscription subs=new Subscription();
		subs.setMaxBooks(5);
		subs.setMaxDays(60);
		slist.add(subs);
		when(planReminderDAO.getUsersByWeek()).thenReturn(slist);
		List<Subscription> actual=(List<Subscription>)planReminderServiceImpl.getUsersByWeek();
		assertEquals(slist, actual);
		
	
		
	}

	@Test
	public void testGetUsersByDays() 
	{

		PlanReminderServiceImpl planReminderServiceImpl=new PlanReminderServiceImpl();
		planReminderServiceImpl.setPlanReminderDAO(planReminderDAO);
		
		List<Subscription> slist=new ArrayList<>();
		Subscription subs=new Subscription();
		subs.setMaxBooks(5);
		subs.setMaxDays(60);
		slist.add(subs);
		when(planReminderDAO.getUsersByDays()).thenReturn(slist);
		List<Subscription> actual=(List<Subscription>)planReminderServiceImpl.getUsersByDays();
		assertEquals(slist, actual);
	}

	@Test
	public void testGetUserSubscriptions() {

		PlanReminderServiceImpl planReminderServiceImpl=new PlanReminderServiceImpl();
		planReminderServiceImpl.setPlanReminderDAO(planReminderDAO);
		
		List<Subscription> slist=new ArrayList<>();
		Subscription subs=new Subscription();
		subs.setMaxBooks(5);
		subs.setMaxDays(60);
		slist.add(subs);
		when(planReminderDAO.getUserSubscriptions()).thenReturn(slist);
		List<Subscription> actual=(List<Subscription>)planReminderServiceImpl.getUserSubscriptions();
		assertEquals(slist, actual);
	}


	@Test
    public void testUpdateSubscription() 
    {
        PlanReminderServiceImpl planReminderServiceImpl=new PlanReminderServiceImpl();
        planReminderServiceImpl.setPlanReminderDAO(planReminderDAO);
        
        Subscription subscription=new Subscription();
        planReminderServiceImpl.updateSubscription(subscription);
        assertTrue(true);

    }

}
