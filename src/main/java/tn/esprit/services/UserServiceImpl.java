package tn.esprit.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import tn.esprit.entities.DataPoint;
import tn.esprit.entities.User;
import tn.esprit.entities.UserRole;
import tn.esprit.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public int addUser(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		Random random = new Random();
		user.setConfirmCode(String.valueOf(random.nextInt(10000000)));
		user.setConfirmed(false);
		if (user.getUserRole() == null)
			user.setUserRole(UserRole.USER);
		userRepository.save(user);
		return user.getId();
	}

	public User confirmUser(String username, String confirmCode) {
		User user = userRepository.getUserByUsername(username);
		if (user != null && confirmCode.equals(user.getConfirmCode())) {
			user.setConfirmed(true);
			user.setConfirmCode(null);
			userRepository.save(user);
			return user;
		} else
			return null;
	}

	public User autentificateUser(String username, String password) {
		User user = userRepository.getUserByUsername(username);

		boolean isPasswordMatches = new BCryptPasswordEncoder().matches(password, user.getPassword());
		if (!isPasswordMatches) // wrong password
			user = null;

		if (user != null && user.getIsConfirmed() && !user.getIsBlocked())
			return user;
		User userWithWrongPass = userRepository.getUserByUsername(username);

		if (userWithWrongPass != null) {
			userWithWrongPass.setWrongPassword(userWithWrongPass.getWrongPassword() + 1);
			if (userWithWrongPass.getWrongPassword() >= 3) {
				userWithWrongPass.setBlocked(true);
				userWithWrongPass.setBlockedDate(new Date());
			}
			userRepository.save(userWithWrongPass);
			return null;
		} else
			return null;
	}

	@Override
	public User deleteUser(int idUser) {
		User user = userRepository.findById(idUser).orElse(null);
		userRepository.delete(user);
		return user;
	}

	@Override
	public int updateUser(User user) {
		User oldUser = userRepository.findById(user.getId()).orElse(null);
		if (user.getFirstName() != null)
			oldUser.setFirstName(user.getFirstName());
		if (user.getLastName() != null)
			oldUser.setLastName(user.getLastName());
		if (user.getEmail() != null)
			oldUser.setEmail(user.getEmail());
		if (user.getUsername() != null)
			oldUser.setUsername(user.getUsername());
		if (user.getPassword() != null)
			oldUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		if (user.getUserRole() != null)
			oldUser.setUserRole(user.getUserRole());
		userRepository.save(oldUser);
		return 0;
	}

	@Override
	public User findUser(int idUser) {
		// TODO Auto-generated method stub
		return userRepository.findById(idUser).orElse(null);
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}

	@Override
	public int BanUser(User user) {
		User bannedUser = userRepository.findById(user.getId()).orElse(null);
		if (bannedUser != null) {
			bannedUser.setBanned(true);
			userRepository.save(bannedUser);
			return 1;
		}
		return 0;
	}

	@Override
	public int unBanUser(User user) {
		User unbannedUser = userRepository.findById(user.getId()).orElse(null);
		if (unbannedUser != null) {
			unbannedUser.setBanned(false);
			userRepository.save(unbannedUser);
			return 1;
		}
		return 0;
	}

	@Override
	public void lockUser(User user) {
		User oldUser = userRepository.getUserByUsername(user.getUsername());
		oldUser.setBlockedDate(new Date());
		oldUser.setBlocked(true);
		userRepository.save(oldUser);
	}

	@Override
	public void unlockUser() {
		List<User> users = userRepository.getLockedUsers(true);
		long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
		for (User user : users) {
			if (user.getBlockedDate() != null) {
				boolean moreThanDay = Math.abs(new Date().getTime() - user.getBlockedDate().getTime()) > MILLIS_PER_DAY;
				if (moreThanDay) {
					user.setBlockedDate(null);
					user.setBlocked(false);
					user.setWrongPassword(0);
					userRepository.save(user);
				}
			}
		}
	}

	@Override
	public User resetPassword(String username) {
		User user = userRepository.getUserByUsername(username);
		Random random = new Random();
		user.setConfirmCode(String.valueOf(random.nextInt(10000000)));
		userRepository.save(user);
		return user;
	}

	@Override
	public User updatePassword(String username, String password, String confirmationCode) {
		User user = userRepository.getUserByUsername(username);
		if (user.getConfirmCode().equals(confirmationCode)) {
			user.setPassword(new BCryptPasswordEncoder().encode(password));
			user.setConfirmCode(null);
			userRepository.save(user);
		}

		return user;
	}

	@Override
	public List<DataPoint> statLockUnlockUser() {
		List<DataPoint> list = new ArrayList<DataPoint>();
		for (Object object : userRepository.getLockUnlockUser()) {
			DataPoint dataPoint = new DataPoint();
			dataPoint.setLabel((((Object[]) object)[0]).toString());
			dataPoint.setY(Float.valueOf((((Object[]) object)[1]).toString()));
			list.add(dataPoint);
		}

		return list;
	}

	@Override
	public List<User> getUserByFirstNameOrLastname(String firstName, String lastName) {
		return userRepository.getUserByFirstNameOrLastname(firstName, lastName);
	}

}
