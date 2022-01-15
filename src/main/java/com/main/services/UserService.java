package com.main.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.dtos.UserResponse;
import com.main.entities.User;
import com.main.exceptions.ResourceNotFoundException;
import com.main.repositories.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private final UserRepository userRepository;

	
	public UserService(UserRepository userRepository) {
     this.userRepository=userRepository;
   
    }
	@Override
	public UserResponse findUserByUserName(String username) {
		// TODO Auto-generated method stub
		User user=userRepository.findByUserName(username)
		        .orElseThrow(()-> new ResourceNotFoundException("User","username",username));
        return convertToDTO(user);	
	}

	//we'd normally use a model mapper to do the conversion from DTO to Entity and vice versa
	private UserResponse convertToDTO(User user) {
        UserResponse response=new UserResponse();
        response.setId(user.getId());
        response.setUserName(user.getUserName());
        response.setLoggedIn(user.isLoggedIn());
      
        return response;
	}
	
	//
	@Override
	public List<UserResponse> getAll() {
		List<UserResponse> responses=null;
		try {
		List<User> users=userRepository.findAll();
		responses=users.stream()
                .map(a->convertToDTO(a))
				.collect(Collectors.toList());
		}catch(Exception e) {
			throw new ResourceNotFoundException("Users","users",e.getMessage());
		}
		
		return responses;
	}
	@Override
	public User getById(long id) {
		User user=userRepository.getById(id).orElseThrow(()->new ResourceNotFoundException("User","user",id));
		return user;
	}
}
