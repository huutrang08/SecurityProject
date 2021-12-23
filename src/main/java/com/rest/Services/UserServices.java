package com.rest.Services;

import java.util.List;
import java.util.Optional;

import com.rest.Entity.Users;

public interface UserServices {

	Optional<Users> findById(Integer id);

	List<Users> findAll();

	<S extends Users> S save(S entity);

	Users findByusername(String name);

	Boolean login(Users user);

	void deleteAll();
	void delete(Users entity);

}
