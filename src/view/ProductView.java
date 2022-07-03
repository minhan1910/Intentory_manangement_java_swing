package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import controller.ProductController;
import model.dto.ProductDTO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductView extends JFrame implements View<ProductDTO> {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtProductId;
	private JTextField txtName;
	private JTextField txtQuantity;
	private JTextField txtDescription;
	public Long currentNumber;
	private JComboBox<String> comboCategory;
	private DefaultTableModel model;
	private List<ProductDTO> productsDTOList = new ArrayList<ProductDTO>();
	private List<String> categoriesNameList = new ArrayList<String>();
	private JTable table;
	private ProductView productView = this;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ProductView frame = new ProductView();
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
	public ProductView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initComponents();
		this.showCategories();
		this.showProductsDTOList();
		this.resetAllTextField();
		// Selected Row in table
		this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int firstIndex = e.getFirstIndex();
				if (!e.getValueIsAdjusting()) { // prevent duplicated event
					if (firstIndex >= 0 && firstIndex < model.getRowCount()) { // for Refresh
						int i_row = table.getSelectedRow();
						String idSelectedRow = model.getValueAt(i_row, 0).toString();
						String productId = model.getValueAt(i_row, 1).toString();
						String productName = model.getValueAt(i_row, 2).toString();
						String productQuantity = model.getValueAt(i_row, 3).toString();
						String productDescription = model.getValueAt(i_row, 4).toString();
						String productCategoryName = model.getValueAt(i_row, 5).toString();

						currentNumber = Long.parseLong(idSelectedRow);
						txtProductId.setText(productId);
						txtName.setText(productName);
						txtQuantity.setText(productQuantity);
						txtDescription.setText(productDescription);
						comboCategory.setSelectedItem(productCategoryName);

					}
				}
			}
		});

		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
	}

	public void resetAllTextField() {
		this.txtDescription.setText("");
		this.txtName.setText("");
		this.txtProductId.setText("");
		this.txtQuantity.setText("");
		this.comboCategory.setSelectedIndex(0);
	}

	private void showProductsDTOList() {
		showData(productsDTOList, model);
	}

	private void showCategories() {
		this.categoriesNameList.stream().forEach(item -> this.comboCategory.addItem(item));
	}

	@Override
	public void showData(List<ProductDTO> list, DefaultTableModel model) {
		model.getDataVector().removeAllElements();
		model.fireTableChanged(null);
		
		if(list.size() > 0 || list != null)
			list.stream().forEach(item -> model.addRow(new Object[] { item.getId(), item.getProductId(), item.getName(),
					item.getQuantity(), item.getDescription(), item.getCategoryName() }));
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
		lblNewLabel_1.setBounds(346, 23, 515, 38);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("MANAGE PRODUCTS");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(438, 71, 298, 38);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 0=yes, 1=no, 2=cancel
				int isExit = JOptionPane.showConfirmDialog(productView, "Are you want to exit ?");
				if(isExit == 0)
					dispose();
			}
		});
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 34));
		lblNewLabel.setBounds(1086, 16, 32, 49);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1_1_1 = new JLabel("ProId");
		lblNewLabel_1_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1_1.setBounds(29, 200, 106, 31);
		contentPane.add(lblNewLabel_1_1_1);

		txtProductId = new JTextField();
		txtProductId.setColumns(10);
		txtProductId.setBounds(179, 200, 254, 31);
		contentPane.add(txtProductId);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Name");
		lblNewLabel_1_1_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1_1_1.setBounds(29, 256, 114, 39);
		contentPane.add(lblNewLabel_1_1_1_1);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(179, 264, 254, 31);
		contentPane.add(txtName);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Quantity");
		lblNewLabel_1_1_1_2.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_1_2.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1_1_2.setBounds(29, 321, 106, 31);
		contentPane.add(lblNewLabel_1_1_1_2);

		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(179, 321, 254, 31);
		contentPane.add(txtQuantity);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Description");
		lblNewLabel_1_1_1_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1_1_1_1.setBounds(29, 377, 140, 39);
		contentPane.add(lblNewLabel_1_1_1_1_1);

		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(179, 385, 254, 31);
		contentPane.add(txtDescription);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Category");
		lblNewLabel_1_1_1_1_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1_1_1_1_1.setBounds(29, 444, 140, 39);
		contentPane.add(lblNewLabel_1_1_1_1_1_1);

		comboCategory = new JComboBox<String>();
		comboCategory.setBackground(Color.WHITE);
		comboCategory.setForeground(new Color(255, 51, 102));
		comboCategory.setFont(new Font("Century Gothic", Font.BOLD, 16));
		comboCategory.setBounds(179, 448, 254, 36);
		contentPane.add(comboCategory);

		// Product Action Listener
		ProductController productAction = new ProductController(this);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(productAction);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnAdd.setBackground(new Color(255, 51, 102));
		btnAdd.setBounds(29, 500, 114, 39);
		contentPane.add(btnAdd);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(productAction);
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnEdit.setBackground(new Color(255, 51, 102));
		btnEdit.setBounds(179, 500, 114, 39);
		contentPane.add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(productAction);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnDelete.setBackground(new Color(255, 51, 102));
		btnDelete.setBounds(319, 500, 114, 39);
		contentPane.add(btnDelete);

		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(productAction);
		btnHome.setForeground(Color.WHITE);
		btnHome.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnHome.setBackground(new Color(255, 51, 102));
		btnHome.setBounds(179, 560, 114, 39);
		contentPane.add(btnHome);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "No", "Product Id", "Name", "Quantity", "Description", "Category" }));
		this.model = (DefaultTableModel) table.getModel();
		table.setSelectionBackground(new Color(255, 51, 102));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(464, 200, 653, 399);
		contentPane.add(scrollPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 51, 102));
		panel_1.setBounds(0, 667, 1141, 20);
		contentPane.add(panel_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("PRODUCTS LIST");
		lblNewLabel_1_1_2.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_2.setFont(new Font("Century Gothic", Font.BOLD, 26));
		lblNewLabel_1_1_2.setBounds(713, 151, 184, 38);
		contentPane.add(lblNewLabel_1_1_2);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(productAction);
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnRefresh.setBackground(new Color(255, 51, 102));
		btnRefresh.setBounds(751, 609, 114, 39);
		contentPane.add(btnRefresh);
	}

	public JTextField getTxtProductId() {
		return txtProductId;
	}

	public void setTxtProductId(JTextField txtProductId) {
		this.txtProductId = txtProductId;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtQuantity() {
		return txtQuantity;
	}

	public void setTxtQuantity(JTextField txtQuantity) {
		this.txtQuantity = txtQuantity;
	}

	public JTextField getTxtDescription() {
		return txtDescription;
	}

	public void setTxtDescription(JTextField txtDescription) {
		this.txtDescription = txtDescription;
	}

	public List<ProductDTO> getProductsDTOList() {
		return productsDTOList;
	}

	public void setProductsDTOList(List<ProductDTO> productsDTOList) {
		this.productsDTOList = productsDTOList;
	}

	public JComboBox<String> getComboCategory() {
		return comboCategory;
	}

	public void setComboCategory(JComboBox<String> comboCategory) {
		this.comboCategory = comboCategory;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public List<String> getCategoriesNameList() {
		return categoriesNameList;
	}

	public void setCategoriesNameList(List<String> categoriesNameList) {
		this.categoriesNameList = categoriesNameList;
	}

}
