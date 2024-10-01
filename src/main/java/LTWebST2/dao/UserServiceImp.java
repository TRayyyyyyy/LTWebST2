package LTWebST2.dao;

import LTWebST2.Models.UserModel;

public class UserServiceImp implements IUserService {

	IUserDao userDao = new UserDaoImp();
	@Override
	public UserModel findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.findByUsername(username);
		if(user != null & password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}
	

	@Override
	public void insert(UserModel user) {
		userDao.insert(user);
	}

	@Override
	public UserModel findByUsernameAndEmail(String username, String email) {
		return userDao.findByUsernameAndEmail(username, email);
	}

	@Override

	public boolean updatePassword(String username, String newpassword) {
		return userDao.updatePassword(username, newpassword);
	}

	@Override
	public boolean register(String fullname, String email, String username, String password) {
		if(userDao.checkExsitEmail(email)) {
			return false;
		}
		if(userDao.checkExistUserName(username)) {
			return false;
		}
		userDao.insert(new UserModel(fullname, email, username, password));
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExsitEmail(email);

	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUserName(username);

	}

}