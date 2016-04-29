package in.co.companyname.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import in.co.companyname.db.model.Plans;
import in.co.companyname.db.model.Subscription;
import in.co.companyname.service.subscriptionplan.SubscriptionPlanService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class SubscriptionPlansControllerTest {
    
    /** The Subscription plan service. */
    @Mock
    private SubscriptionPlanService subscriptionPlanService;
    
    @InjectMocks
    private SubscriptionPlansController subscriptionPlansController =
    new SubscriptionPlansController();

    @Mock
    MockHttpSession session;

    @Mock
    MockHttpServletRequest request;
    
    private MockMvc mockMvc;
    
    private Plans plans = new Plans();
    private List<Plans> plansList = new ArrayList<Plans>();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(subscriptionPlansController).build();

        Date date = new Date(new java.util.Date().getTime());

        
        plans.setMaxBooks(10);
        plans.setMaxDays(10);
        plans.setPlanId(10);
        plans.setPlanName("planName");
        plans.setPrice(10);
        
        plansList.add(plans);
        
    }
    
    @Test
    public void testGetForm() throws Exception {
        session.setAttribute("uname", "yash.s.khatri@gmail.com");

        mockMvc.perform(get("/addSubscriptionPlans.htm")
                .session(session))
                .andExpect(view().name("addSubscriptionPlans"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("addSubscriptionPlans"));

    }

   @Test
    public void testListplans() throws Exception {
       session.setAttribute("uname", "yash.s.khatri@gmail.com");

       mockMvc.perform(get("/subscriptionPlansList.htm")
               .session(session))
               .andExpect(view().name("subscriptionPlansList"))
               .andExpect(status().isOk())
               .andExpect(forwardedUrl("subscriptionPlansList"));
       when( subscriptionPlanService.getPlansList()).thenReturn(plansList);
    }

   @Test
    public void testInsertUser() throws Exception {
       session.setAttribute("uname", "yash.s.khatri@gmail.com");

       mockMvc.perform(get("/subsriptionPlansInsert.htm")
               .session(session))
               .andExpect(view().name("redirect:subscriptionPlansList.htm"))
               .andExpect(status().is(302));
              doNothing().when( subscriptionPlanService).insertPlans(plans);
        
    }

   @Test
    public void testEditPlans() throws Exception {
       mockMvc.perform(get("/subsriptionPlansEdit.htm")
               .param("planId", "1")
               .session(session))
               .andExpect(view().name("addSubscriptionPlans"))
               .andExpect(status().isOk())
               .andExpect(forwardedUrl("addSubscriptionPlans"));
       when( subscriptionPlanService.getPlanById(1)).thenReturn(plans);
    }

  @Test
    public void testDeleteUser() throws Exception {
      mockMvc.perform(get("/subsriptionPlansDelete.htm")
              .param("planId", "1"))
              .andExpect(view().name("redirect:subscriptionPlansList.htm"))
              .andExpect(status().is(302));
             doNothing().when( subscriptionPlanService).deletePlan(1);
    }

}
