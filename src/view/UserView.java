package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.UserController;
import model.dto.UserDTO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserView extends JFrame implements View<UserDTO> {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtPhone;
	private Long currentNumber;
	private JTable table;
	private DefaultTableModel model;
	private List<UserDTO> usersDTOList = new ArrayList<UserDTO>();
	private UserView userView = this;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserView frame = new UserView();
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
	public UserView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initComponents();
		this.initUsersData();
		this.resetAllField();
		// Selected Row in table
		this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int firstIndex = e.getFirstIndex();
				if (!e.getValueIsAdjusting()) { // prevent duplicated event
					if (firstIndex >= 0 && firstIndex < model.getRowCount()) { // for Refresh
						int i_row = table.getSelectedRow();
						String idSelectedRow = model.getValueAt(i_row, 0).toString();
						String username = model.getValueAt(i_row, 1).toString();
						String password = model.getValueAt(i_row, 2).toString();
						String phone = model.getValueAt(i_row, 3).toString();

						currentNumber = Long.parseLong(idSelectedRow);
						txtUsername.setText(username);
						txtPassword.setText(password);
						txtPhone.setText(phone);
					}
				}
			}
		});
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
	}

	private void initComponents() {
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
		lblNewLabel_1.setBounds(320, 23, 515, 38);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("MANAGE USERS");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(468, 71, 235, 38);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 0=yes, 1=no, 2=cancel
				int isExit = JOptionPane.showConfirmDialog(userView, "Are you want to exit ?");
				if(isExit == 0)
					dispose();
			}
		});
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 34));
		lblNewLabel.setBounds(1086, 16, 32, 49);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1_1_1 = new JLabel("Uname");
		lblNewLabel_1_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1_1.setBounds(29, 200, 106, 31);
		contentPane.add(lblNewLabel_1_1_1);

		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(179, 200, 254, 31);
		contentPane.add(txtUsername);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1_1_1.setBounds(29, 256, 114, 39);
		contentPane.add(lblNewLabel_1_1_1_1);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(179, 264, 254, 31);
		contentPane.add(txtPassword);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Phone");
		lblNewLabel_1_1_1_2.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_1_2.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1_1_2.setBounds(29, 321, 106, 31);
		contentPane.add(lblNewLabel_1_1_1_2);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(179, 321, 254, 31);
		contentPane.add(txtPhone);

		// User Action Listener
		UserController userAction = new UserController(this);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(userAction);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnAdd.setBackground(new Color(255, 51, 102));
		btnAdd.setBounds(29, 395, 114, 39);
		contentPane.add(btnAdd);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(userAction);
		btnEdit.addActionListener(userAction);
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnEdit.setBackground(new Color(255, 51, 102));
		btnEdit.setBounds(179, 395, 114, 39);
		contentPane.add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(userAction);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnDelete.setBackground(new Color(255, 51, 102));
		btnDelete.setBounds(319, 395, 114, 39);
		contentPane.add(btnDelete);

		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(userAction);
		btnHome.setForeground(Color.WHITE);
		btnHome.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnHome.setBackground(new Color(255, 51, 102));
		btnHome.setBounds(179, 455, 114, 39);
		contentPane.add(btnHome);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "No", "Name", "Password", "Phone" }));
		this.model = (DefaultTableModel) this.table.getModel();
		table.setSelectionBackground(new Color(255, 51, 102));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(464, 200, 653, 399);
		contentPane.add(scrollPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 51, 102));
		panel_1.setBounds(0, 667, 1141, 20);
		contentPane.add(panel_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("USERS LIST");
		lblNewLabel_1_1_2.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_2.setFont(new Font("Century Gothic", Font.BOLD, 26));
		lblNewLabel_1_1_2.setBounds(708, 152, 132, 38);
		contentPane.add(lblNewLabel_1_1_2);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(userAction);
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnRefresh.setBackground(new Color(255, 51, 102));
		btnRefresh.setBounds(708, 609, 132, 39);
		contentPane.add(btnRefresh);
	}

	public void resetAllField() {
		this.txtUsername.setText("");
		this.txtPassword.setText("");
		this.txtPhone.setText("");
	}
	
	private void initUsersData() {
		this.showData(usersDTOList, model);
	}

	@Override
	public void showData(List<UserDTO> list, DefaultTableModel model) {
		model.getDataVector().removeAllElements();
		model.fireTableChanged(null);

		if (list.size() > 0) {
			list.stream().forEach(item -> model
					.addRow(new Object[] { item.getId(), item.getUsername(), item.getPassword(), item.getPhone() }));
		}
	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public void setTxtUsername(JTextField txtUsername) {
		this.txtUsername = txtUsername;
	}

	public JTextField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(JTextField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public JTextField getTxtPhone() {
		return txtPhone;
	}

	public void setTxtPhone(JTextField txtPhone) {
		this.txtPhone = txtPhone;
	}

	public Long getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(Long currentNumber) {
		this.currentNumber = currentNumber;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public List<UserDTO> getUsersDTOList() {
		return usersDTOList;
	}

	public void setUsersDTOList(List<UserDTO> usersDTOList) {
		this.usersDTOList = usersDTOList;
	}

}
