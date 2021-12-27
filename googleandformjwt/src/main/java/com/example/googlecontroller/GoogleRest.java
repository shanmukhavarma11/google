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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/auth")
public class GoogleRest {
	
	@Autowired
	JwtTokenGenerator jwt;
	
	@GetMapping(value="/custom-login")
	public String data1(@RequestParam("email") String email) {
String token=jwt.getToken(email);
		System.out.println("data coming");
		return token;
	}
	private static HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }
	@GetMapping("/getCalories")
	public Map getcal(@RequestParam("Startdateandtime") String Start,@RequestParam("Enddateandtime") String end, Authentication authentication,@RequestParam("token") String token) throws ParseException {
		System.out.println(Start+end);
		//OAuth2AuthorizedClient user1 = clientService.loadAuthorizedClient("google", authentication.getName() );
	     //org.springframework.security.oauth2.core.OAuth2AccessToken accessToken = user1.getAccessToken();
		//DefaultOidcUser user= (DefaultOidcUser)authentication.getPrincipal();
	//	OAuth2AuthenticatedPrincipal user =(OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
//System.out.println(accessToken.getTokenValue());
		DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z");
	    Date date = (Date) dateFormat.parse(Start);
	    long StartTime = (long) date.getTime()/1000;
	    Date date1 = (Date) dateFormat.parse(end);
	    long EndTime = (long) date1.getTime()/1000;
	    System.out.println(EndTime);
		String url="https://www.googleapis.com/fitness/v1/users/me/dataSources/derived:com.google.calories.expended:com.google.android.gms:merge_calories_expended/datasets/"+StartTime+"000000000-"+EndTime+"000000000";
	Map data=new HashMap();
	try {
	/*	HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<String> response = restTemplate.exchange(url+"?access_token="+accessToken.getTokenValue(),
                HttpMethod.GET, request, String.class);
        String user11 = response.getBody();
		data=restTemplate.getForObject(response, Map.class);
		System.out.println(data+"$$$$$$$$$$$$");*/
		
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + token); //accessToken can be the secret key you generate.
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //HttpEntity <String> entity = new HttpEntity <> (getHeaders(), headers);
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity <String> data2 = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        System.out.println(data2);
	}
	catch(Exception e) {
		
		System.out.println(e.getMessage());
	}
	
	return  data;
	}


}
