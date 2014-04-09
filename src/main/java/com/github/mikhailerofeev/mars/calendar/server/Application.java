package com.github.mikhailerofeev.mars.calendar.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */
@ComponentScan(basePackages = "com.github.mikhailerofeev.mars.calendar")
@EnableAutoConfiguration
public class Application {

  public static void main(String[] args) {
//    final SpringApplication springApplication = new SpringApplication(Application.class, args);
//    springApplication.setShowBanner(false);
//    springApplication.run(args);
    SpringApplication.run(Application.class, args);
  }

}
