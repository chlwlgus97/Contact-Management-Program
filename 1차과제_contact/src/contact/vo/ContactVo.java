package contact.vo;

//	�Ѹ��� ȸ���� ������ �����ϴ� Ŭ����
public class ContactVo {
	private String name; // �̸�
	private String address; // �ּ�
	private String phoneNumber; // ����ó
	private String group; // �׷� : ����, ģ��, ȸ��, ��Ÿ
	
	public ContactVo() {
//		����Ʈ ������ 
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
	
//	������ => �ʱ�ȭ"��" �ҰŶ�� : ������"��" ����
//	���� �����ҰŸ� get/set �޼ҵ� �ʿ�
	
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
		return " [�̸�=" + name + ", �ּ�=" + address + ", ��ȭ��ȣ=" + phoneNumber + ", �׷�=" + group
				+ "]";
	}
	
	
	
	
	
}
