package com.smartuserplatform.mapper;

import org.springframework.stereotype.Component;

import com.smartuserplatform.dto.UserResponse;
import com.smartuserplatform.model.User;

@Component
public class UserMapper {

	public UserResponse toUserResponse(User user) {
		return new UserResponse(user.getId(), user.getName(), user.getEmail());
	}
}
