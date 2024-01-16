package com.jsp.ums.utility;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ResponseStructure<T> {
	
	private int status;
	private String messege;
	private T data;
	
}
