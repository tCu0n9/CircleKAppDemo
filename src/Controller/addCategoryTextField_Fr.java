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

public class addCategoryTextField_Fr extends JFrame {

	private JPanel contentPane;
	private JTextField txtCateID;
	private JTextField txtCateName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addCategoryTextField_Fr frame = new addCategoryTextField_Fr();
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
		
			String query = "select * from dbo.[Category] where CategoryID = ?";
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setString(1, txtCateID.getText());
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
	
	public addCategoryTextField_Fr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 585, 190);
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
					txtCateName.requestFocus();
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
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			private Category c;

			public void actionPerformed(ActionEvent e) {
				if(txtCateID.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Category ID không được rỗng!");
					txtCateID.requestFocus();
					return;
				}else if(txtCateName.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Category Name không được rỗng!");
					txtCateName.requestFocus();
					return;
				}else {
					ArrayList<Category> list = new ArrayList<>();
					c = new Category();
					c.setCategoryID(Integer.parseInt(txtCateID.getText()));
					c.setCategoryName(txtCateName.getText());
					list.add(c);
					
					mainManagerFr_Ver2.AddRowToJTableCategory(new Object[]{
							c.getCategoryID(),
							c.getCategoryName(),
					});
					
					try {
						Connection cn = DBConnection.getConnection();
						String query = "insert into Category(CategoryID,CategoryName) values(?,?)";
						PreparedStatement ps = cn.prepareStatement(query);
						ps.setString(1, txtCateID.getText());
						ps.setString(2, txtCateName.getText());
						ps.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.setBackground(Color.WHITE);
		btnSave.setBounds(100, 120, 105, 35);
		panel.add(btnSave);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCateID.setText(null);
				txtCateName.setText(null);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReset.setBackground(Color.WHITE);
		btnReset.setBounds(300, 120, 105, 35);
		panel.add(btnReset);
	}

}
