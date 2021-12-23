package com.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.Entity.Users;
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String name);
}
