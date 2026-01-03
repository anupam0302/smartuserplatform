package com.smartuserplatform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@Size(min = 8, max = 12)
	private String password;
	
	public CreateUserRequest(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
}
