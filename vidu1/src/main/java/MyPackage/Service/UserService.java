package MyPackage.Service;

import MyPackage.model.UsersModel;

public interface UserService {
	UsersModel login(String username, String password);
	UsersModel get(String username);
}