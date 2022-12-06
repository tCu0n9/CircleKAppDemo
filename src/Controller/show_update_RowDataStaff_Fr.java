package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DBConnection.DBConnection;
import Model.Staff;
import View.mainManagerFr_Ver2;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class show_update_RowDataStaff_Fr extends JFrame {

	private JPanel contentPane;
	public JTextField textField_ID;
	public JTextField textField_Name;
	public JTextField textField_BoD;
	public JTextField textField_Address;
	public JTextField textField_PhoneNumb;

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
		setBounds(100, 100, 548, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("Staff ID", SwingConstants.RIGHT);
		lblID.setBounds(68, 37, 70, 20);
		lblID.setFont(new Font("Monospaced", Font.BOLD, 14));
		contentPane.add(lblID);
		
		textField_ID = new JTextField();
		textField_ID.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_ID.setColumns(10);
		textField_ID.setBounds(160, 32, 265, 30);
		contentPane.add(textField_ID);
		
		JLabel lblName = new JLabel("Full Name", SwingConstants.RIGHT);
		lblName.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblName.setBounds(56, 67, 80, 30);
		contentPane.add(lblName);
		
		JLabel lblBoD = new JLabel("BoD", SwingConstants.RIGHT);
		lblBoD.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblBoD.setBounds(101, 107, 35, 30);
		contentPane.add(lblBoD);
		
		JLabel lblNewLabel_1 = new JLabel("Address", SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblNewLabel_1.setBounds(76, 147, 62, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone Number", SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblNewLabel_2.setBounds(29, 187, 114, 30);
		contentPane.add(lblNewLabel_2);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			private Staff s;

			public void actionPerformed(ActionEvent e) {
					{
	
					
					try {
						Connection cn = DBConnection.getConnection();
						
						String id = textField_ID.getText();
						String name = textField_Name.getText();
						String bod = textField_BoD.getText();
						String address = textField_Address.getText();
						String phone = textField_PhoneNumb.getText();
						
						String query = "UPDATE Employees SET EmpName = N'" + name + "', BoD = '"+ bod +"', EmpAddress = N'"+ address +"',PhoneNum = '"+ phone +"' WHERE EmpID = "+ id +"";
					
						PreparedStatement ps = cn.prepareStatement(query);
						
						ps.execute();
						
						mainManagerFr_Ver2.showDataStaff();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(187, 243, 115, 35);
		contentPane.add(btnUpdate);
		
		textField_Name = new JTextField();
		textField_Name.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_Name.setColumns(10);
		textField_Name.setBounds(160, 74, 265, 30);
		contentPane.add(textField_Name);
		
		textField_BoD = new JTextField();
		textField_BoD.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_BoD.setColumns(10);
		textField_BoD.setBounds(160, 114, 265, 30);
		contentPane.add(textField_BoD);
		
		textField_Address = new JTextField();
		textField_Address.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_Address.setColumns(10);
		textField_Address.setBounds(160, 154, 265, 30);
		contentPane.add(textField_Address);
		
		textField_PhoneNumb = new JTextField();
		textField_PhoneNumb.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_PhoneNumb.setColumns(10);
		textField_PhoneNumb.setBounds(160, 194, 265, 30);
		contentPane.add(textField_PhoneNumb);
	}
}
