package pack.business;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pack.mybatis.SqlMapConfig;

public class ProcessDAO implements Processinter {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession(); // Configuration.xml 객체로 만들어 반환

	@Override
	public List<DataDto> selectDataAll() {
		SqlSession ss = factory.openSession();
		List<DataDto> list = null;
		
		try {
			list = ss.selectList("selectDataAll");
		} catch (Exception e) {
			System.out.println("selectDataAll() ERROR : " + e.getMessage());
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return list;
	}
	
	@Override
	public DataDto selectPart(String para) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean insertData(DataFormBean form) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean updateData(DataFormBean form) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean deleteData(String id) {
		// TODO Auto-generated method stub
		return false;
	}
}
