package com.realpro.authenticate.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.login.CredentialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.realpro.authenticate.jwt.JwtUtils;
import com.realpro.authenticate.payload.request.LoginRequest;
import com.realpro.authenticate.payload.response.UserInfoResponse;
import com.realpro.authenticate.services.UserDetailsImpl;
import com.realpro.repository.master.UserRepository;

//@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils; 
	
	@PostMapping(value ="/signin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
		
		try {
//		    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		    String encoded = encoder.encode("123456");
//		    System.out.println(encoded);
			
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		
		
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
		
		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
	
		
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.body(new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
		} catch (BadCredentialsException e) {
			System.out.println(e.getMessage());
		}
		return null;
	} 
	
	@GetMapping("/signin")
	public String loadAuthenticateUser() {
		return "authenticate/login";
	}
	
}
