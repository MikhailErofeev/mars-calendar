package com.github.mikhailerofeev.mars.calendar.rest.controllers;

import com.github.mikhailerofeev.mars.calendar.model.services.UserService;
import com.github.mikhailerofeev.mars.calendar.rest.dto.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */

@RestController
@Deprecated //for testing purposes
public class GreetingsController {

  @Autowired
  UserService userService;


  @RequestMapping(value = "/rest/v1/greeting", method = RequestMethod.GET)
  public
  @ResponseBody
  Greeting greeting(Authentication authentication, WebRequest webRequest) {
    Connection<?> connection = ProviderSignInUtils.getConnection(webRequest);
    String userName = authentication == null ? "Anonymous" : authentication.getName();
    return new Greeting(String.format("Hello, %s!", userName));    
  }


  @RequestMapping(value = "/rest/v1/greeting", method = RequestMethod.POST)
  @ResponseBody
  @Secured("ROLE_USER")
  public void setGreeting(Greeting greeting) {
  }
}
