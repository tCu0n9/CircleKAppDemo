package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DBConnection.DBConnection;
import Model.Staff;
import Model.Supplier;
import View.mainManagerFr_Ver2;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class addSuppTextField_Fr extends JFrame {

	private JPanel contentPane;
	private JTextField txtSupplier;
	private JTextField txtSupplierName;
	private JTextField txtContactName;
	private JTextField txtSupplierAddress;
	private JTextField txtSupplierPhone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addSuppTextField_Fr frame = new addSuppTextField_Fr();
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
		
			String query = "select * from dbo.[Supplier] where SupID = ?";
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setString(1, txtSupplier.getText());
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
	
	public addSuppTextField_Fr() {
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
		txtSupplier.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					char c = e.getKeyChar();
				
					if(Character.isLetter(c)) {
						JOptionPane.showMessageDialog(null, "Chỉ nhập số!!");
						txtSupplier.setText(null);
						txtSupplier.requestFocus();
				}
			}
		});
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
		txtSupplierPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					char c = e.getKeyChar();
				
					if(Character.isLetter(c)) {
						JOptionPane.showMessageDialog(null, "Chỉ nhập số!!");
						txtSupplierPhone.setText(null);
						txtSupplierPhone.requestFocus();
				}
			}
		});
		txtSupplierPhone.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtSupplierPhone.setColumns(10);
		txtSupplierPhone.setBounds(235, 186, 251, 30);
		panel.add(txtSupplierPhone);
		
		JLabel lblSupplierId_1_1_1 = new JLabel("Phone Number", SwingConstants.RIGHT);
		lblSupplierId_1_1_1.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		lblSupplierId_1_1_1.setBounds(110, 186, 104, 30);
		panel.add(lblSupplierId_1_1_1);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			private Supplier s;

			public void actionPerformed(ActionEvent e) {
				if(txtSupplier.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"ID không được rỗng!");
					txtSupplier.requestFocus();
					return;
				}else if(txtSupplierName.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Name không được rỗng!");
					txtSupplierName.requestFocus();
					return;
				}else if(txtContactName.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Contact Name không được rỗng!");
					txtContactName.requestFocus();
					return;
				}else if(txtSupplierAddress.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Address không được rỗng!");
					txtSupplierAddress.requestFocus();
					return;
				}else if(txtSupplierPhone.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Phone Number không được rỗng!");
					txtSupplierPhone.requestFocus();
					return;
				}else if(checkTrungID() == true){
					JOptionPane.showMessageDialog(null,"ID đã tồn tại!");
					txtSupplier.requestFocus();
					return;
				}else {
					
					ArrayList<Supplier> list = new ArrayList<>();
					s = new Supplier();
					s.setSupID(Integer.parseInt(txtSupplier.getText()));
					s.setSupName(txtSupplierName.getText());
					s.setContactName(txtContactName.getText());
					s.setSupAddress(txtSupplierAddress.getText());
					s.setSupPhoneNum(txtSupplierPhone.getText());
					list.add(s);	
					
					mainManagerFr_Ver2.AddRowToJTableSupplier(new Object[]{
						s.getSupID(),
						s.getSupName(),
						s.getcontactName(),
						s.getSupAddress(),
						s.getSupPhoneNum(),
					});
					
					try {
						Connection cn = DBConnection.getConnection();
						
						String query = "\r\n"
								+ "insert into Supplier(SupID,SupName,ContactName,SupAddress,SupPhone) values(?,?,?,?,?)";
						PreparedStatement ps = cn.prepareStatement(query);
						ps.setString(1, txtSupplier.getText());
						ps.setString(2, txtSupplierName.getText());
						ps.setString(3, txtContactName.getText());
						ps.setString(4, txtSupplierAddress.getText());
						ps.setString(5, txtSupplierPhone.getText());
						ps.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					mainManagerFr_Ver2.showDataSupplier();
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.setBackground(Color.WHITE);
		btnSave.setBounds(169, 228, 105, 35);
		panel.add(btnSave);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSupplier.setText(null);
				txtSupplierName.setText(null);
				txtContactName.setText(null);
				txtSupplierAddress.setText(null);
				txtSupplierPhone.setText(null);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReset.setBackground(Color.WHITE);
		btnReset.setBounds(324, 228, 105, 35);
		panel.add(btnReset);
	}
}
