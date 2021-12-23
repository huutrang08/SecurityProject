package com.rest.Services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rest.Entity.Users;
import com.rest.Repository.UserRepository;
import com.rest.Services.UserServices;
@Service
public class UserServiceImpl implements UserServices {
	@Autowired
     UserRepository userRepository;
    
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Override
	public Boolean login(Users user) {
		
		Users users = findByusername(user.getUsername());
		if (user != null) {
			if (encoder.matches(user.getPassword(), users.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Users findByusername(String name) {
		return userRepository.findByUsername(name);
	}

	@Override
	public <S extends Users> S save(S entity) {
		return userRepository.save(entity);
	}

	@Override
	public List<Users> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<Users> findById(Integer id) {
		return userRepository.findById(id);
	}
	
	
}
