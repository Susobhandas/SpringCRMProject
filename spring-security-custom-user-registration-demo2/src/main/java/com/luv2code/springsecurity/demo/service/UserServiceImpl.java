package com.luv2code.springsecurity.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springsecurity.demo.dao.RoleDao;
import com.luv2code.springsecurity.demo.dao.UserDao;
import com.luv2code.springsecurity.demo.entity.Role;
import com.luv2code.springsecurity.demo.entity.User;
import com.luv2code.springsecurity.demo.user.CrmUser;
import com.luv2code.springsecurity.demo.user.PasswordReset;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userdao;
	
	@Autowired
	private RoleDao roledao;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userdao.findByUserName(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Invalid Username or Password");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRoleToAuthorities(user.getRoles()));
	}
	
	private  Collection<? extends GrantedAuthority> mapRoleToAuthorities(List<Role> roles) {
		// TODO Auto-generated method stub
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	

	@Transactional
	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userdao.findByUserName(userName);
	}
	
	@Transactional
	@Override
	public void save(CrmUser crmUser) {

		User user=new User();
		
		user.setUserName(crmUser.getUserName());
		user.setPassword(pwdEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());
		
		user.setRoles(Arrays.asList(roledao.findRoleByName("ROLE_EMPLOYEE")));
		
		userdao.save(user);
		
	}

	@Transactional
	@Override
	public void updatePassword(PasswordReset passwordReset) {
		User user=findByUserName(passwordReset.getUserName());
		user.setPassword(pwdEncoder.encode(passwordReset.getPassword()));
		
		userdao.updatePassword(user);
	}

}
