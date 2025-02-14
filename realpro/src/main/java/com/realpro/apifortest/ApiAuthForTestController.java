package com.realpro.apifortest;

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
import org.springframework.web.bind.annotation.RestController;

import com.realpro.authenticate.jwt.JwtUtils;
import com.realpro.authenticate.payload.request.LoginRequest;
import com.realpro.authenticate.payload.response.UserInfoResponse;
import com.realpro.authenticate.services.UserDetailsImpl;
import com.realpro.repository.master.UserRepository;

import jakarta.validation.Valid;

//@CrossOrigin(origins = "*",  maxAge = 3600) 
//origins = "http://localhost:4200"
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true") 
@RestController
@RequestMapping("api/auth")
public class ApiAuthForTestController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	//@PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(new UserInfoResponse(
					userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
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
