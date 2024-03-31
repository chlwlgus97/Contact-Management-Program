package contact.main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import contact.vo.ContactVo;

public class ContactEx {
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
	
//	회원 추가
	static void memberInsert(
			HashMap<String, ContactVo> members
			, Scanner scan) {
//		1명의 입력 정보를 저장하는 놈을 먼저 생성 => ContactVo
		System.out.println("==========================================");
		System.out.println("		회원 추가		");
		System.out.println("==========================================");
		ContactVo member = new ContactVo(); // 디폴트 생성자 사용
		System.out.print("이름 입력(필수) : "); // 필수 입력, 공백 안되게 처리
		member.setName(scan.next());
		System.out.print("주소 입력(필수) : "); // 필수 입력, 공백 안되게 처리
		member.setAddress(scan.next());
		System.out.print("번호 입력(필수) : "); // 필수 입력, 01012341234 숫자로만 입력 처리
		member.setPhoneNumber(scan.next());
		System.out.print("그룹 입력(필수) : "); // 옵션 : 없을 수 있다.
		member.setGroup(scan.next());
		System.out.println("종료");
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
	
//	회원 목록 출력
	static void memberSelectAll(HashMap<String, ContactVo> members) {
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
			memberInsert(members, scan) ;
		}
		if(menuNumber.equals("2")) {
			memberSelectAll(members) ;
		}
		if(menuNumber.equals("5")) {
			System.out.println("종료됩니다.");
			break;
		}
		
	}
		scan.close();
	}
}

