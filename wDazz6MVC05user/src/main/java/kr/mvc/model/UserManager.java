package kr.mvc.model;

import java.util.ArrayList;

import kr.mvc.controller.UserForm;

// 여러 개의 DAO 클래스를 관리하는 것이 목적임
public class UserManager {
	// 싱글톤 사용
	private static UserManager um = new UserManager();
	public static UserManager instance() {
		return um;
	}
	
	private UserDaoModel getUserDaoModel() {
		return new UserDaoModel(); // 싱글톤 권장
	}
	
	public boolean login(String user_id, String user_password) {
		UserDto dto = getUserDaoModel().findUser(user_id);
		
		if (dto == null) {
			return false;
		}
		
		if (dto.getPassword().equals(user_password)) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<UserDto> getUserAll() {
		return getUserDaoModel().getUserDataAll();
	}
	
	public int insert(UserForm uf) {
		return getUserDaoModel().insertData(uf); 
	}
	
	public UserDto findUser(String userid) {
		return getUserDaoModel().findUser(userid);
	}
	
	public int update(UserForm uf) {
		return getUserDaoModel().updateData(uf); 
	}
	
	public int delete(String userid) {
		return getUserDaoModel().deleteData(userid); 
	}
}
