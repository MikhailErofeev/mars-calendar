package com.github.mikhailerofeev.mars.calendar.rest.controllers;

import com.github.mikhailerofeev.mars.calendar.model.services.UserService;
import com.github.mikhailerofeev.mars.calendar.model.values.time.PlanetMonth;
import com.github.mikhailerofeev.mars.calendar.rest.dto.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */

@RestController
public class MonthController {

  @RequestMapping(value = "/rest/v1/month/{planet}/{year}/{month}", method = RequestMethod.GET)
  public
  @ResponseBody
  Map<String, PlanetMonth> getMonth(@PathVariable String planet, @PathVariable int year, @PathVariable int month) {
      Map<String, PlanetMonth> retVal = new HashMap<String, PlanetMonth>();

      return retVal;
  }


}
