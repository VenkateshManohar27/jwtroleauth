package com.ven.security.auth.jwtroleauth.repository;

import com.ven.security.auth.jwtroleauth.entities.Role;
import com.ven.security.auth.jwtroleauth.entities.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}
