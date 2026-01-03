package com.smartuserplatform.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.smartuserplatform.dto.CreateUserRequest;
import com.smartuserplatform.exception.UserNotFoundException;
import com.smartuserplatform.model.User;
import com.smartuserplatform.repository.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Service
@Validated
public class UserService {
	
	private final UserRepository userRepository;	
	private final AtomicLong idGenerator = new AtomicLong(1);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	public User createUser(@Valid CreateUserRequest userRequest) {
		Long id = idGenerator.getAndIncrement();
		
		User user = new User(id, userRequest.getName(), userRequest.getEmail());
		userRepository.saveUser(user);
		
		return user;		
	}
	
	public User getUser(@Min(1) long id) {
		User user  = userRepository.getUser(id);
		
		if(user == null) {
			throw new UserNotFoundException("User not found for id: "+id);
		}
		return user;		
	}
	
	public int retriveUsersCount() {
		return userRepository.storeSize();
	}
	
	public List<User> listUsers(int page, int size) {
        return userRepository.retriveUsersList()
                .stream()
                .skip((long) page * size)
                .limit(size)
                .toList();
    }


}
