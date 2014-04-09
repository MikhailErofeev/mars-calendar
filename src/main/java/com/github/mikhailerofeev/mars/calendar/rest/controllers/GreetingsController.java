package com.github.mikhailerofeev.mars.calendar.rest.controllers;

import com.github.mikhailerofeev.mars.calendar.rest.dto.Greeting;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */

@Controller
@Deprecated //for testing purposes
public class GreetingsController {

  @RequestMapping("/rest/v1/greeting")
  public
  @ResponseBody
  @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
  Greeting greeting() {
    return new Greeting("Hello, worlds!");
  }
}
