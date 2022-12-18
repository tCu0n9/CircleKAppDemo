package Controller;

import java.awt.EventQueue;
import Model.*;
import View.mainManagerFr_Ver2;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DBConnection.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.management.loading.PrivateClassLoader;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addProductsTextField_Fr extends JFrame {

	private JPanel contentPane;
	private JTextField textField_ProductID;
	private JTextField textField_ProductName;
	private JTextField textField_SuppID;
	private JLabel lblProductName;
	private JLabel lblSupplier;
	private JLabel lblCategoryId;
	private JLabel lblMFG;
	private JLabel lblEXP;
	private JTextField textField_CategoryID;
	private JTextField textField_MFG;
	private JTextField textField_EXP;
	private JLabel lblUnit;
	private JTextField textField_Unit;
	private JLabel lblPrice;
	private JTextField textField_Price;
	private JTextField textField_Quantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addProductsTextField_Fr frame = new addProductsTextField_Fr();
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
		
			String query = "select * from dbo.[Product] where ID = ?";
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setString(1, textField_ProductID.getText());
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
	
	private boolean checkSupp() {
		try {
			Connection cn = DBConnection.getConnection();
		
			String query  = "select * from dbo.[Supplier] where SupID = ?";
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setString(1,textField_SuppID.getText());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean checkCate() {
		try {
			Connection cn = DBConnection.getConnection();
		
			String query  = "select * from dbo.[Category] where CategoryID = ?";
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setString(1,textField_CategoryID.getText());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public addProductsTextField_Fr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdd = new JButton("Save");
		btnAdd.addActionListener(new ActionListener() {
			private Products p;
			
			public void actionPerformed(ActionEvent e) {
				if(textField_ProductID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "ID không được bỏ trống !");
					textField_ProductID.requestFocus();
					return;
				}else if(textField_ProductName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Name không được bỏ trống !");
					textField_ProductName.requestFocus();
					return;
				}else if(textField_SuppID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Supplier ID không được bỏ trống !");
					textField_SuppID.requestFocus();
					return;
				}else if(textField_CategoryID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Category ID không được bỏ trống !");
					textField_CategoryID.requestFocus();
					return;
				}else if(textField_MFG.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "MFG không được bỏ trống !");
					textField_MFG.requestFocus();
					return;
				}else if(textField_EXP.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "EXP không được bỏ trống !");
					textField_EXP.requestFocus();
					return;
				}else if(textField_Unit.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Unit không được bỏ trống !");
					textField_Unit.requestFocus();
					return;
				}else if(textField_Price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Price không được bỏ trống !");
					textField_Price.requestFocus();
				}else if(textField_Quantity.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Quantity không được bỏ trống !");
					textField_Quantity.requestFocus();
				}else if(checkTrungID() == true) {
					JOptionPane.showMessageDialog(null, "ID đã bị trùng");
					textField_ProductID.requestFocus();
					return;
				}else if(checkSupp() == false) {
					JOptionPane.showMessageDialog(null,"Supplier ID không tồn tại!");
					textField_SuppID.requestFocus();
					return;	
				}else if(checkCate() == false) {
					JOptionPane.showMessageDialog(null,"Category ID không tồn tại!");
					textField_CategoryID.requestFocus();
					return;
				}else {
					ArrayList<Products> list = new ArrayList<>();
					p = new Products();
					p.setProductID(Integer.parseInt(textField_ProductID.getText()));
					p.setProductName(textField_ProductName.getText());
					p.setSupplierID(Integer.parseInt(textField_SuppID.getText()));
					p.setCategoryID(Integer.parseInt(textField_CategoryID.getText()));
					p.setProductMFG(textField_MFG.getText());
					p.setProductBB(textField_EXP.getText());
					p.setUnit(textField_Unit.getText());
					p.setPrice(Integer.parseInt(textField_Price.getText()));
					p.setQuantity(Integer.parseInt(textField_Quantity.getText()));
					list.add(p);
					
					mainManagerFr_Ver2.AddRowToJTableProduct(new Object[]{
						p.getProductID(),
						p.gettProductName(),
						p.getSupplierID(),
						p.getCategoryID(),
						p.getProductMFG(),
						p.getProductBB(),
						p.getUnit(),
						p.getPrice(),
						p.getQuantity()
					});
					
					try {
						Connection cn = DBConnection.getConnection();
						String query = "insert into product(id,name,supID,cateID,MFG,EXP,Unit,Price,Quantity) values(?,?,?,?,?,?,?,?,?)";
						PreparedStatement ps = cn.prepareStatement(query);
						ps.setString(1, textField_ProductID.getText());
						ps.setString(2, textField_ProductName.getText());
						ps.setString(3, textField_SuppID.getText());
						ps.setString(4, textField_CategoryID.getText());
						ps.setString(5, textField_MFG.getText());
						ps.setString(6, textField_EXP.getText());
						ps.setString(7, textField_Unit.getText());
						ps.setString(8, textField_Price.getText());
						ps.setString(9, textField_Quantity.getText());
						
						ps.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					mainManagerFr_Ver2.showDataProductSortID();
				}
			
			}
		});	
		btnAdd.setForeground(new Color(0, 0, 0));
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("Monospaced", Font.BOLD, 20));
		btnAdd.setBounds(121, 430, 85, 33);
		contentPane.add(btnAdd);
		
		JLabel lblProductID = new JLabel("Product ID", SwingConstants.LEFT);
		lblProductID.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblProductID.setBounds(121, 40, 104, 30);
		contentPane.add(lblProductID);
		
		textField_ProductID = new JTextField();
		textField_ProductID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Chỉ nhập số!!");
					textField_ProductID.setText(null);
					textField_ProductID.requestFocus();
				}
			}
		});
		textField_ProductID.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_ProductID.setColumns(10);
		textField_ProductID.setBounds(234, 40, 225, 30);
		contentPane.add(textField_ProductID);
		
		textField_ProductName = new JTextField();
		textField_ProductName.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_ProductName.setColumns(10);
		textField_ProductName.setBounds(234, 81, 225, 30);
		contentPane.add(textField_ProductName);
		
		textField_SuppID = new JTextField();
		textField_SuppID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Chỉ nhập số!!");
					textField_SuppID.setText(null);
					textField_SuppID.requestFocus();
				}
			}
		});
		textField_SuppID.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_SuppID.setColumns(10);
		textField_SuppID.setBounds(234, 122, 225, 30);
		contentPane.add(textField_SuppID);
		
		lblProductName = new JLabel("Product Name", SwingConstants.LEFT);
		lblProductName.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblProductName.setBounds(121, 81, 114, 30);
		contentPane.add(lblProductName);
		
		lblSupplier = new JLabel("Supplier ID", SwingConstants.LEFT);
		lblSupplier.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblSupplier.setBounds(121, 122, 104, 30);
		contentPane.add(lblSupplier);
		
		lblCategoryId = new JLabel("Category ID", SwingConstants.LEFT);
		lblCategoryId.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblCategoryId.setBounds(121, 164, 104, 30);
		contentPane.add(lblCategoryId);
		
		lblMFG = new JLabel("MFG", SwingConstants.LEFT);
		lblMFG.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblMFG.setBounds(121, 204, 104, 30);
		contentPane.add(lblMFG);
		
		lblEXP = new JLabel("EXP", SwingConstants.LEFT);
		lblEXP.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblEXP.setBounds(121, 245, 104, 30);
		contentPane.add(lblEXP);
		
		textField_CategoryID = new JTextField();
		textField_CategoryID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Chỉ nhập số!!");
					textField_CategoryID.setText(null);
					textField_CategoryID.requestFocus();
				}
			}
		});
		textField_CategoryID.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_CategoryID.setColumns(10);
		textField_CategoryID.setBounds(234, 163, 225, 30);
		contentPane.add(textField_CategoryID);
		
		textField_MFG = new JTextField();
		textField_MFG.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String mfg = textField_MFG.getText();
				String reg = "^(0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])[\\/\\-]\\d{4}$";
				if(mfg.matches(reg)) {
					
				}
				else {
					JOptionPane.showMessageDialog(null,"Sai định dạng thời gian (mm/dd/yyyy)!");
					textField_MFG.requestFocus();
					return;
				}
			}
		});
		textField_MFG.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_MFG.setColumns(10);
		textField_MFG.setBounds(234, 204, 225, 30);
		contentPane.add(textField_MFG);
		
		textField_EXP = new JTextField();
		textField_EXP.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String exp = textField_EXP.getText();
				String reg = "^(0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])[\\/\\-]\\d{4}$";
				if(exp.matches(reg)) {
					
				}
				else {
					JOptionPane.showMessageDialog(null,"Sai định dạng thời gian (mm/dd/yyyy)!");
					textField_EXP.requestFocus();
					return;
				}
			}
		});
		textField_EXP.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_EXP.setColumns(10);
		textField_EXP.setBounds(234, 245, 225, 30);
		contentPane.add(textField_EXP);
		
		lblUnit = new JLabel("Unit", SwingConstants.LEFT);
		lblUnit.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblUnit.setBounds(121, 286, 104, 30);
		contentPane.add(lblUnit);
		
		textField_Unit = new JTextField();
		textField_Unit.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_Unit.setColumns(10);
		textField_Unit.setBounds(234, 286, 225, 30);
		contentPane.add(textField_Unit);
		
		lblPrice = new JLabel("Price", SwingConstants.LEFT);
		lblPrice.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblPrice.setBounds(121, 327, 104, 30);
		contentPane.add(lblPrice);
		
		textField_Price = new JTextField();
		textField_Price.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Chỉ nhập số!!");
					textField_Price.setText(null);
					textField_Price.requestFocus();
				}
			}
		});
		textField_Price.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_Price.setColumns(10);
		textField_Price.setBounds(234, 327, 225, 30);
		contentPane.add(textField_Price);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_ProductID.setText(null);
				textField_ProductName.setText(null);
				textField_SuppID.setText(null);
				textField_CategoryID.setText(null);
				textField_MFG.setText(null);
				textField_EXP.setText(null);
				textField_Unit.setText(null);
				textField_Price.setText(null);
				textField_Quantity.setText(null);
			}
		});
		btnClear.setForeground(Color.BLACK);
		btnClear.setFont(new Font("Monospaced", Font.BOLD, 20));
		btnClear.setBackground(new Color(255, 255, 255));
		btnClear.setBounds(353, 430, 104, 33);
		contentPane.add(btnClear);
		
		textField_Quantity = new JTextField();
		textField_Quantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Chỉ nhập số!!");
					textField_Price.setText(null);
					textField_Price.requestFocus();
				}
			}
		});
		textField_Quantity.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_Quantity.setColumns(10);
		textField_Quantity.setBounds(234, 367, 225, 30);
		contentPane.add(textField_Quantity);
		
		JLabel lblQuantity = new JLabel("Quantity", SwingConstants.LEFT);
		lblQuantity.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblQuantity.setBounds(121, 367, 104, 30);
		contentPane.add(lblQuantity);
	}
}
