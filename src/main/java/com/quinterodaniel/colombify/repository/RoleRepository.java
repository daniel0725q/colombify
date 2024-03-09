package com.quinterodaniel.colombify.repository;

import com.quinterodaniel.colombify.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
