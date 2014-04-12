package com.github.mikhailerofeev.mars.calendar.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.*;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 11.04.14
 */
@Configuration
@Controller
public class SocialConfig {

  @Inject
  private Environment environment;

  @Inject
  private DataSource dataSource;

  @Inject
  private TextEncryptor textEncryptor;

  @Bean
  public ConnectController connectController() {
    return new ConnectController(connectionFactoryLocator(),
        connectionRepository());
  }

  @Bean
  @Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
  public ConnectionFactoryLocator connectionFactoryLocator() {
    ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
    final GoogleConnectionFactory gcf = new GoogleConnectionFactory(
        environment.getProperty("google.consumerKey"),
        environment.getProperty("google.consumerSecret"));
    registry.addConnectionFactory(gcf);
    final TwitterConnectionFactory tcf = new TwitterConnectionFactory
        (environment.getProperty("twitter.consumerKey"),
            environment.getProperty("twitter.consumerSecret"));
    registry.addConnectionFactory(tcf);
    return registry;
  }


  @Bean
  @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
  public ConnectionRepository connectionRepository() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
    }
    return usersConnectionRepository().createConnectionRepository(authentication.getName());
  }

  @Bean
  @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
  public Twitter twitter() {
    return connectionRepository().getPrimaryConnection(Twitter.class).getApi();
  }

  @Bean
  @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
  public Google google() {
    return connectionRepository().getPrimaryConnection(Google.class).getApi();
  }

  @Bean
  @Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
  public UsersConnectionRepository usersConnectionRepository() {
    return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), textEncryptor);
  }

  @Bean
  public ProviderSignInController providerSignInController() {
    final ProviderSignInController providerSignInController = new ProviderSignInController(connectionFactoryLocator(),
        usersConnectionRepository(), new SpringSecuritySignInAdapter());
    providerSignInController.setSignUpUrl("/register");
    return providerSignInController;
  }

  @Service
  public static class SpringSecuritySignInAdapter implements SignInAdapter {
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
      SecurityContextHolder.getContext().setAuthentication(
          new UsernamePasswordAuthenticationToken(localUserId, null, null));
      return null;
    }
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  @ResponseBody
  //could been extended to registration form
  public ModelAndView signupForm(WebRequest request) {
    Connection<?> connection = ProviderSignInUtils.getConnection(request);
    if (connection != null) {      
      final UserProfile userProfile = connection.fetchUserProfile();
      ProviderSignInUtils.handlePostSignUp(userProfile.getUsername(), request);
      return new ModelAndView("redirect:/?signup=success");
    } else {
      return new ModelAndView("redirect:/?signup=error");
    }
  }

  @Bean
  public TextEncryptor textEncryptor() {
    return Encryptors.noOpText();
  }
}
