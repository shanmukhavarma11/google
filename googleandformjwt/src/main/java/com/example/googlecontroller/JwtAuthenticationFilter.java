package com.example.googlecontroller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenGenerator jwt;
	//@Autowired
	//private OAuth2AuthorizedClientService clientService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String username=null;
		/*OAuth2AuthorizedClient user1 = clientService.loadAuthorizedClient("google", authentication.getName() );
	     org.springframework.security.oauth2.core.OAuth2AccessToken accessToken = user1.getAccessToken();
		//DefaultOidcUser user= (DefaultOidcUser)authentication.getPrincipal();
		OAuth2AuthenticatedPrincipal user =(OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
System.out.println(accessToken.getTokenValue());
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
			    
			    .setAudience(Collections.singletonList("815315389079-f9tfvf3bcaqnpjf4njhp11i74p2j99up.apps.googleusercontent.com"))
			 
			    .build();
		String username=null;
boolean daw=true;
			GoogleIdToken idToken = verifier.verify(accessToken.getTokenValue());
			if (idToken != null) {
			  Payload payload = idToken.getPayload();
             daw=false;
			 
			  String userId = payload.getSubject();
			  System.out.println("User ID: " + userId);

			   username = payload.getEmail();
			   UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,null );
               authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               logger.info("authenticated user " + username + ", setting security context");
               SecurityContextHolder.getContext().setAuthentication(authentication);
			  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			  String name = (String) payload.get("name");
			  String pictureUrl = (String) payload.get("picture");
			  String locale = (String) payload.get("locale");
			  String familyName = (String) payload.get("family_name");
			  String givenName = (String) payload.get("given_name");

			  // Use or store profile information
			  // ...

			} else {
			  System.out.println("Invalid ID token.");
			}

		*/
				
		System.out.println("Hai ");
		String token =request.getParameter("token");
		System.out.println(request.getParameter("token"));
		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println(requestTokenHeader);System.out.println(token);
		if((requestTokenHeader!=null)) {
			try {
				if(requestTokenHeader.startsWith("Bearer ") ||(requestTokenHeader.startsWith("Bearer"))) {
				token = requestTokenHeader.substring(7);
				System.out.println(token);
			username =jwt.getUsernameFromToken(token);
				}else {
					username =jwt.getUsernameFromToken(token);
					System.out.println(username);
				}
			}
			catch(Exception e) {
				username=e.getMessage();
			}
		}
		else if((token!=null) ) {
			System.out.println(token);
			System.out.println("$$$$$$$$$$$");
		username =jwt.getUsernameFromToken(token);
		}
		else {
			System.out.println("No token");
		}
		
		 if ((username != null) && (SecurityContextHolder.getContext().getAuthentication() == null)  ) {

	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	            if (jwt.validateToken(token, userDetails)) {
	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,null );
	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                logger.info("authenticated user " + username + ", setting security context");
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	            }
	        }

	       filterChain.doFilter(request, response);
		
	}
}
