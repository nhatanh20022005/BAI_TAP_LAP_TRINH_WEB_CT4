package MyPackage.Service.impl;

import MyPackage.DAO.IUserDAO;
import MyPackage.DAO.impl.UserDAOImpl;
import MyPackage.Service.UserService;
import MyPackage.model.UsersModel;

public class UserServiceImpl implements UserService{
	 
	IUserDAO userDao = new UserDAOImpl();
    
	@Override
	public UsersModel login(String username, String password) {
		UsersModel user = this.get(username);
		 if (user != null && password.equals(user.getPassword())) {
		 return user;
	}
		 return null;
	}


	@Override
	public UsersModel get(String username) {
		return userDao.get(username);
		
	}

}