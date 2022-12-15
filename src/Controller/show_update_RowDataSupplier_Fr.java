package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DBConnection.DBConnection;
import View.mainManagerFr_Ver2;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class show_update_RowDataSupplier_Fr extends JFrame {

	private JPanel contentPane;
	public JTextField txtSupplier;
	public JTextField txtSupplierName;
	public JTextField txtContactName;
	public JTextField txtSupplierAddress;
	public JTextField txtSupplierPhone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					show_update_RowDataSupplier_Fr frame = new show_update_RowDataSupplier_Fr();
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
	public show_update_RowDataSupplier_Fr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 584, 286);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSupplierId = new JLabel("Supplier ID", SwingConstants.RIGHT);
		lblSupplierId.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		lblSupplierId.setBounds(110, 22, 104, 30);
		panel.add(lblSupplierId);
		
		txtSupplier = new JTextField();
		txtSupplier.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtSupplier.setColumns(10);
		txtSupplier.setBounds(235, 22, 251, 30);
		panel.add(txtSupplier);
		
		JLabel lblSupplierName = new JLabel("Supplier Name", SwingConstants.RIGHT);
		lblSupplierName.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		lblSupplierName.setBounds(110, 63, 104, 30);
		panel.add(lblSupplierName);
		
		txtSupplierName = new JTextField();
		txtSupplierName.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtSupplierName.setColumns(10);
		txtSupplierName.setBounds(235, 63, 251, 30);
		panel.add(txtSupplierName);
		
		JLabel lblContactName = new JLabel("Contact Name", SwingConstants.RIGHT);
		lblContactName.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		lblContactName.setBounds(110, 104, 104, 30);
		panel.add(lblContactName);
		
		txtContactName = new JTextField();
		txtContactName.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtContactName.setColumns(10);
		txtContactName.setBounds(235, 104, 251, 30);
		panel.add(txtContactName);
		
		JLabel lblSupplierId_1_1 = new JLabel("Address", SwingConstants.RIGHT);
		lblSupplierId_1_1.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		lblSupplierId_1_1.setBounds(110, 145, 104, 30);
		panel.add(lblSupplierId_1_1);
		
		txtSupplierAddress = new JTextField();
		txtSupplierAddress.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtSupplierAddress.setColumns(10);
		txtSupplierAddress.setBounds(235, 145, 251, 30);
		panel.add(txtSupplierAddress);
		
		txtSupplierPhone = new JTextField();
		txtSupplierPhone.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtSupplierPhone.setColumns(10);
		txtSupplierPhone.setBounds(235, 186, 251, 30);
		panel.add(txtSupplierPhone);
		
		JLabel lblSupplierId_1_1_1 = new JLabel("Phone Number", SwingConstants.RIGHT);
		lblSupplierId_1_1_1.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		lblSupplierId_1_1_1.setBounds(110, 186, 104, 30);
		panel.add(lblSupplierId_1_1_1);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection cn = DBConnection.getConnection();
					
					String id = txtSupplier.getText();
					String supname = txtSupplierName.getText();
					String ctName = txtContactName.getText();
					String address = txtSupplierAddress.getText();
					String phoneNum = txtSupplierPhone.getText();
					
					String query = "UPDATE Supplier SET SupName = N'" + supname + "', contactName = N'"+ ctName +"', SupAddress = N'"+ address +"',SupPhone= '"+ phoneNum +"' WHERE supID = "+ id +"";
				
					PreparedStatement ps = cn.prepareStatement(query);
					
					ps.execute();
					
					mainManagerFr_Ver2.showDataSupplier();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(245, 226, 115, 35);
		panel.add(btnUpdate);
	}
}
