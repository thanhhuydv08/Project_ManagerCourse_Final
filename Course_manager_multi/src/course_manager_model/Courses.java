package course_manager_model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JOptionPane;

import course_manager_controller.SQLLibrary;

public class Courses extends Abstract implements InterfaceNone<Students>{// ex để tại class studnt nhận được biến classname từ court
private String className;
private String speciality;
private String department;
public Courses() {

}

public Courses(String className,String speciality,String department) {
	this.className=className;
	this.speciality=speciality;
	this.department=department;
}

public String getClassName() {
	return className;
}

public void setClassName(String className) {
	this.className = className;
}

public String getSpeciality() {
	return speciality;
}

public void setSpeciality(String speciality) {
	this.speciality = speciality;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}
@Override
public String toString() {
	return this.className;// phần này để hiển thị trong cobox
}

@Override
public void InsertIntoDatabase(Students t) {
	SQLLibrary sqlL= new SQLLibrary();
	Connection connect = sqlL.SQLOpenConnection("localhost", "huynt", "271190", "MANAGERSTUDENT", 1433);
	//ResultSet records;// không lấy ra nên ko cần lệnh này
	CallableStatement statement;

try {
	int thongBao1 = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu thông tin sinh viên này không", "Thông Báo", JOptionPane.YES_NO_OPTION)	;
	if(thongBao1==0)	{
		statement = connect.prepareCall("insert into Students(STUDENTNAME,ADDRES,IDCARD,PHONE,GENDER,CLASSNAME)"
				+ "values (N'"+t.getStudentName() +"',N'"+t.getAddress()+"','"+t.getIdCard()+"','"+t.getPhone()+"',N'"+t.getGender()+"',N'"+t.getclassName()+"') ");			
	statement.execute();
		JOptionPane.showMessageDialog(null, "Đã lưu thành công !");}
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
	}	
}

@Override
public void DeleteValuesDatabase(Students t) {
	SQLLibrary sqlL= new SQLLibrary();
	Connection connect = sqlL.SQLOpenConnection("localhost", "huynt", "271190", "MANAGERSTUDENT", 1433);
	//ResultSet records;
	CallableStatement statement;// bắt buộc có 2 lệnh này
	try {
		statement = connect.prepareCall("delete from STUDENTS where IDCARD ='"+t.getIdCard()+"' AND CLASSNAME='"+t.getclassName()+"'");// trong ngoặt là 1 câu lệnh trong sql hoăc 1 store của sql trả về

		statement.execute();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
}

@Override
public void UpdateValuesDatabase(Students t) {
	SQLLibrary sqlL= new SQLLibrary();
	Connection connect = sqlL.SQLOpenConnection("localhost", "huynt", "271190", "MANAGERSTUDENT", 1433);
	//ResultSet records;// không lấy ra nên ko cần lệnh này
	CallableStatement statement;

try {
	int thongBao1 = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu thông tin sinh viên này không", "Thông Báo", JOptionPane.YES_NO_OPTION)	;
	if(thongBao1==0)	{
		statement = connect.prepareCall("update  Students set STUDENTNAME =N'"+t.getStudentName()+"',ADDRES=N'"+t.getAddress()+"',PHONE='"+t.getPhone()+"',GENDER=N'"+t.getGender()+"'where IDCARD='"
	+t.getIdCard()+"' AND CLASSNAME='"+t.getclassName()+"'" ); 
						
	statement.execute();
		JOptionPane.showMessageDialog(null, "Đã lưu thành công !");}
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
	}	
	
}


}
