package com.cooksys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.entity.User;
import com.cooksys.repository.UserRepository;
import com.cooksys.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repo;
	
	@Autowired
	public UserServiceImpl(UserRepository repo) {
		super();
		this.repo = repo;
	}

///////////////////////////////////////////////////////////////////////////////
	
	@Override
	public List<User> index() {
		return this.repo.findAll();

	}

	@Override
	public User create(User user) {
		return this.repo.save(user);
	}

///////////////////////////////////////////////////////////////////////////////
	
	@Override
	public User read(User user) {
		return user;
	}

	@Override
	public User update(Integer id, User userToUpdate) {
		userToUpdate.setId(id);
		return this.repo.save(userToUpdate);
	}

	@Override
	public User delete(User user) {
		this.repo.delete(user);
		this.repo.saveAndFlush(user);
		return user;
	}

////////////////////////////////////////////////////////////////
	
	@Override
	public User find(String username) {
		
		List<User> tempList = this.repo.findAll();
		
		for(User user : tempList) {
	        if(user.getUserName().equals(username)) {
	        	return user;
	        }
	    }
		return null;
	}
	

}
