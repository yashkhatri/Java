package in.co.companyname.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ErrorHandlerControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ErrorHandlerController errorHandlerController = new ErrorHandlerController(); 




    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(errorHandlerController).build();
    }


    @Test
    public void testGe403denied() throws Exception {
        mockMvc.perform(get("/403page"))
        .andExpect(view().name("redirect:login?denied"))
        .andExpect(status().is(302));

    }


    @Test
    public void testError404() throws Exception {
        mockMvc.perform(get("/404"))
        .andExpect(view().name("pageNotFound"))
        .andExpect(status().isOk());
    }


    @Test
    public void testError400() throws Exception {
        mockMvc.perform(get("/400"))
        .andExpect(view().name("errorPage"))
        .andExpect(status().isOk());
    }

    @Test
    public void testError405() throws Exception {
        mockMvc.perform(get("/405"))
        .andExpect(view().name("errorPage"))
        .andExpect(status().isOk());
    }

}
