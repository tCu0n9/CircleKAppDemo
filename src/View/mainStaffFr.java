package View;

import javax.management.Query;

import java.lang.System.Logger;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.show_update_RowDataProduct_Fr;
import DBConnection.DBConnection;
import Model.Staff;
import Model.billDetail;
import View.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Label;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class mainStaffFr extends JFrame {

	private JPanel contentPane;
	private static JTable table_BillDetail;
	private static JTable tableProduct;
	private JTextField textField_FindID;
	private static int id;
	@SuppressWarnings("unused")
	private String EmpNAME;
	private JTextField textField;
	private int totalPr;
	private int totalItem;
	private static int vat;
	private static int total;
	private JPanel panelPayment;
	private JLabel lblTotalPrice;
	private JPanel panelDiscount;

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
	
	public mainStaffFr() {
		EmpNAME= LoginFr.EmpName;
		clearData();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				Showdulieu();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0xffffff));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelBillDetail = new JPanel();
		panelBillDetail.setBounds(0, 0, 876, 436);
		contentPane.add(panelBillDetail);
		panelBillDetail.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 43, 876, 393);
		panelBillDetail.add(scrollPane);
		
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
		
		JPanel panelTotalBill = new JPanel();
		panelTotalBill.setBounds(884, 0, 380, 681);
		contentPane.add(panelTotalBill);
		panelTotalBill.setLayout(null);
		
		panelPayment = new JPanel();
		panelPayment.setBounds(10, 404, 357, 266);
		panelTotalBill.add(panelPayment);
		panelPayment.setBackground(new Color(255, 255, 255));
		panelPayment.setVisible(false);
		panelPayment.setLayout(null);
		
		panelDiscount = new JPanel();
		panelDiscount.setBackground(new Color(255, 255, 255));
		panelDiscount.setBounds(10, 404, 357, 266);
		panelTotalBill.add(panelDiscount);
		panelDiscount.setVisible(false);
		panelDiscount.setLayout(null);
		
		JLabel lblDc25 = new JLabel("DC25", SwingConstants.CENTER);
		lblDc25.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelDiscount.setVisible(false);
			}
		});
		lblDc25.setOpaque(true);
		lblDc25.setForeground(Color.WHITE);
		lblDc25.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDc25.setBackground(new Color(64, 0, 127));
		lblDc25.setBounds(22, 25, 135, 50);
		panelDiscount.add(lblDc25);
		
		JLabel lblFreeship = new JLabel("FREESHIP", SwingConstants.CENTER);
		lblFreeship.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelDiscount.setVisible(false);
			}
		});
		lblFreeship.setOpaque(true);
		lblFreeship.setForeground(Color.WHITE);
		lblFreeship.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFreeship.setBackground(new Color(64, 0, 127));
		lblFreeship.setBounds(189, 25, 135, 50);
		panelDiscount.add(lblFreeship);
		
		JLabel lblDc50 = new JLabel("DC50", SwingConstants.CENTER);
		lblDc50.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelDiscount.setVisible(false);
			}
		});
		lblDc50.setOpaque(true);
		lblDc50.setForeground(Color.WHITE);
		lblDc50.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDc50.setBackground(new Color(64, 0, 127));
		lblDc50.setBounds(22, 86, 135, 50);
		panelDiscount.add(lblDc50);
		
		JLabel lbl1K = new JLabel("1K", SwingConstants.CENTER);
		lbl1K.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelDiscount.setVisible(false);
			}
		});
		lbl1K.setOpaque(true);
		lbl1K.setForeground(Color.WHITE);
		lbl1K.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl1K.setBackground(new Color(64, 0, 127));
		lbl1K.setBounds(189, 86, 135, 50);
		panelDiscount.add(lbl1K);
		
		JLabel lblDc75 = new JLabel("DC75", SwingConstants.CENTER);
		lblDc75.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelDiscount.setVisible(false);
			}
		});
		lblDc75.setOpaque(true);
		lblDc75.setForeground(Color.WHITE);
		lblDc75.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDc75.setBackground(new Color(64, 0, 127));
		lblDc75.setBounds(22, 147, 135, 50);
		panelDiscount.add(lblDc75);
		
		JLabel lblNone = new JLabel("NONE", SwingConstants.CENTER);
		lblNone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelDiscount.setVisible(false);
			}
		});
		lblNone.setOpaque(true);
		lblNone.setForeground(Color.WHITE);
		lblNone.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNone.setBackground(new Color(64, 0, 127));
		lblNone.setBounds(189, 147, 135, 50);
		panelDiscount.add(lblNone);
		
		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDiscount.setBounds(10, 302, 218, 25);
		panelTotalBill.add(lblDiscount);
		
		JLabel lblVat = new JLabel("VAT %:");
		lblVat.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblVat.setBounds(10, 332, 357, 25);
		panelTotalBill.add(lblVat);
		
		JLabel lblTotalItem = new JLabel("Total Item:");
		lblTotalItem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTotalItem.setBounds(10, 242, 357, 25);
		panelTotalBill.add(lblTotalItem);
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 17)); 
		lblSubtotal.setBounds(10, 272, 357, 25);
		panelTotalBill.add(lblSubtotal);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String time = dateFormat.format(cal.getTime());
		
		JLabel lblDateTime = new JLabel("DateTime: " + time);
		lblDateTime.setBounds(10, 142, 325, 25);
		panelTotalBill.add(lblDateTime);
		lblDateTime.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblBillId = new JLabel("Bill Number:");
		lblBillId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBillId.setBounds(197, 172, 170, 25);
		panelTotalBill.add(lblBillId);
		
		JLabel lblStaffID = new JLabel("Staff Name: "+ EmpNAME);
		lblStaffID.setBounds(10, 112, 357, 25);
		panelTotalBill.add(lblStaffID);
		lblStaffID.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblBillCount = new JLabel("Bill ID:");
		lblBillCount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBillCount.setBounds(10, 172, 170, 25);
		panelTotalBill.add(lblBillCount);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(10, 6, 104, 30);
		panelBillDetail.add(btnDelete);
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
		panelBillDetail.add(btnClean);
		btnClean.addActionListener(new ActionListener() {
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
				
				lblSubtotal.setText("Subtotal: ");
				lblVat.setText("VAT 8%: ");
				lblTotalPrice.setText("Total: ");
				lblTotalItem.setText("Total Item: ");
			}
		});
		btnClean.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblCustomerID = new JLabel("Customer ID:");
		lblCustomerID.setBounds(245, 6, 109, 25);
		panelBillDetail.add(lblCustomerID);
		lblCustomerID.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textField = new JTextField();
		textField.setBounds(367, 7, 203, 25);
		panelBillDetail.add(textField);
		textField.setColumns(10);
		
		JLabel lblSignout = new JLabel("Sign out",SwingConstants.CENTER);
		lblSignout.setForeground(new Color(255, 255, 255));
		lblSignout.setBackground(new Color(64, 0, 128));
		lblSignout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?") == 0) {
					LoginFr login = new LoginFr();
					login.setVisible(true);
					mainStaffFr.this.dispose();
				}
			}
		});
		lblSignout.setOpaque(true);
		lblSignout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSignout.setBounds(817, 6, 59, 25);
		panelBillDetail.add(lblSignout);
		
		JLabel lblIBillIcon = new JLabel("");
		lblIBillIcon.setIcon(new ImageIcon("D:\\JavaWorkSpace\\CircleKAppDemo\\src\\Img\\image.loginframe.png"));
		lblIBillIcon.setBounds(39, 6, 303, 100);
		panelTotalBill.add(lblIBillIcon);
		
		JLabel lblCash = new JLabel("CASH", SwingConstants.CENTER);
		lblCash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPayment.setVisible(false);
			}
		});
		lblCash.setOpaque(true);
		lblCash.setForeground(Color.WHITE);
		lblCash.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCash.setBackground(new Color(64, 0, 127));
		lblCash.setBounds(27, 20, 135, 50);
		panelPayment.add(lblCash);
		
		JLabel lblPayment = new JLabel("PAYMENT",SwingConstants.CENTER);
		lblPayment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPayment.setVisible(true);
			}
		});
		lblPayment.setOpaque(true);
		lblPayment.setForeground(new Color(255, 255, 255));
		lblPayment.setBackground(new Color(64, 0, 127));
		lblPayment.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPayment.setBounds(132, 620, 135, 50);
		panelTotalBill.add(lblPayment);
		
		JLabel lblTotalPrice = new JLabel("Total:");
		lblTotalPrice.setBounds(10, 368, 360, 25);
		panelTotalBill.add(lblTotalPrice);
		lblTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblDiscountClicked = new JLabel("DISCOUNT", SwingConstants.CENTER);
		lblDiscountClicked.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelDiscount.setVisible(true);
			}
		});
		lblDiscountClicked.setOpaque(true);
		lblDiscountClicked.setForeground(Color.WHITE);
		lblDiscountClicked.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDiscountClicked.setBackground(new Color(64, 0, 127));
		lblDiscountClicked.setBounds(238, 302, 102, 25);
		panelTotalBill.add(lblDiscountClicked);
		
		JLabel lblCreditCard = new JLabel("CREDIT CARD", SwingConstants.CENTER);
		lblCreditCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPayment.setVisible(false);
			}
		});
		lblCreditCard.setOpaque(true);
		lblCreditCard.setForeground(Color.WHITE);
		lblCreditCard.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCreditCard.setBackground(new Color(64, 0, 127));
		lblCreditCard.setBounds(27, 81, 135, 50);
		panelPayment.add(lblCreditCard);
		
		JLabel lblShopeepay = new JLabel("SHOPEEPAY", SwingConstants.CENTER);
		lblShopeepay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPayment.setVisible(false);
			}
		});
		lblShopeepay.setOpaque(true);
		lblShopeepay.setForeground(Color.WHITE);
		lblShopeepay.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblShopeepay.setBackground(new Color(64, 0, 127));
		lblShopeepay.setBounds(27, 142, 135, 50);
		panelPayment.add(lblShopeepay);
		
		JLabel lblVnpay = new JLabel("VNPAY", SwingConstants.CENTER);
		lblVnpay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPayment.setVisible(false);
			}
		});
		lblVnpay.setOpaque(true);
		lblVnpay.setForeground(Color.WHITE);
		lblVnpay.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblVnpay.setBackground(new Color(64, 0, 127));
		lblVnpay.setBounds(194, 142, 135, 50);
		panelPayment.add(lblVnpay);
		
		JLabel lblZalopay = new JLabel("ZALOPAY", SwingConstants.CENTER);
		lblZalopay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPayment.setVisible(false);
			}
		});
		lblZalopay.setOpaque(true);
		lblZalopay.setForeground(Color.WHITE);
		lblZalopay.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblZalopay.setBackground(new Color(64, 0, 127));
		lblZalopay.setBounds(194, 81, 135, 50);
		panelPayment.add(lblZalopay);
		
		JLabel lblMomo = new JLabel("MOMO", SwingConstants.CENTER);
		lblMomo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelPayment.setVisible(false);
			}
		});
		lblMomo.setOpaque(true);
		lblMomo.setForeground(Color.WHITE);
		lblMomo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMomo.setBackground(new Color(64, 0, 127));
		lblMomo.setBounds(194, 20, 135, 50);
		panelPayment.add(lblMomo);
		
		JLabel lblCustomerid = new JLabel("CustomerID:");
		lblCustomerid.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCustomerid.setBounds(10, 206, 325, 25);
		panelTotalBill.add(lblCustomerid);
		
		JPanel panelProduct = new JPanel();
		panelProduct.setBounds(0, 446, 876, 235);
		contentPane.add(panelProduct);
		panelProduct.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 28, 876, 207);
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
		textField_FindID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
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
			}
		});
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
