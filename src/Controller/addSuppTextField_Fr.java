package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class addSuppTextField_Fr extends JFrame {

	private JPanel contentPane;

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
	public addSuppTextField_Fr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID", SwingConstants.RIGHT);
		lblId.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblId.setBounds(114, 41, 30, 30);
		contentPane.add(lblId);
		
		JLabel lblSupplierName = new JLabel("Supplier Name", SwingConstants.RIGHT);
		lblSupplierName.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblSupplierName.setBounds(43, 74, 129, 30);
		contentPane.add(lblSupplierName);
		
		JLabel lblContactName = new JLabel("Contact Name", SwingConstants.RIGHT);
		lblContactName.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblContactName.setBounds(43, 114, 111, 30);
		contentPane.add(lblContactName);
		
		JLabel lblAddress = new JLabel("Address", SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblAddress.setBounds(43, 154, 111, 30);
		contentPane.add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number", SwingConstants.RIGHT);
		lblPhoneNumber.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblPhoneNumber.setBounds(43, 187, 111, 30);
		contentPane.add(lblPhoneNumber);
	}
}
