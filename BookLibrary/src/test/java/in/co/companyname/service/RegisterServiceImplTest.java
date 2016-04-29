package in.co.companyname.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import in.co.companyname.db.dao.BookSearchDAO;
import in.co.companyname.db.dao.RegisterDao;
import in.co.companyname.db.dao.RequestDAO;
import in.co.companyname.db.dao.SubscriptionPlansDao;
import in.co.companyname.db.model.Plans;
import in.co.companyname.db.model.Roles;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.db.model.Users;
import in.co.companyname.service.registration.RegisterServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RegisterServiceImplTest
{
    private RegisterDao registerDao;
    private BookSearchDAO bookSearchDAO;
    private SubscriptionPlansDao subscriptionPlansDao;
    private RequestDAO requestDAO;



@Before
public void setUp() 
{
    registerDao=mock(RegisterDao.class);
    bookSearchDAO=mock(BookSearchDAO.class);
    subscriptionPlansDao=mock(SubscriptionPlansDao.class);
    requestDAO=mock(RequestDAO.class);
}

@Test
public void getUserById()
{
	RegisterServiceImpl registerServiceImpl=new RegisterServiceImpl();
	registerServiceImpl.setRegisterDao(registerDao);

	Users users1=new Users();
	users1.setFirstName("abc");
	users1.setLastName("abc");
	
	when(registerDao.getUserById("a")).thenReturn(users1);
	assertEquals(users1, registerServiceImpl.getUserById("a"));
		
}

@Test
public void checkAvailability()
{
	RegisterServiceImpl registerServiceImpl=new RegisterServiceImpl();
	registerServiceImpl.setRegisterDao(registerDao);
	when(registerServiceImpl.checkAvailability("a")).thenReturn(true);
	Boolean actual=registerServiceImpl.checkAvailability("a");
	assertEquals(true, actual);
}

@Test
public void getUsersList()
{
	RegisterServiceImpl registerServiceImpl=new RegisterServiceImpl();
	registerServiceImpl.setRegisterDao(registerDao);
	
	List<Users> ulist=new ArrayList<>();
	Users users1=new Users();
	users1.setFirstName("manish");
	users1.setLastName("sharma");
	users1.setUserName("xyz");
	ulist.add(users1);
	

	when(registerDao.getUsersList()).thenReturn(ulist);

	List<Users> actual=(List<Users>)registerServiceImpl.getUsersList();
	assertEquals(ulist,actual);

}

@Test
public void testdeleteRow()
{
	try
	{
	RegisterServiceImpl registerServiceImpl=new RegisterServiceImpl();
	registerServiceImpl.setRegisterDao(registerDao);
	
	Users users = new Users();
	users.setUserName("abc");
	
	registerServiceImpl.deleteUser("abc");
	assertTrue(true);
	}
	
	
		catch(Exception e){
			assertTrue(false);
	}
}

@Test
public void testinsertSubscription()
{
    RegisterServiceImpl registerServiceImpl=new RegisterServiceImpl();
    registerServiceImpl.setRegisterDao(registerDao);

    
    Plans plan = new Plans();
    plan.setMaxBooks(10);
    plan.setMaxDays(10);
    plan.setPlanName("Gold");
    plan.setPrice(300);
    when(registerDao.getPlanById(1)).thenReturn(plan);
    registerServiceImpl.insertSubscription("test", 1);
}

@Test
public void upgradeSubscription()
{
    RegisterServiceImpl registerServiceImpl=new RegisterServiceImpl();
    registerServiceImpl.setRequestDAO(requestDAO);
    registerServiceImpl.setRegisterDao(registerDao);
    Plans plan = new Plans();
    plan.setMaxBooks(10);
    plan.setMaxDays(10);
    plan.setPlanName("Gold");
    plan.setPrice(300);
    when(registerDao.getPlanById(1)).thenReturn(plan);
    when(requestDAO.checkSubscription("h")).thenReturn(new Subscription());
    when(requestDAO.countRequestedBooks("h")).thenReturn(0);
    when(requestDAO.countHoldingBooks("a")).thenReturn(0);
    registerServiceImpl.upgradeSubscription("h", 1);
   
}


@Test
public void upgradeSubscriptionElsePart()
{
    RegisterServiceImpl registerServiceImpl=new RegisterServiceImpl();
    registerServiceImpl.setRequestDAO(requestDAO);
    registerServiceImpl.setRegisterDao(registerDao);
    Plans plan = new Plans();
    plan.setMaxBooks(10);
    plan.setMaxDays(10);
    plan.setPlanName("Gold");
    plan.setPrice(300);
    when(registerDao.getPlanById(1)).thenReturn(plan);
    when(requestDAO.checkSubscription("h")).thenReturn(new Subscription());
    when(requestDAO.countRequestedBooks("h")).thenReturn(1);
    when(requestDAO.countHoldingBooks("a")).thenReturn(1);
    registerServiceImpl.upgradeSubscription("h", 1);
   
}


@Test
public void testupdateUser()
{
	try
	{
	RegisterServiceImpl registerServiceImpl=new RegisterServiceImpl();
	registerServiceImpl.setRegisterDao(registerDao);
	
	Users users=new Users();
	users.setEnabled(true);
	
	registerServiceImpl.updateUser(users);
	assertTrue(true);
}

catch(Exception e)
{
	assertTrue(false);
}
}


@Test
public void testinsertRow()
{
    RegisterServiceImpl registerServiceImpl=new RegisterServiceImpl();
    registerServiceImpl.setRegisterDao(registerDao);
    Plans plan = new Plans();
    plan.setMaxBooks(10);
    plan.setMaxDays(10);
    plan.setPlanName("Gold");
    plan.setPrice(300);
    when(registerDao.getPlanById(1)).thenReturn(plan);
    Users users=new Users();
    users.setUserName("a");
   users.setPlanId(1);
doNothing().when(registerDao).insertUser(users);
doNothing().when(registerDao).insertUser(users);
 registerServiceImpl.insertUser(users);
 assertTrue(true);
    
    
}

@Test
public void insertRoles()
{
RegisterServiceImpl registerServiceImpl=new RegisterServiceImpl();
registerServiceImpl.setRegisterDao(registerDao);
Roles roles = new Roles();
Users users=new Users();
users.setUserName("abc");
roles.setRoleId("ROLE_USER");
roles.setUserName(users.getUserName());
registerServiceImpl.insertRoles(roles.getUserName());
assertTrue(true);

    
}
}