package com.github.mikhailerofeev.mars.calendar.model.services;

import com.github.mikhailerofeev.mars.calendar.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 10.04.14
 */
@Service
public class UserService {

  @Autowired
  UserRepository userRepository;
}
