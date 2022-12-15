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



public class show_update_RowDataStaff_Fr extends JFrame implements ActionListener {

	private JPanel contentPane;
	public JTextField textField_Name;
	public JTextField textField_Address;
	public JTextField textField_PhoneNumb;
	public JTextField textField_BoD;
	public JTextField textField_ID;
	public JTextField textField_UserName;
	public JTextField textField_Password;
	public JRadioButton rdbtnManager;
	public JRadioButton rdbtnStaff;
	private String pst;
	public String bit;
	
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					show_update_RowDataStaff_Fr frame = new show_update_RowDataStaff_Fr();
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
	
	
	public show_update_RowDataStaff_Fr() {
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
				String reg = "\\d{4}[-]\\d{1,2}[-]\\d{1,2}$";
				
				if(age.matches(reg)) {
				}
				else {
					JOptionPane.showMessageDialog(null,"Sai định dạng Ngày sinh (yyyy-mm-dd)!");
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
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection cn = DBConnection.getConnection();
					
					bit = "";
					if(rdbtnManager.isSelected()) {
						bit = "1";
					}else if(rdbtnStaff.isSelected()){
						bit = "0";
					}
					
					String id = textField_ID.getText();
					String name = textField_Name.getText();
					String bod = textField_BoD.getText();
					String address = textField_Address.getText();
					String phone = textField_PhoneNumb.getText();
					String username = textField_UserName.getText();
					String pass = textField_Password.getText();
					String position = bit;
					
					String query = "UPDATE Employees SET EmpName = N'" + name + "', BoD = '"+ bod +"', EmpAddress = N'"+ address +"',PhoneNum = '"+ phone +"',username ='"+username+"',pw ='"+pass+"',position ='"+position+"' WHERE EmpID = "+ id +"";
				
					PreparedStatement ps = cn.prepareStatement(query);
					
					ps.execute();
					
					mainManagerFr_Ver2.showDataStaff();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Monospaced", Font.BOLD, 20));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(241, 341, 115, 35);
		contentPane.add(btnUpdate);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
