package course_manager_view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import course_manager_controller.ControllerPullDatabaseToJtable;
import course_manager_controller.MainController;
import course_manager_model.Courses;
import course_manager_model.ManagerCourse;
import course_manager_model.Students;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
public class FrmMenubarTableFrame extends JFrame {
	/**
	 * khai bao thuoc tinh jmenubar
	 */
	private JMenuBar menubar;
	private JMenu fileMenu;
	private JMenu course;
	private JMenu refresh;
	private JMenuItem refrItem;
	private JMenuItem addItem;
	private JMenuItem removeItem;
	private JMenuItem openItem;
	private JMenuItem exitItem;
	/**
	 * khai bao thuoc tinh jtable
	 */
	private Object [] titleJtable;
	private Object[][] valuesColums;
	private JTable table;
	private JButton btnThemSv;
	private JButton btnXoa ;
	private JButton btnSua;
	DefaultTableModel dtm ;
//public static void main(String[] args) {
//	FrmMixJmenuAndJtable ccc= new FrmMixJmenuAndJtable();
//}

	public FrmMenubarTableFrame() {
		JFrame jFrame = new JFrame();

		getContentPane().setBackground(SystemColor.text);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("System management students year 2018"); //2 cái này là của jframe
		setSize(680, 380); //2 cái này là của jframe
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);// sét ở giua man hình de frame2 nam trong frame nay
		/**
		 * design jmenubar
		 */
		menubar = new JMenuBar();
		fileMenu= new JMenu("File");
		openItem = new JMenuItem("Open");
		exitItem = new JMenuItem("Exit");
		course = new JMenu("Courses");
		addItem = new JMenuItem("AddCourses..");
		refresh = new JMenu("Refresh");
		refrItem = new JMenuItem("RefreshTable");
		refrItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefreshJtable();// ấn nút refresh để load lại bảng dữ liệu
			}
		});
		
		/**
		 * Khi click chọn AddCourse thì gọi frm frmCourseFrame để nhập thông tin khóa học
		 */
		
		addItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCourseFrame frmCourseFrame = new FrmCourseFrame();
				frmCourseFrame.setLocationRelativeTo(jFrame); // yeu cau frame frmCourseFramenam trong frame chính
				frmCourseFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// chỉ đong frame frmCourseFrame thôi các frame khac ko đóng
				Courses  course = new Courses();// hàm contractor ko làm gì
				ManagerCourse managerCourse = new ManagerCourse();// hàm contractor ko làm gì
				MainController control = new MainController(frmCourseFrame,course,managerCourse);
				
			}
		});
		removeItem = new JMenuItem("RemoveCourses..");
		removeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// khi remove khóa học sẽ xóa sinh viên của khóa học đó luôn
				FrmRemoveCourse frmRemoveCourse = new FrmRemoveCourse();
				
			}
		});
		fileMenu.add(openItem);
		fileMenu.add(exitItem);
		course.add(addItem);
		course.add(removeItem);
		refresh.add(refrItem);
		menubar.add(fileMenu);
		menubar.add(course);
		menubar.add(refresh);
		this.setJMenuBar(menubar); //vào ham chinh thực thi cái này
		/**
		 * design jtable and scropanel
		 */		
		getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(238, 232, 170));
		scrollPane.setBounds(10, 11, 644, 253);
		getContentPane().add(scrollPane);	
		table = new JTable();
		RefreshJtable();// gọi hàm để refresh bẳng 
		scrollPane.setViewportView(table);
		
		btnThemSv = new JButton("Thêm");
		btnThemSv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmStudentFrame addStudentsView = new FrmStudentFrame();
				addStudentsView.setLocationRelativeTo(jFrame); // yeu cau frame frmCourseFramenam trong frame chính
				addStudentsView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// chỉ đong frame frmCourseFrame thôi các frame khac ko đóng
				Courses  course = new Courses();
				Students courseStudents = new Students();
				MainController controltudents = new MainController(addStudentsView, courseStudents, course, "INSERT");
		
			}
		});
		btnThemSv.setBounds(107, 275, 89, 23);
		getContentPane().add(btnThemSv);
		
		btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(table.getSelectedRow()>-1) {
						FrmStudentFrame addStudentsView = new FrmStudentFrame();// gọi tới bảng luôn và ngay
						addStudentsView.setLocationRelativeTo(jFrame); // yeu cau frame frmCourseFramenam trong frame chính
						addStudentsView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// chỉ đong frame frmCourseFrame thôi các frame khac ko đóng
				//	id1 =(int) table.getValueAt(table.getSelectedRow(), 0);// nếu chọn nhiều rows thì phải làm ntn
						addStudentsView.txtHoVaTen.setText((String) table.getValueAt(table.getSelectedRow(), 1));
						addStudentsView.txtDiaChi.setText((String) table.getValueAt(table.getSelectedRow(), 2));
						addStudentsView.comboBox.removeAllItems();
						addStudentsView.comboBox.addItem((String) table.getValueAt(table.getSelectedRow(), 3)); // hiển thị 1 cái cần sửa thôi
						addStudentsView.txtCmtSv.setText((String) table.getValueAt(table.getSelectedRow(), 4));
						addStudentsView.txtSoPhone.setText((String) table.getValueAt(table.getSelectedRow(), 5));
						addStudentsView.cbCheck=((String) table.getValueAt(table.getSelectedRow(), 6));//ko cần hiển thị thằng này vi nó dang co
						Courses  course = new Courses();
						Students courseStudents = new Students();
						MainController controltudents = new MainController(addStudentsView, courseStudents, course, "UPDATE");

					}else JOptionPane.showMessageDialog(null, " Bạn chưa chọn dòng cần sữa, vui lòng chọn lại");
					
				
			}
		});
		btnSua.setBounds(226, 275, 89, 23);
		getContentPane().add(btnSua);
		
		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Students st2 = new Students();// chu y trung doi tuong dan den xoa nguyen database
			//	int id1 =0;
				if(table.getSelectedRow()>-1) {
			//	id1 =(int) table.getValueAt(table.getSelectedRow(), 0);// nếu chọn nhiều rows thì phải làm ntn
				st2.setIdCard((String) table.getValueAt(table.getSelectedRow(), 4));
				st2.setclassName((String) table.getValueAt(table.getSelectedRow(), 3));
				int ret = JOptionPane.showConfirmDialog(rootPane, "Bạn muốn xóa không ", "xác nhận ", JOptionPane.YES_NO_OPTION);
				if(ret == JOptionPane.YES_OPTION) {	// 
				dtm.removeRow(table.getSelectedRow());// xoa rows chon ra bang
				}
				}else JOptionPane.showMessageDialog(null, " Bạn chưa chọn dòng cần xóa, vui lòng chọn lại");
				Courses  course = new Courses();
				MainController controltudents = new MainController(st2, course);// gọi tới control để đẫy dữ liệu lên data
			}
		});
		btnXoa.setBounds(345, 275, 89, 23);
		getContentPane().add(btnXoa);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);			}
		});
		btnThoat.setBounds(462, 275, 89, 23);
		getContentPane().add(btnThoat);
	
		setVisible(true);
	}
	/**
	 * 
	 * Tạo 1 mảng 1 chiều các tiêu đề cho bảng jtable
	 */
	
	Object [] titleJtable() {
		titleJtable = new Object[7];
		titleJtable[0]="Stt";
		titleJtable[1]="Họ và tên";
		titleJtable[2]="Địa chỉ";
		titleJtable[3]="Lớp học";
		titleJtable[4]="Số cmt";
		titleJtable[5]="Số đt";
		titleJtable[6]="Giới tính";
		return titleJtable;			
		}
	/**
	 * cài đặt thuộc tính các đối tượng trong jtable - độ rộng cột, màu, độ rộng, nền,
	 */
	void SetZiseColums() {
		table.setSelectionForeground(new Color(255, 0, 0));// chọn thì màu chuyển sang đỏ
		table.setAutoCreateRowSorter(true);// tự lọc 
		table.setBorder(new LineBorder(new Color(154, 205, 50), 1, true));
		table.setBackground(new Color(250, 250, 210));
		table.setSelectionBackground(new Color(224, 255, 255));//đổi màu khi chọn
		table.getColumnModel().getColumn(0).setPreferredWidth(28);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(182);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.getColumnModel().getColumn(6).setPreferredWidth(80);
		JTableHeader head = table.getTableHeader();
		head.setBackground(Color.white);// đổi màu title
		head.setForeground(Color.black);// set màu cho title 
		head.setFont(new Font("Times New Roman", Font.BOLD, 15));// set font cho title
		table.setFont(new Font("Times New Roman", Font.PLAIN, 12));// setfont cho bảng
	
	}
	/**
	 * tạo một method để refresh bảng -> khi vừa mở 
	 *                                -> khi thêm thông tin sinh viên
	 */
	
	public void RefreshJtable() {
		ControllerPullDatabaseToJtable pull = new ControllerPullDatabaseToJtable();
		table.removeAll();
		valuesColums = pull.PullDatabase();
		dtm = new DefaultTableModel(valuesColums, titleJtable());
		table.setModel(dtm);
		SetZiseColums();
	}
/**
 * 
 * @param listenForCalcButton xoa danh sach sinh vien ra jtable va database
 */
	public void RemoveStudentsListener(ActionListener listenForCalcButton){	
		btnXoa.addActionListener(listenForCalcButton);
			
		}
}
