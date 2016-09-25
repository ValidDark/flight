package com.cooksys.service;

import java.util.List;
import com.cooksys.entity.User;

public interface UserService {

	List<User> index();

	User create(User user);

////////////////////////////////////////////////////////////////
	
	User read(User user);

	User update(Integer id, User userToUpdate);

	User delete(User user);

////////////////////////////////////////////////////////////////
	
	User find(String username);

}
