package com.github.mikhailerofeev.mars.calendar.rest.controllers;

import com.github.mikhailerofeev.mars.calendar.model.services.UserService;
import com.github.mikhailerofeev.mars.calendar.rest.dto.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */

@RestController
@Deprecated //for testing purposes
public class GreetingsController {

  @Autowired
  UserService userService;

  private Greeting greeting;

  @RequestMapping(value = "/rest/v1/greeting", method = RequestMethod.GET)
  public
  @ResponseBody
  Greeting greeting(Principal principal) {
    String userName = principal == null ? "Anonymous" : principal.getName();
    if (greeting == null) {
      return new Greeting(String.format("Hello, %s!", userName));
    } else {
      return greeting;
    }
  }


  @RequestMapping(value = "/rest/v1/greeting", method = RequestMethod.POST)
  public
  @ResponseBody
  @Secured("ROLE_USER")
  void setGreeting(Greeting greeting) {
    this.greeting = greeting;
  }
}
