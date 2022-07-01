package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class Customer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame = new Customer();
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
	public Customer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1141, 687);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 51, 102));
		panel.setBounds(0, 0, 1140, 141);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("INVENTORY MANAGEMENT SYSTEM");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel_1.setBounds(280, 23, 515, 38);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("CUSTOMER PRODUCT");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(374, 79, 311, 38);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 34));
		lblNewLabel.setBounds(1086, 16, 32, 49);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("CusId");
		lblNewLabel_1_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1_1.setBounds(29, 200, 106, 31);
		contentPane.add(lblNewLabel_1_1_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(179, 200, 254, 31);
		contentPane.add(textField);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Name");
		lblNewLabel_1_1_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1_1_1.setBounds(29, 256, 114, 39);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(179, 264, 254, 31);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Phone");
		lblNewLabel_1_1_1_2.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_1_2.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1_1_2.setBounds(29, 321, 106, 31);
		contentPane.add(lblNewLabel_1_1_1_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(179, 321, 254, 31);
		contentPane.add(textField_2);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnAdd.setBackground(new Color(255, 51, 102));
		btnAdd.setBounds(29, 395, 114, 39);
		contentPane.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnEdit.setBackground(new Color(255, 51, 102));
		btnEdit.setBounds(179, 395, 114, 39);
		contentPane.add(btnEdit);
		
		JButton btnEdit_1 = new JButton("Delete");
		btnEdit_1.setForeground(Color.WHITE);
		btnEdit_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnEdit_1.setBackground(new Color(255, 51, 102));
		btnEdit_1.setBounds(319, 395, 114, 39);
		contentPane.add(btnEdit_1);
		
		JButton btnHome = new JButton("Home");
		btnHome.setForeground(Color.WHITE);
		btnHome.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnHome.setBackground(new Color(255, 51, 102));
		btnHome.setBounds(179, 455, 114, 39);
		contentPane.add(btnHome);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Customer Id", "Name", "Phone"
			}
		));
		table.setSelectionBackground(new Color(255, 51, 102));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(464, 200, 653, 399);
		contentPane.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 51, 102));
		panel_1.setBounds(0, 667, 1141, 20);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("CUSTOMERS LIST");
		lblNewLabel_1_1_2.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_2.setFont(new Font("Century Gothic", Font.BOLD, 26));
		lblNewLabel_1_1_2.setBounds(693, 152, 207, 38);
		contentPane.add(lblNewLabel_1_1_2);

		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
	}
}
