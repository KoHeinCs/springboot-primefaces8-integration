package com.ashwetaw.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author heinhtet_aung
 * @created 9/26/2023
 **/
@Configuration
public class ServletContainerConfiguration  {

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                // Register the FacesServlet
                servletContext.addServlet("FacesServlet", FacesServlet.class)
                        .addMapping("*.xhtml");

                // Configure JSF to use its own scanner
                servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development"); // Set as needed
                servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", "true");
                servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");

            }
        };
    }





}
