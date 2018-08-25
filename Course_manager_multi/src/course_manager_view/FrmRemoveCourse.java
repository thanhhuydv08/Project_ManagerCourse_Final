package course_manager_view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import course_manager_controller.MainController;
import course_manager_model.Courses;
import course_manager_model.ManagerCourse;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRemoveCourse extends JFrame {

	private JPanel contentPane;
	JComboBox <String >comboBox ;
	public FrmRemoveCourse() {
		MainController scontroller = new MainController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Remove Course on Database");
		setBounds(100, 100, 296, 131);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = scontroller.ReturnValuesCourseToJcombobox();
		comboBox.setBounds(108, 11, 112, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Tên Lớp");
		lblNewLabel.setBounds(32, 14, 66, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnRemoveCourse = new JButton("Remove");
		btnRemoveCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String remove = comboBox.getSelectedItem().toString();	
			Courses course = new Courses();
            ManagerCourse managerCourse = new ManagerCourse();
            MainController mainController = new MainController(managerCourse, remove, course);
				
			}
		});
		btnRemoveCourse.setBounds(55, 59, 89, 23);
		contentPane.add(btnRemoveCourse);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnThot.setBounds(159, 59, 89, 23);
		contentPane.add(btnThot);
		setVisible(true);
	}
}
