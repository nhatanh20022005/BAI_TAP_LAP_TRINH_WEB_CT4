package MyPackage.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import MyPackage.DAO.IUserDAO;
import MyPackage.config.DBMYSQLConnect;
import MyPackage.model.UsersModel;

public class UserDAOImpl extends DBMYSQLConnect implements IUserDAO{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public List<UsersModel> findAll() {

		String sql = "select * from users";

		List<UsersModel> list = new ArrayList<>(); // Tạo 1 list để truyền dữ liệu

		try {
			conn = super.getDatabaseConnection(); // Kết nối database
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) { // Next từng DÒNG cuối tới cuối bảng
				list.add(
						new UsersModel(
									rs.getInt("id"), 
									rs.getString("username"),
									rs.getString("email"), 
									rs.getString("fullname"), 
									rs.getString("images"),
									rs.getString("password"))); // Add vào list
			}
			return list;

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public UsersModel findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(UsersModel user) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public UsersModel get(String username) {
		 String sql = "SELECT * FROM users WHERE username = ? ";
		 try {
		 
		 conn = new DBMYSQLConnect().getDatabaseConnection();
		 ps = conn.prepareStatement(sql);
		 ps.setString(1, username);
		 rs = ps.executeQuery();
		 while (rs.next()) {
		 UsersModel user = new UsersModel();
		 user.setId(rs.getInt("id"));
		 user.setUsername(rs.getString("username"));
		 user.setEmail(rs.getString("email"));
		 user.setFullname(rs.getString("fullname"));
		 user.setImages(rs.getString("images"));
		 user.setPassword(rs.getString("password"));
		
		 return user;}
		 } catch (Exception e) {e.printStackTrace();}
		return null;
		
	}
	public static void main(String[] args) {
	    UserDAOImpl userDao = new UserDAOImpl();
	    List<UsersModel> list = userDao.findAll();

	    for (UsersModel user : list) {
	        System.out.println(user);
	    }
	}
}