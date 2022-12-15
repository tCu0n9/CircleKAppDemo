
package View;


import java.awt.Color;
import java.awt.EventQueue;import javax.management.Query;
import javax.swing.AbstractButton;
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
	private String EmpNAME;
	private int EmpID;
	private JTextField textField;
	private int totalPr;
	private int totalItem;
	private static int vat;
	private static int total;
	private int billID;

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
	
	public int generateNumber() {       
        double randomDouble = Math.random();
        randomDouble = (randomDouble * (999999999-100000000) + 1)+100000000;
        int randomInt = (int) randomDouble;
        return randomInt;
	}
	
	public mainStaffFr() {
		EmpNAME = LoginFr.EmpName;
		EmpID = LoginFr.EmpID;
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
		scrollPane.setBounds(0, 43, 876, 393);
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
		table_BillDetail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrollPane.setViewportView(table_BillDetail);
		
		JPanel panel_TotalBill = new JPanel();
		panel_TotalBill.setBounds(884, 0, 380, 637);
		contentPane.add(panel_TotalBill);
		panel_TotalBill.setLayout(null);
		
		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDiscount.setBounds(10, 302, 357, 21);
		panel_TotalBill.add(lblDiscount);
		
		JLabel lblVat = new JLabel("VAT 8%:");
		lblVat.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblVat.setBounds(10, 332, 357, 21);
		panel_TotalBill.add(lblVat);
		
		JLabel lblTotalPrice = new JLabel("Total:");
		lblTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTotalPrice.setBounds(10, 363, 360, 25);
		panel_TotalBill.add(lblTotalPrice);
		
		JLabel lblTotalItem = new JLabel("Total Item:");
		lblTotalItem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTotalItem.setBounds(10, 242, 357, 21);
		panel_TotalBill.add(lblTotalItem);
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 17)); 
		lblSubtotal.setBounds(10, 272, 357, 21);
		panel_TotalBill.add(lblSubtotal);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String time = dateFormat.format(cal.getTime());
		
		JLabel lblDateTime = new JLabel("DateTime: " + time);
		lblDateTime.setBounds(10, 142, 325, 25);
		panel_TotalBill.add(lblDateTime);
		lblDateTime.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblBillNumber = new JLabel("Bill Number:");
		lblBillNumber.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBillNumber.setBounds(197, 172, 170, 25);
		panel_TotalBill.add(lblBillNumber);
		
		JLabel lblStaffID = new JLabel("Staff Name: "+ EmpNAME);
		lblStaffID.setBounds(10, 112, 357, 25);
		panel_TotalBill.add(lblStaffID);
		lblStaffID.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		billID = generateNumber();
		JLabel lblBillID = new JLabel("Bill ID: "+ billID);
		lblBillID.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBillID.setBounds(10, 172, 170, 25);
		panel_TotalBill.add(lblBillID);
		
		JLabel lblCustomerID = new JLabel("Customer ID:");
		lblCustomerID.setBounds(10, 202, 109, 28);
		panel_TotalBill.add(lblCustomerID);
		lblCustomerID.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textField = new JTextField();
		textField.setBounds(132, 202, 203, 28);
		panel_TotalBill.add(textField);
		textField.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(10, 6, 104, 30);
		panel_BillDetail.add(btnDelete);
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
						String query1 = "delete from dbo.[billDetail] where ID = "+ id +"";
						String query2 = "delete from dbo.[Details] where ID = "+ id +"";
						PreparedStatement ps1 = cn.prepareStatement(query1);
						PreparedStatement ps2 = cn.prepareStatement(query2);
						ps1.execute();
						ps2.execute();
						Showdulieu();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				BDtotal();
				bdVAT();
				totalPr(total, vat);
				totalItem();
				
				lblSubtotal.setText("Subtotal: "+ total + " VNĐ");
				lblVat.setText("VAT 8%: "+ vat +" VNĐ");
				lblTotalPrice.setText("Total: "+ totalPr +" VNĐ");
				lblTotalItem.setText("Total Item: "+totalItem);
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnClean = new JButton("Clear");
		btnClean.setBounds(121, 6, 114, 30);
		panel_BillDetail.add(btnClean);
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frConfirm = new JFrame("Confirm");
				if(JOptionPane.showConfirmDialog(frConfirm, "Confirm if you want to detele all ?","Circle K App",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					try {
						Connection cn  = DBConnection.getConnection();
						String query = "delete billDetail;\r\n"
										+ "delete details;";
						PreparedStatement ps;
						ps = cn.prepareStatement(query);
						ps.execute();
						Showdulieu();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				lblSubtotal.setText("Subtotal: ");
				lblVat.setText("VAT 8%: ");
				lblTotalPrice.setText("Total: ");
				lblTotalItem.setText("Total Item: ");
			}
		});
		btnClean.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnCash = new JButton("Payment");
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String time = dateFormat.format(cal.getTime());
				
				JFrame frConfirm = new JFrame("Confirm");
				if(JOptionPane.showConfirmDialog(frConfirm, "Payment confirmation!","Circle K App",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					if(checkBD() == false) {
						JOptionPane.showMessageDialog(null, "Chưa có mặt hàng!");
					}else {
						try {
							Connection cn = DBConnection.getConnection();
							
							String query = "insert into bill(billID,price, billDate,empID) values("+ billID +","+ totalPr +",'"+ time +"',"+EmpID+")";
							PreparedStatement ps = cn.prepareStatement(query);
							ps.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						try {
							Connection cn  = DBConnection.getConnection();
							String query = "delete billDetail";
							PreparedStatement ps;
							ps = cn.prepareStatement(query);
							ps.execute();
							Showdulieu();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						billID = generateNumber();
						lblBillID.setText("Bill ID: "+ billID);
						
						lblSubtotal.setText("Subtotal: ");
						lblVat.setText("VAT 8%: ");
						lblTotalPrice.setText("Total: ");
						lblTotalItem.setText("Total Item: ");
					}
				}
				
				
			}
		});
		btnCash.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCash.setActionCommand("");
		btnCash.setBounds(10, 448, 170, 52);
		panel_TotalBill.add(btnCash);
		
		JButton btnDiscount = new JButton("Discount");
		btnDiscount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDiscount.setActionCommand("");
		btnDiscount.setBounds(197, 448, 170, 52);
		panel_TotalBill.add(btnDiscount);
		
		JButton btnShopeepay = new JButton("Sign out");
		btnShopeepay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?") == 0) {
					LoginFr login = new LoginFr();
					login.setVisible(true);
					mainStaffFr.this.dispose();
				}
			}
		});
		btnShopeepay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShopeepay.setActionCommand("");
		btnShopeepay.setBounds(10, 510, 357, 52);
		panel_TotalBill.add(btnShopeepay);
		
		JLabel lblIMG = new JLabel("");
		lblIMG.setIcon(new ImageIcon("D:\\JavaWorkSpace\\CircleKAppDemo\\src\\Img\\image.loginframe.png"));
		lblIMG.setBounds(39, 6, 303, 100);
		panel_TotalBill.add(lblIMG);
		
		JPanel panelProduct = new JPanel();
		panelProduct.setBounds(0, 446, 876, 191);
		contentPane.add(panelProduct);
		panelProduct.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 28, 876, 161);
		panelProduct.add(scrollPane_3);
		
		tableProduct = new JTable();
		tableProduct.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tableProduct.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"ID", "Name", "Supplier ID", "Category ID", "MFG", "EXP", "Unit", "Price"
			}
		));
		scrollPane_3.setViewportView(tableProduct);
		
		JButton btnAdd = new JButton("Add");
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
							JOptionPane.showMessageDialog(null, "Sản phẩm đã được chọn!");
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
								
								String query1 = "insert into BillDetail(ID,NamePr,cateID,Price,Quantity,totalPrice) values("+ id +",N'"+ name +"',"+ cateid +","+ price +","+ quantity +","+tPrice+")";
								String query2 = "insert into details(ID,NamePr,Price,Quantity,totalPrice,billID) values("+ id +",N'"+ name +"',"+ price +","+ quantity +","+ tPrice +","+ billID +")";
								PreparedStatement ps1 = cn.prepareStatement(query1);
								PreparedStatement ps2 = cn.prepareStatement(query2);
								ps1.executeUpdate();
								ps2.executeUpdate();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Showdulieu();
							
							BDtotal();
							bdVAT();
							totalPr(total, vat);
							totalItem();
							
							lblSubtotal.setText("Subtotal: "+ total + " VNĐ");
							lblVat.setText("VAT 8%: "+ vat +" VNĐ");
							lblTotalPrice.setText("Total: "+ totalPr +" VNĐ");
							lblTotalItem.setText("Total Item: "+totalItem);
						}						
					}
				}	
			}
		});
		btnAdd.setBackground(new Color(240, 240, 240));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(438, 0, 95, 25);
		panelProduct.add(btnAdd);
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(0, 0, 37, 25);
		panelProduct.add(lblID);
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField_FindID = new JTextField();
		textField_FindID.setBounds(35, 0, 288, 25);
		panelProduct.add(textField_FindID);
		textField_FindID.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		btnFind.setBounds(333, 0, 95, 25);
		panelProduct.add(btnFind);
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
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
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
	
	private boolean checkBD() {	
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
	
	private void totalPr(int total, int vat) {
		totalPr = this.total + this.vat;
	}
	
	private void totalItem() {
		try {
			Connection cn = DBConnection.getConnection();
			
			String query = "SELECT SUM(Quantity) as Totalitem FROM BillDetail;";
			PreparedStatement ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				totalItem = rs.getInt("Totalitem");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}