package com.github.mikhailerofeev.mars.calendar.server;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
  public WebSecurityConfigurerAdapter applicationSecurity() {

    return new WebSecurityConfigurerAdapter() {
      @Override
      protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().anonymous();
      }
    };
  }

  public static void main(String[] args) {
    new SpringApplicationBuilder(Application.class)
        .showBanner(false)
        .run(args);
  }

}
