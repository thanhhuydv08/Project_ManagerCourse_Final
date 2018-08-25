package course_manager_model;

import java.sql.CallableStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;

import course_manager_controller.SQLLibrary;

public class ManagerCourse implements InterfaceNone<Courses>{

@Override
public void InsertIntoDatabase(Courses t) {
	SQLLibrary sqlL= new SQLLibrary();
	Connection connect = sqlL.SQLOpenConnection("localhost", "huynt", "271190", "MANAGERSTUDENT", 1433);
	//ResultSet records;// không lấy ra nên ko cần lệnh này
	CallableStatement statement;

	try {
		int thongBao = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu khóa học này không ", "Thông Báo", JOptionPane.YES_NO_OPTION)	;
		if(thongBao==0)	{
		statement = connect.prepareCall("insert into Courses(CLASSNAME,SPECIALITY,DEPARTMENT)"
				+ "values ('"+t.getClassName() +"',N'"+t.getSpeciality()+"',N'"+t.getDepartment()+"') ");			
	statement.execute();
		JOptionPane.showMessageDialog(null, "Đã lưu thành công !");}
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
	}
}

@Override
public void DeleteValuesDatabase(Courses t) {
	// TODO Auto-generated method stub
	
}

@Override
public void UpdateValuesDatabase(Courses t) {
	// TODO Auto-generated method stub
	
}

// remove khóa học dẫn đến remove danh sách sinh viên trong lớp học đó luôn
public void RemoveCourse(Courses course) {
	SQLLibrary sqlL= new SQLLibrary();
	Connection connect = sqlL.SQLOpenConnection("localhost", "huynt", "271190", "MANAGERSTUDENT", 1433);
	//ResultSet records;// không lấy ra nên ko cần lệnh này
	CallableStatement statement;

	try {
		int thongBao = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa khóa học này không ", "Thông Báo", JOptionPane.YES_NO_OPTION)	;
		if(thongBao==0)	{
		statement = connect.prepareCall("DELETE From COURSES WHERE CLASSNAME='"+course.getClassName()+"'");			
	    statement.execute();
	//	statement = connect.prepareCall("DELETE From STUDENTS WHERE CLASSNAME='"+course.getClassName()+"'");			
	 //   statement.execute();
	  //ok : do khóa ngoại tôi cho ON DELETE CASCADE; nên khi xóa row có cột khóa chính khóa chính sẽ xóa luôn các rows trong bản con chứa khóa ngoại
		JOptionPane.showMessageDialog(null, "Đã xóa thành công !");}
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
	}
}

}
