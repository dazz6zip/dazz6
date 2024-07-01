package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class SangpumModel {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession(); // static
	
	public List<SangpumDto> selectDataAll() {
		List<SangpumDto> list = null;
		
		SqlSession ss = factory.openSession();
		list = ss.selectList("selectDataAll");
		ss.close();
		
		return list;
	}
}
