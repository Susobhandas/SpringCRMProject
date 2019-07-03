package com.luv2code.springsecurity.demo.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.luv2code.springsecurity.demo.validation.FieldMatch;
import com.luv2code.springsecurity.demo.validation.ValidateEmail;


@FieldMatch.List(value =
{ @FieldMatch(first = "password", second = "matchingPassword" , message="Password mismatch") })
@Component
public class CrmUser {
  
	@NotNull(message="* Is Required")
	@Size(min=1, message="* Is Required")
	private String userName;
	
	@NotNull(message="* Is Required")
	@Size(min=1, message="* Is Required")
	private String password;
	
	@NotNull(message="* Is Required")
	@Size(min=1, message="* Is Required")
	private String matchingPassword;
	
	@NotNull(message="* Is Required")
	@Size(min=1, message="* Is Required")
	private String firstName;
	
	@NotNull(message="* Is Required")
	@Size(min=1, message="* Is Required")
	private String lastName;
	
	@ValidateEmail
	@NotNull(message="* Is Required")
	@Size(min=1, message="* Is Required")
	private String email;

	public CrmUser() {
		
	}

	public CrmUser(String userName, String password, String matchingPassword, String firstName, String lastName,
			String email) {

		this.userName = userName;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CrmUser [userName=" + userName + ", password=" + password + ", matchingPassword=" + matchingPassword
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	
	
	
}
