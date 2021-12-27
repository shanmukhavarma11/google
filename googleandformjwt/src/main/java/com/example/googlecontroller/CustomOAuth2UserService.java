package com.example.googlecontroller;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;



import java.util.*;
@Service
public class CustomOAuth2UserService extends OidcUserService  {
	
	@Override
	public OidcUser loadUser(OidcUserRequest req) {
		System.out.println("*********88");
		OidcUser oid=super.loadUser(req);
		Map<String,Object> data=oid.getAttributes();
		System.out.println("I am here");
		
        return oid;
	}

}
