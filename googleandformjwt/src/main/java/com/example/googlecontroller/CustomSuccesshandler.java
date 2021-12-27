package com.example.googlecontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.*;
@Component
public class CustomSuccesshandler  extends  SimpleUrlAuthenticationSuccessHandler{
	
	@Autowired
	JwtTokenGenerator jwtgen;
	@Autowired
	private OAuth2AuthorizedClientService clientService;
	@Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException {
		
		System.out.println("@@@@@@@@@@@@@@@@@@22222222");
		System.out.println(request.getRequestURI());
		System.out.println(request.getHeader("Authorization"));
		if(response.isCommitted()) {
			return ;
		}
		OAuth2AuthorizedClient user1 = clientService.loadAuthorizedClient("google", authentication.getName() );
	     org.springframework.security.oauth2.core.OAuth2AccessToken accessToken = user1.getAccessToken();
		//DefaultOidcUser user= (DefaultOidcUser)authentication.getPrincipal();
		OAuth2AuthenticatedPrincipal user =(OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
System.out.println(accessToken.getTokenValue());
		Map<String,Object> data=user.getAttributes();
		 System.out.println(data);
		String email=(String)data.get("email");
		try {
			System.out.println("((((((((((((((((((((((");
		System.out.println(data.get("token"));
		}
		catch(Exception e) {
			
		}
		String token=jwtgen.getToken(email);
		//accessToken.getTokenValue()
		String access_token_url=UriComponentsBuilder.fromUriString("http://localhost:8080/").queryParam("token", token)
				.build().toUriString();
		getRedirectStrategy().sendRedirect(request, response, access_token_url);
	}

}
