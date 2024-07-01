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
			SqlMapperInter inter = ss.getMapper(SqlMapperInter.class);
			list = inter.selectDataAll();
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
	public DataDto selectPart(String id) {
		SqlSession ss = factory.openSession();
		DataDto dto = null;
		try {
			SqlMapperInter inter = ss.getMapper(SqlMapperInter.class);
			dto = inter.selectDataPart(id);
		} catch (Exception e) {
			System.out.println("selectPart() ERROR : " + e.getMessage());
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return dto;
	}

	@Override
	public boolean insertData(DataFormBean form) {
		boolean b = false;

		SqlSession ss = factory.openSession();
		try {
			SqlMapperInter inter = ss.getMapper(SqlMapperInter.class);
			if(inter.insertData(form) > 0) {
				b = true;
			}
			ss.commit();
		} catch (Exception e) {
			System.out.println("insertData() ERROR : " + e.getMessage());
			ss.rollback();
		} finally {
			if (ss != null) {
				ss.close();
			}
		}

		return b;
	}

	@Override
	public boolean updateData(DataFormBean form) {
		boolean b = false;
		
		SqlSession ss = factory.openSession();
		try {
			SqlMapperInter inter = ss.getMapper(SqlMapperInter.class);
			DataDto dto = inter.selectDataPart(form.getId());
			// 비밀번호 비교 후 수정 여부 판단
			if(dto.getPasswd().equals(form.getPasswd())) {
				// 수정 처리
				if(inter.updateData(form) > 0) {
					b = true;
					ss.commit();
				}
			}
		} catch (Exception e) {
			System.out.println("updateData() ERROR : " + e.getMessage());
			ss.rollback();
		} finally {
			if (ss != null) {
				ss.close();
			}
		}

		return b;
	}

	@Override
	public boolean deleteData(String id) {
		boolean b = false;

		SqlSession ss = factory.openSession();
		try {
			SqlMapperInter inter = ss.getMapper(SqlMapperInter.class);
			int cou = inter.deleteData(id);
			if(cou > 0) {
				b = true;
			}
			ss.commit();
		} catch (Exception e) {
			System.out.println("insertData() ERROR : " + e.getMessage());
			ss.rollback();
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return b;
	}
}
