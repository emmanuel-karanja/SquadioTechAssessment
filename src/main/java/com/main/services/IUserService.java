package com.main.services;

import java.util.List;
import java.util.Optional;

import com.main.dtos.UserResponse;
import com.main.entities.User;

public interface IUserService {

	UserResponse findUserByUserName(String email);
	List<UserResponse> getAll();
	User getById(long id);
}

