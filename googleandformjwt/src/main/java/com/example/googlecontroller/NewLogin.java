package com.example.googlecontroller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//@EnableOAuth2Sso
public class NewLogin {
	//@Autowired
//	OAuth2RestTemplate fitbitOaut;
	@Autowired
	private OAuth2AuthorizedClientService clientService;
	@Autowired
	RestTemplate restTemplate;
	@GetMapping(value="/")
	public String data(Principal p) 
	{
		
		System.out.println(p);
		return "hello world";
	}
	@GetMapping(value="/login")
	public String data(Model model) {
		System.out.println("data coming");
		return "index";
	}
	@GetMapping(value="/getaccess")
	public Principal getdataww(Principal pri) {
		return pri;
	}
	

}
