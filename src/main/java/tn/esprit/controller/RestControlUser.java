package tn.esprit.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.User;
import tn.esprit.services.EmailServiceImpl;
import tn.esprit.services.IUserService;

@RestController
public class RestControlUser {

	private static final Logger logger = Logger.getLogger(RestControlUser.class);

	@Autowired
	IUserService iUserService;
	@Autowired
	EmailServiceImpl emailServiceImpl;

	/*
	 * http://localhost:8082/SpringMVC/servlet/addUser {"id": 1,"firstName":
	 * "aymen","lastName": "jeddey", "email": "aymen@esprit.tn", "username":
	 * "aymenjeddey", "password": "123456","userRole": null}
	 */
	@PostMapping("/addUser")
	@ResponseBody
	public User addUser(@RequestBody User user) {
		logger.info("---- ajouter User Méthode ---");
		iUserService.addUser(user);
		emailServiceImpl.sendSimpleMessage(user.getEmail(), "Confirmation Code",
				"your confirmation code is :" + user.getConfirmCode());
		return user;
	}

	@PostMapping("/loginUser")
	@ResponseBody
	public User loginUser(@RequestBody User userLogin) {
		logger.info(userLogin.getUsername() + "-----" + userLogin.getPassword());

		User user = iUserService.autentificateUser(userLogin.getUsername(), userLogin.getPassword());
		return user;
	}

	@PostMapping("/confirmUser")
	@ResponseBody
	public User confirmUser(@RequestBody User userConfirm) {
		User user = iUserService.confirmUser(userConfirm.getUsername(), userConfirm.getConfirmCode());
		emailServiceImpl.sendSimpleMessage(user.getEmail(), "Confirmation Account", "Your Account is confirmed :");
		return user;
	}

	@PostMapping("/updateUser")
	@ResponseBody
	public User updateUser(@RequestBody User user) {
		logger.info("---- update User Méthode ---");
		iUserService.updateUser(user);
		return user;
	}

	@DeleteMapping(value = "/deleteUser/{idUser}")
	public User deleteUser(@PathVariable("idUser") int idUser) {
		logger.info("---- delete User Méthode ---");
		return iUserService.deleteUser(idUser);
	}

	// http://localhost:8082/SpringMVC/servlet/findUser/1
	@GetMapping(value = "/findUser/{idUser}")
	public User findUser(@PathVariable("idUser") int idUser) {
		logger.info("---- find User Méthode ---");
		return iUserService.findUser(idUser);
	}
	
	// http://localhost:8082/SpringMVC/servlet/findAllUser/
	@GetMapping(value = "/findAllUser")
	public List<User> findAllUser() {
		logger.info("---- find All User Méthode ---");
		return iUserService.findAllUser();
	}

	@PostMapping("/banUser")
	@ResponseBody
	public User banUser(@RequestBody User user) {
		logger.info("---- Ban User Méthode ---");
		iUserService.BanUser(user);
		return user;
	}

	@PostMapping("/unBanuser")
	@ResponseBody
	public User unBanUser(@RequestBody User user) {
		logger.info("---- UnBan User Méthode ---");
		iUserService.unBanUser(user);
		return user;
	}

	@PostMapping("/lockUser")
	@ResponseBody
	public User lockUser(@RequestBody User user) {
		iUserService.lockUser(user);
		return user;
	}

	
	@Scheduled(fixedDelay = 100000)
	@GetMapping(value = "/unlockUser")
	public void unlockUser() {
		iUserService.unlockUser();
	}

	@GetMapping(value = "/resetPassword")
	public User resetPassword(@RequestParam("username") String username) {
		User user = iUserService.resetPassword(username);
		user.getConfirmCode();
		emailServiceImpl.sendSimpleMessage(user.getEmail(), "Reset Password",
				"Rest password code :" + user.getConfirmCode());
		return user;
	}

	@PostMapping("/updatePassword")
	@ResponseBody
	public User updatePassword(@RequestBody User user) {
		return iUserService.updatePassword(user.getUsername(), user.getPassword(), user.getConfirmCode());
	}

	@GetMapping(value = "/statLockUnlockUser")
	public List statLockUnlockUser() {
		return iUserService.statLockUnlockUser();
	}
	
	@GetMapping(value = "/userByFirstLastname")
	public List userByFirstLastname(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName) {
		return iUserService.getUserByFirstNameOrLastname(firstName, lastName);
	}

}
