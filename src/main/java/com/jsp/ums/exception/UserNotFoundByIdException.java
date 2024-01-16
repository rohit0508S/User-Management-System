package com.jsp.ums.exception;

public class UserNotFoundByIdException extends RuntimeException{
		
		private String messege;

		public UserNotFoundByIdException(String messege) {
			this.messege = messege;
		}

		@Override
		public String getMessage() {
			return messege;
		}
		
		
}
