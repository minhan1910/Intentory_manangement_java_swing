package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import utils.Utils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JFrame{

	private JPanel contentPane;
	private JTextField txtUserId;
	private JTextField txtPassword;
	private boolean isLogin = false;
	private LoginView loginView = this;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 51, 102));
		panel.setBounds(0, 0, 469, 114);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("InvSys");
		lblNewLabel_1.setBounds(169, 20, 106, 73);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		
		JLabel lblNewLabel_2 = new JLabel("X");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 0=yes, 1=no, 2=cancel
				int isExit = JOptionPane.showConfirmDialog(loginView, "Are you want to exit ?");
				if(isExit == 0)
					dispose();
			}
		});
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.PLAIN, 34));
		lblNewLabel_2.setBounds(385, 30, 32, 49);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Uid");
		lblNewLabel_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1.setBounds(25, 244, 106, 31);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1_1.setBounds(25, 300, 114, 39);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtUserId = new JTextField();
		txtUserId.setBounds(154, 244, 254, 31);
		contentPane.add(txtUserId);
		txtUserId.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(154, 308, 254, 31);
		contentPane.add(txtPassword);
		
		// Login Action Listener
		LoginController loginAction = new LoginController(this);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(loginAction);
		btnLogin.setBackground(new Color(255, 51, 102));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnLogin.setBounds(39, 380, 114, 39);
		contentPane.add(btnLogin);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(loginAction);
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnClear.setBackground(new Color(255, 51, 102));
		btnClear.setBounds(241, 380, 114, 39);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon imgIcon = new ImageIcon("src/resources/login_icon4.png");
		ImageIcon newImgIcon = Utils.setIcon(imgIcon, 100, 100);
		lblNewLabel.setIcon(newImgIcon);
		lblNewLabel.setBounds(150, 100, 133, 151);
		contentPane.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 51, 102));
		panel_1.setBounds(0, 476, 428, 39);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("CodeSpace");
		lblNewLabel_1_2.setBounds(133, -16, 189, 73);
		panel_1.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Century Gothic", Font.BOLD, 26));
		
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
	}
	
	public void resetAllField() {
		this.txtUserId.setText("");
		this.txtPassword.setText("");
	}
	
	public JTextField getTxtUserId() {
		return txtUserId;
	}

	public void setTxtUserId(JTextField txtUserId) {
		this.txtUserId = txtUserId;
	}

	public JTextField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(JTextField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
}
