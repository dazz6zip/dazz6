package domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// JPA annotation
// https://www.icatpark.com/entry/JPA-%EA%B8%B0%EB%B3%B8-Annotation-%EC%A0%95%EB%A6%AC

// sangdata 테이블과 연결

@Entity // JPA를 사용해 테이블과 매핑할 클래스에 붙여 주는 어노테이션
@Table (name = "sangdata") // 테이블과 클래스 이름이 다를 때 사용하는 어노테이션
public class SangpumTable {
	@Id // PRIMARY KEY라는 의미
	@Column (name = "code") // 원본 칼럼 이름과 변수 이름이 같으면 쓰지 않아도 됨
	private int code;
	
	@Column (name = "sang", nullable = false) // NULL을 허용하지 않음, 원본이 NOT NULL일 경우 추가 권장
	private String sang;
	
	private int su;
	
	private int dan;

	
	public SangpumTable() { // JPA에서는 비어 있는 생성자라도 꼭 있어야 함
	
	}
	
	public SangpumTable(int code, String sang, int su, int dan) {
		this.code = code;
		this.sang = sang;
		this.su = su;
		this.dan = dan;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getSang() {
		return sang;
	}

	public void setSang(String sang) {
		this.sang = sang;
	}

	public int getSu() {
		return su;
	}

	public void setSu(int su) {
		this.su = su;
	}

	public int getDan() {
		return dan;
	}

	public void setDan(int dan) {
		this.dan = dan;
	}
	
	
}
