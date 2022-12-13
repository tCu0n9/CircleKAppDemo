package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import DBConnection.DBConnection;

@SuppressWarnings("serial")
public class LoginFr extends JFrame {

	private JPanel contentPane;
	private JTextField TextField_UserName;
	private JPasswordField pwdPassword;
	public static String EmpName;
	public static int EmpID;
	private final JPanel cam = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFr frame = new LoginFr();
					frame.setTitle("Circle K App");
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
	
	public int Login() {
			
			try {
				Connection cn = DBConnection.getConnection();
				
				String username = TextField_UserName.getText();
				String pass = pwdPassword.getText();
				
				String query = "Select * from dbo.[Employees] where userName = '"+username+"' and pw = '"+ pass+"'";
				PreparedStatement ps = cn.prepareStatement(query); 
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					int position = rs.getInt("position");
					if(position == 1) {
						// manager
						return 1;
					}
					else if(position == 0) {
						//staff
						return 0;
					}else {
						//sai
						return 2;
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//sai
			return 2;
	}
	
	public String getName() {
		try {
			Connection cn = DBConnection.getConnection();
			
			String username = TextField_UserName.getText();
			String pass = pwdPassword.getText();
			
			String query = "Select * from dbo.[Employees] where userName = '"+username+"' and pw = '"+ pass+"'";
			PreparedStatement ps = cn.prepareStatement(query); 
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String StName = rs.getString("EmpName");
				return StName;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int getID() {
		try {
			Connection cn = DBConnection.getConnection();
			
			String username = TextField_UserName.getText();
			String pass = pwdPassword.getText();
			
			String query = "Select * from dbo.[Employees] where userName = '"+username+"' and pw = '"+ pass+"'";
			PreparedStatement ps = cn.prepareStatement(query); 
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int StID = rs.getInt("EmpID");
				return StID;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public LoginFr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 463);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0xec2934));
	
		setUndecorated(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		ImageIcon img = new ImageIcon("D:\\JavaWorkSpace\\CircleKAppDemo\\src\\Img\\logo.selectionTab.png");
		
		setIconImage(img.getImage());
		
		JPanel panel = new JPanel();
		panel.setBounds(473, 0, 477, 463);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255));
		
		
	
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(255, 255, 255));
		btnExit.setBounds(246, 304, 134, 29);
		panel.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frExit = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frExit, "Confirm if you want to exit","Circle K App",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		TextField_UserName = new JTextField();
		TextField_UserName.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		TextField_UserName.setBounds(138, 150, 242, 35);
		panel.add(TextField_UserName);
		TextField_UserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		TextField_UserName.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pwdPassword.setBounds(138, 195, 242, 35);
		panel.add(pwdPassword);
		pwdPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(new Color(255, 255, 255));
		btnReset.setBounds(91, 303, 134, 31);
		panel.add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TextField_UserName.setText(null);
				pwdPassword.setText(null);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setBackground(new Color(255, 255, 255));
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogIn.setBounds(91, 263, 289, 31);
		panel.add(btnLogIn);
		
		btnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Login() == 1) {
					mainManagerFr_Ver2 main = new mainManagerFr_Ver2();
					main.setVisible(true);
					main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					main.setBounds(100, 50, 1280, 720);
					
					dispose();
				}else if(Login() == 0) {
					EmpName = getName();
					EmpID = getID();
					
					mainStaffFr main = new mainStaffFr();
					main.setVisible(true);
					main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					main.setBounds(100, 100, 1280, 675);
					
					
					dispose();

				}else {
					JOptionPane.showMessageDialog(null, "sai thông tin");
				}
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 17));
		ImageIcon iconUser = new ImageIcon("D:\\JavaWorkSpace\\CircleKAppDemo\\src\\Img\\User (3).png");
		ImageIcon iconLock = new ImageIcon("D:\\JavaWorkSpace\\CircleKAppDemo\\src\\Img\\locked (3).png");

		JLabel lb_User = new JLabel();
		lb_User.setBounds(93, 150, 35, 35);
		panel.add(lb_User);
		lb_User.setIcon(iconUser);
		
		JLabel lb_Pass = new JLabel();
		lb_Pass.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Pass.setBounds(93, 195, 35, 35);
		panel.add(lb_Pass);
		lb_Pass.setIcon(iconLock);
		
		JLabel btnX = new JLabel("X");
		btnX.setHorizontalTextPosition(SwingConstants.CENTER);
		btnX.setHorizontalAlignment(SwingConstants.CENTER);
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frExit = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frExit, "Confirm if you want to exit","Circle K App",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnX.setFont(new Font("Myanmar Text", Font.BOLD, 20));
		btnX.setBounds(457, 0, 20, 31);
		panel.add(btnX);
		
		JLabel lbLogo = new JLabel("");
		ImageIcon icon1 = new ImageIcon("D:\\JavaWorkSpace\\CircleKAppDemo\\src\\Img\\Circle_K tron2.png");
		lbLogo.setIcon(icon1);
		lbLogo.setBounds(134,108,200,202);
		getContentPane().add(lbLogo);
		
		JLabel Title = new JLabel("Circle K");
		Title.setHorizontalTextPosition(SwingConstants.CENTER);
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setForeground(new Color(0xff7f27));
		Title.setFont(new Font("Monospaced", Font.BOLD, 50));
		Title.setBounds(86, 320, 275, 40);
		contentPane.add(Title);
		cam.setBackground(new Color(255, 127, 39));
		cam.setBounds(0, 443, 475, 20);
		contentPane.add(cam);
		

	}
}

