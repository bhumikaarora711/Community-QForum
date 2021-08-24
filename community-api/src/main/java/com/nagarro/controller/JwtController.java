package com.nagarro.controller;
/*
 * @author Bhumika_arora
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.models.JwtRequest;
import com.nagarro.models.JwtResponse;
import com.nagarro.services.CustomUserDetailService;
import com.nagarro.utilities.JwtUtil;

/*
 * This class consist of Post API to authenticate username and password.
 */
@CrossOrigin(origins = "*")
@RestController
public class JwtController {

	private static final String BAD_CREDENTIALS = "bad credentials";
	private static final String USER_NOT_FOUND = "User not found";
	private static final String TOKEN = "/token";

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	/*
	 * In this method username and password will passed in body and using Authentication Manager will authenticate the credentials. 
	 * If credentials are valid, JWT token would be created by JWTTokenUtil and provided to client.
	 */
	@RequestMapping(value = TOKEN, method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody final JwtRequest jwtRequest) throws Exception {
		System.out.println(jwtRequest);

		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (final UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception(USER_NOT_FOUND);
		} catch (final BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception(BAD_CREDENTIALS);
		}

		final UserDetails userDetails = this.customUserDetailService.loadUserByUsername(jwtRequest.getUsername());

		final String token = this.jwtUtil.generateToken(userDetails);
		// System.out.println("JWT" + token);

		return ResponseEntity.ok(new JwtResponse(token));
	}

}
