package com.luv2code.springsecurity.demo.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.luv2code.springsecurity.demo.validation.FieldMatch;
//import com.luv2code.springsecurity.demo.validation.ValidateEmail;

@Component
@FieldMatch.List(value = { @FieldMatch(first = "password", second = "matchingPassword" , message="Password mismatch") })
public class PasswordReset {
	
	@NotNull(message="* Is Required")
	@Size(min=1, message="* Is Required")
	private String userName;
	
	@NotNull(message="* Is Required")
	@Size(min=1, message="* Is Required")
	private String oldPassword;
	
	@NotNull(message="* Is Required")
	@Size(min=1, message="* Is Required")
	private String password;
	
	@NotNull(message="* Is Required")
	@Size(min=1, message="* Is Required")
	private String matchingPassword;
	

	@NotNull(message="* Is Required")
	@Size(min=1, message="* Is Required")
	private String email;

	
	

	public PasswordReset() {
		
	}

	

	public PasswordReset(String userName,
			String oldPassword,
			String password,
			String matchingPassword,
			String email) {
		super();
		this.userName = userName;
		this.oldPassword = oldPassword;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.email = email;
	}



	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getOldPassword() {
		return oldPassword;
	}


	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getMatchingPassword() {
		return matchingPassword;
	}


	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	

}
