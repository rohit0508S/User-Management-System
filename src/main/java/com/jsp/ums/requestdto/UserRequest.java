package com.jsp.ums.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	
	@NotNull(message = "userName can not be Null")//NotBlank & NotNull  @NotEmpty(message = "email can not be blank")  @NotEmpty(message = "userName can not be Null")
    @Pattern(regexp = "^[A-Z][a-z]* [A-Z][a-z]*$", message = "Invalid username format")
	private String userName;
	@NotBlank(message = "email can not be blank")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String userEmail;
	@NotNull
	@NotBlank
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must"
			+ " contain at least one letter, one number, one special character")
	private String userPassword;
}
