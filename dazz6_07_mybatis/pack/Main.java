package pack;

import java.util.List;

// MyBatis Framework 사용
public class Main {
	public static void main(String[] args) {
		ProcessDAO dao = ProcessDAO.getInstance(); // 싱글톤 패턴
		try {
			System.out.println("sangdata 추가");
//			DataDTO dataDto = new DataDTO();
//			dataDto.setCode("100");
//			dataDto.setSang("바나나");
//			dataDto.setSu("5");
//			dataDto.setDan("5000");
//			
//			dao.insData(dataDto);
			
			System.out.println("\nsangdata 수정");
//			DataDTO dataDto = new DataDTO();
//			dataDto.setCode("100");
//			dataDto.setSang("바나나우유");
//			dataDto.setSu("7");
//			
//			dao.upData(dataDto);
			
			System.out.println("\nsangdata 레코드 삭제");
			boolean b = dao.delData(100);
			if(b) { // delete 작업에 성공
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
			
			System.out.println("\nsangdata 전체 자료 읽기");
			List<DataDTO> list = dao.selectDataAll();
//			System.out.println(list.size());
			
			for(DataDTO s : list) {
				System.out.println(s.getCode() + " " +  s.getSang() + " " + s.getSu() + " " + s.getDan());
			}
			
			System.out.println("\nsangdata 자료 (한 개) 읽기");
			DataDTO dto = dao.selectPart("1");
			System.out.println(dto.getCode() + " " + dto.getSang() + " " + dto.getSu() + " " + dto.getDan());
		} catch (Exception e) {
			System.out.println("main ERROR : " + e.getMessage());
		}
	}
}