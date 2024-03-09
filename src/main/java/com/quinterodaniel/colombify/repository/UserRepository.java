package com.quinterodaniel.colombify.repository;

import com.quinterodaniel.colombify.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    List<User> findAll();
}
