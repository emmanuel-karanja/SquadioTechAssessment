package com.main.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greet")
public class TestGreetingsController {

	@GetMapping("/")
	public String sayHello() {
		return "I am a live!";
	}
}
