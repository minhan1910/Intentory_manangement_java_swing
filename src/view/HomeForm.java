package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.HomeController;
import utils.Utils;

public class HomeForm extends JFrame {
	private JPanel contentPane;
	private HomeForm homeForm = this;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					HomeForm frame = new HomeForm();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	// Tạm thời để image như thế để nó hiện icon lên giao diện cho dễ chỉnh
	public HomeForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1133, 689);
		
		// Home Action Listener
		HomeController homeAction = new HomeController(this);
		
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
		lblNewLabel_1.setBounds(316, 23, 515, 38);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("MAIN FORM");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(477, 71, 183, 38);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 0=yes, 1=no, 2=cancel
				int isExit = JOptionPane.showConfirmDialog(homeForm, "Are you want to exit ?");
				if(isExit == 0)
					dispose();
				
			}
		});
		lblClose.setForeground(Color.WHITE);
		lblClose.setFont(new Font("Century Gothic", Font.PLAIN, 34));
		lblClose.setBounds(1090, 16, 32, 49);
		panel.add(lblClose);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 51, 102));
		panel_1.setBounds(0, 674, 1132, 16);
		contentPane.add(panel_1);
		
		JLabel lblProduct = new JLabel("");
		lblProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductView productView = new ProductView();
				productView.setVisible(true);
				JOptionPane.showMessageDialog(productView, "Welcome to Products Management");
			}
		});
		ImageIcon imgIconProduct = new ImageIcon("C:\\Users\\Admin\\Desktop\\cart.png");
		ImageIcon newImgIconProduct = Utils.setIcon(imgIconProduct, 90, 90);
		lblProduct.setIcon(newImgIconProduct);
		lblProduct.setBounds(48, 219, 113, 128);
		contentPane.add(lblProduct);
		
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
		
		JLabel lblUser = new JLabel("");
		lblUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserView userView = new UserView();
				userView.setVisible(true);
				JOptionPane.showMessageDialog(userView, "Welcome to Users Management");
			}
		});
		lblUser.setIcon(Utils.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\login_icon.png"), 90, 90));
		lblUser.setBounds(930, 230, 100, 117);
		contentPane.add(lblUser);
		
		JLabel lblCategory = new JLabel("");
		lblCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CategoryView categoryView = new CategoryView();
				categoryView.setVisible(true);
				JOptionPane.showMessageDialog(categoryView, "Welcome to Categories Management");
			}
		});
		lblCategory.setIcon(Utils.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\inventory_icons.png"), 90, 90));
		lblCategory.setBounds(484, 336, 113, 128);
		contentPane.add(lblCategory);
		
		JLabel lblNewLabel_2_2 = new JLabel("CUSTOMER");
		lblNewLabel_2_2.setForeground(new Color(255, 51, 102));
		lblNewLabel_2_2.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_2_2.setBounds(48, 456, 139, 39);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblCustomer = new JLabel("");
		lblCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerView customerView = new CustomerView();
				customerView.setVisible(true);
				JOptionPane.showMessageDialog(customerView, "Welcome to Customers Management");
			}
		});
		lblCustomer.setIcon(Utils.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\customer_icon.png"), 90, 90));
		lblCustomer.setBounds(61, 505, 100, 90);
		contentPane.add(lblCustomer);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("ORDER");
		lblNewLabel_2_2_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_2_2_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_2_2_1.setBounds(942, 456, 88, 39);
		contentPane.add(lblNewLabel_2_2_1);
		
		JLabel lblOrder = new JLabel("");
		lblOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OrderView orderView = new OrderView();
				orderView.setVisible(true);
				JOptionPane.showMessageDialog(orderView, "Welcome to Orders Management");
			}
		});
		lblOrder.setIcon(Utils.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\order_icon.png"), 90, 90));
		lblOrder.setBounds(945, 505, 100, 90);
		contentPane.add(lblOrder);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(homeAction);
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnLogout.setBackground(new Color(255, 51, 102));
		btnLogout.setBounds(474, 556, 123, 39);
		contentPane.add(btnLogout);
		
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		
	}
}
