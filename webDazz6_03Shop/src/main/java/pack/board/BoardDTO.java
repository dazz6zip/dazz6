package pack.board;

import lombok.Data;

@Data // getter, setter, argument 없는 생성자
public class BoardDTO {
	private String name, pass, mail, title, cont, bip, bdate;
	private int num, readcnt, gnum, onum, nested;
}
