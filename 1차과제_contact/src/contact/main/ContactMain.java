package contact.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import contact.vo.ContactVo;

public class ContactMain {
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
	
//	# 1. ȸ�� �߰�
	static void memberInsert(
			HashMap<String, ContactVo> members
			, Scanner scan) {
//		1���� �Է� ������ �����ϴ� ���� ���� ���� => ContactVo
		System.out.println("==========================================");
		System.out.println("		ȸ�� �߰�		");
		System.out.println("==========================================");
		ContactVo member = new ContactVo();
		 System.out.println("-----------------�ʼ�----------------");
         System.out.print("�̸� �Է� : ");
         String name = "";

         while (true) {
             name = scan.next().replaceAll("\\s+", "");
             scan.nextLine();

             if (!name.isEmpty() && name.matches("[^0-9]+")) { 
                 break;
             } else {
                 System.out.println("�����̰ų� ������ �Է��� �� �����ϴ�. �ٽ� �Է��ϼ���.");
             }
         }
        
	    member.setName(name);
	    
	    System.out.print("�ּ� �Է� : ");
	    String address = scan.nextLine().replaceAll("\\s+", ""); 
	    while (address.isEmpty()) { 
	    	System.out.println("�ٽ� �Է��ϼ���.");
	        System.out.print("�ּ� �Է� : ");
	        address = scan.nextLine().replaceAll("\\s+", "");
	    }
	    member.setAddress(address);
	    
	    System.out.print("��ȭ��ȣ �Է� : ");
	    String phoneNumber = "";
	    while (true) {
	        phoneNumber = scan.next();
	        if (phoneNumber.matches("[0-9]+") && phoneNumber.length() == 11) {
	            break;
	        } else {
	            System.out.println("�ٽ� �Է��ϼ���.");
	            System.out.print("��ȭ��ȣ �Է� : ");
	        }
	    }
	    member.setPhoneNumber(phoneNumber);
	    
	    System.out.println("---------------�ɼ� ����---------------");
	    String group = scan.nextLine().replaceAll("\\s+", "");
	    while (group.isEmpty()) {
	        System.out.print("�׷� �Է� : ");
	        group = scan.nextLine().replaceAll("\\s+", "");
	    }

	    member.setGroup(group);
	    
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
	
//	# 2. ȸ�� ��� ���
	static void memberSelectAll(HashMap<String, ContactVo> members) {
		int memberCount = members.size(); // ȸ�� �� ���
	    System.out.println("�� " + memberCount + "���� ����ó");
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
	
//	# 3. ȸ�� ����	
	static void memberModify(HashMap<String, ContactVo> members, Scanner scan) {
	    System.out.println("-----------------�ʼ�----------------");
	    System.out.println("������ ȸ���� �̸��� �Է��ϼ���: ");
	    
	    String phonenumber = scan.next().replaceAll("\\s+", "");
	    Set<String> keys = members.keySet();
	    Iterator<String> it = keys.iterator();

	    ArrayList<ContactVo> matchingMembers = new ArrayList<>(); 

	    int count = 1; 

	    while (it.hasNext()) {
	        String key = it.next();
	        ContactVo jhMember = members.get(key);

	        if (jhMember.getName().equals(phonenumber)) {
	            matchingMembers.add(jhMember);
	        }
	    }

	    if (!matchingMembers.isEmpty()) {
	        System.out.println("�ߺ��� ȸ���� Ȥ�� ������ ȸ���� �Դϴ� : ");
	    	
	        for (ContactVo member : matchingMembers) {
	            System.out.println(count + ". " + member);
	            count++;
	        }

	        System.out.println("������ ȸ���� ��ȣ�� �Է��ϼ��� : ");
	        int selectedNumber = scan.nextInt();

	        if (selectedNumber >= 1 && selectedNumber <= matchingMembers.size()) {
	            ContactVo memberToModify = matchingMembers.get(selectedNumber - 1);

	            // ���� ����
	            System.out.println("-----------------�ʼ�----------------");
	            System.out.print("���ο� �̸� �Է� : ");
	            String name = "";

	            while (true) {
	                name = scan.next().replaceAll("\\s+", "");
	                scan.nextLine(); 

	                if (!name.isEmpty() && name.matches("[^0-9]+")) { 
	                    break;
	                } else {
	                    System.out.println("�����̰ų� ������ �Է��� �� �����ϴ�. �ٽ� �Է��ϼ���.");
	                }
	            }

	            memberToModify.setName(name);

	            System.out.print("���ο� �ּ� �Է� : ");
	            String address = scan.next().replaceAll("\\s+", ""); 
	            while (address.isEmpty()) { 
	                System.out.println("�ٽ� �Է��ϼ���.");
	                System.out.print("���ο� �ּ� �Է� : ");
	                address = scan.nextLine().replaceAll("\\s+", "");
	            }
	            memberToModify.setAddress(address);

	            System.out.print("���ο� ��ȭ��ȣ �Է� : ");
	            String phoneNumber = "";
	            while (true) {
	                phoneNumber = scan.next();
	                if (phoneNumber.matches("[0-9]+") && phoneNumber.length() == 11) {
	                    break;
	                } else {
	                    System.out.println("�ٽ� �Է��ϼ���.");
	                    System.out.print("���ο� ��ȭ��ȣ �Է� : ");
	                }
	            }
	            memberToModify.setPhoneNumber(phoneNumber);

	            System.out.println("---------------�ɼ� ����---------------"); 
	            System.out.print("���ο� �׷� �Է� : ");
	            String group = scan.next().replaceAll("\\s+", "");
	            scan.nextLine(); // ���๮�� ó��
	            while (group.isEmpty()) {
	                System.out.print("���ο� �׷� �Է� : ");
	                group = scan.nextLine().replaceAll("\\s+", "");
	            }

	            memberToModify.setGroup(group);

	            System.out.println("ȸ�� ������ �����Ǿ����ϴ�.");

	            members.remove(phonenumber);
	        } else {
	            System.out.println("�ùٸ� ��ȣ�� �Է��ϼ���.");
	        }
	        
	    } else {
	    	System.out.println("ȸ�� ������ �����ϴ�");
	    }
	    
}
// # 4. ȸ�� ����
	static void memberErase(HashMap<String, ContactVo> members, Scanner scan) {    
	    int memberCount = members.size(); 
	    System.out.println("�� " + memberCount + "���� ����ó");

	    ArrayList<ContactVo> memberList = new ArrayList<>(members.values());

	    for (int i = 0; i < memberList.size(); i++) {
	        System.out.println((i + 1) + ". " + memberList.get(i));
	    }

	    System.out.println("������ ȸ���� ��ȣ�� �Է��ϼ���: ");
	    int selectedNumber = scan.nextInt();

	    if (selectedNumber >= 1 && selectedNumber <= memberList.size()) {
	        ContactVo memberErase = memberList.get(selectedNumber - 1);

	        System.out.println("���� ȸ���� �����Ͻðڽ��ϱ�?");
	        System.out.println(memberErase);

	        System.out.println("�����Ϸ��� 'Y'�� �Է��ϼ���: ");
	        String confirm = scan.next();

	        if (confirm.equalsIgnoreCase("Y")) {
	            String phoneNumber = memberErase.getPhoneNumber();
	            members.remove(phoneNumber);
	            System.out.println("ȸ���� �����Ǿ����ϴ�.");
	        } else {
	            System.out.println("������ ��ҵǾ����ϴ�.");
	        }
	    } else {
	        System.out.println("�ùٸ� ��ȣ�� �Է��ϼ���.");
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
			memberInsert(members, scan);
		}
		if(menuNumber.equals("2")) {
			memberSelectAll(members);
		}
		if(menuNumber.equals("3")) {
			memberModify(members, scan);
		}
		if(menuNumber.equals("4")) {
			memberErase(members, scan);
		}
		if(menuNumber.equals("5")) {
			System.out.println("����˴ϴ�.");
			break;
		}
		
	}
		scan.close();
	}
}

