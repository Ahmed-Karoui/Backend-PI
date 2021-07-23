package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.User;

public interface IUserService {

	public int addUser(User user);

	public User deleteUser(int idUser);

	public int updateUser(User user);

	public List<User> findAllUser();

	public User findUser(int idUser);

	public User autentificateUser(String username, String password);

	public User confirmUser(String username, String confirmCode);
	
	public int BanUser (User user);
	
	public int unBanUser (User user);

	public void unlockUser();
	
	public void lockUser(User user);
	
	public User resetPassword(String username);

	
	public User updatePassword(String username,String password, String confirmationCode) ;

	public List statLockUnlockUser();

	
	public List getUserByFirstNameOrLastname(String firstName, String lastName) ;

	
	

}
