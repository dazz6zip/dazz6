package pack;

public class Para01Class { // jsp로 호출될 클래스
	private String msg;
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg + " (메시지 처리)";
	}
}
