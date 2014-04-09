package com.github.mikhailerofeev.mars.calendar.server;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */
@Controller
@ComponentScan(basePackages = "com.github.mikhailerofeev.mars.calendar")
@EnableAutoConfiguration
public class Application extends WebMvcConfigurerAdapter {

  @Bean
  public ApplicationSecurity applicationSecurity() {
    return new ApplicationSecurity();
  }


  @Order(Ordered.LOWEST_PRECEDENCE - 8)
  protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      // this is obviously for a simple "login page" not a custom filter!
      http.authorizeRequests().anyRequest().anonymous();
    }
  }

  public static void main(String[] args) {
    new SpringApplicationBuilder(Application.class)
        .showBanner(false)
        .run(args);
  }

}
