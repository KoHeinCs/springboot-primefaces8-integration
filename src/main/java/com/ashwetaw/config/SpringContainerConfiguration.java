package com.ashwetaw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author heinhtet_aung
 * @created 9/26/2023
 **/
@Configuration
public class SpringContainerConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("redirect:/welcome.xhtml");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
