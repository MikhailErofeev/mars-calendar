package com.github.mikhailerofeev.mars.calendar.rest.controllers;

import com.github.mikhailerofeev.mars.calendar.model.services.UserService;
import com.github.mikhailerofeev.mars.calendar.model.values.time.LinkedMonth;
import com.github.mikhailerofeev.mars.calendar.model.values.time.PlanetMonth;
import com.github.mikhailerofeev.mars.calendar.rest.dto.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 */

@RestController
public class MonthController {

  @RequestMapping(value = "/rest/v1/month/{originalPlanet}/{alternativePlanet}/{year}/{month}", method = RequestMethod.GET)
  public
  @ResponseBody
  List<LinkedMonth> getMonth(@PathVariable String originalPlanet,
                                    @PathVariable String alternativePlanet,
                                    @PathVariable int year,
                                    @PathVariable int month) {
      List<LinkedMonth> retVal = new ArrayList<LinkedMonth>();
      // month can be overcapped (month +1 > 12 months)
      LinkedMonth currMonth = new LinkedMonth(originalPlanet, alternativePlanet, year, month);
      LinkedMonth prevMonth = new LinkedMonth(originalPlanet, alternativePlanet, year, month - 1);
      LinkedMonth nextMonth = new LinkedMonth(originalPlanet, alternativePlanet, year, month + 1);

      retVal.add(prevMonth);
      retVal.add(currMonth);
      retVal.add(nextMonth);

      return retVal;
  }


}
