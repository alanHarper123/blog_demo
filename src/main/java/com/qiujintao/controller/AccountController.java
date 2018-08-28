package com.qiujintao.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.qiujintao.model.User;
import com.qiujintao.service.UserService;

@Controller
@SessionAttributes("url_prior_login")
public class AccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@GetMapping("/login")
	public String login(HttpServletRequest request, ModelMap modelAndView) {
		String referrer = request.getHeader("Referer");
		if(referrer!=null) {
			request.getSession().setAttribute("url_prior_login", referrer);
		}
		return "login";
	}
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		LOGGER.info(user.getPasswordHash());
		modelAndView.setViewName("registration");
		LOGGER.info("global errors: "+(bindingResult.getGlobalErrors()==null?null:bindingResult.getGlobalErrors().toString()));
		LOGGER.info("field errors: "+(bindingResult.getFieldErrors()==null?null:bindingResult.getFieldErrors().toString()));
		if(bindingResult.hasErrors()) {
			return modelAndView;
		}else {
			List<User> userExists = userService.findUserByEmail(user.getEmail());
			if (!userExists.isEmpty()) {
				bindingResult.
						rejectValue("email", "error.user",
								"There is already a user registered with the email provided");
			}
			userExists = userService.findUserByNickNanme(user.getNickName());
			if (!userExists.isEmpty()) {
				bindingResult
						.rejectValue("nickName", "error.user",
								"There is already a user registered with the nickName provided");
			}
		}
		LOGGER.info("global errors: "+(bindingResult.getGlobalErrors()==null?null:bindingResult.getGlobalErrors().toString()));
		LOGGER.info("field errors: "+(bindingResult.getFieldErrors()==null?null:bindingResult.getFieldErrors().toString()));
		LOGGER.info(user.getNickName()+", "+user.getEmail());
		if (!bindingResult.hasErrors()) {
			user.setActive(true);
			user.setPasswordHash(bCryptPasswordEncoder.encode(user.getPasswordHash()));
			user.setRole("USER");
			userService.saveUser(user);
			LOGGER.info("new user no."+user.getId()+" created");
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
		}
		
		return modelAndView;
	}
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName()).get(0);
		modelAndView.addObject("userName", "Welcome " + user.getNickName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
}
