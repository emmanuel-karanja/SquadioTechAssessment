//package com.main.services;
//
//import java.util.Date;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.Authentication;
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.main.dtos.AuthResponse;
//import com.main.dtos.LoginRequest;
//import com.main.entities.User;
//import com.main.exceptions.ResourceNotFoundException;
//import com.main.repositories.UserRepository;
//import com.main.security.JwtTokenProvider;
//
//@Service
//public class AuthService implements IAuthService {
//	
//	Logger logger=LoggerFactory.getLogger(AuthService.class);
//
//	@Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtTokenProvider tokenProvider;
//
//	@Override
//	public AuthResponse authenticate(LoginRequest loginRequest) {
//		
//		logger.info("logim attempt:"+loginRequest.getUserName()+" at: "+new Date());
//		User user=userRepository.findByUserName(loginRequest.getUserName())
//				      .orElseThrow(()-> new ResourceNotFoundException("User","Username",loginRequest.getUserName()));
//		Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUserName(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//       
//        user.setLoggedIn(true);
//        userRepository.save(user);//set the logged in flag
//        String jwt = tokenProvider.generateToken(authentication);
//        
//        logger.info("login success:"+loginRequest.getUserName()+" at: "+new Date());
//        AuthResponse response=new AuthResponse();
//        response.setUserName(user.getUserName());
//        response.setIsAdmin(user.isAdmin());
//        response.setJwtToken(jwt);
//        
//        return response;
//        
//	}
//	
//	public void Logout(Long userId) {
////		logger.info("logout attempt:"+userId+"at: "+new Date());
////		User user=userRepository.getById(userId) 
////				.orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));
////		
////		user.setLoggedIn(false);
////	
////		userRepository.save(user);
////		logger.info("logout success:"+userId+"at: "+new Date());
//
//	}
//
//}
