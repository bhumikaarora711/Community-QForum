package com.nagarro.controller;

/*
 * @author Bhumika_arora
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.models.User;
import com.nagarro.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	private static final String GETUSERS = "/getusers";
	private static final String GETUSERSCOUNT = "/getuserscount";
	private static final String USER_ALREADY_EXIST_WITH = "user already exist with ";
	private static final String SIGNUP = "/signup";

	@Autowired
	public UserRepository userRepo;

	@RequestMapping(value = SIGNUP, method = RequestMethod.POST)
	public User createUser(@RequestBody final User user) throws Exception {
		final String tempEmail = user.getEmail();
		final User userObj = userRepo.findUserByEmail(tempEmail);
		if (userObj != null) {
			throw new Exception(USER_ALREADY_EXIST_WITH + tempEmail);
		}
		return this.userRepo.save(user);
	}

	@RequestMapping(value = GETUSERS, method = RequestMethod.GET)
	public List<User> getUsers() {
		return this.userRepo.findAll();
	}

	@RequestMapping(value = GETUSERSCOUNT, method = RequestMethod.GET)
	public  int getUsersCount() {
		int count=0;
		final List<User> users= this.userRepo.findAll();
		for(@SuppressWarnings("unused") final User element:users) {
			count++;
		}
		return count;
	}
}
