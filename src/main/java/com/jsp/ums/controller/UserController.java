package com.jsp.ums.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.service.UserService;
import com.jsp.ums.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@Tag(name = "User", description = "User REST API's")
public class UserController {

	@Autowired
	private UserService userService;
	
	

	@Operation(description = "**User Registeration -** the API endpoint is used to register the user", responses = {
			@ApiResponse(responseCode = "201", description = "user successfully added", content = {
					@Content(schema = @Schema(implementation = UserResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "failed to add user") })	
	
	
	
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody  @Valid UserRequest userRequest) {
		return userService.saveUser(userRequest);
	}
	
	
	
	@Operation(description = "**Get user by userId -**"
			+ " the API endpoint is used to fetch the user based on the userId ", responses = {
					@ApiResponse(responseCode = "302", description = "user successfully fetched", content = {
							@Content(schema = @Schema(implementation = UserResponse.class)) }),
					@ApiResponse(responseCode = "404", description = "user not found") })
	
	
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> fetchById(@PathVariable int userId) {
		return userService.fetchById(userId);
	}
	
	
	
	
	@Operation(description = "**Update User -**"
			+ " the API endpoint is used to update the user data based on the userId", responses = {
					@ApiResponse(responseCode = "200", description = "user updated", content = {
							@Content(schema = @Schema(implementation = UserResponse.class)) }),
					@ApiResponse(responseCode = "400", description = "failed to update user") })
	
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@PathVariable int userId,@RequestBody UserRequest userRequest){
		return userService.updateUserById(userId,userRequest);
	}
	
	
	@Operation(description = "**Delete User -**"
			+ " the api endpoint is used to mark the user to be deleted", responses = {
					@ApiResponse(responseCode = "200", description = "user deleted", content = {
							@Content(schema = @Schema(implementation = UserResponse.class)) }),
					@ApiResponse(responseCode = "404", description = "user not found") })
	
	
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(@PathVariable int userId){
		return userService.deleteUserById(userId);
	}
	
	
	
}
