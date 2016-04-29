/*
 * 
 */
package in.co.companyname.controllers;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class MySuccessHandler.
 * This class is responsible for distinguishing whether the admin has logged in
 * or normal user has logged in. 
 */
public class MySuccessHandler implements AuthenticationSuccessHandler {
    

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(MySuccessHandler.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.web.authentication.AuthenticationSuccessHandler
     * #onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse,
     * org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication
                .getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            LOGGER.info("Request for Admin Login Sending Admin home page");
            response.sendRedirect("admin");
        } else if (roles.contains("ROLE_USER")) {
            LOGGER.info("Request for User Login Sending User home page");
            response.sendRedirect("user");
        } else {
            LOGGER.info("Access Denied");
            response.sendRedirect("403page");
        }
    } 
}