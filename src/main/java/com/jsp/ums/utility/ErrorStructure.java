package com.jsp.ums.utility;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ErrorStructure<T> {
		
		private int status;
		private String messege;
		private T rootCause;
}
