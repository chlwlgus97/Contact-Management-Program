package contact.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import contact.vo.ContactVo;

public class ContactMain {
//	전체 메뉴 출력
	private static void showMenu() {
		System.out.println("==========================================");
		System.out.println("		회원관리프로그램		");
		System.out.println("==========================================");
		System.out.println("1. 회원 추가");
		System.out.println("2. 회원 목록");
		System.out.println("3. 회원 수정");
		System.out.println("4. 회원 삭제");
		System.out.println("5. 종료");
		System.out.print("번호 입력 : ");
	}
	
//	# 1. 회원 추가
	static void memberInsert(
			HashMap<String, ContactVo> members
			, Scanner scan) {
//		1명의 입력 정보를 저장하는 놈을 먼저 생성 => ContactVo
		System.out.println("==========================================");
		System.out.println("		회원 추가		");
		System.out.println("==========================================");
		ContactVo member = new ContactVo();
		 System.out.println("-----------------필수----------------");
         System.out.print("이름 입력 : ");
         String name = "";

         while (true) {
             name = scan.next().replaceAll("\\s+", "");
             scan.nextLine();

             if (!name.isEmpty() && name.matches("[^0-9]+")) { 
                 break;
             } else {
                 System.out.println("숫자이거나 공백은 입력할 수 없습니다. 다시 입력하세요.");
             }
         }
        
	    member.setName(name);
	    
	    System.out.print("주소 입력 : ");
	    String address = scan.nextLine().replaceAll("\\s+", ""); 
	    while (address.isEmpty()) { 
	    	System.out.println("다시 입력하세요.");
	        System.out.print("주소 입력 : ");
	        address = scan.nextLine().replaceAll("\\s+", "");
	    }
	    member.setAddress(address);
	    
	    System.out.print("전화번호 입력 : ");
	    String phoneNumber = "";
	    while (true) {
	        phoneNumber = scan.next();
	        if (phoneNumber.matches("[0-9]+") && phoneNumber.length() == 11) {
	            break;
	        } else {
	            System.out.println("다시 입력하세요.");
	            System.out.print("전화번호 입력 : ");
	        }
	    }
	    member.setPhoneNumber(phoneNumber);
	    
	    System.out.println("---------------옵션 사항---------------");
	    String group = scan.nextLine().replaceAll("\\s+", "");
	    while (group.isEmpty()) {
	        System.out.print("그룹 입력 : ");
	        group = scan.nextLine().replaceAll("\\s+", "");
	    }

	    member.setGroup(group);
	    
//		1명 회원 정보 입력 완료
//		해쉬맵 추가 : key : 전화번호, value : ContavtVo object
		members.put(member.getPhoneNumber(), member);
		
//		추가된 회원 출력
//		System.out.println("이름 : " + member.getName()
//						+ ", " + "주소 : " + member.getAddress()
//						+ ", " + "전화번호 : " + member.getPhoneNumber()	
//						+ ", " + "그룹 : " + member.getGroup());
		System.out.println(member);
	}
	
//	# 2. 회원 목록 출력
	static void memberSelectAll(HashMap<String, ContactVo> members) {
		int memberCount = members.size(); // 회원 수 계산
	    System.out.println("총 " + memberCount + "명의 연락처");
//		키를 Set 추출
		Set<String> keys = members.keySet();
//		iterator
		Iterator<String> it = keys.iterator();
		
//		while 반복
		while(it.hasNext()) {
			String key = it.next(); 
			// members.get(key) value 추출 : ContactVo object
			System.out.println(members.get(key).toString());
		}
	}
	
//	# 3. 회원 수정	
	static void memberModify(HashMap<String, ContactVo> members, Scanner scan) {
	    System.out.println("-----------------필수----------------");
	    System.out.println("수정할 회원의 이름을 입력하세요: ");
	    
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
	        System.out.println("중복된 회원들 혹은 수정할 회원들 입니다 : ");
	    	
	        for (ContactVo member : matchingMembers) {
	            System.out.println(count + ". " + member);
	            count++;
	        }

	        System.out.println("수정할 회원의 번호를 입력하세요 : ");
	        int selectedNumber = scan.nextInt();

	        if (selectedNumber >= 1 && selectedNumber <= matchingMembers.size()) {
	            ContactVo memberToModify = matchingMembers.get(selectedNumber - 1);

	            // 정보 수정
	            System.out.println("-----------------필수----------------");
	            System.out.print("새로운 이름 입력 : ");
	            String name = "";

	            while (true) {
	                name = scan.next().replaceAll("\\s+", "");
	                scan.nextLine(); 

	                if (!name.isEmpty() && name.matches("[^0-9]+")) { 
	                    break;
	                } else {
	                    System.out.println("숫자이거나 공백은 입력할 수 없습니다. 다시 입력하세요.");
	                }
	            }

	            memberToModify.setName(name);

	            System.out.print("새로운 주소 입력 : ");
	            String address = scan.next().replaceAll("\\s+", ""); 
	            while (address.isEmpty()) { 
	                System.out.println("다시 입력하세요.");
	                System.out.print("새로운 주소 입력 : ");
	                address = scan.nextLine().replaceAll("\\s+", "");
	            }
	            memberToModify.setAddress(address);

	            System.out.print("새로운 전화번호 입력 : ");
	            String phoneNumber = "";
	            while (true) {
	                phoneNumber = scan.next();
	                if (phoneNumber.matches("[0-9]+") && phoneNumber.length() == 11) {
	                    break;
	                } else {
	                    System.out.println("다시 입력하세요.");
	                    System.out.print("새로운 전화번호 입력 : ");
	                }
	            }
	            memberToModify.setPhoneNumber(phoneNumber);

	            System.out.println("---------------옵션 사항---------------"); 
	            System.out.print("새로운 그룹 입력 : ");
	            String group = scan.next().replaceAll("\\s+", "");
	            scan.nextLine(); // 개행문자 처리
	            while (group.isEmpty()) {
	                System.out.print("새로운 그룹 입력 : ");
	                group = scan.nextLine().replaceAll("\\s+", "");
	            }

	            memberToModify.setGroup(group);

	            System.out.println("회원 정보가 수정되었습니다.");

	            members.remove(phonenumber);
	        } else {
	            System.out.println("올바른 번호를 입력하세요.");
	        }
	        
	    } else {
	    	System.out.println("회원 정보가 없습니다");
	    }
	    
}
// # 4. 회원 삭제
	static void memberErase(HashMap<String, ContactVo> members, Scanner scan) {    
	    int memberCount = members.size(); 
	    System.out.println("총 " + memberCount + "명의 연락처");

	    ArrayList<ContactVo> memberList = new ArrayList<>(members.values());

	    for (int i = 0; i < memberList.size(); i++) {
	        System.out.println((i + 1) + ". " + memberList.get(i));
	    }

	    System.out.println("삭제할 회원의 번호를 입력하세요: ");
	    int selectedNumber = scan.nextInt();

	    if (selectedNumber >= 1 && selectedNumber <= memberList.size()) {
	        ContactVo memberErase = memberList.get(selectedNumber - 1);

	        System.out.println("다음 회원을 삭제하시겠습니까?");
	        System.out.println(memberErase);

	        System.out.println("삭제하려면 'Y'를 입력하세요: ");
	        String confirm = scan.next();

	        if (confirm.equalsIgnoreCase("Y")) {
	            String phoneNumber = memberErase.getPhoneNumber();
	            members.remove(phoneNumber);
	            System.out.println("회원이 삭제되었습니다.");
	        } else {
	            System.out.println("삭제가 취소되었습니다.");
	        }
	    } else {
	        System.out.println("올바른 번호를 입력하세요.");
	    }
	}
		

	public static void main(String[] args) {
//		회원정보 저장 HashMap 생성
//		key : phoneNumber, value : new ContactVo()
		HashMap<String, ContactVo> members = 
			new HashMap<String, ContactVo>();
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
		showMenu();
		String menuNumber = scan.next();
		System.out.println("선택한 번호 : " + menuNumber);
		
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
			System.out.println("종료됩니다.");
			break;
		}
		
	}
		scan.close();
	}
}

