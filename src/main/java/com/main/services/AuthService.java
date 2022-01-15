package com.main.services;

import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.dtos.ApiResponse;
import com.main.dtos.AuthResponse;
import com.main.dtos.LoginRequest;
import com.main.entities.User;
import com.main.exceptions.BadRequestException;
import com.main.exceptions.ResourceNotFoundException;
import com.main.repositories.UserRepository;
import com.main.security.JwtTokenProvider;

@Service
public class AuthService implements IAuthService {
	
	Logger logger=LoggerFactory.getLogger(AuthService.class);

	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

	@Override
	public AuthResponse authenticate(LoginRequest loginRequest) {
	
		logger.info("login attempt:"+loginRequest.getUserName()+" at: "+LocalDateTime.now().toString());
		AuthResponse response=new AuthResponse();
		try {
		User user=userRepository.findByUserName(loginRequest.getUserName())
				      .orElseThrow(()-> new ResourceNotFoundException("User","Username",loginRequest.getUserName()));
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
       //check if the user is already logged in
        if(user.isLoggedIn()) {
        	throw new BadRequestException(new ApiResponse(false,"User is already logged in."));
        }
        user.setLoggedIn(true);
        logger.info("Is User Logged In: "+String.valueOf(user.isLoggedIn()));
        userRepository.save(user);//set the logged in flag
        String jwt = tokenProvider.generateToken(authentication);
        
        logger.info("login success:"+loginRequest.getUserName()+" at: "+new Date());
        
        response.setUserName(user.getUserName());
        response.setIsAdmin(user.isAdmin());
        response.setJwtToken(jwt);
		}catch(Exception e) {
			logger.error(loginRequest.getUserName()+" login failed.");
			throw new BadRequestException(new ApiResponse(false,"Login failed.User is already logged in"));
		}
        
        return response;
        
	}
	
	@Override
	public String logout(long userId) {
		logger.info("logout attempt:"+userId+"at: "+new Date());
	    String result="";
		try {
		User user=userRepository.getById(userId).orElseThrow(()-> new BadRequestException(new ApiResponse(false,"Logout failed.")));
		
		user.setLoggedIn(false);
	
		logger.info(user.getUserName()+" logout successful at :"+LocalDateTime.now().toString());
		userRepository.save(user);
		logger.info("logout success:"+userId+"at: "+new Date());
		result="User Logged Out Successfully.";
		}catch(Exception e) {
		   logger.error("Exception: "+e.getMessage());
		   result=e.getMessage();
		   throw new BadRequestException(new ApiResponse(false,e.getMessage()));
		   
		}
		
		return result;
   
	}

}
