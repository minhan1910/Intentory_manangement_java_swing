package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import utils.Utils;

public class Splash extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash frame = new Splash();
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
	public Splash() {
		this.setTitle("Inventory Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 382);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(0, 315, 631, 30);
		contentPane.add(progressBar);
		
		JLabel lblNewLabel = new JLabel("Inventor Management System");
		lblNewLabel.setForeground(new Color(255, 51, 102));
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel.setBounds(104, 10, 445, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("InvSys");
		lblNewLabel_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel_1.setBounds(329, 132, 232, 46);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		//"C:\\Users\\Admin\\Desktop\\db_icon.png"
		ImageIcon imageIcon = new ImageIcon("src/resources/db_icon.png");
		Image image = imageIcon.getImage(); // transform it 
		image = Utils.getScaledImage(image, 120, 120); // scale it
		imageIcon = new ImageIcon(image);
		lblNewLabel_2.setIcon(imageIcon);
		lblNewLabel_2.setBounds(197, 77, 120, 149);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("%");
		lblNewLabel_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(283, 259, 34, 46);
		contentPane.add(lblNewLabel_1_1);
		
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
