package com.busyqa.course.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.busyqa.course.jpa.User;
import com.busyqa.course.service.UserService;

@RestController
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;

	@GetMapping(value = "/users")
    public List<User> listUsers() {
		
		logger.debug("List Users");
		
        return this.userService.listUsers();
    }
	
	@GetMapping(value = "/users/{id}")
    public User getUser(@PathVariable("id") int id){

		logger.debug("Get User");
		
		return this.userService.findUser(id).orElse(null);
    }	
	
	
	@PostMapping("/users")
    public User createUser(@RequestBody User user){

		logger.debug("Create User");
		
		return this.userService.createUser(user);
    }	

	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") int id){
		
		logger.debug("Update User");
		
		return this.userService.updateUser(user,id);
	}	
	
	@DeleteMapping(value = "/users/{id}")
	public void deleteUser(@PathVariable("id") int id){

		logger.debug("Delete User");

		this.userService.deleteUser(id);	
	}	
}
