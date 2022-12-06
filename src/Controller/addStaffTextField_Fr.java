package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.EventTarget;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import DBConnection.DBConnection;
import View.mainManagerFr_Ver2;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Event;

import javax.lang.model.element.NestingKind;
import javax.swing.AbstractButton;
import javax.swing.JButton;

import View.*;
import Model.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class addStaffTextField_Fr extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Name;
	private JTextField textField_Address;
	private JTextField textField_PhoneNumb;
	private JTextField txtYyyymmdd;
	private JTextField textField_ID;
	private List<Staff> list;
	StaffDAO staffDAO = new StaffDAO();
	
	/**
	 * Launch the application.
	 */
	
	public Staff getModel() {
		Staff st = new Staff();
		st.setID(Integer.parseInt(textField_ID.getText().toString().trim()));
		st.setName(textField_Name.getText().toString().trim());
		st.setBoD(txtYyyymmdd.getText().toString().trim());
		st.setAddress(textField_Address.getText().toString().trim());
		st.setPhoneNumb(textField_PhoneNumb.getText().toString().trim());
		return st;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addStaffTextField_Fr frame = new addStaffTextField_Fr();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private boolean checkTrungID() {
		try {
			Connection cn = DBConnection.getConnection();
		
			String query = "select * from dbo.[Employees] where EmpID = ?";
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setString(1, textField_ID.getText());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public addStaffTextField_Fr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		//setUndecorated(true);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Full Name",SwingConstants.RIGHT);
		lblName.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblName.setBounds(123, 61, 80, 30);
		contentPane.add(lblName);
		
		textField_Name = new JTextField();
		textField_Name.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_Name.setBounds(227, 61, 251, 30);
		contentPane.add(textField_Name);
		textField_Name.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Address", SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblNewLabel_1.setBounds(141, 141, 62, 30);
		contentPane.add(lblNewLabel_1);
		
		textField_Address = new JTextField();
		textField_Address.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_Address.setColumns(10);
		textField_Address.setBounds(227, 141, 251, 30);
		contentPane.add(textField_Address);
		
		JLabel lblNewLabel_2 = new JLabel("Phone Number", SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblNewLabel_2.setBounds(91, 181, 112, 30);
		contentPane.add(lblNewLabel_2);
		
		textField_PhoneNumb = new JTextField();
		textField_PhoneNumb.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String PhoneNumb = textField_PhoneNumb.getText();
				String reg = "^\\d{10}$";
				if(PhoneNumb.matches(reg)) {
					
				}
				else {
					JOptionPane.showMessageDialog(null,"Sai định dạng số điện thoại(10 chữ số) !");
					textField_PhoneNumb.requestFocus();
					return;
				}
				
			}
		});
		textField_PhoneNumb.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_PhoneNumb.setColumns(10);
		textField_PhoneNumb.setBounds(227, 181, 251, 30);
		contentPane.add(textField_PhoneNumb);
		
		JLabel lblBoD = new JLabel("BoD", SwingConstants.RIGHT);
		lblBoD.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblBoD.setBounds(168, 101, 35, 30);
		contentPane.add(lblBoD);
		
		txtYyyymmdd = new JTextField();
		txtYyyymmdd.setToolTipText("yyyy-mm-dd");
		txtYyyymmdd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String age = txtYyyymmdd.getText();
				String reg = "\\d{4}[-]\\d{1,2}[-]\\d{1,2}$";
				
				if(age.matches(reg)) {
				}
				else {
					JOptionPane.showMessageDialog(null,"Sai định dạng Ngày sinh (yyyy-mm-dd)!");
					txtYyyymmdd.requestFocus();
					return;
				}
				
			}
		});
		txtYyyymmdd.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtYyyymmdd.setColumns(10);
		txtYyyymmdd.setBounds(227, 101, 251, 30);
		contentPane.add(txtYyyymmdd);
		
		textField_ID = new JTextField();
		textField_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Chỉ nhập số!!");
					textField_ID.setText(null);
					textField_ID.requestFocus();
				}

			}
		});
		textField_ID.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_ID.setColumns(10);
		textField_ID.setBounds(227, 21, 251, 30);
		contentPane.add(textField_ID);
		
		JLabel lblMNhnVin = new JLabel("Staff ID", SwingConstants.RIGHT);
		lblMNhnVin.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblMNhnVin.setBounds(102, 21, 104, 30);
		contentPane.add(lblMNhnVin);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(new Color(255, 255, 255));
		btnSave.addActionListener(new ActionListener() {

			private Staff s;

			public void actionPerformed(ActionEvent e) {
				if(textField_ID.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"ID không được rỗng!");
					textField_ID.requestFocus();
					return;
				}else if(textField_Name.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Name không được rỗng!");
					textField_Name.requestFocus();
					return;
				}else if(txtYyyymmdd.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"BoD không được rỗng!");
					txtYyyymmdd.requestFocus();
					return;
				}else if(textField_Address.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Address không được rỗng!");
					textField_Address.requestFocus();
					return;
				}else if(textField_PhoneNumb.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Phone Number không được rỗng!");
					textField_PhoneNumb.requestFocus();
					return;
				}else if(checkTrungID() == true){
					JOptionPane.showMessageDialog(null,"ID đã tồn tại!");
					textField_ID.requestFocus();
					return;
				}else {
					ArrayList<Staff> list = new ArrayList<>();
					s = new Staff();
					s.setID(Integer.parseInt(textField_ID.getText()));
					s.setName(textField_Name.getText());
					s.setBoD(txtYyyymmdd.getText());
					s.setAddress(textField_Address.getText());
					s.setPhoneNumb(textField_PhoneNumb.getText());
					list.add(s);
					
					
					mainManagerFr_Ver2.AddRowToJTableStaff(new Object[]{
						s.getID(),
						s.getName(),
						s.getBoD(),
						s.getAddress(),
						s.getPhoneNumb(),
				});
					
					try {
						Connection cn = DBConnection.getConnection();
						String query = "insert into employees(EmpID,EmpName,BoD,EmpAddress,PhoneNum) values(?,?,?,?,?)";
						PreparedStatement ps = cn.prepareStatement(query);
						ps.setString(1, textField_ID.getText());
						ps.setString(2, textField_Name.getText());
						ps.setString(3, txtYyyymmdd.getText());
						ps.setString(4, textField_Address.getText());
						ps.setString(5, textField_PhoneNumb.getText());
						ps.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				/*Staff st = getModel();
				if (staffDAO.add(st)>0) {
					JOptionPane.showMessageDialog(null, "Add new staff completed!");
				}*/
			}
			
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSave.setBounds(159, 238, 105, 35);
		contentPane.add(btnSave);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_ID.setText(null);
				textField_Name.setText(null);
				txtYyyymmdd.setText(null);
				textField_Address.setText(null);
				textField_PhoneNumb.setText(null);
			}
		});
		btnReset.setBackground(new Color(255, 255, 255));
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnReset.setBounds(314, 238, 105, 35);
		contentPane.add(btnReset);
	}
}
