package pack.business;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SqlMapperInter {
	@Select("SELECT * FROM memberteb")
	public List<DataDto> selectDataAll();
	
	@Select("SELECT id, name, passwd, reg_date FROM memberteb WHERE id = #{id}")
	public DataDto selectDataPart(String id);
	
	@Insert("INSERT INTO memberteb VALUES(#{id}, #{name}, #{passwd}, now())")
	public int insertData(DataFormBean form);
	
	@Update("UPDATE memberteb SET name = #{name} WHERE id = #{id}")
	public int updateData(DataFormBean form);
	
	@Delete("DELETE FROM memberteb WHERE id = #{id}")
	public int deleteData(String id);
}
