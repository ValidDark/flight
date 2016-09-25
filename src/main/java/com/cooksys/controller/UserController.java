package com.cooksys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cooksys.entity.User;
import com.cooksys.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("user")
public class UserController {
	
	
	private final UserService service;
	
	@Autowired
	public UserController(UserService service) {
		super();
		this.service = service;
	}
	
////////////////////////////////////////////////////////////////	
	
	@RequestMapping(method = RequestMethod.GET)
	public List<User> index() {
		return this.service.index();
	}

	@RequestMapping(method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		return this.service.create(user);
	}

	///////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public User update(@PathVariable Integer id, @RequestBody User UserToUpdate) {
		return this.service.update(id, UserToUpdate);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public User delete(@PathVariable User id) {
		return this.service.delete(id);
	}

	///////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public User read(@PathVariable String username) {
		return this.service.find(username);
	}
}
	