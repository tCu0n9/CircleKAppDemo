package Controller;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DBConnection.DBConnection;
import View.mainManagerFr_Ver2;

import javax.swing.JScrollPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class showDetailsBill_Fr extends JFrame {

	private JPanel contentPane;
	private static JTable tableDetails;
	static String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showDetailsBill_Fr frame = new showDetailsBill_Fr();
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
	
	public static void showDataDetails() {
		try {
			tableDetails.removeAll();
			String[] arr3 = {"ID", "Product Name", "Price", "Quantity", "Total Price"};
			DefaultTableModel model3 = new DefaultTableModel(arr3,0);
			
			Connection cn = DBConnection.getConnection();
			
			String query = "select * from dbo.Details where Billid = " + id;
			PreparedStatement ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector vector = new Vector();
				vector.add(rs.getString("ID"));
				vector.add(rs.getString("NamePr"));
				vector.add(rs.getString("Price"));
				vector.add(rs.getString("Quantity"));
				vector.add(rs.getString("totalPrice"));
				model3.addRow(vector);
			}
			tableDetails.setModel(model3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public showDetailsBill_Fr() {
		id = mainManagerFr_Ver2.Billid;
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				showDataDetails();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 662, 335);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 661, 332);
		panel.add(scrollPane);
		
		tableDetails = new JTable();
		scrollPane.setViewportView(tableDetails);
		tableDetails.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Product Name", "Price", "Quantity", "Total Price"
			}
		));
	}
}
