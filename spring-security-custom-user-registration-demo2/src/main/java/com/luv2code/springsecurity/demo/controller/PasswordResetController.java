package com.luv2code.springsecurity.demo.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springsecurity.demo.entity.User;
import com.luv2code.springsecurity.demo.service.UserService;
import com.luv2code.springsecurity.demo.user.PasswordReset;

@Controller
@RequestMapping("/ForgotPassword")
public class PasswordResetController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor trimmer=new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, trimmer);
		
	}

	Logger logger=Logger.getLogger(getClass().getName());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping("/ResetPassword")
	public String restPasswordPage(Model theModel) {
		
		theModel.addAttribute("passwordReset", new PasswordReset());
		
		return "password-reset";
		
	}

	
	@RequestMapping("/ResetPasswordProcessed")
	public String restPasswordConfirmation(@Valid @ModelAttribute("passwordReset") PasswordReset userDetails,
			
			BindingResult bindingResult, Model theModel
			) {
		
		String userName=userDetails.getUserName();
		   
		   logger.info("UserName ===> "+userName);
		   
		   if(bindingResult.hasErrors()) {
			   return "password-reset";
		   }
		 
		   
		   User existing=userService.findByUserName(userName);
		   //logger.info("User obj  ===> "+existing); 
	
		   /*
		  logger.info("password maching?===> "+passwordEncoder.matches(userDetails.getOldPassword(), existing.getPassword()));
	 logger.info("raw password ===> "+userDetails.getOldPassword());
		   logger.info("existing password ===> "+existing.getPassword());
		   logger.info("Inserted passwordReset obj  ===> "+userDetails);
		   
		   logger.info("existing email ===> "+existing.getEmail());
		   logger.info("Inserted passwordReset email  ===> "+userDetails.getEmail());
		   
		   logger.info("email maching?===> "+((userDetails.getEmail().toString()).equals(existing.getEmail().toString())));
		  
		   */
		   if(existing==null) {
			   
			   logger.warning("User  ===> "+userName+" Not registered"); 
			   theModel.addAttribute("passwordReset", new PasswordReset());
			   
			   theModel.addAttribute("userNotExistError","You have entered Wrong User Id!");
			   return "password-reset";
		   }
		   else {
		   
         if(!(passwordEncoder.matches(userDetails.getOldPassword(), existing.getPassword()))) {
			   
			   logger.info("Entered Password  ===> "+userDetails.getOldPassword()); 
			   logger.info("User "+userName+" Password  ===> "+existing.getPassword()); 
			   theModel.addAttribute("passwordReset", new PasswordReset());
			   
			   theModel.addAttribute("oldPasswordMismatch","You have entered Wrong Old Password!");
			   return "password-reset";
		   }
         
         if(!((userDetails.getEmail().toString()).equals(existing.getEmail().toString()))) {
        	 logger.info("User "+userName+" Email  ===> "+existing.getEmail()); 
			   theModel.addAttribute("passwordReset", new PasswordReset());
			   
			   theModel.addAttribute("emailMismatch","You have entered Wrong Email id!");
			   return "password-reset";
		   }
         
         userService.updatePassword(userDetails);
         
        
         logger.info("Password Successfully Changed"); 
		return "pasword-reset-success";
		   }
	}
	
}
