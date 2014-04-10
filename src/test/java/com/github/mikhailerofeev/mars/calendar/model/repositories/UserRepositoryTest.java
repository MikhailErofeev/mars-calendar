package com.github.mikhailerofeev.mars.calendar.model.repositories;

import com.github.mikhailerofeev.mars.calendar.model.entities.User;
import com.github.mikhailerofeev.mars.calendar.server.Application;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 10.04.14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@DirtiesContext
@ActiveProfiles("test")
public class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Test
  public void testRepository() {
    userRepository.save(new User("u1"));
    userRepository.save(new User("u2"));
    final List<User> users = Lists.newArrayList(userRepository.findAll());
    assertEquals(2, users.size());
  }
}
