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
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import View.*;
import Model.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JRadioButtonMenuItem;



public class addStaffTextField_Fr extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField_Name;
	private JTextField textField_Address;
	private JTextField textField_PhoneNumb;
	private JTextField textField_BoD;
	private JTextField textField_ID;
	private List<Staff> list;
	private JTextField textField_UserName;
	private JTextField textField_Password;
	private JRadioButton rdbtnManager;
	private JRadioButton rdbtnStaff;
	private String pst;
	private String bit;
	
	/**
	 * Launch the application.
	 */
	
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
		setBounds(100, 100, 600, 440);
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
		

		rdbtnManager = new JRadioButton("Manager");
		rdbtnManager.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnManager.setBounds(227, 301, 103, 21);
		rdbtnManager.addActionListener(this);
		contentPane.add(rdbtnManager);
		
		rdbtnStaff = new JRadioButton("Staff");
		rdbtnStaff.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		rdbtnStaff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnStaff.setBounds(347, 301, 103, 21);
		rdbtnStaff.addActionListener(this);
		contentPane.add(rdbtnStaff);
		
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
		
		textField_BoD = new JTextField();
		textField_BoD.setToolTipText("yyyy-mm-dd");
		textField_BoD.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String age = textField_BoD.getText();
				String reg = "^(0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])[\\/\\-]\\d{4}$";
				
				if(age.matches(reg)) {
				}
				else {
					JOptionPane.showMessageDialog(null,"Sai định dạng thời gian (mm/dd/yyyy)!");
					textField_BoD.requestFocus();
					return;
				}
				
			}
		});
		textField_BoD.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_BoD.setColumns(10);
		textField_BoD.setBounds(227, 101, 251, 30);
		contentPane.add(textField_BoD);
		
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
				}else if(textField_BoD.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"BoD không được rỗng!");
					textField_BoD.requestFocus();
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
					if(e.getSource()==rdbtnManager) {
						pst = "Manager";
					}else if(e.getSource()==rdbtnStaff){
						pst = "Staff";
					}
					
					ArrayList<Staff> list = new ArrayList<>();
					s = new Staff();
					s.setID(Integer.parseInt(textField_ID.getText()));
					s.setName(textField_Name.getText());
					s.setBoD(textField_BoD.getText());
					s.setAddress(textField_Address.getText());
					s.setPhoneNumb(textField_PhoneNumb.getText());
					s.setUserName(textField_UserName.getText());
					s.setPwd(textField_Password.getText());
					s.setPosition(pst);
					list.add(s);	
					
					mainManagerFr_Ver2.AddRowToJTableStaff(new Object[]{
						s.getID(),
						s.getName(),
						s.getBoD(),
						s.getAddress(),
						s.getPhoneNumb(),
						s.getUserName(),
						s.getPwd(),
						s.getPosition(),
				});
					bit = "";
					if(rdbtnManager.isSelected()) {
						bit = "1";
					}else if(rdbtnStaff.isSelected()){
						bit = "0";
					}
					
					try {
						Connection cn = DBConnection.getConnection();
						
						String query = "insert into Employees(EmpID,EmpName,BoD,EmpAddress,PhoneNum,Username,pw,position) values (?,?,?,?,?,?,?,?)";
						PreparedStatement ps = cn.prepareStatement(query);
						ps.setString(1, textField_ID.getText());
						ps.setString(2, textField_Name.getText());
						ps.setString(3, textField_BoD.getText());
						ps.setString(4, textField_Address.getText());
						ps.setString(5, textField_PhoneNumb.getText());
						ps.setString(6, textField_UserName.getText());
						ps.setString(7, textField_Password.getText());
						ps.setString(8, bit);
						ps.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					mainManagerFr_Ver2.showDataStaff();
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSave.setBounds(141, 356, 105, 35);
		contentPane.add(btnSave);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_ID.setText(null);
				textField_Name.setText(null);
				textField_BoD.setText(null);
				textField_Address.setText(null);
				textField_PhoneNumb.setText(null);
				textField_UserName.setText(null);
				textField_Password.setText(null);
			}
		});
		btnReset.setBackground(new Color(255, 255, 255));
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnReset.setBounds(358, 356, 105, 35);
		contentPane.add(btnReset);
		
		textField_UserName = new JTextField();
		textField_UserName.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_UserName.setColumns(10);
		textField_UserName.setBounds(227, 221, 251, 30);
		contentPane.add(textField_UserName);
		
		textField_Password = new JTextField();
		textField_Password.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_Password.setColumns(10);
		textField_Password.setBounds(227, 261, 251, 30);
		contentPane.add(textField_Password);
		
		JLabel lblUserName = new JLabel("Username", SwingConstants.RIGHT);
		lblUserName.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblUserName.setBounds(91, 221, 112, 30);
		contentPane.add(lblUserName);
		
		JLabel lblPwd = new JLabel("Password", SwingConstants.RIGHT);
		lblPwd.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblPwd.setBounds(91, 261, 112, 30);
		contentPane.add(lblPwd);
		
		JLabel lblPosition = new JLabel("Position", SwingConstants.RIGHT);
		lblPosition.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblPosition.setBounds(91, 297, 112, 30);
		contentPane.add(lblPosition);
		
		ButtonGroup gr = new ButtonGroup();
		gr.add(rdbtnStaff);
		gr.add(rdbtnManager);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
