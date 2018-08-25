package course_manager_controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import course_manager_model.Students;

public class ControllerPullDatabaseToJtable {
	/**
	 * kết nối database và thêm thông tin vào table của database -> add 1 vertor để lấy độ dài rồi add vào mảng 2 chiều để xử lý jtable
	 */
Object[][] rows ;
Vector<Students> vStudents;
public Object[][] PullDatabase() {
		SQLLibrary sqlL= new SQLLibrary();
		vStudents = new Vector<>();
		Connection connect = sqlL.SQLOpenConnection("localhost", "huynt", "271190", "MANAGERSTUDENT", 1433);
		ResultSet records;
		CallableStatement statement;
		try {
			statement = connect.prepareCall("SELECT * FROM STUDENTS");
			records= statement.executeQuery();
			if (records != null) {
				int i=1;
				while (records.next()) {	
					Students st = new Students();
				    st.setStt(i);// gán stt cho nó theo bài tập
					st.setStudentName(records.getString("STUDENTNAME"));
					st.setAddress(records.getString("ADDRES"));
					st.setclassName(records.getString("CLASSNAME"));
					st.setIdCard(records.getString("IDCARD"));
			 		st.setPhone(records.getString("PHONE"));
					st.setGender(records.getString("GENDER"));
                    vStudents.add(st);
                    i++;
				}
				rows= new Object[vStudents.size()][7];
				for(int j=0;j<vStudents.size();j++) {
					rows[j][0]=vStudents.get(j).getStt();
					rows[j][1]=vStudents.get(j).getStudentName();
					rows[j][2]=vStudents.get(j).getAddress();
					rows[j][3]=vStudents.get(j).getclassName();
					rows[j][4]=vStudents.get(j).getIdCard();
					rows[j][5]=vStudents.get(j).getPhone();
					rows[j][6]=vStudents.get(j).getGender();
				}
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return rows;
	}
}