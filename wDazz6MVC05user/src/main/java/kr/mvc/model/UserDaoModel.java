package kr.mvc.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.mvc.controller.UserForm;

//Controller 클래스의 요청을 받아 DB 연동 처리 담당
public class UserDaoModel {
	private SqlSessionFactory ssf = SqlMapConfig.getSqlSession();
	
	public UserDaoModel() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDto findUser(String userid) {
		UserDto dto = null;
		SqlSession ss = ssf.openSession();
		
		try {
			dto = ss.selectOne("findUser", userid);
		} catch (Exception e) {
			System.out.println("findUser ERROR : " + e.getMessage());
		} finally {
			ss.close();
		}
		
		return dto;
	}
	
	public ArrayList<UserDto> getUserDataAll() {
		List<UserDto> list = null;
		SqlSession ss = ssf.openSession();
		
		try {
			list = ss.selectList("selectDataAll");
		} catch (Exception e) {
			System.out.println("getUserAll ERROR : " + e.getMessage());
		} finally {
			ss.close();
		}
		
		return (ArrayList<UserDto>)list;
	}
	
	public int insertData(UserForm uf) {
		int result = 0;
		SqlSession ss = ssf.openSession();
		
		try {
			result = ss.insert("insertData", uf);
			ss.commit();
		} catch (Exception e) {
			System.out.println("insertData ERROR : " + e.getMessage());
			ss.rollback();
		} finally {
			ss.close();
		}
		
		return result;
	}
	
	public int updateData(UserForm uf) {
		int result = 0;
		SqlSession ss = ssf.openSession();
		
		try {
			result = ss.insert("updateData", uf);
			ss.commit();
		} catch (Exception e) {
			System.out.println("updateData ERROR : " + e.getMessage());
			ss.rollback();
		} finally {
			ss.close();
		}
		
		return result;
	}
	
	public int deleteData(String userid) {
		int result = 0;
		SqlSession ss = ssf.openSession();
		
		try {
			result = ss.insert("deleteData", userid);
			ss.commit();
		} catch (Exception e) {
			System.out.println("deleteData ERROR : " + e.getMessage());
			ss.rollback();
		} finally {
			ss.close();
		}
		
		return result;
	}
}
