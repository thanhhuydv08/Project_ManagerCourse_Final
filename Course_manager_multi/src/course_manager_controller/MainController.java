package course_manager_controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import course_manager_model.Courses;
import course_manager_model.ManagerCourse;
import course_manager_model.Students;
import course_manager_view.FrmCourseFrame;
import course_manager_view.FrmStudentFrame;
import course_manager_controller.SQLLibrary;

public class MainController {
	private FrmCourseFrame frmCourseFrame ;
	private Courses course;
	private ManagerCourse managerCourse;
	private FrmStudentFrame frmStudentFrame ;
	private Students student;
	JComboBox<String> combobox =new JComboBox<>();
	private String InsertOrUpdate = null;
	private boolean CheckSocmtAndTenLop = false;
	private boolean CheckSocmtAndTenSinhVien= true;
public MainController() {
	
}
public MainController(FrmCourseFrame frmCourseFrame,Courses course,ManagerCourse managerCourse) {
	this.frmCourseFrame=frmCourseFrame;
	this.course=course;
	this.managerCourse=managerCourse;
	this.frmCourseFrame.addCoursesListener(new CoursesListener());
}
public MainController(FrmStudentFrame frmStudentFrame,Students student,Courses course, String InsertOrUpdate) {
	this.frmStudentFrame=frmStudentFrame;
	this.student=student;
	this.course=course;
	this.InsertOrUpdate=InsertOrUpdate;
	this.frmStudentFrame.addStudentsListener(new StudentsListener());

}
public MainController(Students student,Courses course ) {
	this.student = student;
	course.DeleteValuesDatabase(student);// KHÔNG ĐƯỢC THIS.course NHÉ
}
public MainController(ManagerCourse managerCourse, String remove, Courses course ) {
	course.setClassName(remove);
	managerCourse.RemoveCourse(course);
}
/**
* bat su kien khi click button luu khoa hoc
 */
class CoursesListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {	
		String classname,speciality, department =null;
		try{
			//lstCourses = new ArrayList<>();
			classname = frmCourseFrame.getTxtMaLop();
			speciality = frmCourseFrame.getTxtChuyenNganh();
			department= frmCourseFrame.getTxtKhoaHoc();
            if(classname.equals("")||speciality.equals("")||department.equals("")) {
            	JOptionPane.showMessageDialog(null, " Fiels can not empty , please retype values");
            	return;
            } else {
    			course.setClassName(classname);
    			course.setSpeciality(speciality);
    			course.setDepartment(department);	
    			managerCourse.InsertIntoDatabase(course);
    			
            }
		}
		catch(Exception ex){
			
		}
	}
}
/**
* bat su kien khi click button luu ds sinh viên
 */
class StudentsListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {	
		String hoVaTen, diaChi, lopHoc, gioiTinh ,soCMT, soPhone=null;

		try{
			//lstCourses = new ArrayList<>();
			hoVaTen = frmStudentFrame.GetHoVaTen();
			diaChi = frmStudentFrame.GetDiaChi();
			soCMT = frmStudentFrame.GetSoCmt();
			soPhone = frmStudentFrame.GetSoPhone();
			gioiTinh = frmStudentFrame.GetCheckBox();
			lopHoc =frmStudentFrame.GetCombobox();
			if(hoVaTen.equals("")||diaChi.equals("")||soCMT.equals("")||soPhone.equals("")||gioiTinh.equals("")||lopHoc.equals("")) {
				JOptionPane.showMessageDialog(null, " Fiels can not empty , please retype values");
				return;
			}else if(soPhone.length()==10&& CheckFielsPhoneIsNumber()){
				// System.out.println(CheckFielsExistOnDatabase());
				student.setStudentName(hoVaTen);
				student.setAddress(diaChi);
				student.setIdCard(soCMT);
				student.setPhone(soPhone);
				student.setGender(gioiTinh);
				student.setclassName(lopHoc);
			//	System.out.println(lopHoc);
				if(InsertOrUpdate.equals("INSERT") ){
					CheckFielsExistOnDatabase();
					//System.out.println(" qua rôi");
					if(!CheckSocmtAndTenLop) {if(CheckSocmtAndTenSinhVien) {
						course.InsertIntoDatabase(student);
					}else JOptionPane.showMessageDialog(null, " IDCard has been used for another StudentName");
					}else JOptionPane.showMessageDialog(null, "IDCard has registered for one CourseName");
				}else {
                          course.UpdateValuesDatabase(student);
				       }
			}else {JOptionPane.showMessageDialog(null, "Format of number phone is false "); return;}
		}
		catch(Exception ex){

		}
	}
}

/**
 * Hàm switch danh sách các khóa học trong data về combox 
 */
public JComboBox<String> ReturnValuesCourseToJcombobox() {
	Connection jconn;
	SQLLibrary sqlLib = new SQLLibrary();
	try {
		jconn = sqlLib.SQLOpenConnection("localhost", "huynt", "271190", "MANAGERSTUDENT", 1433);// truyền tham số truyền vào vào trỏ đến 1 data base nào đó 
		if (!jconn.isClosed()) {
			ResultSet objRs;
			CallableStatement objCmst;
			objCmst = jconn.prepareCall("SELECT * from COURSES");
			objRs = objCmst.executeQuery();
			if (objRs != null) {
				while (objRs.next()) {
					combobox.addItem(objRs.getString("CLASSNAME"));
				}
			}

		}
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
	}
	return combobox;
}
/**
 * 
 * @return kiểm tra xem nhập vào số chứng mính và mã lớp học có trùng nhau không nếu có thì ko add vao data
 */
void CheckFielsExistOnDatabase() {
	ControllerPullDatabaseToJtable pull = new ControllerPullDatabaseToJtable();
	int index =0;
	Object[][] objCheck = null;
	objCheck = pull.PullDatabase();
	for(int i=0; i<objCheck.length;i++) {
		if((frmStudentFrame.GetSoCmt().equals(objCheck[i][4].toString()))&&(frmStudentFrame.GetCombobox().equals(objCheck[i][3].toString()))) {
			//	if(frmStudentFrame.GetHoVaTen().equals(objCheck[i][1].toString())){
			CheckSocmtAndTenLop=true;
			break;// khi mà kiểm tra có trong mảng liền break ra vòng lặp ngay lập tức
		}	else {CheckSocmtAndTenLop=false; 
		if(frmStudentFrame.GetSoCmt().equals(objCheck[i][4].toString())) {
			index=i;// nếu trùng nhưng khác tên lớp thì có thể thêm sinh viên nhưng phải so sánh tên sinh viên xem có trùng không nếu ko trùng 
			// thì nhập tên đó sai -> không add yêu cầu nhập lại
			// không break chỗ này
		}

		}
	}

	if(frmStudentFrame.GetHoVaTen().toLowerCase().equals(objCheck[index][1].toString().toLowerCase())){
		CheckSocmtAndTenSinhVien = true;
	}else {CheckSocmtAndTenSinhVien =false;}

}
boolean CheckFielsPhoneIsNumber() {
	boolean checkIsNumber = false;
	for (int i = 0; i < frmStudentFrame.GetSoPhone().length(); i++) {
        if (Character.isLetter(frmStudentFrame.GetSoPhone().charAt(i))) {
            break;
        }
        if (i + 1 == frmStudentFrame.GetSoPhone().length()) {
            checkIsNumber= true;
        }
		
	}
	return checkIsNumber;
}

}
