/*
 * 
 */
package in.co.companyname.db.dao;

import static org.junit.Assert.assertTrue;
import in.co.companyname.db.model.Plans;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class SubscriptionPlansDaoImplTest.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/java/resources/applicationContext.xml"})
 
public class SubscriptionPlansDaoImplTest {
	
	
	/** The subscription plans dao impl. */
	@Autowired
	private SubscriptionPlansDao subscriptionPlansDaoImpl;


	/**
	 * Test insert row.
	 */
	@Test
	public void testInsertPlan() {
		try{
			Plans plan = new Plans(10, 10, 10, "Test");
			subscriptionPlansDaoImpl.insertPlan(plan);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test get plans list.
	 */
	@Test
	public void testGetPlansList() {
		try{
			subscriptionPlansDaoImpl.getPlansList();
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test get row by id.
	 */
	@Test
	public void testGetRowById() {
		try{
			subscriptionPlansDaoImpl.getPlanById(3);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	/**
	 * Test delete row.
	 */
	@Test
	public void testDeleteRow() {
		try{
			subscriptionPlansDaoImpl.deletePlan(9);
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}

	
	
	
}
