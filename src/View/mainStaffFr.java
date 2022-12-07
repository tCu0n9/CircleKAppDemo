package View;


import java.awt.Color;
import java.awt.EventQueue;import javax.management.Query;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.show_update_RowDataProduct_Fr;
import DBConnection.DBConnection;
import Model.Staff;
import Model.billDetail;
import View.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Label;
import java.awt.Button;

public class mainStaffFr extends JFrame {

	private JPanel contentPane;
	private static JTable table_BillDetail;
	private static JTable tableProduct;
	private JTextField textField_FindID;
	private static int id;
	@SuppressWarnings("unused")
	private static String EmpNAME= LoginFr.EmpName;
	private JTextField textField;
	private static int vat;
	private static int total;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainStaffFr frame = new mainStaffFr();
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
	private static void Showdulieu() {
		try {
			table_BillDetail.removeAll();
			String[] arr = {"ID","Name","Category ID","Price","Unit","Total Price"};
			DefaultTableModel model = new DefaultTableModel(arr,0);
			
			Connection connection = DBConnection.getConnection();
			
			String query = "Select * From dbo.[BillDetail]";
			PreparedStatement ps = connection.prepareStatement(query);
		
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector vector = new Vector<>();
				vector.add(rs.getString("ID"));
				vector.add(rs.getString("NamePr"));
				vector.add(rs.getString("CateID"));
				vector.add(rs.getString("price"));
				vector.add(rs.getString("quantity"));
				vector.add(rs.getString("totalPrice"));
				model.addRow(vector);
			}
			table_BillDetail.setModel(model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void showDataProduct() {
		
		try {
			tableProduct.removeAll();
			String[] arr2 = {"ID", "Name", "Supplier ID", "Category ID", "MFG", "EXP", "Unit", "Price"};
			DefaultTableModel model2 = new DefaultTableModel(arr2,0);
			
			Connection cn = DBConnection.getConnection();
			
			String query = "select * from dbo.[Product] where id = "+ id;
			PreparedStatement ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Vector vector = new Vector();
				vector.add(rs.getString("id"));
				vector.add(rs.getString("Name"));
				vector.add(rs.getString("SupID"));
				vector.add(rs.getString("CateID"));
				vector.add(rs.getString("MFG"));
				vector.add(rs.getString("EXP"));
				vector.add(rs.getString("Unit"));
				vector.add(rs.getString("Price"));
				model2.addRow(vector);
			}
			tableProduct.setModel(model2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void clearData() {
		Connection cn  = DBConnection.getConnection();
		String query = "delete billDetail";
		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(query);
			ps.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void componentShown(ComponentEvent e) {
		 
	}
	public mainStaffFr() {
		clearData();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				Showdulieu();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 675);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0xffffff));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_BillDetail = new JPanel();
		panel_BillDetail.setBounds(0, 0, 876, 436);
		contentPane.add(panel_BillDetail);
		panel_BillDetail.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 876, 393);
		panel_BillDetail.add(scrollPane);
		
		table_BillDetail = new JTable();
		table_BillDetail.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				
			}
		});
		table_BillDetail.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Category ID", "Price", "Quantity", "Total Price"
			}
		));
		table_BillDetail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(table_BillDetail);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table_BillDetail.getSelectedRow();
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm !");
				}else {
					TableModel model = table_BillDetail.getModel();
					String id = model.getValueAt(index, 0).toString();
					
					try {
						Connection cn = DBConnection.getConnection();
						String query = "delete from dbo.[billDetail] where ID = ?";
						PreparedStatement ps = cn.prepareStatement(query);
						ps.setString(1,id);
						ps.execute();
						Showdulieu();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnDelete.setBounds(0, 391, 445, 45);
		panel_BillDetail.add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frConfirm = new JFrame("Confirm");
				if(JOptionPane.showConfirmDialog(frConfirm, "Confirm if you want to detele all ?","Circle K App",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					Connection cn  = DBConnection.getConnection();
					String query = "delete billDetail";
					PreparedStatement ps;
					try {
						ps = cn.prepareStatement(query);
						ps.execute();
						Showdulieu();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}		
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnClear.setBounds(444, 391, 432, 45);
		panel_BillDetail.add(btnClear);
		
		JPanel panel_TotalBill = new JPanel();
		panel_TotalBill.setBounds(886, 0, 380, 498);
		contentPane.add(panel_TotalBill);
		panel_TotalBill.setLayout(null);
		
		JLabel lbl_TotalBill = new JLabel("TOTAL BILL");
		lbl_TotalBill.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_TotalBill.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TotalBill.setBounds(34, 0, 315, 56);
		panel_TotalBill.add(lbl_TotalBill);
		
		JLabel lblPrice = new JLabel("Price:");			
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(10, 136, 357, 21);
		panel_TotalBill.add(lblPrice);
		
		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiscount.setBounds(10, 250, 357, 21);
		panel_TotalBill.add(lblDiscount);
		
		JLabel lblVat = new JLabel("VAT 8%: ");
		lblVat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVat.setBounds(10, 191, 357, 21);
		panel_TotalBill.add(lblVat);
		
		JLabel lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblTotalPrice.setBounds(10, 325, 360, 39);
		panel_TotalBill.add(lblTotalPrice);
		
		JButton btnPrintBill = new JButton("Print Bill");
		btnPrintBill.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnPrintBill.setActionCommand("");
		btnPrintBill.setBounds(0, 446, 189, 52);
		panel_TotalBill.add(btnPrintBill);
		
		JButton btnPay = new JButton("Pay");
		btnPay.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnPay.setActionCommand("");
		btnPay.setBounds(191, 446, 189, 52);
		panel_TotalBill.add(btnPay);
		
		JLabel lblBillID = new JLabel("Bill ID:");
		lblBillID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBillID.setBounds(10, 66, 360, 39);
		panel_TotalBill.add(lblBillID);
		
		JPanel panelInformation = new JPanel();
		panelInformation.setBounds(886, 508, 380, 129);
		contentPane.add(panelInformation);
		panelInformation.setLayout(null);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String time = dateFormat.format(cal.getTime());
		
		JLabel lblDateTime = new JLabel("DateTime: "+ time);
		lblDateTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDateTime.setBounds(10, 10, 370, 28);
		panelInformation.add(lblDateTime);
		
		JLabel lblStaffID = new JLabel("Staff Name: "+ EmpNAME);
		lblStaffID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStaffID.setBounds(10, 48, 370, 28);
		panelInformation.add(lblStaffID);
		
		JLabel lblCustomerID = new JLabel("Customer ID:");
		lblCustomerID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCustomerID.setBounds(10, 86, 118, 28);
		panelInformation.add(lblCustomerID);
		
		textField = new JTextField();
		textField.setBounds(133, 86, 237, 28);
		panelInformation.add(textField);
		textField.setColumns(10);
		
		JPanel panelProduct = new JPanel();
		panelProduct.setBounds(0, 446, 876, 191);
		contentPane.add(panelProduct);
		panelProduct.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 0, 783, 153);
		panelProduct.add(scrollPane_3);
		
		tableProduct = new JTable();
		tableProduct.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"ID", "Name", "Supplier ID", "Category ID", "MFG", "EXP", "Unit", "Price"
			}
		));
		scrollPane_3.setViewportView(tableProduct);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addMouseListener(new MouseAdapter() {
			private billDetail bd;

			public void mouseClicked(MouseEvent e) {
				show_update_RowDataProduct_Fr jtableProdcutRowdata = new show_update_RowDataProduct_Fr();
				
				int index = tableProduct.getSelectedRow();
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "Chưa chọn mặt hàng !");
				}else {
					TableModel model = tableProduct.getModel();
					String id = model.getValueAt(index, 0).toString();
					String name = model.getValueAt(index, 1).toString();
					String supid = model.getValueAt(index, 2).toString();
					String cateid = model.getValueAt(index, 3).toString();
					String mfg = model.getValueAt(index, 4).toString();
					String exp = model.getValueAt(index, 5).toString();
					String unit = model.getValueAt(index, 6).toString();
					String price = model.getValueAt(index, 7).toString();
					
					int quantity = Integer.parseInt(JOptionPane.showInputDialog("Quantity"));
					if(quantity == -1) {
						JOptionPane.showMessageDialog(null, "Chưa nhập só lượng !");
					}else if(quantity == 0 ) {
						JOptionPane.showMessageDialog(null, "Só lượng không hợp lệ!");
					}else{
						
						if(checkTrungID_bd()==true) {
							JOptionPane.showMessageDialog(null, "Sản phẩm đã được chợn!");
							return;
						}else{
							int tPrice = quantity*Integer.parseInt(price);
							ArrayList<billDetail> list = new ArrayList<>();
							bd = new billDetail();
							bd.setId(Integer.parseInt(id));
							bd.setPrName(name);
							bd.setCateID(Integer.parseInt(cateid));
							bd.setPrice(Integer.parseInt(price));
							bd.setQuantity(quantity);
							bd.setTotalPrice(quantity,Integer.parseInt(price));
							list.add(bd);
							
							try {
								
								Connection cn = DBConnection.getConnection();
								
								String query = "insert into BillDetail(ID,NamePr,cateID,Price,Quantity,totalPrice) values("+ id +",N'"+ name +"',"+ cateid +","+ price +","+ quantity +","+tPrice+")";
								PreparedStatement ps = cn.prepareStatement(query);
								ps.executeUpdate();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Showdulieu();
							BDtotal();
							bdVAT();
							
							lblPrice.setText("Price: "+ total + " VNĐ");
							lblVat.setText("VAT 8%: "+ vat +" VNĐ");
						}						
					}
				}	
			}
		});
		btnAdd.setBackground(new Color(240, 240, 240));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnAdd.setBounds(781, 0, 95, 189);
		panelProduct.add(btnAdd);
		
		textField_FindID = new JTextField();
		textField_FindID.setBounds(40, 155, 623, 35);
		panelProduct.add(textField_FindID);
		textField_FindID.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		btnFind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textField_FindID.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Chưa nhập ID sản phẩm!");
					textField_FindID.requestFocus();
					return;
				}else {
					id = Integer.parseInt(textField_FindID.getText());
					if(checkTrungID_Pr() == false) {
						JOptionPane.showMessageDialog(null, "ID sản phẩm không tồn tại!");
					}else {
						showDataProduct();
					}
				}	
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnFind.setBounds(664, 155, 119, 35);
		panelProduct.add(btnFind);
		
		JLabel lblID = new JLabel("ID");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblID.setBounds(5, 155, 37, 35);
		panelProduct.add(lblID);
		
		ImageIcon img = new ImageIcon("D:\\JavaWorkSpace\\CircleKApp\\src\\Img\\download.png");
		setIconImage(img.getImage());
	}
	
	private boolean checkTrungID_bd() {	
		try {
			Connection cn = DBConnection.getConnection();
			
			String query = "select * from dbo.[billDetail] where id = ? ";
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setString(1, textField_FindID.getText());
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
	
	private boolean checkTrungID_Pr() {	
		try {
			Connection cn = DBConnection.getConnection();
			
			String query = "select * from dbo.[Product] where id = ? ";
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setString(1, textField_FindID.getText());
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
	
	private boolean Checkbd() {
		try {
			Connection cn = DBConnection.getConnection();
			
			String query = "select * from dbo.[billDetail]";
			PreparedStatement ps = cn.prepareStatement(query);
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
	
	private void BDtotal() {
		try {
			Connection cn = DBConnection.getConnection();
			
			String query = "SELECT SUM(totalPrice) as Total FROM BillDetail;";
			PreparedStatement ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				total = rs.getInt("total");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void bdVAT() {
		try {
			Connection cn = DBConnection.getConnection();
			
			String query = "SELECT SUM(totalPrice) as Total FROM BillDetail;";
			PreparedStatement ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				total = rs.getInt("total");
				vat = total * 8 / 100;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
