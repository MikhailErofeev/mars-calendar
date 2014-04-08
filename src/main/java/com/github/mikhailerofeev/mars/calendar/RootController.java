package com.github.mikhailerofeev.mars.calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */
@Controller
public class RootController {

  @RequestMapping("/*")
  public String greeting(Model model) {
    return "index";
  }
}
