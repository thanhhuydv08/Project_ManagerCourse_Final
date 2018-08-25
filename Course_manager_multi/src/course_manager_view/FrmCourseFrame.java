package course_manager_view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.Font;
@SuppressWarnings("serial")
public class FrmCourseFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaLop= new JTextField();
	private JTextField txtChuyenNganh= new JTextField();
	private JTextField txtKhoaHoc= new JTextField();
	JButton btnCourtsLuu;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FrmCourseFrame() {
		setTitle("Thêm mới khóa học");
		setBounds(100, 100,400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);
		panel.setBounds(0, 0, 384, 262);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(127, 255, 0), 1, true));
		panel_1.setBackground(SystemColor.text);
		panel_1.setBounds(10, 32, 364, 150);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã lớp");
		lblNewLabel.setBounds(34, 30, 78, 14);
		panel_1.add(lblNewLabel);
		
		
		txtMaLop.setBounds(135, 27, 111, 20);
		panel_1.add(txtMaLop);
		txtMaLop.setColumns(10);
		
		JLabel lblChuynNgnh = new JLabel("Chuyên ngành");
		lblChuynNgnh.setBounds(34, 70, 91, 14);
		panel_1.add(lblChuynNgnh);
		
		
		txtChuyenNganh.setColumns(10);
		txtChuyenNganh.setBounds(135, 67, 159, 20);
		panel_1.add(txtChuyenNganh);
		
		JLabel lblKhaHc = new JLabel("Khóa học");
		lblKhaHc.setBounds(34, 109, 78, 14);
		panel_1.add(lblKhaHc);
		
		
		txtKhoaHoc.setColumns(10);
		txtKhoaHoc.setBounds(135, 106, 159, 20);
		panel_1.add(txtKhoaHoc);
		
		JLabel lbltia = new JLabel("(tối đa 10 kí tự)");
		lbltia.setFont(new Font("Tahoma", Font.ITALIC, 8));
		lbltia.setBounds(256, 30, 68, 14);
		panel_1.add(lbltia);
		
		btnCourtsLuu = new JButton("Lưu");
		btnCourtsLuu.setBackground(SystemColor.text);
		btnCourtsLuu.setBounds(55, 209, 89, 23);
		panel.add(btnCourtsLuu);
		
		JButton btnCourtsHuy = new JButton("Hủy");
		btnCourtsHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMaLop.setText("");
				txtKhoaHoc.setText("");
				txtChuyenNganh.setText("");
			}
		});
		btnCourtsHuy.setBackground(SystemColor.text);
		btnCourtsHuy.setBounds(154, 209, 89, 23);
		panel.add(btnCourtsHuy);
		
		JButton btnCourtsThoat = new JButton("Thoát");
		btnCourtsThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setVisible(false);//ẩn nó đi vi system.exit se dong het cac frame đang mở
			}
		});
		btnCourtsThoat.setBackground(SystemColor.text);
		btnCourtsThoat.setBounds(253, 209, 89, 23);
		panel.add(btnCourtsThoat);
		setVisible(true);
		
	}

	public String getTxtMaLop() {
		return txtMaLop.getText();
	}
	public String getTxtChuyenNganh() {
		return txtChuyenNganh.getText();
	}

	public String getTxtKhoaHoc() {
		return txtKhoaHoc.getText();
	}
public void addCoursesListener(ActionListener listenForCalcButton){	
	btnCourtsLuu.addActionListener(listenForCalcButton);
		
	}
	
}
