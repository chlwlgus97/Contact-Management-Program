package contact.main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import contact.vo.ContactVo;

public class ContactEx {
//	��ü �޴� ���
	private static void showMenu() {
		System.out.println("==========================================");
		System.out.println("		ȸ���������α׷�		");
		System.out.println("==========================================");
		System.out.println("1. ȸ�� �߰�");
		System.out.println("2. ȸ�� ���");
		System.out.println("3. ȸ�� ����");
		System.out.println("4. ȸ�� ����");
		System.out.println("5. ����");
		System.out.print("��ȣ �Է� : ");
	}
	
//	ȸ�� �߰�
	static void memberInsert(
			HashMap<String, ContactVo> members
			, Scanner scan) {
//		1���� �Է� ������ �����ϴ� ���� ���� ���� => ContactVo
		System.out.println("==========================================");
		System.out.println("		ȸ�� �߰�		");
		System.out.println("==========================================");
		ContactVo member = new ContactVo(); // ����Ʈ ������ ���
		System.out.print("�̸� �Է�(�ʼ�) : "); // �ʼ� �Է�, ���� �ȵǰ� ó��
		member.setName(scan.next());
		System.out.print("�ּ� �Է�(�ʼ�) : "); // �ʼ� �Է�, ���� �ȵǰ� ó��
		member.setAddress(scan.next());
		System.out.print("��ȣ �Է�(�ʼ�) : "); // �ʼ� �Է�, 01012341234 ���ڷθ� �Է� ó��
		member.setPhoneNumber(scan.next());
		System.out.print("�׷� �Է�(�ʼ�) : "); // �ɼ� : ���� �� �ִ�.
		member.setGroup(scan.next());
		System.out.println("����");
//		1�� ȸ�� ���� �Է� �Ϸ�
//		�ؽ��� �߰� : key : ��ȭ��ȣ, value : ContavtVo object
		members.put(member.getPhoneNumber(), member);
		
//		�߰��� ȸ�� ���
//		System.out.println("�̸� : " + member.getName()
//						+ ", " + "�ּ� : " + member.getAddress()
//						+ ", " + "��ȭ��ȣ : " + member.getPhoneNumber()	
//						+ ", " + "�׷� : " + member.getGroup());
		System.out.println(member);
	}
	
//	ȸ�� ��� ���
	static void memberSelectAll(HashMap<String, ContactVo> members) {
//		Ű�� Set ����
		Set<String> keys = members.keySet();
//		iterator
		Iterator<String> it = keys.iterator();
		
//		while �ݺ�
		while(it.hasNext()) {
			String key = it.next(); 
			// members.get(key) value ���� : ContactVo object
			System.out.println(members.get(key).toString());
		}
	}
	
	public static void main(String[] args) {
//		ȸ������ ���� HashMap ����
//		key : phoneNumber, value : new ContactVo()
		HashMap<String, ContactVo> members = 
			new HashMap<String, ContactVo>();
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
		showMenu();
		String menuNumber = scan.next();
		System.out.println("������ ��ȣ : " + menuNumber);
		
		if(menuNumber.equals("1")) {
			memberInsert(members, scan) ;
		}
		if(menuNumber.equals("2")) {
			memberSelectAll(members) ;
		}
		if(menuNumber.equals("5")) {
			System.out.println("����˴ϴ�.");
			break;
		}
		
	}
		scan.close();
	}
}

