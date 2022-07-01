package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Transparency;
import utils.Utils;
import javax.swing.JButton;

public class HomeForm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeForm frame = new HomeForm();
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
	// Tạm thời để image như thế để nó hiện icon lên giao diện cho dễ chỉnh
	public HomeForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1133, 689);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 51, 102));
		panel.setBounds(0, 0, 1132, 141);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("INVENTORY MANAGEMENT SYSTEM");
		lblNewLabel_1.setBounds(280, 23, 515, 38);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("MAIN FORM");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(443, 71, 183, 38);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 34));
		lblNewLabel.setBounds(1090, 16, 32, 49);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 51, 102));
		panel_1.setBounds(0, 674, 1132, 16);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel1 = new JLabel("");
		ImageIcon imgIconProduct = new ImageIcon("C:\\Users\\Admin\\Desktop\\cart.png");
		ImageIcon newImgIconProduct = Utils.setIcon(imgIconProduct, 90, 90);
		lblNewLabel1.setIcon(newImgIconProduct);
		lblNewLabel1.setBounds(48, 219, 113, 128);
		contentPane.add(lblNewLabel1);
		
		JLabel lblNewLabel_2 = new JLabel("PRODUCT");
		lblNewLabel_2.setForeground(new Color(255, 51, 102));
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_2.setBounds(48, 184, 157, 39);
		contentPane.add(lblNewLabel_2);
		ImageIcon imgIconUser = new ImageIcon("C:\\Users\\Admin\\Desktop\\login_icon.png");
		ImageIcon newImgIconUser = Utils.setIcon(imgIconUser, 90, 90);
		
		JLabel lblNewLabel_2_1 = new JLabel("USER");
		lblNewLabel_2_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_2_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_2_1.setBounds(945, 185, 64, 39);
		contentPane.add(lblNewLabel_2_1);
		ImageIcon imgIconCategory = new ImageIcon("C:\\Users\\Admin\\Desktop\\cart.png");
		ImageIcon newImgIconCategory = Utils.setIcon(imgIconCategory, 90, 90);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("CATEGORY");
		lblNewLabel_2_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_2_1_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_2_1_1.setBounds(474, 305, 157, 39);
		contentPane.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(Utils.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\login_icon.png"), 90, 90));
		lblNewLabel_3.setBounds(930, 230, 100, 117);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(Utils.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\inventory_icons.png"), 90, 90));
		lblNewLabel_4.setBounds(484, 336, 113, 128);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2_2 = new JLabel("CUSTOMER");
		lblNewLabel_2_2.setForeground(new Color(255, 51, 102));
		lblNewLabel_2_2.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_2_2.setBounds(48, 456, 139, 39);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(Utils.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\customer_icon.png"), 90, 90));
		lblNewLabel_5.setBounds(61, 505, 100, 90);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("ORDER");
		lblNewLabel_2_2_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_2_2_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_2_2_1.setBounds(942, 456, 88, 39);
		contentPane.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(Utils.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\order_icon.png"), 90, 90));
		lblNewLabel_6.setBounds(945, 505, 100, 90);
		contentPane.add(lblNewLabel_6);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnLogout.setBackground(new Color(255, 51, 102));
		btnLogout.setBounds(474, 556, 123, 39);
		contentPane.add(btnLogout);
		
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		
	}
}
