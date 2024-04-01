// D: Data A: Access O: Object
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dto.ContactDto;

public class ContactDao {
	
	
	// 2. ��ü ȸ�� ��� ��ȸ - select
	public ArrayList<ContactDto> memberSelect() {
//		����̹�, connection�� �ʿ��� ����
		String driver 	= "oracle.jdbc.driver.OracleDriver";
//		String url 		= "jdbc:oracle:thin:@ip address:port:sid";
		String url 		= "jdbc:oracle:thin:@localhost:1521:xe"; //���� ���;ȿ� �� ��ġ�Ǿ��ֱ⶧���� �� �ڵ� ���
		String user 	= "ora_user";
		String password = "1234";
		
//		Connection, PreparedStatement, ResultSet(select)
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null; //select
		
//		sql => select��
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 							");
		sql.append("	   m.MEMBER_NAME  			");
		sql.append("	  , m.ADDRESS				");
		sql.append("	  , m.PHONE_NUMBER			");
		sql.append("	  , g.GROUP_NAME 			");
		sql.append("  FROM MEMBERS m				");
		sql.append("  	  , GROUPS g				");
		sql.append(" WHERE m.GROUP_ID = g.GROUP_ID	");
		
		ArrayList<ContactDto> aList = new ArrayList<>();
		
		try {
//		1. driver load
			Class.forName(driver);
//		2. Connection ����
			conn = DriverManager.getConnection(url, user, password);
//		3. PreparedStatement ����
			pstmt = conn.prepareStatement(sql.toString()); //parameter : String
//		4. ���ε� ���� : ? ���� => �ٷ� ����
			rs = pstmt.executeQuery(); // select ����, returun ResultSet
//		5. contactDto = ArrayList add
			while(rs.next()) { // rs.next() : �� row ó�� : �Ѹ� ��� ���� => Dto
				ContactDto cdto = new ContactDto();
				cdto.setName(rs.getString("Member_Name"));
				cdto.setAddress(rs.getString("Address"));
				cdto.setPhone(rs.getString("Phone_Number"));
				cdto.setGroupnm(rs.getString("Group_Name"));
				aList.add(cdto);

				}
		} catch(ClassNotFoundException e) {
			System.out.println(e.getMessage()); // ����̹� Ŭ������ ���� ���
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			 try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			 } catch (SQLException e) {
					e.printStackTrace();
			 }
		}
		return aList;
		
	}// select end
	
	//	1. ȸ�� �߰� - insert �� �۵� �ڵ� ����
	public void memberInsert(Scanner scan) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid= "ora_user";
		String password = "1234";
		
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append( "INSERT INTO MEMBERS (member_name, address, phone_number, group_id)	");
		sql.append( "VALUES (?,?,?,?)								");
		

//		1���� �Է� ������ �����ϴ� ���� ���� ���� => ContactVo
		System.out.println("==========================================");
		System.out.println("		ȸ�� �߰�		");
		System.out.println("==========================================");
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
       
	    System.out.print("�ּ� �Է� : ");
	    String address = scan.nextLine().replaceAll("\\s+", "");
	    while (address.isEmpty()) {
	    	System.out.println("�ٽ� �Է��ϼ���.");
	        System.out.print("�ּ� �Է� : ");
	        address = scan.nextLine().replaceAll("\\s+", "");
	    }
	    
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
	    
	    System.out.println("---------------�ɼ� ����---------------");
	    String group = scan.nextLine().replaceAll("\\s+", "");
	    while (group.isEmpty()) {
	        System.out.print("�׷� �Է� : ");
	        group = scan.nextLine().replaceAll("\\s+", "");
	    }
	    
	    int group_id;
	    if (group.equals("����")) {
	    	group_id = 1;
	    }
	    else if (group.equals("ģ��")) {
	    	group_id = 2;
	    }
	    else if (group.equals("ȸ��")) {
	    	group_id = 3;
	    }
	    else {
	    	group_id = 4;
	    }
	    	
//	    ������ �Է� -> 1 ģ�� ->2 ȸ�� ->3 ��Ÿ ->4
//		1�� ȸ�� ���� �Է� �Ϸ�
//		�ؽ��� �߰� : key : ��ȭ��ȣ, value : ContavtVo object
		
//		�߰��� ȸ�� ���
//		System.out.println("�̸� : " + member.getName()
//						+ ", " + "�ּ� : " + member.getAddress()
//						+ ", " + "��ȭ��ȣ : " + member.getPhoneNumber()	
//						+ ", " + "�׷� : " + member.getGroup());
		
		
//		1. Driver Loading
		try {
			conn = DriverManager.getConnection(url, userid, password);
			pstmt = conn.prepareStatement(sql.toString());
		    
//			5�� ���� ���� => Ű����� �Է�
			pstmt.setString(1,name); // ����� ���� Ű����� �Է¹޾� ó�� �ؾ���
			pstmt.setString(2,address); // �μ���ȣ ���� Ű����� �Է¹޾� ó�� �ؾ���
			pstmt.setString(3,phoneNumber);
			pstmt.setInt(4,group_id);
//			insert ����
			pstmt.executeUpdate(); //�߰�, int return : �߰��� �� �� ��ȯ
//			close
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}// insert end
	
//	3. ȸ�� ����
	public void memberModify(Scanner scan) {
		// ������ ���̽��� �����ϴ� �ڵ�	
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid= "ora_user";
		String password = "1234";
		// ���� ��ü �� ���ǹ��� ��Ÿ���� ��ü �ʱⰪ���� ���� �������� �� ����
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		// sql ������ �����ϱ����� ��Ʈ������ ��ü ����
		StringBuilder sql = new StringBuilder();
		sql.append( "UPDATE MEMBERS							");
		sql.append( "SET MEMBER_NAME = ?					");
		sql.append( ",	   ADDRESS = ?						");
		sql.append( ",	   PHONE_NUMBER = ?					");
		sql.append( ",	   GROUP_ID = ?						");
		sql.append( " WHERE MEMBER_NAME = ?					"); //�Է¹޴� �ش��̸� ���⼭ sql�� ���� �Ǿ��������� �Է��� ����
																//�ؿ� pstmt�� ������ Mname��� ���������� Mname ������ ���� �����Ͽ� �����ͺ��̽��� ����
		sql.append( "   AND PHONE_NUMBER = ?				");
		
		 System.out.println("-----------------�ʼ�----------------");
		 System.out.println("������ ȸ���� �̸��� �Է��ϼ���: ");
		 // Mname Mname�� ���ڿ��� ������ �����̰�. �� ������ ���߿� �Է��� �޾� �� ���� �����ϴ°�
		 String Mname;
		 Mname = scan.next();
		 //Mname �� �ش��ϴ� ���� Ű����� �޾Ƽ� �� �ش� �̸���
		 int count = 1; //ȸ�� ��� ��ȣ �ʱⰪ�� 1���� �����ϰԲ� �Ѵ�
		 ArrayList<ContactDto> cList = searchByName(Mname); //searchByName(Mname) �޼��带 ȣ���Ͽ� Ư�� �̸����� �˻��� ȸ�� ����� ���
		 for (ContactDto member : cList) { //Mname�� �������� ȸ���� �˻��ϰ�, �� ����� cList�� ���� cList�� ContactDto ��ü���� ��� �ִ� ArrayList.
	            System.out.println(count + ". " + member); //cList�� �ִ� �ش� �ο츦 count ������ �����ü�� �̿��ؼ� �ҷ��´�
	            count++; //1�� �������� ���� ���� ȸ����ȣ�� ��Ÿ��
	     }
		
		 System.out.println("������ ȸ���� ��ȣ�� �Է��ϼ��� : ");
	     int selectedNumber = scan.nextInt(); //������ Ű����� �Է¹޾� ������ selectdeNumber�� �����
		
	     String phNum = cList.get(selectedNumber-1).getPhone();
		 //ContactDto ��ü���� ��ȭ��ȣ�� �������� : getp~N
	     //String phNum -> �ռ� ������ ��ȭ ��ȣ�� phNum ������ �����Ѵ�
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
       
	    
	    System.out.print("�ּ� �Է� : ");
	    String address = scan.nextLine().replaceAll("\\s+", "");
	    while (address.isEmpty()) {
	    	System.out.println("�ٽ� �Է��ϼ���.");
	        System.out.print("�ּ� �Է� : ");
	        address = scan.nextLine().replaceAll("\\s+", "");
	    }
	    
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
	    
	    System.out.println("---------------�ɼ� ����---------------");
	    String group = scan.nextLine().replaceAll("\\s+", "");
	    while (group.isEmpty()) {
	        System.out.print("�׷� �Է� : ");
	        group = scan.nextLine().replaceAll("\\s+", "");
	    }
		
		 int group_id;
		    if (group.equals("����")) {
		    	group_id = 1;
		    }
		    else if (group.equals("ģ��")) {
		    	group_id = 2;
		    }
		    else if (group.equals("ȸ��")) {
		    	group_id = 3;
		    }
		    else {
		    	group_id = 4;
		    }
		    
		    try {
				conn = DriverManager.getConnection(url, userid, password);
				pstmt = conn.prepareStatement(sql.toString());
				
//				5�� ���� ���� => Ű����� �Է�
				pstmt.setString(1,name); // ȸ���̸� ���� Ű����� �Է¹޾� ó��
				pstmt.setString(2,address); // �ּ� ���� Ű����� �Է¹޾� ó��
				pstmt.setString(3,phoneNumber); //��ȭ��ȣ ���� Ű����� �Է¹޾� ó��
				pstmt.setInt(4,group_id); //ȸ����ȣ ���� Ű����� �Է¹޾� ó��
				pstmt.setString(5, Mname); // �Է¹޴� �ش� �̸�
				pstmt.setString(6, phNum); // �Է¹��� �ش� ��ȭ��ȣ
//				insert ����
				pstmt.executeUpdate(); //�߰�, int return : �߰��� �� �� ��ȯ
//				close
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			
			}
	}
	

// 3-1. �ߺ� ȸ�� �̸�
	public ArrayList<ContactDto> searchByName(String nameTemp) {
		//	����̹�, connection�� �ʿ��� ����
		String driver 	= "oracle.jdbc.driver.OracleDriver";
		//	String url 		= "jdbc:oracle:thin:@ip address:port:sid";
		String url 		= "jdbc:oracle:thin:@localhost:1521:xe"; //���� ���;ȿ� �� ��ġ�Ǿ��ֱ⶧���� �� �ڵ� ���
		String user 	= "ora_user";
		String password = "1234";
	
		//	Connection, PreparedStatement, ResultSet(select)
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null; //select
	
	//	sql => select��
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 							");
		sql.append("	   m.MEMBER_NAME  			");
		sql.append("	  , m.ADDRESS				");
		sql.append("	  , m.PHONE_NUMBER			");
		sql.append("	  , g.GROUP_NAME 			");
		sql.append("  FROM MEMBERS m				");
		sql.append("  	  , GROUPS g				");
		sql.append(" WHERE m.GROUP_ID = g.GROUP_ID	");
		sql.append("   and MEMBER_NAME = ?			");
	
	
		ArrayList<ContactDto> aList = new ArrayList<>();
	
		try {
	//	1. driver load
			Class.forName(driver);
	//	2. Connection ����
			conn = DriverManager.getConnection(url, user, password);
	//	3. PreparedStatement ����
			pstmt = conn.prepareStatement(sql.toString()); //parameter : String
			pstmt.setString(1,nameTemp);
	//	4. ���ε� ���� : ? ���� => �ٷ� ����
			rs = pstmt.executeQuery(); // select ����, returun ResultSet
	//	5. contactDto = ArrayList add
			while(rs.next()) { // rs.next() : �� row ó�� : �Ѹ� ��� ���� => Dto
				ContactDto cdto = new ContactDto();
				cdto.setName(rs.getString("Member_Name"));
				cdto.setAddress(rs.getString("Address"));
				cdto.setPhone(rs.getString("Phone_Number"));
				cdto.setGroupnm(rs.getString("Group_Name"));
				aList.add(cdto);
	
				}
		} catch(ClassNotFoundException e) {
			System.out.println(e.getMessage()); // ����̹� Ŭ������ ���� ���
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			 try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			 } catch (SQLException e) {
					e.printStackTrace();
			 }
		}
		return aList;

	
}//  �ߺ��̸� insert end

//	4. ȸ�� ����
	public void memberErase(Scanner scan) {
		// ������ ���̽��� �����ϴ� �ڵ�	
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid= "ora_user";
		String password = "1234";
		// ���� ��ü �� ���ǹ��� ��Ÿ���� ��ü �ʱⰪ���� ���� �������� �� ����
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		
		System.out.println("-------------ȸ�� ����� ����ϰڽ��ϴ�.-----------");
        ArrayList<ContactDto> cList = memberSelect();

        int count=1;
       
        for (ContactDto member : cList) {
        	 System.out.println(count + ". " + member);
        	 count++;
        }
		
//		sql => selectvo
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE 						");
		sql.append("  FROM MEMBERS 				");
		sql.append(" WHERE PHONE_NUMBER = ?		");
		
		// 1. ȸ����ȣ�� �Է¹޾Ƽ� �ش��ȣ�� �Է��ؼ� ���� ��ȣ
		
		
		System.out.println("������ ȸ���� ��ȣ�� �Է��ϼ���: ");
	    int selectedNumber = scan.nextInt();
	    
	    ContactDto memberErase = cList.get(selectedNumber - 1);
	    	
    	System.out.println("���� ȸ���� �����Ͻðڽ��ϱ�?");
	    System.out.println(memberErase);
	    
	    System.out.println("�����Ϸ��� 'Y'�� �Է��ϼ���: ");
        String ok = scan.next();
       
        String phoneNumber = null ;
       
        if(ok.equalsIgnoreCase("Y")) {
        	phoneNumber = cList.get(selectedNumber-1).getPhone();
        	cList.remove(memberErase);
        	System.out.println("ȸ���� �����Ǿ����ϴ�.");
        } else {
        	System.out.println("������ ��ҵǾ����ϴ�.");
        }
	    
	    	
		 try {
				conn = DriverManager.getConnection(url, userid, password);
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1,phoneNumber); //��ȭ��ȣ ���� Ű����� �Է¹޾� ó��
//				insert ����
				pstmt.executeUpdate(); //�߰�, int return : �߰��� �� �� ��ȯ
//				close
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		
	}

} // Dao Ŭ���� ��ü end
