package com.example.googlecontroller;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

/*@Configuration
public class AttherateBean {


	@Bean
	OAuth2RestTemplate fitbitOAuthRestTemplate(UserInfoRestTemplateFactory uir) {
		return uir.getUserInfoRestTemplate();
	}
}
*/