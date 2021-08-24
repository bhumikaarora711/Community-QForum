package com.nagarro.models;

/*
 * @author Bhumika_arora 
 * @description JwtRequest object used as a request object for getting username and password from the client.
*/
public class JwtRequest {

	String username;
	String password;

	public JwtRequest(final String username, final String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public JwtRequest() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtRequest [username=" + username + ", password=" + password + "]";
	}

}
