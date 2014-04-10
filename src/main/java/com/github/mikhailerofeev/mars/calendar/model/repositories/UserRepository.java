package com.github.mikhailerofeev.mars.calendar.model.repositories;

import com.github.mikhailerofeev.mars.calendar.model.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mikhail Erofeev https://github.com/MikhailErofeev
 * @since 10.04.14
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  
  List<User> findUsersByName(String name);

}
