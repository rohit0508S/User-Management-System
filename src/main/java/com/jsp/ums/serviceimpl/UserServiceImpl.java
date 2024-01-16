package com.jsp.ums.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.ums.entity.User;
import com.jsp.ums.exception.UserNotFoundByIdException;
import com.jsp.ums.repository.UserRepository;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.service.UserService;
import com.jsp.ums.utility.ErrorStructure;
import com.jsp.ums.utility.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResponseStructure<UserResponse> responseStructure;

	private User mapToUser(UserRequest userRequest) {
		return User.builder().userName(userRequest.getUserName())
				.userEmail(userRequest.getUserEmail())
				.userPassword(userRequest.getUserPassword()).build();
	}

	private UserResponse mapToUSerResponse(User user) {
		return UserResponse.builder().userId(user.getUserId()).userName(user.getUserName())
				.userEmail(user.getUserEmail()).build();
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest) {
		User user = mapToUser(userRequest);
		user = userRepository.save(user);
		UserResponse userResponse = mapToUSerResponse(user);

		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessege("User data Saved Successfully...!");
		responseStructure.setData(userResponse);
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> fetchById(int userId) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			UserResponse userResponse = mapToUSerResponse(user);

			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessege("Data Found");
			responseStructure.setData(userResponse);
			return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.FOUND);
		}else {
			throw new UserNotFoundByIdException("Failed to find the userId");
		}	
	}

//	@Override
//	public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(int userId, UserRequest userRequest) {
//		User user = userRepository.findById(userId).map(u -> {
//			u.setUserId(userId);
//			u.setUserName(userRequest.getUserName());
//			u.setUserEmail(userRequest.getUserEmail());
//			u.setUserPassword(userRequest.getUserPassword());
//			UserResponse userResponse = mapToUSerResponse(u);
//			return userRepository.save(u);
//
//		}).orElseThrow(() -> new RuntimeException("Given userId->" + userId + " Not Found"));
//
//		responseStructure.setStatus(HttpStatus.OK.value());
//		responseStructure.setMessege("Data Updated...!");
//		responseStructure.setData(mapToUSerResponse(user));
//		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);
//	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(int userId, UserRequest userRequest) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundByIdException("Given userId->" + userId + " Not Found"));
		user.setUserId(userId);
		user.setUserName(userRequest.getUserName());
		user.setUserEmail(userRequest.getUserEmail());
		user.setUserPassword(userRequest.getUserPassword());
		
		UserResponse userResponse=mapToUSerResponse(user);
		userRepository.save(user);

		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessege("data Updated...!");
		responseStructure.setData(userResponse);
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);
	}

//	@Override
//	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(int userId) {
//		Optional<User> id = userRepository.findById(userId);
//		if(id.isPresent()) {
//			userRepository.deleteById(userId);
//			responseStructure.setStatus(HttpStatus.OK.value());
//			responseStructure.setMessege("User deleted Successfully...!");
//			responseStructure.setData(null);
//			return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.OK);
//		}
//		return null;
//	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(int userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundByIdException("Given userId->" + userId + " Not Found"));
			
		userRepository.delete(user);
		UserResponse mapToUSerResponse = mapToUSerResponse(user);
			
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessege("Data Deleted Successfully...!");
		responseStructure.setData(mapToUSerResponse);
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);
	}
}
