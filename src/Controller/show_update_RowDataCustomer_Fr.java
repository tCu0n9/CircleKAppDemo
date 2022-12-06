package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DBConnection.DBConnection;
import View.mainManagerFr_Ver2;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class show_update_RowDataCustomer_Fr extends JFrame {

	private JPanel contentPane;
	public JTextField textField_ID;
	public JTextField textField_Name;
	public JTextField textField_Address;
	public JTextField textField_PhoneNumb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					show_update_RowDataCustomer_Fr frame = new show_update_RowDataCustomer_Fr();
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
	public show_update_RowDataCustomer_Fr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMNhnVin = new JLabel("Customer ID", SwingConstants.RIGHT);
		lblMNhnVin.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblMNhnVin.setBounds(31, 30, 111, 30);
		contentPane.add(lblMNhnVin);
		
		JLabel lblName = new JLabel("Full Name", SwingConstants.TRAILING);
		lblName.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblName.setBounds(50, 70, 92, 30);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address", SwingConstants.TRAILING);
		lblAddress.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblAddress.setBounds(45, 111, 97, 30);
		contentPane.add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number", SwingConstants.TRAILING);
		lblPhoneNumber.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblPhoneNumber.setBounds(36, 151, 106, 30);
		contentPane.add(lblPhoneNumber);
		
		textField_ID = new JTextField();
		textField_ID.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_ID.setColumns(10);
		textField_ID.setBounds(152, 30, 251, 30);
		contentPane.add(textField_ID);
		
		textField_Name = new JTextField();
		textField_Name.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_Name.setColumns(10);
		textField_Name.setBounds(152, 70, 251, 30);
		contentPane.add(textField_Name);
		
		textField_Address = new JTextField();
		textField_Address.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_Address.setColumns(10);
		textField_Address.setBounds(152, 110, 251, 30);
		contentPane.add(textField_Address);
		
		textField_PhoneNumb = new JTextField();
		textField_PhoneNumb.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_PhoneNumb.setColumns(10);
		textField_PhoneNumb.setBounds(152, 151, 251, 30);
		contentPane.add(textField_PhoneNumb);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection cn = DBConnection.getConnection();
					
					String id = textField_ID.getText();
					String name = textField_Name.getText();
					String address = textField_Address.getText();
					String phone = textField_PhoneNumb.getText();
					
					String query = "UPDATE Customer SET CustomerName = N'" + name + "', CustomerAddress = N'"+ address +"',CustomerPhone = '"+ phone +"' WHERE CustomerID = "+ id +"";
				
					PreparedStatement ps = cn.prepareStatement(query);
					
					ps.execute();
					
					mainManagerFr_Ver2.showDataCustomer();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(175, 201, 115, 35);
		contentPane.add(btnUpdate);
	}

}
