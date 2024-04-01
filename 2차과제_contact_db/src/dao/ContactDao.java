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
	
	
	// 2. 전체 회원 목록 조회 - select
	public ArrayList<ContactDto> memberSelect() {
//		드라이버, connection에 필요한 변수
		String driver 	= "oracle.jdbc.driver.OracleDriver";
//		String url 		= "jdbc:oracle:thin:@ip address:port:sid";
		String url 		= "jdbc:oracle:thin:@localhost:1521:xe"; //같은 컴터안에 다 설치되어있기때문에 이 코드 사용
		String user 	= "ora_user";
		String password = "1234";
		
//		Connection, PreparedStatement, ResultSet(select)
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null; //select
		
//		sql => select문
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
//		2. Connection 생성
			conn = DriverManager.getConnection(url, user, password);
//		3. PreparedStatement 생성
			pstmt = conn.prepareStatement(sql.toString()); //parameter : String
//		4. 바인드 변수 : ? 없다 => 바로 실행
			rs = pstmt.executeQuery(); // select 실행, returun ResultSet
//		5. contactDto = ArrayList add
			while(rs.next()) { // rs.next() : 한 row 처리 : 한명 사원 정보 => Dto
				ContactDto cdto = new ContactDto();
				cdto.setName(rs.getString("Member_Name"));
				cdto.setAddress(rs.getString("Address"));
				cdto.setPhone(rs.getString("Phone_Number"));
				cdto.setGroupnm(rs.getString("Group_Name"));
				aList.add(cdto);

				}
		} catch(ClassNotFoundException e) {
			System.out.println(e.getMessage()); // 드라이버 클래스가 없을 경우
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
	
	//	1. 회원 추가 - insert 및 작동 코드 포함
	public void memberInsert(Scanner scan) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid= "ora_user";
		String password = "1234";
		
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append( "INSERT INTO MEMBERS (member_name, address, phone_number, group_id)	");
		sql.append( "VALUES (?,?,?,?)								");
		

//		1명의 입력 정보를 저장하는 놈을 먼저 생성 => ContactVo
		System.out.println("==========================================");
		System.out.println("		회원 추가		");
		System.out.println("==========================================");
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
       
	    System.out.print("주소 입력 : ");
	    String address = scan.nextLine().replaceAll("\\s+", "");
	    while (address.isEmpty()) {
	    	System.out.println("다시 입력하세요.");
	        System.out.print("주소 입력 : ");
	        address = scan.nextLine().replaceAll("\\s+", "");
	    }
	    
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
	    
	    System.out.println("---------------옵션 사항---------------");
	    String group = scan.nextLine().replaceAll("\\s+", "");
	    while (group.isEmpty()) {
	        System.out.print("그룹 입력 : ");
	        group = scan.nextLine().replaceAll("\\s+", "");
	    }
	    
	    int group_id;
	    if (group.equals("가족")) {
	    	group_id = 1;
	    }
	    else if (group.equals("친구")) {
	    	group_id = 2;
	    }
	    else if (group.equals("회사")) {
	    	group_id = 3;
	    }
	    else {
	    	group_id = 4;
	    }
	    	
//	    가족이 입력 -> 1 친구 ->2 회사 ->3 기타 ->4
//		1명 회원 정보 입력 완료
//		해쉬맵 추가 : key : 전화번호, value : ContavtVo object
		
//		추가된 회원 출력
//		System.out.println("이름 : " + member.getName()
//						+ ", " + "주소 : " + member.getAddress()
//						+ ", " + "전화번호 : " + member.getPhoneNumber()	
//						+ ", " + "그룹 : " + member.getGroup());
		
		
//		1. Driver Loading
		try {
			conn = DriverManager.getConnection(url, userid, password);
			pstmt = conn.prepareStatement(sql.toString());
		    
//			5개 값을 세팅 => 키보드로 입력
			pstmt.setString(1,name); // 사원명 값을 키보드로 입력받아 처리 해야함
			pstmt.setString(2,address); // 부서번호 값을 키보드로 입력받아 처리 해야함
			pstmt.setString(3,phoneNumber);
			pstmt.setInt(4,group_id);
//			insert 실행
			pstmt.executeUpdate(); //추가, int return : 추가된 행 수 반환
//			close
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}// insert end
	
//	3. 회원 수정
	public void memberModify(Scanner scan) {
		// 데이터 베이스를 연결하는 코드	
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid= "ora_user";
		String password = "1234";
		// 연결 개체 와 질의문을 나타내는 객체 초기값에는 값이 없음으로 널 지정
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		// sql 쿼리를 실행하기위한 스트링빌더 객체 생성
		StringBuilder sql = new StringBuilder();
		sql.append( "UPDATE MEMBERS							");
		sql.append( "SET MEMBER_NAME = ?					");
		sql.append( ",	   ADDRESS = ?						");
		sql.append( ",	   PHONE_NUMBER = ?					");
		sql.append( ",	   GROUP_ID = ?						");
		sql.append( " WHERE MEMBER_NAME = ?					"); //입력받는 해당이름 여기서 sql이 연동 되어있음으로 입력한 값을
																//밑에 pstmt가 받으면 Mname라는 변수값으로 Mname 변수의 값을 설정하여 데이터베이스에 전달
		sql.append( "   AND PHONE_NUMBER = ?				");
		
		 System.out.println("-----------------필수----------------");
		 System.out.println("수정할 회원의 이름을 입력하세요: ");
		 // Mname Mname은 문자열을 저장할 변수이고. 이 변수는 나중에 입력을 받아 그 값을 저장하는것
		 String Mname;
		 Mname = scan.next();
		 //Mname 에 해당하는 값을 키보드로 받아서 그 해당 이름을
		 int count = 1; //회원 목록 번호 초기값을 1부터 시작하게끔 한다
		 ArrayList<ContactDto> cList = searchByName(Mname); //searchByName(Mname) 메서드를 호출하여 특정 이름으로 검색된 회원 목록을 출력
		 for (ContactDto member : cList) { //Mname을 기준으로 회원을 검색하고, 그 결과를 cList에 저장 cList는 ContactDto 객체들을 담고 있는 ArrayList.
	            System.out.println(count + ". " + member); //cList에 있는 해당 로우를 count 변수와 멤버객체를 이용해서 불러온다
	            count++; //1씩 증가시켜 남은 다음 회원번호를 나타냄
	     }
		
		 System.out.println("수정할 회원의 번호를 입력하세요 : ");
	     int selectedNumber = scan.nextInt(); //정수를 키보드로 입력받아 번수인 selectdeNumber에 저장됨
		
	     String phNum = cList.get(selectedNumber-1).getPhone();
		 //ContactDto 객체에서 전화번호를 가져오고 : getp~N
	     //String phNum -> 앞서 가져온 전화 번호를 phNum 변수에 저장한다
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
       
	    
	    System.out.print("주소 입력 : ");
	    String address = scan.nextLine().replaceAll("\\s+", "");
	    while (address.isEmpty()) {
	    	System.out.println("다시 입력하세요.");
	        System.out.print("주소 입력 : ");
	        address = scan.nextLine().replaceAll("\\s+", "");
	    }
	    
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
	    
	    System.out.println("---------------옵션 사항---------------");
	    String group = scan.nextLine().replaceAll("\\s+", "");
	    while (group.isEmpty()) {
	        System.out.print("그룹 입력 : ");
	        group = scan.nextLine().replaceAll("\\s+", "");
	    }
		
		 int group_id;
		    if (group.equals("가족")) {
		    	group_id = 1;
		    }
		    else if (group.equals("친구")) {
		    	group_id = 2;
		    }
		    else if (group.equals("회사")) {
		    	group_id = 3;
		    }
		    else {
		    	group_id = 4;
		    }
		    
		    try {
				conn = DriverManager.getConnection(url, userid, password);
				pstmt = conn.prepareStatement(sql.toString());
				
//				5개 값을 세팅 => 키보드로 입력
				pstmt.setString(1,name); // 회원이름 값을 키보드로 입력받아 처리
				pstmt.setString(2,address); // 주소 값을 키보드로 입력받아 처리
				pstmt.setString(3,phoneNumber); //전화번호 값을 키보드로 입력받아 처리
				pstmt.setInt(4,group_id); //회원번호 값을 키보드로 입력받아 처리
				pstmt.setString(5, Mname); // 입력받는 해당 이름
				pstmt.setString(6, phNum); // 입력받은 해당 전화번호
//				insert 실행
				pstmt.executeUpdate(); //추가, int return : 추가된 행 수 반환
//				close
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			
			}
	}
	

// 3-1. 중복 회원 이름
	public ArrayList<ContactDto> searchByName(String nameTemp) {
		//	드라이버, connection에 필요한 변수
		String driver 	= "oracle.jdbc.driver.OracleDriver";
		//	String url 		= "jdbc:oracle:thin:@ip address:port:sid";
		String url 		= "jdbc:oracle:thin:@localhost:1521:xe"; //같은 컴터안에 다 설치되어있기때문에 이 코드 사용
		String user 	= "ora_user";
		String password = "1234";
	
		//	Connection, PreparedStatement, ResultSet(select)
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null; //select
	
	//	sql => select문
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
	//	2. Connection 생성
			conn = DriverManager.getConnection(url, user, password);
	//	3. PreparedStatement 생성
			pstmt = conn.prepareStatement(sql.toString()); //parameter : String
			pstmt.setString(1,nameTemp);
	//	4. 바인드 변수 : ? 없다 => 바로 실행
			rs = pstmt.executeQuery(); // select 실행, returun ResultSet
	//	5. contactDto = ArrayList add
			while(rs.next()) { // rs.next() : 한 row 처리 : 한명 사원 정보 => Dto
				ContactDto cdto = new ContactDto();
				cdto.setName(rs.getString("Member_Name"));
				cdto.setAddress(rs.getString("Address"));
				cdto.setPhone(rs.getString("Phone_Number"));
				cdto.setGroupnm(rs.getString("Group_Name"));
				aList.add(cdto);
	
				}
		} catch(ClassNotFoundException e) {
			System.out.println(e.getMessage()); // 드라이버 클래스가 없을 경우
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

	
}//  중복이름 insert end

//	4. 회원 삭제
	public void memberErase(Scanner scan) {
		// 데이터 베이스를 연결하는 코드	
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid= "ora_user";
		String password = "1234";
		// 연결 개체 와 질의문을 나타내는 객체 초기값에는 값이 없음으로 널 지정
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		
		System.out.println("-------------회원 목록을 출력하겠습니다.-----------");
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
		
		// 1. 회원번호를 입력받아서 해당번호를 입력해서 나온 번호
		
		
		System.out.println("삭제할 회원의 번호를 입력하세요: ");
	    int selectedNumber = scan.nextInt();
	    
	    ContactDto memberErase = cList.get(selectedNumber - 1);
	    	
    	System.out.println("다음 회원을 삭제하시겠습니까?");
	    System.out.println(memberErase);
	    
	    System.out.println("삭제하려면 'Y'를 입력하세요: ");
        String ok = scan.next();
       
        String phoneNumber = null ;
       
        if(ok.equalsIgnoreCase("Y")) {
        	phoneNumber = cList.get(selectedNumber-1).getPhone();
        	cList.remove(memberErase);
        	System.out.println("회원이 삭제되었습니다.");
        } else {
        	System.out.println("삭제가 취소되었습니다.");
        }
	    
	    	
		 try {
				conn = DriverManager.getConnection(url, userid, password);
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1,phoneNumber); //전화번호 값을 키보드로 입력받아 처리
//				insert 실행
				pstmt.executeUpdate(); //추가, int return : 추가된 행 수 반환
//				close
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		
	}

} // Dao 클래스 전체 end
