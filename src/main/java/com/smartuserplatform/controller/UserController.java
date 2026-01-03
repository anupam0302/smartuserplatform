package com.smartuserplatform.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartuserplatform.dto.CreateUserRequest;
import com.smartuserplatform.dto.PageResponse;
import com.smartuserplatform.dto.UserResponse;
import com.smartuserplatform.mapper.UserMapper;
import com.smartuserplatform.model.User;
import com.smartuserplatform.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

	private final UserService service;
	private final UserMapper responseMapper;

	UserController(UserService service, UserMapper responseMapper) {
		this.service = service;
		this.responseMapper = responseMapper;
	}

	@PostMapping
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest userRequest) {

		User user = service.createUser(userRequest);
		UserResponse response = responseMapper.toUserResponse(user);

		response.add(linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel());
		response.add(linkTo(methodOn(UserController.class).getUser(user.getId())).withRel("all-users"));

		return ResponseEntity.ok(response);

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUser(@Min(1) @PathVariable long id) {
		User user = service.getUser(id);
		UserResponse response = responseMapper.toUserResponse(user);

		response.add(linkTo(methodOn(UserController.class).getUser(id)).withSelfRel());

		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<PageResponse<UserResponse>> listUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		List<User> users = service.listUsers(page, size);

		long total = service.retriveUsersCount();

		List<UserResponse> responses = users.stream().map(u -> {

			UserResponse r = responseMapper.toUserResponse(u);
			r.add(linkTo(methodOn(UserController.class)).withSelfRel());

			return r;
		}).toList();

		return ResponseEntity.ok(new PageResponse<>(responses, page, size, total));

	}

}
