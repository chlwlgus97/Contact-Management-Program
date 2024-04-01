// D: Data T: Transfer O: Object
package dto;

//	select 한 결과를 저장하는 클래스
public class ContactDto {
	private int id;
	private String name;
	private String address;
	private String phone;
	private String groupnm;
	
	public ContactDto(int id, String name, String address, String phone, String groupnm) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.groupnm = groupnm;
		
		
	}

	public ContactDto() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGroupnm() {
		return groupnm;
	}

	public void setGroupnm(String groupnm) {
		this.groupnm = groupnm;
	}

	@Override
	public String toString() {
		return "회원이름=" + name + ", 주소=" + address + ", 전화번호=" + phone + ", 그룹이름=" + groupnm;
	}
	
	
}	
	
	


