package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dao.ContactDao;
import dto.ContactDto;

public class ContactController {

    static ContactDao cDao = new ContactDao(); // ContactDao ��ü ����	

    static void showMenu() {
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
   
   
// 	------------------------------2. ȸ�� ���------------------------------------------------
    static void memberSelectAll() {
    	System.out.println("-------------ȸ�� ����� ����ϰڽ��ϴ�.-----------");
        ArrayList<ContactDto> cList = cDao.memberSelect();

        for (int i = 0; i < cList.size(); i++) {
            System.out.println(cList.get(i));
        }
       
    }
   
   
//	-------------------------------ȸ������ ���� �޴� �κ�-----------------------------------------
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ContactDao cDao = new ContactDao();
        while (true) {
            showMenu();
            String menuNumber = scan.next();
            System.out.println("������ ��ȣ : " + menuNumber);
           
            if (menuNumber.equals("1")) {
            	cDao.memberInsert(scan); // ȸ�� �߰� �޼ҵ� ȣ��
            	System.out.println("ȸ�� �߰��� �Ϸ�ƽ��ϴ�.");
            }
            if (menuNumber.equals("2")) {
                memberSelectAll(); // ȸ�� ��� ��� �޼ҵ� ȣ��
                System.out.println("-----------------------------------");
            }
            if (menuNumber.equals("3")) {
            	cDao.memberModify(scan); // ȸ�� ���� ��� �޼ҵ� ȣ��
            	System.out.println("-----------------------------------");
            }
            if(menuNumber.equals("4")) {
            	cDao.memberErase(scan);
            }
            if(menuNumber.equals("5")) { // ���� �޼ҵ�
    			System.out.println("����˴ϴ�.");
    			break;
    		}
        }

        scan.close();
    }
}
