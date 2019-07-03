package com.luv2code.springsecurity.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.luv2code.springsecurity.demo.entity.User;
import com.luv2code.springsecurity.demo.user.CrmUser;
import com.luv2code.springsecurity.demo.user.PasswordReset;

public interface UserService extends UserDetailsService {
	
	User findByUserName(String userName);
	void save(CrmUser crmUser);
	void updatePassword(PasswordReset passwordReset);

}
