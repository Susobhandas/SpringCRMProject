package com.luv2code.springsecurity.demo.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springsecurity.demo.entity.User;
import com.luv2code.springsecurity.demo.service.UserService;
import com.luv2code.springsecurity.demo.user.CrmUser;

@Controller
@RequestMapping("/Register")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor trim=new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, trim);
	}
	
	
	@RequestMapping("/RegistrationForm")
	public String showRegisterForm(Model theModel) {
		theModel.addAttribute("crmUser", new CrmUser());
		return "registration-form";
		
	}
	
	

	@RequestMapping("/RegistrationProcessed")
	public String RegistrationProcessed(@Valid @ModelAttribute("crmUser") CrmUser crmuser,
			BindingResult theBindResult,
			Model theModle) {
		
	       Logger logger=Logger.getLogger(getClass().getName());
	   
		   String userName=crmuser.getUserName();
		   
		   logger.info("UserName ===> "+userName);
		   
		   if(theBindResult.hasErrors()) {
			   
			   return "registration-form";
		   }
		
		   
		   User existing=userService.findByUserName(userName);
		   
		   if(existing!=null) {
			   
			   logger.warning("UserName ===> "+userName+" Already Exists");
			   theModle.addAttribute("crmUser", new CrmUser());
			   theModle.addAttribute("registrationError", "User  "+userName+" Already Exists");
			   return "registration-form";
		   }
		   
		   userService.save(crmuser);
		   theModle.addAttribute("userName",userName);
		   logger.info("Successfully created user: " + userName);
		   return "registration-confirmation";
		
	}

}
