package contact.vo;

//	한명의 회원의 정보를 저장하는 클래스
public class ContactVo {
	private String name; // 이름
	private String address; // 주소
	private String phoneNumber; // 연락처
	private String group; // 그룹 : 가족, 친구, 회사, 기타
	
	public ContactVo() {
//		디폴트 생성자 
	}
	
	public ContactVo(String name
				   , String address
				   , String phoneNumber
				   , String group) {
		this.name=name;
		this.address=address;
		this.phoneNumber=phoneNumber;
		this.group=group;
	}
	
//	생성자 => 초기화"만" 할거라면 : 생성자"만" 선언
//	값을 변경할거면 get/set 메소드 필요
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		 this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return " [이름=" + name + ", 주소=" + address + ", 전화번호=" + phoneNumber + ", 그룹=" + group
				+ "]";
	}
	
	
	
	
	
}
