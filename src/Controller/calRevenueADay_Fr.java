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

public class calRevenueADay_Fr extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private int total;
	private int totalbill;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calRevenueADay_Fr frame = new calRevenueADay_Fr();
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
	public calRevenueADay_Fr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRevenueIn = new JLabel("Revenue in ");
		lblRevenueIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRevenueIn.setBounds(33, 18, 74, 27);
		contentPane.add(lblRevenueIn);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String age = textField.getText();
				String reg = "^(0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])[\\/\\-]\\d{4}$";
				
				if(age.matches(reg)) {
				}
				else {
					JOptionPane.showMessageDialog(null,"Sai định dạng thời gian (mm/dd/yyyy)!");
					textField.requestFocus();
					return;
				}
			}
		});
		textField.setBounds(117, 20, 212, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTotalBill = new JLabel("Total Bill:");
		lblTotalBill.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalBill.setBounds(35, 71, 91, 20);
		contentPane.add(lblTotalBill);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotal.setBounds(192, 71, 256, 20);
		contentPane.add(lblTotal);
		
		JButton btnCal = new JButton("Calculation");
		btnCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Chưa nhập dữ liệu !");
					textField.requestFocus();
					return;
				}else {
					String day = textField.getText();
					
					totalbill(day);
					totalRevenou(day);
					
					lblTotalBill.setText("Total Bill: "+ totalbill);
					lblTotal.setText("Total: "+ total +" VNĐ");
				}
			}
		});
		btnCal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCal.setBounds(339, 18, 109, 27);
		contentPane.add(btnCal);
	}
	
	private void totalbill(String day) {
		try {
			Connection cn = DBConnection.getConnection();
			
			String query = "SELECT count(BillId) as Billid FROM Bill where billDate between '"+ day +" 00:00:00' and '"+ day +" 23:59:59'";
			PreparedStatement ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				totalbill = rs.getInt("Billid");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}
	}
	
	private void totalRevenou(String day) {
		try {
			Connection cn = DBConnection.getConnection();
			
			String query = "SELECT sum(price) as total FROM Bill where billDate between '"+ day +" 00:00:00' and '"+ day +" 23:59:59'";
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
