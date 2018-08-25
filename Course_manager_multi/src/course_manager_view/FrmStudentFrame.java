package course_manager_view;


import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import course_manager_controller.MainController;
import java.awt.Color;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.ButtonGroup;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
public class FrmStudentFrame extends JFrame implements ItemListener {
	JTextField txtHoVaTen= new JTextField();
	JTextField txtDiaChi= new JTextField();
	JTextField txtCmtSv= new JTextField();
	JTextField txtSoPhone= new JTextField();
	String cbCheck=null;// biến trả về khi click chuột tức gọi hàm
	JCheckBox cbNam;
	JCheckBox cbNu;
	JComboBox<String> comboBox;
	JButton btnLuuSv ;
	JButton btnHuySv ;
	JButton btnThoat ;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public FrmStudentFrame() {
		MainController scontroller = new MainController();
	//	setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(SystemColor.text);
		setTitle("Thêm danh sách các Sinh Viên");
		setBounds(100, 100,400, 300);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(173, 255, 47)));
		panel.setBackground(SystemColor.text);
		panel.setBounds(10, 11, 364, 201);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Họ Và Tên");
		lblNewLabel.setBounds(25, 11, 82, 14);
		panel.add(lblNewLabel);
		
		txtHoVaTen = new JTextField();
		txtHoVaTen.setBorder(new LineBorder(new Color(255, 0, 0)));
		txtHoVaTen.setBounds(117, 8, 146, 20);
		panel.add(txtHoVaTen);
		txtHoVaTen.setColumns(10);
		
		JLabel lblaCh = new JLabel("Địa Chỉ");
		lblaCh.setBounds(25, 39, 82, 14);
		panel.add(lblaCh);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setBorder(new LineBorder(new Color(255, 0, 0)));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(117, 36, 180, 20);
		panel.add(txtDiaChi);
		
		JLabel lblSSoCMT = new JLabel("Số CMND");
		lblSSoCMT.setBounds(25, 67, 82, 14);
		panel.add(lblSSoCMT);
		
		txtCmtSv = new JTextField();
		txtCmtSv.setBorder(new LineBorder(new Color(255, 0, 0)));
		txtCmtSv.setColumns(10);
		txtCmtSv.setBounds(117, 64, 128, 20);
		panel.add(txtCmtSv);
		
		JLabel lblSPhone = new JLabel("Số Phone");
		lblSPhone.setBounds(25, 95, 66, 14);
		panel.add(lblSPhone);
		
		txtSoPhone = new JTextField();
		txtSoPhone.setBorder(new LineBorder(new Color(255, 0, 0)));
		txtSoPhone.setColumns(10);
		txtSoPhone.setBounds(117, 92, 98, 20);
		panel.add(txtSoPhone);
		
		JLabel lblGiiTnh = new JLabel("Giới Tính");
		lblGiiTnh.setBounds(25, 120, 86, 14);
		panel.add(lblGiiTnh);
		
		JLabel lblTnLp = new JLabel("Tên Lớp");
		lblTnLp.setBounds(25, 153, 66, 14);
		panel.add(lblTnLp);
		
		cbNam = new JCheckBox("Nam");
		cbNam.addItemListener(this);
		buttonGroup.add(cbNam);
		cbNam.setBackground(SystemColor.text);
		cbNam.setBounds(117, 119, 53, 23);
		panel.add(cbNam);
		
		cbNu = new JCheckBox("Nữ");
		cbNu.addItemListener(this);
		buttonGroup.add(cbNu);
		cbNu.setBackground(SystemColor.text);
		cbNu.setBounds(192, 119, 53, 23);
		panel.add(cbNu);
		
	   
		comboBox = scontroller.ReturnValuesCourseToJcombobox();// khong duoc gọi đối tượng comboBox = new JComboBox();
		comboBox.setBorder(new LineBorder(new Color(255, 0, 0)));
		comboBox.setBounds(117, 150, 119, 20);
        
		panel.add(comboBox);
		
		btnLuuSv = new JButton("Lưu");
		btnLuuSv.setBackground(UIManager.getColor("Button.highlight"));
		btnLuuSv.setBounds(53, 223, 89, 23);
		getContentPane().add(btnLuuSv);
		
		btnHuySv = new JButton("Hủy");
		btnHuySv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCmtSv.setText("");
				txtDiaChi.setText("");
				txtHoVaTen.setText("");
				txtSoPhone.setText("");
			}
		});
		btnHuySv.setBackground(Color.WHITE);
		btnHuySv.setBounds(152, 223, 89, 23);
		getContentPane().add(btnHuySv);
		
		btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnThoat.setBackground(Color.WHITE);
		btnThoat.setBounds(251, 223, 89, 23);
		getContentPane().add(btnThoat);
		setVisible(true);
	}
public String GetHoVaTen() {
	return txtHoVaTen.getText();
}

public String GetDiaChi() {
	return txtDiaChi.getText();
}
public String GetSoPhone() {
	return txtSoPhone.getText(); // vi muốn là 1 kiểu int để truyền vào data
}
public String GetSoCmt() {
	return txtCmtSv.getText();// vi muốn là 1 kiểu int để truyền vào data
}
public String GetCheckBox() {
	return cbCheck;
}
public String GetCombobox() {
	return comboBox.getSelectedItem().toString();
}

/**
 * Hàm bắt sự kiện khi click vào checkbox và biến nội thân hàm cbCheck sẽ mang một giá trị nào đó
 * nhưng phải khai báo thêm cbNam.addItemListener(this);cbNu.addItemListener(this);
 */
@Override
public void itemStateChanged(ItemEvent e) {
if(e.getItemSelectable()==cbNam) {
	cbCheck =cbNam.getText();
}else {cbCheck = cbNu.getText();}
}
/**
 * 
 * @param listenForCalcButton : hàm add sv trong controller để lưu lên database
 */

public void addStudentsListener(ActionListener listenForCalcButton){	
	btnLuuSv.addActionListener(listenForCalcButton);
		
	}


}
