package course_manager_model;

public class Students extends Abstract{
private String studentName;
private String address;
private String idCard;
private String phone;
private String gender;
private String className; 
private int stt;

public Students() {

}
public Students(String studentName,String address,String idCard,String phone,String gender,String className ) {
 this.address=address;	
 this.studentName=studentName;	
 this.idCard=idCard;	
 this.phone=phone;	
 this.gender=gender;
 this.className=className;
 //this.stt=stt;
}
public int getStt() {
	return stt;
}
public void setStt(int stt) {
	this.stt = stt;
}
public String getclassName() {
	return className;
}
public void setclassName(String className) {
	this.className = className;
}
public String getStudentName() {
	return studentName;
}

public void setStudentName(String studentName) {
	this.studentName = studentName;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getIdCard() {
	return idCard;
}

public void setIdCard(String idCard) {
	this.idCard = idCard;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

@Override
public String toString() {
	return "Students [studentName=" + studentName + ", address=" + address + ", idCard=" + idCard + ", phone=" + phone
			+ ", gender=" + gender +"]";
}

}
