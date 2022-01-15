package com.main.services;

import com.main.dtos.AuthResponse;
import com.main.dtos.LoginRequest;

public interface IAuthService {

	public AuthResponse authenticate(LoginRequest request);
}
