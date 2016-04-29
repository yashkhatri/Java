package in.co.companyname.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CacheControlFilterTest {

    private MockMvc mockMvc;


    @InjectMocks
    private CacheControlFilter cacheControlFilter = new CacheControlFilter();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(cacheControlFilter).build();
    }
    @Test
    public void testDoFilter() throws Exception {

        MockHttpServletRequest request  = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();
        cacheControlFilter.doFilter(request, response, chain);

    }

    @Test
    public void testDestroy() {
        cacheControlFilter.destroy();
    }

    @Test
    public void testInit() throws ServletException {
        MockFilterConfig arg0 = new MockFilterConfig();
        cacheControlFilter.init(arg0);
    }

}
