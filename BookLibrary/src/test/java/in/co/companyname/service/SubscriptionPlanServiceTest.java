package in.co.companyname.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import in.co.companyname.db.dao.SubscriptionPlansDao;
import in.co.companyname.db.model.Plans;
import in.co.companyname.service.subscriptionplan.SubscriptionPlanServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class SubscriptionPlanServiceTest 
{
	private static SubscriptionPlansDao subscriptionPlansDao;

	@Before
	public  void setUp()
	{
		subscriptionPlansDao=mock(SubscriptionPlansDao.class);

	}

	@Test
	public void testInsertRow()
	{
		Plans plans=new Plans();
		plans.setPlanId(0);
		SubscriptionPlanServiceImpl subscriptionPlanServiceImpl=new SubscriptionPlanServiceImpl();
		subscriptionPlanServiceImpl.setSubscriptionPlansDao(subscriptionPlansDao);
		subscriptionPlanServiceImpl.insertPlans(plans);
		assertTrue(true);
	  
	}

	@Test
	public void testGetPlansList() 
	{
		SubscriptionPlanServiceImpl subscriptionPlanServiceImpl=new SubscriptionPlanServiceImpl();
		subscriptionPlanServiceImpl.setSubscriptionPlansDao(subscriptionPlansDao);
		List<Plans> plist=new ArrayList<>();
		Plans plans=new Plans();
		plans.setMaxBooks(5);
		plans.setMaxDays(60);
		plist.add(plans);

		when(subscriptionPlansDao.getPlansList()).thenReturn(plist);
		List<Plans>actual=(List<Plans>)subscriptionPlanServiceImpl.getPlansList();
		assertEquals(plist, actual);

	}

	@Test
	public void testGetRowById() 
	{
		SubscriptionPlanServiceImpl subscriptionPlanServiceImpl=new SubscriptionPlanServiceImpl();
		subscriptionPlanServiceImpl.setSubscriptionPlansDao(subscriptionPlansDao);
		Plans plans=new Plans();
		plans.setMaxBooks(5);
		plans.setMaxDays(60);
		plans.setPlanId(4);
		
		when(subscriptionPlansDao.getPlanById(4)).thenReturn(plans);
		assertEquals(plans,subscriptionPlanServiceImpl.getPlanById(4));
	}

  @Test 
  public void testDeleteRow()
  {
	  Integer planId=1;
		SubscriptionPlanServiceImpl subscriptionPlanServiceImpl=new SubscriptionPlanServiceImpl();
		subscriptionPlanServiceImpl.setSubscriptionPlansDao(subscriptionPlansDao);
		subscriptionPlanServiceImpl.deletePlan(planId);
		assertTrue(true);
	  
  }

	}


