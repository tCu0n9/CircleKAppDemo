package Controller;

import java.awt.EventQueue;

import View.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DBConnection.DBConnection;
import Model.Customers;
import Model.Staff;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
public class addCustomerTextField_Fr extends JFrame {

	
	private JPanel contentPane;
	private JTextField textField_ID;
	private JTextField textField_Name;
	private JTextField textField_Address;
	private JTextField textField_PhoneNumb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addCustomerTextField_Fr frame = new addCustomerTextField_Fr();
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
	//trùng dữ liệu
	private boolean checkTrungID() {
		
		
		try {
			Connection cn = DBConnection.getConnection();
		
			String query = "select * from dbo.[Customer] where CustomerID = ?";
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
	
	public addCustomerTextField_Fr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(600,301);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMNhnVin = new JLabel("Customer ID", SwingConstants.RIGHT);
		lblMNhnVin.setBounds(85, 23, 111, 30);
		lblMNhnVin.setFont(new Font("Monospaced", Font.BOLD, 14));
		contentPane.add(lblMNhnVin);
		
		textField_ID = new JTextField();
		textField_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char k=e.getKeyChar();
				
				if(Character.isLetter(k)) {
					JOptionPane.showMessageDialog(null, "Chỉ nhập số!!");
					textField_ID.setText(null);
					textField_ID.requestFocus();
				}
			}
		});
		textField_ID.setBounds(215, 23, 251, 30);
		textField_ID.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_ID.setColumns(10);
		contentPane.add(textField_ID);
		
		textField_Name = new JTextField();
		textField_Name.setBounds(215, 63, 251, 30);
		textField_Name.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_Name.setColumns(10);
		contentPane.add(textField_Name);
		
		textField_Address = new JTextField();
		textField_Address.setBounds(215, 103, 251, 30);
		textField_Address.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_Address.setColumns(10);
		contentPane.add(textField_Address);
		
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
		textField_PhoneNumb.setBounds(215, 143, 251, 30);
		textField_PhoneNumb.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_PhoneNumb.setColumns(10);
		contentPane.add(textField_PhoneNumb);
		
		JLabel lblName = new JLabel("Full Name", SwingConstants.TRAILING);
		lblName.setBounds(99, 63, 92, 30);
		lblName.setFont(new Font("Monospaced", Font.BOLD, 14));
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address", SwingConstants.TRAILING);
		lblAddress.setBounds(99, 103, 97, 30);
		lblAddress.setFont(new Font("Monospaced", Font.BOLD, 14));
		contentPane.add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number", SwingConstants.TRAILING);
		lblPhoneNumber.setBounds(90, 143, 106, 30);
		lblPhoneNumber.setFont(new Font("Monospaced", Font.BOLD, 14));
		contentPane.add(lblPhoneNumber);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			private Customers c;

			public void actionPerformed(ActionEvent e) {
				if(textField_ID.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"ID không được rỗng!");
					textField_ID.requestFocus();
					return;
				}else if(textField_Name.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Name không được rỗng!");
					textField_Name.requestFocus();
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
					ArrayList<Customers> list = new ArrayList<>();
					c = new Customers();
					c.setCustomerID(Integer.parseInt(textField_ID.getText()));
					c.setName(textField_Name.getText());
					c.setAddress(textField_Address.getText());
					c.setPhoneNumber(textField_PhoneNumb.getText());
					list.add(c);
					
					
					mainManagerFr_Ver2.AddRowToJTableCustomer(new Object[]{
							c.getCustomerID(),
							c.getName(),
							c.getAddress(),
							c.getPhoneNumber(),
					});
					
					try {
						Connection cn = DBConnection.getConnection();
						String query = "insert into Customer(CustomerID,CustomerName,CustomerAddress,CustomerPhone) values(?,?,?,?)";
						PreparedStatement ps = cn.prepareStatement(query);
						ps.setString(1, textField_ID.getText());
						ps.setString(2, textField_Name.getText());
						ps.setString(3, textField_Address.getText());
						ps.setString(4, textField_PhoneNumb.getText());
						ps.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSave.setBounds(135, 197, 105, 35);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSave.setBackground(Color.WHITE);
		contentPane.add(btnSave);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(361, 197, 105, 35);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_ID.setText(null);
				textField_Name.setText(null);
				textField_Address.setText(null);
				textField_PhoneNumb.setText(null);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnReset.setBackground(Color.WHITE);
		contentPane.add(btnReset);
	}
}
