package com.github.mikhailerofeev.mars.calendar.server;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 09.04.14
 * <p/>
 * filter to allow Cross-Origin Resource Sharing
 * http://spring.io/guides/gs/rest-service-cors/
 */
@Component
public class SimpleCORSFilter implements Filter {

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
    chain.doFilter(req, res);
  }

  public void init(FilterConfig filterConfig) {
  }

  public void destroy() {
  }

}
