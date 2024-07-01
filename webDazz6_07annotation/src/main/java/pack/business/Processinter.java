package pack.business;

import java.util.List;

// 인터페이스를 만들어서 관리자 역할
public interface Processinter {
	List<DataDto> selectDataAll();
	DataDto selectPart(String para);
	boolean insertData(DataFormBean form);
	boolean updateData(DataFormBean form);
	boolean deleteData(String id);
}
