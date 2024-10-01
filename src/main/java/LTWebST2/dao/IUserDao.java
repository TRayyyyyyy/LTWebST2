package LTWebST2.dao;

import java.util.List;

import LTWebST2.Models.UserModel;

public interface IUserDao  {
	UserModel findByUsername(String username);
	List <UserModel> findAll();
	void insert(UserModel user); // register
	boolean checkExsitEmail (String email);
	boolean checkExistUserName (String username);
	UserModel findByUsernameAndEmail(String username, String email);
    boolean updatePassword(String username, String password);
}
