package com.github.mikhailerofeev.mars.calendar.rest.v1.controllers;

import com.github.mikhailerofeev.mars.calendar.rest.v1.dto.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */

@Controller
public class GreetingsController {

  @RequestMapping("/greeting")
  public
  @ResponseBody
  Greeting greeting() {
    return new Greeting("Hello, worlds!");
  }
}
