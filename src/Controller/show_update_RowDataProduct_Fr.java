package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class show_update_RowDataProduct_Fr extends JFrame {

	private JPanel contentPane;
	public JTextField textField_ID;
	public JTextField textField_PrName;
	public JTextField textField_SuppID;
	public JTextField textField_CateID;
	public JTextField textField_MFG;
	public JTextField textField_EXP;
	public JTextField textField_Unit;
	public JTextField textField_Price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					show_update_RowDataProduct_Fr frame = new show_update_RowDataProduct_Fr();
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
	public show_update_RowDataProduct_Fr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMNhnVin = new JLabel("Product ID", SwingConstants.LEFT);
		lblMNhnVin.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblMNhnVin.setBounds(52, 38, 104, 30);
		contentPane.add(lblMNhnVin);
		
		JLabel lblProductName = new JLabel("Product Name", SwingConstants.LEFT);
		lblProductName.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblProductName.setBounds(52, 78, 114, 30);
		contentPane.add(lblProductName);
		
		JLabel lblSupplier = new JLabel("Supplier ID", SwingConstants.LEFT);
		lblSupplier.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblSupplier.setBounds(52, 118, 104, 30);
		contentPane.add(lblSupplier);
		
		JLabel lblCategoryId = new JLabel("Category ID", SwingConstants.LEFT);
		lblCategoryId.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblCategoryId.setBounds(52, 158, 104, 30);
		contentPane.add(lblCategoryId);
		
		JLabel lblMNhnVin_4 = new JLabel("MFG", SwingConstants.LEFT);
		lblMNhnVin_4.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblMNhnVin_4.setBounds(52, 198, 104, 30);
		contentPane.add(lblMNhnVin_4);
		
		JLabel lblMNhnVin_5 = new JLabel("EXP", SwingConstants.LEFT);
		lblMNhnVin_5.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblMNhnVin_5.setBounds(52, 238, 104, 30);
		contentPane.add(lblMNhnVin_5);
		
		JLabel lblMNhnVin_1 = new JLabel("Unit", SwingConstants.LEFT);
		lblMNhnVin_1.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblMNhnVin_1.setBounds(52, 278, 104, 30);
		contentPane.add(lblMNhnVin_1);
		
		JLabel lblMNhnVin_2 = new JLabel("Price", SwingConstants.LEFT);
		lblMNhnVin_2.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblMNhnVin_2.setBounds(52, 318, 104, 30);
		contentPane.add(lblMNhnVin_2);
		
		textField_ID = new JTextField();
		textField_ID.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_ID.setColumns(10);
		textField_ID.setBounds(166, 38, 225, 30);
		contentPane.add(textField_ID);
		
		textField_PrName = new JTextField();
		textField_PrName.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_PrName.setColumns(10);
		textField_PrName.setBounds(166, 78, 225, 30);
		contentPane.add(textField_PrName);
		
		textField_SuppID = new JTextField();
		textField_SuppID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char k=e.getKeyChar();
				
				if(Character.isLetter(k)) {
					JOptionPane.showMessageDialog(null, "Chỉ nhập số!!");
					textField_SuppID.setText(null);
					textField_SuppID.requestFocus();
				}
			}
		});
		textField_SuppID.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_SuppID.setColumns(10);
		textField_SuppID.setBounds(166, 118, 225, 30);
		contentPane.add(textField_SuppID);
		
		textField_CateID = new JTextField();
		textField_CateID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char k=e.getKeyChar();
				
				if(Character.isLetter(k)) {
					JOptionPane.showMessageDialog(null, "Chỉ nhập số!!");
					textField_CateID.setText(null);
					textField_CateID.requestFocus();
				}
			}
		});
		textField_CateID.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_CateID.setColumns(10);
		textField_CateID.setBounds(166, 158, 225, 30);
		contentPane.add(textField_CateID);
		
		textField_MFG = new JTextField();
		textField_MFG.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String time = textField_MFG.getText();
				String reg = "\\d{4}[-]\\d{1,2}[-]\\d{1,2}$";
				
				if(time.matches(reg)) {
				}
				else {
					JOptionPane.showMessageDialog(null,"Sai định dạng thời gian (yyyy-mm-dd)!");
					textField_MFG.requestFocus();
					return;
				}
			}
		});
		textField_MFG.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_MFG.setColumns(10);
		textField_MFG.setBounds(166, 198, 225, 30);
		contentPane.add(textField_MFG);
		
		textField_EXP = new JTextField();
		textField_EXP.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String time = textField_EXP.getText();
				String reg = "\\d{4}[-]\\d{1,2}[-]\\d{1,2}$";
				
				if(time.matches(reg)) {
				}
				else {
					JOptionPane.showMessageDialog(null,"Sai định dạng thời gian (yyyy-mm-dd)!");
					textField_EXP.requestFocus();
					return;
				}
			}
		});
		textField_EXP.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_EXP.setColumns(10);
		textField_EXP.setBounds(166, 238, 225, 30);
		contentPane.add(textField_EXP);
		
		textField_Unit = new JTextField();
		textField_Unit.setFont(new Font("Monospaced", Font.BOLD, 14));
		textField_Unit.setColumns(10);
		textField_Unit.setBounds(166, 278, 225, 30);
		contentPane.add(textField_Unit);
		
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
		textField_Price.setBounds(166, 318, 225, 30);
		contentPane.add(textField_Price);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection cn = DBConnection.getConnection();
					
					String prid = textField_ID.getText();
					String prname = textField_PrName.getText();
					String suppid = textField_SuppID.getText();
					String cateid = textField_CateID.getText();
					String mfg = textField_MFG.getText();
					String exp = textField_EXP.getText();
					String unit = textField_Unit.getText();
					String price = textField_Price.getText();
					
					String query = "UPDATE Product SET name = N'" + prname + "', Supid = "+ suppid +", cateID = "+ cateid +", MFG = '"+ mfg +"', EXP = '"+ exp +"', unit ='"+ unit +"', price ='"+ price +"' WHERE ID = "+ prid +"";
				
					PreparedStatement ps = cn.prepareStatement(query);
					
					ps.execute();
					
					mainManagerFr_Ver2.showDataProduct();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Monospaced", Font.BOLD, 20));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(166, 374, 115, 35);
		contentPane.add(btnUpdate);
	}
}
