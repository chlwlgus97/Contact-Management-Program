package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dao.ContactDao;
import dto.ContactDto;

public class ContactController {

    static ContactDao cDao = new ContactDao(); // ContactDao 객체 생성	

    static void showMenu() {
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
   
   
// 	------------------------------2. 회원 목록------------------------------------------------
    static void memberSelectAll() {
    	System.out.println("-------------회원 목록을 출력하겠습니다.-----------");
        ArrayList<ContactDto> cList = cDao.memberSelect();

        for (int i = 0; i < cList.size(); i++) {
            System.out.println(cList.get(i));
        }
       
    }
   
   
//	-------------------------------회원정보 저장 메뉴 부분-----------------------------------------
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ContactDao cDao = new ContactDao();
        while (true) {
            showMenu();
            String menuNumber = scan.next();
            System.out.println("선택한 번호 : " + menuNumber);
           
            if (menuNumber.equals("1")) {
            	cDao.memberInsert(scan); // 회원 추가 메소드 호출
            	System.out.println("회원 추가가 완료됐습니다.");
            }
            if (menuNumber.equals("2")) {
                memberSelectAll(); // 회원 목록 출력 메소드 호출
                System.out.println("-----------------------------------");
            }
            if (menuNumber.equals("3")) {
            	cDao.memberModify(scan); // 회원 수정 출력 메소드 호출
            	System.out.println("-----------------------------------");
            }
            if(menuNumber.equals("4")) {
            	cDao.memberErase(scan);
            }
            if(menuNumber.equals("5")) { // 종료 메소드
    			System.out.println("종료됩니다.");
    			break;
    		}
        }

        scan.close();
    }
}
