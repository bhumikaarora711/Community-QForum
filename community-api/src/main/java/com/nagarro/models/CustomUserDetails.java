package com.nagarro.models;

/*
 * @author Bhumika_arora 
 */
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/*
 * 
 */
public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final com.nagarro.models.User user;

	public CustomUserDetails(final com.nagarro.models.User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/*
		 * SimpleGrantedAuthority simpleGrantedAuthority = new
		 * SimpleGrantedAuthority(user.getFirstname()); return
		 * List.of(simpleGrantedAuthority);
		 */
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
