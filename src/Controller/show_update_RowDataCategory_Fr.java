package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DBConnection.DBConnection;
import Model.Category;
import Model.Customers;
import View.mainManagerFr_Ver2;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class show_update_RowDataCategory_Fr extends JFrame {

	private JPanel contentPane;
	public JTextField txtCateID;
	public JTextField txtCateName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					show_update_RowDataCategory_Fr frame = new show_update_RowDataCategory_Fr();
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
	
	public show_update_RowDataCategory_Fr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 561, 183);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCategoryId = new JLabel("Category ID", SwingConstants.RIGHT);
		lblCategoryId.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		lblCategoryId.setBounds(65, 25, 104, 30);
		panel.add(lblCategoryId);
		
		txtCateID = new JTextField();
		txtCateID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char k=e.getKeyChar();
				
				if(Character.isLetter(k)) {
					JOptionPane.showMessageDialog(null, "Chỉ nhập số!!");
					txtCateID.setText(null);
					txtCateID.requestFocus();
				}
			}
		});
		txtCateID.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtCateID.setColumns(10);
		txtCateID.setBounds(180, 25, 251, 30);
		panel.add(txtCateID);
		
		JLabel lblCategoryName = new JLabel("Category Name", SwingConstants.RIGHT);
		lblCategoryName.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		lblCategoryName.setBounds(65, 65, 104, 30);
		panel.add(lblCategoryName);
		
		txtCateName = new JTextField();
		txtCateName.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtCateName.setColumns(10);
		txtCateName.setBounds(180, 65, 251, 30);
		panel.add(txtCateName);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection cn = DBConnection.getConnection();
					
					String cateid = txtCateID.getText();
					String catename = txtCateName.getText();
					
					String query = "UPDATE Category SET CategoryName =  '"+ catename +"' WHERE CategoryID = "+ cateid ;
				
					PreparedStatement ps = cn.prepareStatement(query);
					
					ps.execute();
					
					mainManagerFr_Ver2.showDataCategory();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Monospaced", Font.BOLD, 20));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(207, 115, 115, 35);
		panel.add(btnUpdate);
	}

}
