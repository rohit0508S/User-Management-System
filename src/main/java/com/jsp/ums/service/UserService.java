package com.jsp.ums.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.ums.entity.User;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.utility.ResponseStructure;

public interface UserService {

	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest);

	public ResponseEntity<ResponseStructure<UserResponse>> fetchById(int userId);

	public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(int userId, UserRequest userRequest);

	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(int userId);

	

	
	
}
