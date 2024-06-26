package pack.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import pack.business.DataDTO;
import pack.mybatis.SqlMapConfig;

public class ProcessDAO {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession(); // Configuration.xml 객체로 만들어 반환

	public List<DataDTO> selectDataAll() throws SQLException {
		SqlSession sqlSession = factory.openSession(); // 세션 열기 (세션: 하나의 작업 단위, 웹에서의 세션과 다른 개념)
		List list = sqlSession.selectList("selectDataAll"); // DataMapper의 태그 id 입력
		// 반환값 기본은 list, ArrayList로 받고 싶으면 casting
		sqlSession.close(); // 반환받은 뒤 close()
		return list;
	}

	public DataDTO selectPart(String para) throws SQLException {
		SqlSession sqlSession = factory.openSession();
		DataDTO dto = sqlSession.selectOne("selectDataById", para);
		// 넘기는 para 값은 순서대로 넘어감
		// DataMapper에서 지정한 이름과 변수 이름 같을 필요 없음!
		sqlSession.close();
		return dto;
	}

	public void insData(DataForm form) throws SQLException {
		// INSERT, DELETE, UPDATE는 transaction 을 고려해야 함
		SqlSession sqlSession = factory.openSession(false); // false(dafault) : transaction 수동 처리
		sqlSession.insert("insertData", form);
		sqlSession.commit(); // commit()이나 rollback
		sqlSession.close();
	}

	public void upData(DataForm form) throws SQLException {
		SqlSession sqlSession = factory.openSession(true); // true : transaction 자동 처리
		sqlSession.update("updateData", form);
		sqlSession.close();
	}

	public boolean delData(int para) {
		boolean result = false;
		SqlSession sqlSession = factory.openSession(false);
		try {
			int cou = sqlSession.delete("deleteData", para);
			if (cou > 0) {
				result = true;
			}
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("delData() ERROR : " + e.getMessage());
			sqlSession.rollback();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}
}
