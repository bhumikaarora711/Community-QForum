package com.nagarro.services;

/*
 * @author Bhumika_arora
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.models.CustomUserDetails;
import com.nagarro.repository.UserRepository;

/*
 *  This class implements the UserDetailsService interface in order to search the username,
 *  password and GrantedAuthorities for given user.
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
	
	private static final String USER_NOT_FOUND = "User not found";

	@Autowired
	private UserRepository userRepo;

	/*
	 *  This method is for getting the user details from the database when authenticating the
	 * user details provided by the user, this method returns a hardcoded user. 
	 */
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		final com.nagarro.models.User user = this.userRepo.findUserByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(USER_NOT_FOUND);
		} else {
			return new CustomUserDetails(user);
		}
	}

}
