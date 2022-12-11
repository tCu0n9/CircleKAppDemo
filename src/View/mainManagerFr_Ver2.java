package View;

import Model.*;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.JobAttributes;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.ietf.jgss.Oid;

import Controller.addStaffTextField_Fr;
import Controller.findDataStaff_Fr;
import Controller.show_update_RowDataCustomer_Fr;
import Controller.show_update_RowDataProduct_Fr;
import Controller.show_update_RowDataStaff_Fr;
import Controller.addCustomerTextField_Fr;
import Controller.addProductsTextField_Fr;
import DBConnection.DBConnection;

import javax.swing.JScrollPane;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class mainManagerFr_Ver2 extends JFrame {
	private final JLabel lblDashBoardLogo = new JLabel("");
	private JLayeredPane layeredPane;
	private JPanel panelProducts;
	private JPanel panelStaffs;
	private JPanel panelCustomers;
	private JPanel panelSupplier;
	private JPanel panelCategory;
	
	public static JTable tableStaff;
	private static JTable tableProduct;
	private static JTable tableCustomer;
	private static JTable tableSupplier;
	private static JTable tableCategory;
	
	private JTextField textField_FindID;
	private JTextField textField_FindName;
	
	private String inputID;
	private static String pst;
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainManagerFr_Ver2 frame = new mainManagerFr_Ver2();
					frame.setTitle("Circle K App");
					frame.setVisible(true);
					
					ImageIcon img = new ImageIcon("D:\\JavaWorkSpace\\CircleKAppDemo\\src\\Img\\logo.selectionTab.png");
					frame.setIconImage(img.getImage());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	
	
	
	public static void showDataProduct() {
		
		try {
			tableProduct.removeAll();
			String[] arr2 = {"ID", "Name", "Supplier ID", "Category ID", "MFG", "EXP", "Unit", "Price"};
			DefaultTableModel model2 = new DefaultTableModel(arr2,0);
			
			Connection cn = DBConnection.getConnection();
			
			String query = "select * from dbo.[Product]";
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
	
	@SuppressWarnings("unchecked")
	public static void showDataStaff() {
		
		try {
			tableStaff.removeAll();
			String[] arr1 = {"ID", "Full Name", "Age", "Address", "Phone Number", "UserName", "Password", "Position"};
			DefaultTableModel model1 = new DefaultTableModel(arr1,0);
			
			Connection cn = DBConnection.getConnection();
			
			String query = "select * from dbo.[Employees]";
			PreparedStatement ps = cn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector vector = new Vector();
				vector.add(rs.getString("EmpID"));
				vector.add(rs.getString("EmpName"));
				vector.add(rs.getString("BoD"));
				vector.add(rs.getString("EmpAddress"));
				vector.add(rs.getString("PhoneNum"));
				vector.add(rs.getString("Username"));
				vector.add(rs.getString("pw"));
				
				int rsPst = Integer.parseInt(rs.getString("position"));
				if( rsPst == 1) {
					pst = "Manager";
				}else if(rsPst == 0) {
					pst = "Staff";
				}
				vector.add(pst);
				
				model1.addRow(vector);
			}
			tableStaff.setModel(model1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//LogManager.getLogger(mainManagerFr.class.getName()).log(Level,SERVER,null,e);
			e.printStackTrace();
		}
		
	}
	
	public static void showDataCustomer() {
		
		try {
			tableCustomer.removeAll();
			String[] arr3 = {"ID","Full Name","Address","Phone Number"};
			DefaultTableModel model3 = new DefaultTableModel(arr3,0);
			
			Connection cn = DBConnection.getConnection();
			
			String query = "select * from dbo.Customer";
			PreparedStatement ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector vector = new Vector();
				vector.add(rs.getString("CustomerID"));
				vector.add(rs.getString("CustomerName"));
				vector.add(rs.getString("CustomerAddress"));
				vector.add(rs.getString("CustomerPhone"));
				model3.addRow(vector);
			}
			tableCustomer.setModel(model3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void showDataSupplier() {
		tableSupplier.removeAll();
		String[] arr4 = {"ID", "Supplier Name", "Contact Name", "Address", "Phone Number"};
		DefaultTableModel model4 = new DefaultTableModel(arr4,0);
		
		Connection cn = DBConnection.getConnection();
		
		String query = "select * from dbo.supplier";
		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector vector = new Vector();
				vector.add(rs.getString("SupID"));
				vector.add(rs.getString("SupName"));
				vector.add(rs.getString("ContactName"));
				vector.add(rs.getString("SupAddress"));
				vector.add(rs.getString("SupPhone"));
				model4.addRow(vector);
			}
			tableSupplier.setModel(model4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void showDataCategory() {
		tableCategory.removeAll();
		String[] arr5 = {"Category ID", "Category"};
		DefaultTableModel model5 = new DefaultTableModel(arr5,0);
		
		Connection cn = DBConnection.getConnection();
		
		String query = "select * from dbo.category";
		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector vector = new Vector();
				vector.add(rs.getString("CategoryID"));
				vector.add(rs.getString("CategoryName"));
				model5.addRow(vector);
			}
			tableCategory.setModel(model5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void findStaff() {
		try {
			tableStaff.removeAll();
			String[] arr1 = {"ID","Full Name","BoD","Address","Phone Number"};
			DefaultTableModel model1 = new DefaultTableModel(arr1,0);
			
			Connection cn = DBConnection.getConnection();
			
			String id = textField_FindID.getText();
			String name = textField_FindName.getText();
			
			String query = "select * from dbo.[Employees] where EmpID = "+ id +" or EmpName like N'%"+ name +"%'";
			PreparedStatement ps = cn.prepareStatement(query);
			
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Vector vector = new Vector();
				vector.add(rs1.getString("EmpID"));
				vector.add(rs1.getString("EmpName"));
				vector.add(rs1.getString("BoD"));
				vector.add(rs1.getString("EmpAddress"));
				vector.add(rs1.getString("PhoneNum"));
				model1.addRow(vector);
			}
			tableStaff.setModel(model1);

			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	
	
	public mainManagerFr_Ver2() {
		Staff st = new Staff();
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				showDataProduct();
				showDataStaff();
				showDataCustomer();
				showDataSupplier();
				showDataCategory();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setUndecorated(true);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new LineBorder(new Color(255, 255, 255), 5, true));
		mainPanel.setBounds(0, 0, 1005, 620);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);	
		
		JPanel selectionTabPanel = new JPanel();
		selectionTabPanel.setBounds(0, 0, 230, 720);
		selectionTabPanel.setBackground(new Color(0xec2934));
		selectionTabPanel.setBorder(new LineBorder(new Color(0xff7f27), 2));
		
		selectionTabPanel.setLayout(null);
		lblDashBoardLogo.setBounds(15, 5, 210, 210);
		lblDashBoardLogo.setIcon(new ImageIcon("D:\\JavaWorkSpace\\CircleKAppDemo\\src\\Img\\logo.selectionTab.png"));
		selectionTabPanel.add(lblDashBoardLogo);
		mainPanel.add(selectionTabPanel);
		
		JPanel productPanel = new JPanel();
		productPanel.addMouseListener(new PanelButtonMouseAdapter(productPanel));
		productPanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				panelProducts.setVisible(true);
				panelStaffs.setVisible(false);
				panelCustomers.setVisible(false);
				//panelSupplier.setVisible(false);
				//panelCategory.setVisible(false);
			}
		});
		productPanel.setBorder(new LineBorder(new Color(0xff7f27), 2));
		productPanel.setBounds(0, 225, 230, 70);
		productPanel.setBackground(new Color(0xec2934));
		selectionTabPanel.add(productPanel);
		productPanel.setLayout(null);
		
		JLabel lblTextProduct = new JLabel("PRODUCTS");
		lblTextProduct.setBounds(93, 0, 137, 70);
		lblTextProduct.setForeground(new Color(255, 255, 255));
		lblTextProduct.setFont(new Font("Monospaced", Font.BOLD, 20));
		productPanel.add(lblTextProduct);
		
		JLabel lblIconPr = new JLabel("");
		lblIconPr.setBounds(30, 10, 50, 50);
		lblIconPr.setIcon(new ImageIcon("D:\\JavaWorkSpace\\CircleKAppDemo\\src\\Img\\icon.product.png"));
		productPanel.add(lblIconPr);
		
		JPanel staffPanel = new JPanel();
		staffPanel.addMouseListener(new PanelButtonMouseAdapter(staffPanel));
		staffPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				panelProducts.setVisible(false);
				panelStaffs.setVisible(true);
				
				panelCustomers.setVisible(false);
				//panelSupplier.setVisible(false);
				//panelCategory.setVisible(false);
			}
		});
		staffPanel.setBorder(new LineBorder(new Color(0xff7f27), 2));
		staffPanel.setBounds(0, 293, 230, 70);
		staffPanel.setBackground(new Color(0xec2934));
		selectionTabPanel.add(staffPanel);
		staffPanel.setLayout(null);
		
		JLabel lblTextStaff = new JLabel("STAFF");
		lblTextStaff.setForeground(new Color(255, 255, 255));
		lblTextStaff.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblTextStaff.setBounds(93, 0, 137, 70);
		staffPanel.add(lblTextStaff);
		
		JLabel lblIconStaff = new JLabel("");
		lblIconStaff.setIcon(new ImageIcon("D:\\JavaWorkSpace\\CircleKAppDemo\\src\\Img\\icon.staff.png"));
		lblIconStaff.setBounds(30, 10, 50, 50);
		staffPanel.add(lblIconStaff);
		
		JPanel customerPanel = new JPanel();
		customerPanel.addMouseListener(new PanelButtonMouseAdapter(customerPanel));
		customerPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelProducts.setVisible(false);
				panelStaffs.setVisible(false);
				panelCustomers.setVisible(true);
				//panelSupplier.setVisible(false);
				//panelCategory.setVisible(false);
			}
		});
		customerPanel.setBorder(new LineBorder(new Color(0xff7f27), 2));
		customerPanel.setBounds(0, 360, 230, 70);
		customerPanel.setBackground(new Color(0xec2934));
		selectionTabPanel.add(customerPanel);
		customerPanel.setLayout(null);
		
		JLabel lblTextCustomer = new JLabel("CUSTOMER");
		lblTextCustomer.setForeground(new Color(255, 255, 255));
		lblTextCustomer.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblTextCustomer.setBounds(93, 0, 137, 70);
		customerPanel.add(lblTextCustomer);
		
		JLabel lblIconCustomer = new JLabel("");
		lblIconCustomer.setIcon(new ImageIcon("D:\\JavaWorkSpace\\CircleKAppDemo\\src\\Img\\icon.customer.png"));
		lblIconCustomer.setBounds(30, 10, 50, 50);
		customerPanel.add(lblIconCustomer);
		
		JPanel signoutPanel = new JPanel();
		signoutPanel.addMouseListener(new PanelButtonMouseAdapter(signoutPanel));
		signoutPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?") == 0) {
					LoginFr login = new LoginFr();
					login.setVisible(true);
					mainManagerFr_Ver2.this.dispose();
				}
			}
		});
		signoutPanel.setBorder(new LineBorder(new Color(0xff7f27), 2));
		signoutPanel.setBackground(new Color(236, 41, 52));
		signoutPanel.setBounds(0, 562, 230, 70);
		selectionTabPanel.add(signoutPanel);
		signoutPanel.setLayout(null);
		
		JLabel lblTextSignout = new JLabel("SIGN OUT");
		lblTextSignout.setBounds(93, 0, 137, 70);
		lblTextSignout.setForeground(Color.WHITE);
		lblTextSignout.setFont(new Font("Monospaced", Font.BOLD, 20));
		signoutPanel.add(lblTextSignout);
		
		JLabel lblIconExit = new JLabel("");
		lblIconExit.setBounds(30, 10, 50, 50);
		lblIconExit.setIcon(new ImageIcon("D:\\JavaWorkSpace\\CircleKAppDemo\\src\\Img\\icon.exit.png"));
		signoutPanel.add(lblIconExit);
		
		JPanel SupplierPanel = new JPanel();
		SupplierPanel.addMouseListener(new PanelButtonMouseAdapter(SupplierPanel));
		SupplierPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelProducts.setVisible(false);
				panelStaffs.setVisible(false);
				panelCustomers.setVisible(false);
				panelSupplier.setVisible(true);
				panelCategory.setVisible(false);
			}
		});
		SupplierPanel.setLayout(null);
		SupplierPanel.setBorder(new LineBorder(new Color(0xff7f27), 2));
		SupplierPanel.setBackground(new Color(236, 41, 52));
		SupplierPanel.setBounds(0, 428, 230, 70);
		SupplierPanel.addMouseListener(new PanelButtonMouseAdapter(SupplierPanel));
		selectionTabPanel.add(SupplierPanel);
		
		JLabel lblTextProduct_1 = new JLabel("SUPPLIER");
		lblTextProduct_1.setForeground(Color.WHITE);
		lblTextProduct_1.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblTextProduct_1.setBounds(93, 0, 137, 70);
		SupplierPanel.add(lblTextProduct_1);
		
		JLabel lblIconSupp = new JLabel("");
		lblIconSupp.setBounds(30, 10, 50, 50);
		lblIconSupp.setIcon(new ImageIcon("D:\\JavaWorkSpace\\CircleKAppDemo\\src\\Img\\SupplierIcon.png"));
		SupplierPanel.add(lblIconSupp);
		
		JPanel CategoryPanel = new JPanel();
		CategoryPanel.addMouseListener(new PanelButtonMouseAdapter(CategoryPanel));
		CategoryPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelProducts.setVisible(false);
				panelStaffs.setVisible(false);
				panelCustomers.setVisible(false);
				panelSupplier.setVisible(false);
				panelCategory.setVisible(true);
			}
		});
		CategoryPanel.setLayout(null);
		CategoryPanel.setBorder(new LineBorder(new Color(0xff7f27), 2));
		CategoryPanel.setBackground(new Color(236, 41, 52));
		CategoryPanel.setBounds(0, 495, 230, 70);
		CategoryPanel.addMouseListener(new PanelButtonMouseAdapter(CategoryPanel));
		selectionTabPanel.add(CategoryPanel);
		
		JLabel lblCategory = new JLabel("CATEGORY");
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblCategory.setBounds(93, 0, 137, 70);
		CategoryPanel.add(lblCategory);
		
		JLabel lblIconCate = new JLabel("");
		lblIconCate.setBounds(30, 10, 50, 50);
		lblIconCate.setIcon(new ImageIcon("D:\\JavaWorkSpace\\CircleKAppDemo\\src\\Img\\CategoryIcon.png"));
		CategoryPanel.add(lblIconCate);
		
		JPanel mainContentPanel = new JPanel();
		mainContentPanel.setBounds(240, 41, 1015, 680);
		mainPanel.add(mainContentPanel);
		mainContentPanel.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1015, 679);
		mainContentPanel.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		panelProducts = new JPanel();
		layeredPane.add(panelProducts, "name_89738470225600");
		panelProducts.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 995, 565);
		panelProducts.add(scrollPane);
		
		tableProduct = new JTable();
		tableProduct.setFont(new Font("Cambria", Font.BOLD, 14));
		tableProduct.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Name", "Supplier ID", "Category ID", "MFG", "EXP", "Unit", "Price"
			}
		));
		tableProduct.getColumnModel().getColumn(0).setPreferredWidth(29);
		tableProduct.getColumnModel().getColumn(0).setMinWidth(30);
		tableProduct.getColumnModel().getColumn(0).setMaxWidth(30);
		tableProduct.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableProduct.getColumnModel().getColumn(2).setPreferredWidth(65);
		tableProduct.getColumnModel().getColumn(3).setPreferredWidth(65);
		tableProduct.getColumnModel().getColumn(4).setPreferredWidth(90);
		tableProduct.getColumnModel().getColumn(5).setPreferredWidth(90);
		tableProduct.getColumnModel().getColumn(6).setPreferredWidth(90);
		tableProduct.getColumnModel().getColumn(7).setPreferredWidth(89);
		
		scrollPane.setViewportView(tableProduct);
		
		JLabel lblAddPr = new JLabel("Add", SwingConstants.CENTER);
		lblAddPr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addProductsTextField_Fr frText = new addProductsTextField_Fr();
				frText.setVisible(true);
				frText.setBounds(100, 100, 600, 325);
				frText.setLocationRelativeTo(null);
				frText.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		lblAddPr.setOpaque(true);
		lblAddPr.setForeground(Color.WHITE);
		lblAddPr.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblAddPr.setBackground(new Color(236, 41, 52));
		lblAddPr.setBounds(10, 600, 155, 70);
		panelProducts.add(lblAddPr);
		
		JLabel lblUpdatePr = new JLabel("Update", SwingConstants.CENTER);
		lblUpdatePr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				show_update_RowDataProduct_Fr jtableProdcutRowdata = new show_update_RowDataProduct_Fr();
				
				int index = tableProduct.getSelectedRow();
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "Chưa chọn đối tượng !");
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
					
					jtableProdcutRowdata.setVisible(true);
					jtableProdcutRowdata.setBounds(100, 100, 500, 500);
					jtableProdcutRowdata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
					jtableProdcutRowdata.textField_ID.setText(id);
					jtableProdcutRowdata.textField_PrName.setText(name);
					jtableProdcutRowdata.textField_SuppID.setText(supid);
					jtableProdcutRowdata.textField_CateID.setText(cateid);
					jtableProdcutRowdata.textField_MFG.setText(mfg);
					jtableProdcutRowdata.textField_EXP.setText(exp);
					jtableProdcutRowdata.textField_Unit.setText(unit);
					jtableProdcutRowdata.textField_Price.setText(price);
				}	
			}
		});
		lblUpdatePr.setOpaque(true);
		lblUpdatePr.setForeground(Color.WHITE);
		lblUpdatePr.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblUpdatePr.setBackground(new Color(236, 41, 52));
		lblUpdatePr.setBounds(575, 600, 155, 70);
		panelProducts.add(lblUpdatePr);
		
		JLabel lblDeletePr = new JLabel("Delete", SwingConstants.CENTER);
		lblDeletePr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = tableProduct.getSelectedRow();
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "Chưa chọn đối tượng !");
				}else {
					TableModel model = tableProduct.getModel();
					String id = model.getValueAt(index, 0).toString();
					
					try {
						Connection cn = DBConnection.getConnection();
						String query = "delete from dbo.[Product] where ID = ?";
						PreparedStatement ps = cn.prepareStatement(query);
						ps.setString(1,id);
						ps.execute();
						showDataProduct();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		lblDeletePr.setOpaque(true);
		lblDeletePr.setForeground(Color.WHITE);
		lblDeletePr.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblDeletePr.setBackground(new Color(236, 41, 52));
		lblDeletePr.setBounds(850, 600, 155, 70);
		panelProducts.add(lblDeletePr);
		
		JLabel lblFindPr = new JLabel("Find", SwingConstants.CENTER);
		lblFindPr.setOpaque(true);
		lblFindPr.setForeground(Color.WHITE);
		lblFindPr.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblFindPr.setBackground(new Color(236, 41, 52));
		lblFindPr.setBounds(295, 600, 155, 70);
		panelProducts.add(lblFindPr);
		
		panelStaffs = new JPanel();
		layeredPane.add(panelStaffs, "name_89813235964000");
		panelStaffs.setLayout(null);
		
		JScrollPane scrollPaneStaff = new JScrollPane();
		scrollPaneStaff.setBounds(10, 10, 995, 565);
		panelStaffs.add(scrollPaneStaff);
		
		tableStaff = new JTable() {
			public boolean isCellEditTable(int row, int column) {
				for(int i=0;i<tableStaff.getRowCount();i++) {
					if(row == i && column == 0) {
						return false;
					}
				}
				return true;
			}
		};
		tableStaff.setFont(new Font("Cambria", Font.BOLD, 14));
			
		scrollPaneStaff.setViewportView(tableStaff);
		tableStaff.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Full Name", "Age", "Address", "Phone Number", "UserName", "Position", "Password"
			}
		));
		tableStaff.getColumnModel().getColumn(3).setPreferredWidth(25);
		tableStaff.getColumnModel().getColumn(5).setPreferredWidth(32);
		tableStaff.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableStaff.getColumnModel().getColumn(7).setPreferredWidth(79);
		
		JLabel lblAddStaff = new JLabel("Add",SwingConstants.CENTER);
		lblAddStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addStaffTextField_Fr frTextAdd = new addStaffTextField_Fr();
				frTextAdd.setVisible(true);
				frTextAdd.setBounds(100, 100, 600, 440);
				frTextAdd.setLocationRelativeTo(null);
				frTextAdd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		lblAddStaff.setBackground(new Color(236, 41, 52));
		lblAddStaff.setOpaque(true);
		lblAddStaff.setForeground(new Color(255, 255, 255));
		lblAddStaff.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblAddStaff.setBounds(10, 600, 155, 70);
		panelStaffs.add(lblAddStaff);
		
		JLabel lblUpdateStaff = new JLabel("Update", SwingConstants.CENTER);
		lblUpdateStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				show_update_RowDataStaff_Fr jtableProdcutRowdata = new show_update_RowDataStaff_Fr();
				
				int index = tableStaff.getSelectedRow();
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "Chưa chọn đối tượng !");
				}else {
					TableModel model = tableStaff.getModel();
					String id = model.getValueAt(index, 0).toString();
					String fullname = model.getValueAt(index, 1).toString();
					String bod = model.getValueAt(index, 2).toString();
					String address = model.getValueAt(index, 3).toString();
					String phoneNumb = model.getValueAt(index, 4).toString();
					
					jtableProdcutRowdata.setVisible(true);
					jtableProdcutRowdata.setBounds(100, 100, 548, 325);
					jtableProdcutRowdata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
					jtableProdcutRowdata.textField_ID.setText(id);
					jtableProdcutRowdata.textField_Name.setText(fullname);
					jtableProdcutRowdata.textField_BoD.setText(bod);
					jtableProdcutRowdata.textField_Address.setText(address);
					jtableProdcutRowdata.textField_PhoneNumb.setText(phoneNumb);
				}
			}
		});
		lblUpdateStaff.setOpaque(true);
		lblUpdateStaff.setForeground(Color.WHITE);
		lblUpdateStaff.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblUpdateStaff.setBackground(new Color(236, 41, 52));
		lblUpdateStaff.setBounds(575, 600, 155, 70);
		panelStaffs.add(lblUpdateStaff);
		
		JLabel lblDeleteStaff = new JLabel("Delete", SwingConstants.CENTER);
		lblDeleteStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = tableStaff.getSelectedRow();
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "Chưa chọn đối tượng !");
				}else {
					TableModel model = tableStaff.getModel();
					String id = model.getValueAt(index, 0).toString();
					
					try {
						
						Connection cn = DBConnection.getConnection();
						String query = "delete from dbo.[Employees] where EmpID = ?";
						PreparedStatement ps = cn.prepareStatement(query);
						ps.setString(1,id);
						ps.execute();
						
						showDataStaff();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		lblDeleteStaff.setOpaque(true);
		lblDeleteStaff.setForeground(Color.WHITE);
		lblDeleteStaff.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblDeleteStaff.setBackground(new Color(236, 41, 52));
		lblDeleteStaff.setBounds(850, 600, 155, 70);
		panelStaffs.add(lblDeleteStaff);
		
		JLabel lblFindStaff = new JLabel("Find", SwingConstants.CENTER);
		lblFindStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				findStaff();
			}
		});
		lblFindStaff.setOpaque(true);
		lblFindStaff.setForeground(Color.WHITE);
		lblFindStaff.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblFindStaff.setBackground(new Color(236, 41, 52));
		lblFindStaff.setBounds(295, 600, 155, 70);
		panelStaffs.add(lblFindStaff);
		
		panelCustomers = new JPanel();
		layeredPane.add(panelCustomers, "name_89867989374100");
		panelCustomers.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 995, 565);
		panelCustomers.add(scrollPane_1);
		
		tableCustomer = new JTable();
		tableCustomer.setFont(new Font("Cambria", Font.BOLD, 14));
		tableCustomer.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"ID", "Full Name", "Address", "Phone Number"
			}
		));
		scrollPane_1.setViewportView(tableCustomer);
		
		JLabel lblAddCus = new JLabel("Add", SwingConstants.CENTER);
		lblAddCus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addCustomerTextField_Fr frTextAdd = new addCustomerTextField_Fr();
				frTextAdd.setVisible(true);
				frTextAdd.setBounds(100, 100, 600, 300);
				frTextAdd.setLocationRelativeTo(null);
				frTextAdd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		lblAddCus.setOpaque(true);
		lblAddCus.setForeground(Color.WHITE);
		lblAddCus.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblAddCus.setBackground(new Color(236, 41, 52));
		lblAddCus.setBounds(10, 600, 155, 70);
		panelCustomers.add(lblAddCus);
		
		JLabel lblUpdateCus = new JLabel("Update", SwingConstants.CENTER);
		lblUpdateCus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				show_update_RowDataCustomer_Fr jtableProdcutRowdata = new show_update_RowDataCustomer_Fr();
				
				int index = tableCustomer.getSelectedRow();
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "Chưa chọn đối tượng !");
				}else {
					TableModel model = tableCustomer.getModel();
					String id = model.getValueAt(index, 0).toString();
					String fullname = model.getValueAt(index, 1).toString();
					String address = model.getValueAt(index, 2).toString();
					String phoneNumb = model.getValueAt(index, 3).toString();
					
					jtableProdcutRowdata.setVisible(true);
					jtableProdcutRowdata.setBounds(100, 100, 548, 325);
					jtableProdcutRowdata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
					jtableProdcutRowdata.textField_ID.setText(id);
					jtableProdcutRowdata.textField_Name.setText(fullname);
					jtableProdcutRowdata.textField_Address.setText(address);
					jtableProdcutRowdata.textField_PhoneNumb.setText(phoneNumb);
				}

			}
		});
		lblUpdateCus.setOpaque(true);
		lblUpdateCus.setForeground(Color.WHITE);
		lblUpdateCus.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblUpdateCus.setBackground(new Color(236, 41, 52));
		lblUpdateCus.setBounds(575, 600, 155, 70);
		panelCustomers.add(lblUpdateCus);
		
		JLabel lblDeleteCus = new JLabel("Delete", SwingConstants.CENTER);
		lblDeleteCus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableCustomer.getSelectedRow();
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "Chưa chọn đối tượng !");
				}else {
					TableModel model = tableCustomer.getModel();
					String id = model.getValueAt(index, 0).toString();
					
					try {
						
						Connection cn = DBConnection.getConnection();
						String query = "delete from dbo.[Customer] where CustomerID = ?";
						PreparedStatement ps = cn.prepareStatement(query);
						ps.setString(1,id);
						ps.execute();
						
						showDataCustomer();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		lblDeleteCus.setOpaque(true);
		lblDeleteCus.setForeground(Color.WHITE);
		lblDeleteCus.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblDeleteCus.setBackground(new Color(236, 41, 52));
		lblDeleteCus.setBounds(850, 600, 155, 70);
		panelCustomers.add(lblDeleteCus);
		
		JLabel lblFindCus = new JLabel("Find", SwingConstants.CENTER);
		lblFindCus.setOpaque(true);
		lblFindCus.setForeground(Color.WHITE);
		lblFindCus.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblFindCus.setBackground(new Color(236, 41, 52));
		lblFindCus.setBounds(295, 600, 155, 70);
		panelCustomers.add(lblFindCus);
		
		panelSupplier = new JPanel();
		layeredPane.add(panelSupplier, "name_21413985300800");
		panelSupplier.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 10, 995, 565);
		panelSupplier.add(scrollPane_2);
		
		tableSupplier = new JTable();
		tableSupplier.setFont(new Font("Cambria", Font.BOLD, 14));
		tableSupplier.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Supplier Name", "Contact Name", "Address", "Phone Number"
			}
		));
		scrollPane_2.setViewportView(tableSupplier);
		
		JLabel lblAddSupp = new JLabel("Add", SwingConstants.CENTER);
		lblAddSupp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		lblAddSupp.setOpaque(true);
		lblAddSupp.setForeground(Color.WHITE);
		lblAddSupp.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblAddSupp.setBackground(new Color(236, 41, 52));
		lblAddSupp.setBounds(10, 600, 155, 70);
		panelSupplier.add(lblAddSupp);
		
		JLabel lblUpdateSupp = new JLabel("Update", SwingConstants.CENTER);
		lblUpdateSupp.setOpaque(true);
		lblUpdateSupp.setForeground(Color.WHITE);
		lblUpdateSupp.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblUpdateSupp.setBackground(new Color(236, 41, 52));
		lblUpdateSupp.setBounds(575, 600, 155, 70);
		panelSupplier.add(lblUpdateSupp);
		
		JLabel lblDeleteSupp = new JLabel("Delete", SwingConstants.CENTER);
		lblDeleteSupp.setOpaque(true);
		lblDeleteSupp.setForeground(Color.WHITE);
		lblDeleteSupp.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblDeleteSupp.setBackground(new Color(236, 41, 52));
		lblDeleteSupp.setBounds(850, 600, 155, 70);
		panelSupplier.add(lblDeleteSupp);
		
		JLabel lblFindCus_1 = new JLabel("Find", SwingConstants.CENTER);
		lblFindCus_1.setOpaque(true);
		lblFindCus_1.setForeground(Color.WHITE);
		lblFindCus_1.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblFindCus_1.setBackground(new Color(236, 41, 52));
		lblFindCus_1.setBounds(295, 600, 155, 70);
		panelSupplier.add(lblFindCus_1);
		
		panelCategory = new JPanel();
		layeredPane.add(panelCategory, "name_21437883108900");
		panelCategory.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 10, 995, 565);
		panelCategory.add(scrollPane_3);
		
		tableCategory = new JTable();
		tableCategory.setFont(new Font("Cambria", Font.BOLD, 14));
		tableCategory.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Category ID", "Category"
			}
		));
		scrollPane_3.setViewportView(tableCategory);
		
		JLabel lblAddCate = new JLabel("Add", SwingConstants.CENTER);
		lblAddCate.setOpaque(true);
		lblAddCate.setForeground(Color.WHITE);
		lblAddCate.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblAddCate.setBackground(new Color(236, 41, 52));
		lblAddCate.setBounds(10, 600, 155, 70);
		panelCategory.add(lblAddCate);
		
		JLabel lblFindCate = new JLabel("Find", SwingConstants.CENTER);
		lblFindCate.setOpaque(true);
		lblFindCate.setForeground(Color.WHITE);
		lblFindCate.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblFindCate.setBackground(new Color(236, 41, 52));
		lblFindCate.setBounds(295, 600, 155, 70);
		panelCategory.add(lblFindCate);
		
		JLabel lblUpdateCate = new JLabel("Update", SwingConstants.CENTER);
		lblUpdateCate.setOpaque(true);
		lblUpdateCate.setForeground(Color.WHITE);
		lblUpdateCate.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblUpdateCate.setBackground(new Color(236, 41, 52));
		lblUpdateCate.setBounds(575, 600, 155, 70);
		panelCategory.add(lblUpdateCate);
		
		JLabel lblDeleteCate = new JLabel("Delete", SwingConstants.CENTER);
		lblDeleteCate.setOpaque(true);
		lblDeleteCate.setForeground(Color.WHITE);
		lblDeleteCate.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblDeleteCate.setBackground(new Color(236, 41, 52));
		lblDeleteCate.setBounds(850, 600, 155, 70);
		panelCategory.add(lblDeleteCate);
		
		JLabel lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frExit = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frExit, "Confirm if you want to exit","Circle K App",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		lblExit.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblExit.setBounds(1252, 0, 28, 40);
		mainPanel.add(lblExit);
		
		textField_FindID = new JTextField();
		textField_FindID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c)) {
					JOptionPane.showMessageDialog(null, "Chỉ nhập số!!");
					textField_FindID.setText(null);
				}
			}
		});
		textField_FindID.setBounds(299, 10, 69, 28);
		mainPanel.add(textField_FindID);
		textField_FindID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblNewLabel.setBounds(253, 10, 36, 30);
		mainPanel.add(lblNewLabel);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblName.setBounds(388, 10, 54, 30);
		mainPanel.add(lblName);
		
		textField_FindName = new JTextField();
		textField_FindName.setColumns(10);
		textField_FindName.setBounds(447, 10, 343, 28);
		mainPanel.add(textField_FindName);
	}
	
	public void switchPanel(JPanel p) {
		layeredPane.removeAll();
		layeredPane.add(p);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	public class PanelButtonMouseAdapter extends MouseAdapter {
		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel = panel;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(0x4E4D4D));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(236,41,52));
		}
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(0x4E4D4D));
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(0x4E4D4D));
		}
	}

	public static void AddRowToJTableProduct(Object[] dataRow) {
		DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
		model.addRow(dataRow);
	}
	
	public static void AddRowToJTableStaff(Object[] dataRow) {
		// TODO Auto-generated method stub
		DefaultTableModel model = (DefaultTableModel)tableStaff.getModel();
		model.addRow(dataRow);
	}
	
	public static void AddRowToJTableCustomer(Object[] dataRow) {
		DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
		model.addRow(dataRow);
	}
	
	
	@SuppressWarnings("unused")
	private boolean checkTrungIDProduct() {
		try {
			Connection cn = DBConnection.getConnection();
		
			String query = "select * from dbo.[Product] where ID = ?";
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setString(1, inputID);
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
	
	
	@SuppressWarnings("unused")
	private boolean checkTrungIDStaff() {
		try {
			Connection cn = DBConnection.getConnection();
		
			String query = "select * from dbo.[Employees] where EmpID = ?";
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setString(1, inputID);
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
	
	@SuppressWarnings("unused")
	private boolean checkTrungIDCustomer() {
		try {
			Connection cn = DBConnection.getConnection();
		
			String query = "select * from dbo.[Customer] where CustomerID = ?";
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setString(1, inputID);
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
}
