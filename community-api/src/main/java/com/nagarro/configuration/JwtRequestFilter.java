package com.nagarro.configuration;

/*
 * @author Bhumika_arora
 */
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nagarro.services.CustomUserDetailService;
import com.nagarro.utilities.JwtUtil;
/*
 * JwtRequestFilter class is executed for any incoming requests and validate JWT from the request
 *  and sets it in the context to indicate that logged in user is authenticated.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final String TOKEN_NOT_VALIDATED = "token not validated";
	private static final String BEARER = "Bearer ";
	private static final String AUTHORIZATION = "Authorization";

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain) throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader(AUTHORIZATION);

		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith(BEARER)) {

			jwtToken = requestTokenHeader.substring(7);

			try {
				username = this.jwtUtil.extractUsername(jwtToken);
			} catch (final Exception e) {
				e.printStackTrace();
			}

			final UserDetails userDetails = this.customUserDetailService.loadUserByUsername(username);

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			} else {
				System.out.println(TOKEN_NOT_VALIDATED);
			}
		}
		filterChain.doFilter(request, response);

	}

}
