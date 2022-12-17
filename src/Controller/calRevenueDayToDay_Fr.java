package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DBConnection.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class calRevenueDayToDay_Fr extends JFrame {

	private JPanel contentPane;
	private JTextField txtday1;
	private int total;
	private int totalbill;
	private JTextField txtday2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calRevenueDayToDay_Fr frame = new calRevenueDayToDay_Fr();
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
	public calRevenueDayToDay_Fr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRevenueIn = new JLabel("Revenue in ");
		lblRevenueIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRevenueIn.setBounds(33, 18, 74, 27);
		contentPane.add(lblRevenueIn);
		
		txtday1 = new JTextField();
		txtday1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String age = txtday1.getText();
				String reg = "^(0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])[\\/\\-]\\d{4}$";
				
				if(age.matches(reg)) {
				}
				else {
					JOptionPane.showMessageDialog(null,"Sai định dạng thời gian (mm/dd/yyyy)!");
					txtday1.requestFocus();
					return;
				}
			}
		});
		txtday1.setBounds(117, 20, 109, 27);
		contentPane.add(txtday1);
		txtday1.setColumns(10);
		
		JLabel lblTotalBill = new JLabel("Total Bill:");
		lblTotalBill.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalBill.setBounds(35, 71, 91, 20);
		contentPane.add(lblTotalBill);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotal.setBounds(227, 71, 256, 20);
		contentPane.add(lblTotal);
		
		JButton btnCal = new JButton("Calculation");
		btnCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtday1.getText().equals("") || txtday2.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Chưa nhập đủ dữ liệu !");
					return;
				}else {
					String day1 = txtday1.getText();
					String day2 = txtday2.getText();
					
					totalbill(day1,day2);
					totalRevenue(day1,day2);
					
					lblTotalBill.setText("Total Bill: "+ totalbill);
					lblTotal.setText("Total: "+ total +" VNĐ");
				}
			}
		});
		btnCal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCal.setBounds(387, 18, 109, 27);
		contentPane.add(btnCal);
		
		txtday2 = new JTextField();
		txtday2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String age = txtday1.getText();
				String reg = "^(0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])[\\/\\-]\\d{4}$";
				
				if(age.matches(reg)) {
				}
				else {
					JOptionPane.showMessageDialog(null,"Sai định dạng thời gian (mm/dd/yyyy)!");
					txtday1.requestFocus();
					return;
				}
			}
		});
		txtday2.setColumns(10);
		txtday2.setBounds(258, 20, 109, 27);
		contentPane.add(txtday2);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTo.setBounds(236, 18, 23, 27);
		contentPane.add(lblTo);
	}
	
	private void totalbill(String day1,String day2) {
		try {
			Connection cn = DBConnection.getConnection();
			
			String query = "SELECT count(BillId) as Billid FROM Bill where billDate between '"+ day1 +" 00:00:00' and '"+ day2 +" 23:59:59'";
			PreparedStatement ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				totalbill = rs.getInt("Billid");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}
	}
	
	private void totalRevenue(String day1,String day2) {
		try {
			Connection cn = DBConnection.getConnection();
			
			String query = "SELECT sum(price) as total FROM Bill where billDate between '"+ day1 +" 00:00:00' and '"+ day2 +" 23:59:59'";
			PreparedStatement ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				total = rs.getInt("total");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}
	}
}
