package MyPackage.DAO;

import java.util.List;
import MyPackage.model.UsersModel;

public interface IUserDAO {
	
	List <UsersModel> findAll();
	UsersModel findById(int id);
	void insert (UsersModel user);
	UsersModel get (String username);
	

}






	
	
	


