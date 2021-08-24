package com.nagarro.models;

/*
 * @author Bhumika_arora 
 * @description JwtResponse used to create the response object send to the client.
 */
public class JwtResponse {
	String token;

	public String getToken() {
		return token;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + "]";
	}

	public JwtResponse(final String token) {
		super();
		this.token = token;
	}

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
