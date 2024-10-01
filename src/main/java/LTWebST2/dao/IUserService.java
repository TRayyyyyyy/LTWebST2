package LTWebST2.dao;

import LTWebST2.Models.UserModel;


public interface IUserService {
	UserModel findByUsername(String username);
	UserModel login(String username, String password);
	void insert(UserModel user);
	UserModel findByUsernameAndEmail(String username, String email);
	boolean updatePassword(String username, String password);
	boolean register (String email, String password, String username, String fullname);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
}