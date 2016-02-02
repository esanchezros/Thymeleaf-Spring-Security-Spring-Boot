package thymeleafexamples.springsecurity.springboot.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (container -> {
            ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/error.html");
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error.html");
            ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/error.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error.html");
            ErrorPage error503Page = new ErrorPage(HttpStatus.SERVICE_UNAVAILABLE, "/error.html");

            container.addErrorPages(error400Page, error401Page, error403Page, error404Page, error500Page, error503Page);
        });
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}
