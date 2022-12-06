package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DBConnection.DBConnection;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class findDataStaff_Fr extends JFrame {

	private JPanel contentPane;
	private JTextField textField_ID;
	private JTextField textField_name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					findDataStaff_Fr frame = new findDataStaff_Fr();
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
	public findDataStaff_Fr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("Staff ID", SwingConstants.RIGHT);
		lblID.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblID.setBounds(54, 26, 70, 30);
		contentPane.add(lblID);
		
		JLabel lblName = new JLabel("Full Name", SwingConstants.RIGHT);
		lblName.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblName.setBounds(44, 75, 80, 30);
		contentPane.add(lblName);
		
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
		textField_ID.setBounds(134, 26, 265, 30);
		textField_ID.setText(null);
		contentPane.add(textField_ID);
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_name.setColumns(10);
		textField_name.setBounds(134, 75, 265, 30);
		textField_name.setText(null);
		contentPane.add(textField_name);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainManagerFr_Ver2.tableStaff.removeAll();
					String[] arr1 = {"ID","Full Name","BoD","Address","Phone Number"};
					DefaultTableModel model1 = new DefaultTableModel(arr1,0);
					
					Connection cn = DBConnection.getConnection();
					
					String id = textField_ID.getText();
					String name = textField_name.getText();
					
					String query = "select * from dbo.[Employees] where EmpID = "+ id +" or EmpName like '%"+ name +"%'";
					PreparedStatement ps = cn.prepareStatement(query);
					
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						Vector vector = new Vector();
						vector.add(rs.getString("EmpID"));
						vector.add(rs.getString("EmpName"));
						vector.add(rs.getString("BoD"));
						vector.add(rs.getString("EmpAddress"));
						vector.add(rs.getString("PhoneNum"));
						model1.addRow(vector);
					}
					mainManagerFr_Ver2.tableStaff.setModel(model1);

					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnFind.setBackground(Color.WHITE);
		btnFind.setBounds(167, 132, 115, 35);
		contentPane.add(btnFind);
	}
}
