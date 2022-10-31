package com.cohort.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cohort.repository.UserRepository;
import com.cohort.service.UserService;
import com.cohort.to.User;

@Controller
public class UserController {

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(String username, String password, String firstName, String lastName, String email)
			throws Exception {
		System.out.println("inside register method.." + username + ", " + password + ", " + firstName + ", " + lastName
				+ ", " + email);

		UserRepository userRepository = new UserRepository();

		User user = new User(username, password, firstName, lastName, email);

		userRepository.save(user);

		

		return "index";
	}

	@RequestMapping("/login")
	public ModelAndView login(HttpSession session, String username, String password) throws Exception {

		System.out.println("The username is " + username + ", the password is " + password);

		if (username.equals("") || password.equals("")) {// This would return the index or login page back to user if
															// either username or
															// password or both are incorrect...

			ModelAndView modelAndView = new ModelAndView("index");
			modelAndView.addObject("loginErrorMessage", "Please do Not submit empty fields!!");

			return modelAndView;
		}

		UserService userService = new UserService();

		User user = userService.validateUser(username, password);
		
		if (user != null) {
			session.setAttribute("loggedinuser", user);
			ModelAndView modelAndView = new ModelAndView("welcome");
			modelAndView.addObject("userData", user);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("index");
			modelAndView.addObject("loginErrorMessage", "Incorrect Credentials, please try again!!");

			return modelAndView;
		}

	}

	@RequestMapping("delete/{uid}")
	public ModelAndView deleteUser(@PathVariable String uid) throws Exception {

		System.out.println("the userid is " + uid);

		UserService userService = new UserService();

		userService.deleteUser(uid);

		List<User> users = userService.getAllUsers();

		ModelAndView modelAndView = new ModelAndView("masterUserPage");

		modelAndView.addObject("allUsers", users);

		return modelAndView;
	}


	@RequestMapping("/update/{username}")
	public String updateUser(@PathVariable String username, HttpSession session) throws Exception {

		System.out.println("inside updateUser " + username);

		

		UserService userService = new UserService();

		User user = userService.getUser(username);
		session.setAttribute("user", user);
		
			
		

		return "updateUserPage";

	}
	
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

	@PostMapping("/update/accountUpdated")
	public ModelAndView update(HttpSession session,String username, String password, String firstName, String lastName, String email)
			throws Exception {
		System.out.println("inside account updated...");
		User user = (User)session.getAttribute("user");
		System.out.println(user.getUsername());
		System.out.println(username);
		UserService userservice = new UserService();
		userservice.updateUser(password, firstName, lastName, email, user.getUsername());
		
		User currentUser = (User)session.getAttribute("loggedinuser");
		if(user.getUsername().equals(currentUser.getUsername())) {
			session.removeAttribute("loggedinuser");
			session.setAttribute("loggedinuser", user);
		}
		ModelAndView modelAndView = new ModelAndView("welcome");
		modelAndView.addObject("accountUpdated", "Your account has been updated ");
		return modelAndView;
	}

}