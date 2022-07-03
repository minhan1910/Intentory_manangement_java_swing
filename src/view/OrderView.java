package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import model.dto.CustomerDTO;
import model.dto.CustomerOrderDTO;
import model.dto.OrderDTO;
import model.dto.ProductDTO;

public class OrderView extends JFrame {

	private JPanel contentPane;
	private JTable customerTable;
	private JTable productTable;
	private JTextField txtOrderId;
	private JTextField txtOrderPrice;
	private JTextField txtOrderQuantity;
	private JTable orderTable;
	private List<OrderDTO> ordersDTOList 				= new ArrayList<>();
	private List<ProductDTO> productsDTOList 			= new ArrayList<>();
	private List<CustomerDTO> customersDTOList 			= new ArrayList<>();
	private List<CustomerOrderDTO> customersOrdersList 	= new ArrayList<>();
	private DefaultTableModel orderModel;
	private DefaultTableModel customerModel;
	private DefaultTableModel productModel;
	private CustomerDTO customerDTORowSelected;
	private ProductDTO productDTORowSelected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderView frame = new OrderView();
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
	public OrderView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1194, 820);
		this.initComponents();
		
		this.initCustomerDatas();
		this.initProductDatas();
		
		
		
		
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
	}
	
	private void customerTableAddMouseListener() {
		this.customerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int firstIndex = e.getFirstIndex();
				if(!e.getValueIsAdjusting()) { //prevent dupliacted event	
					if (firstIndex >= 0 && firstIndex < customerModel.getRowCount()) { // for Refresh
						int i_row = customerTable.getSelectedRow();
						String idSelectedRow	= customerModel.getValueAt(i_row, 0).toString();
						String customerName 	= customerModel.getValueAt(i_row, 1).toString();
						String customerPhone 	= customerModel.getValueAt(i_row, 2).toString();
						
						customerDTORowSelected = new CustomerDTO();
					} 
				}
			}
		});
	}
	
	private void productTableAddMouseListener() {
		this.productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int firstIndex = e.getFirstIndex();
				if(!e.getValueIsAdjusting()) { //prevent dupliacted event	
					if (firstIndex >= 0 && firstIndex < productModel.getRowCount()) { // for Refresh
						int i_row = productTable.getSelectedRow();
//						"PRODID", "PRODNAME", "PRODQTY", "PRODDESC", "PRODCAT"
						String idSelectedRow		= productModel.getValueAt(i_row, 0).toString();
						String productName 			= productModel.getValueAt(i_row, 1).toString();
						String productQuantity 		= productModel.getValueAt(i_row, 2).toString();
						String productDesc 			= productModel.getValueAt(i_row, 3).toString();
						String productCategoryName 	= productModel.getValueAt(i_row, 4).toString();
					} 
				}
			}
		});
	}
	
	public void initCustomerDatas() {
		this.showData(this.customersDTOList, this.customerModel);
	}
	
	public void initProductDatas() {
		this.showData(this.productsDTOList, this.productModel);
	}
	
	public void initOrderDatas() {
		this.showData(this.ordersDTOList, this.orderModel);
	}
	
	<T> void showData(List<T> list, DefaultTableModel model) {
		model.getDataVector().removeAllElements();
		model.fireTableChanged(null);
		
		list.stream().forEach(item -> {
			if(item instanceof CustomerDTO) {
				CustomerDTO customerDTO = (CustomerDTO) item;
				model.addRow(new Object[] {
						customerDTO.getId(), customerDTO.getName(), customerDTO.getPhone()
				});
			}
			
			if(item instanceof ProductDTO) {
				ProductDTO productDTO = (ProductDTO) item;
				model.addRow(new Object[] {
//						"PRODID", "PRODNAME", "PRODQTY", "PRODDESC", "PRODCAT"
						productDTO.getId(), productDTO.getName(), 
						productDTO.getQuantity(), productDTO.getDescription(),
						productDTO.getCategoryName()
				});
			}
			
			
			if(item instanceof OrderDTO) {
				OrderDTO orderDTO = (OrderDTO) item;
				model.addRow(new Object[] {
//						"Num", "Product", "Quantity", "UPrice", "Total"
						orderDTO.getId(), orderDTO.getProductName(), orderDTO.getQuantity(),
						orderDTO.getPrice(), orderDTO.getTotal()
				});
			}
		});
	}
	
	private void initComponents() {
		
		// Order Action Listener
		OrderController orderAction = new OrderController(this);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 51, 102));
		panel.setBounds(0, 0, 1220, 141);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("INVENTORY MANAGEMENT SYSTEM");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel_1.setBounds(350, 23, 515, 38);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("MAIN FORM");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(500, 74, 183, 38);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblClose = new JLabel("X");
		lblClose.setForeground(Color.WHITE);
		lblClose.setFont(new Font("Century Gothic", Font.PLAIN, 34));
		lblClose.setBounds(1148, 16, 32, 49);
		panel.add(lblClose);
		
		customerTable = new JTable();
		customerTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CUSTID", "CUSTNAME", "CUSTPHONE"
			}
		));
		this.customerModel = (DefaultTableModel) this.customerTable.getModel();
		JScrollPane scrollPane = new JScrollPane(customerTable);
		scrollPane.setBounds(36, 199, 459, 215);
		contentPane.add(scrollPane);
			
		JLabel lblNewLabel_1_1_2 = new JLabel("CUSTOMERS LIST");
		lblNewLabel_1_1_2.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_2.setFont(new Font("Century Gothic", Font.BOLD, 26));
		lblNewLabel_1_1_2.setBounds(140, 151, 207, 38);
		contentPane.add(lblNewLabel_1_1_2);
		
		productTable = new JTable();
		productTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PRODID", "PRODNAME", "PRODQTY", "PRODDESC", "PRODCAT"
			}
		));
		this.productModel = (DefaultTableModel) this.productTable.getModel();
		JScrollPane scrollPane_1 = new JScrollPane(productTable);
		scrollPane_1.setBounds(614, 199, 559, 215);
		contentPane.add(scrollPane_1);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("PRODUCTS LIST");
		lblNewLabel_1_1_2_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_2_1.setFont(new Font("Century Gothic", Font.BOLD, 26));
		lblNewLabel_1_1_2_1.setBounds(800, 151, 207, 38);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("ORDER ID");
		lblNewLabel_1_1_2_2.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_2_2.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_1_1_2_2.setBounds(64, 424, 103, 38);
		contentPane.add(lblNewLabel_1_1_2_2);
		
		txtOrderId = new JTextField();
		txtOrderId.setForeground(new Color(255, 51, 102));
		txtOrderId.setFont(new Font("Century Gothic", Font.BOLD, 16));
		txtOrderId.setBounds(251, 429, 207, 38);
		contentPane.add(txtOrderId);
		txtOrderId.setColumns(10);
		
		JLabel lblNewLabel_1_1_2_2_1 = new JLabel("PRICE");
		lblNewLabel_1_1_2_2_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_2_2_1.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblNewLabel_1_1_2_2_1.setBounds(642, 425, 79, 38);
		contentPane.add(lblNewLabel_1_1_2_2_1);
		
		txtOrderPrice = new JTextField();
		txtOrderPrice.setForeground(new Color(255, 51, 102));
		txtOrderPrice.setFont(new Font("Century Gothic", Font.BOLD, 16));
		txtOrderPrice.setColumns(10);
		txtOrderPrice.setBounds(715, 429, 164, 38);
		contentPane.add(txtOrderPrice);
		
		JLabel lblNewLabel_1_1_2_2_2 = new JLabel("QUANTITY");
		lblNewLabel_1_1_2_2_2.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_2_2_2.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblNewLabel_1_1_2_2_2.setBounds(897, 425, 79, 38);
		contentPane.add(lblNewLabel_1_1_2_2_2);
		
		txtOrderQuantity = new JTextField();
		txtOrderQuantity.setForeground(new Color(255, 51, 102));
		txtOrderQuantity.setFont(new Font("Century Gothic", Font.BOLD, 16));
		txtOrderQuantity.setColumns(10);
		txtOrderQuantity.setBounds(989, 429, 164, 38);
		contentPane.add(txtOrderQuantity);
		
		JLabel lblNewLabel_1_1_2_2_3 = new JLabel("CUSTOMER NAME");
		lblNewLabel_1_1_2_2_3.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_2_2_3.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_1_1_2_2_3.setBounds(64, 480, 177, 38);
		contentPane.add(lblNewLabel_1_1_2_2_3);
		
		JLabel lblName = new JLabel("Minh An");
		lblName.setForeground(new Color(255, 51, 102));
		lblName.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblName.setBounds(251, 482, 177, 38);
		contentPane.add(lblName);
		
		JLabel lblNewLabel_1_1_2_2_3_2 = new JLabel("DATE");
		lblNewLabel_1_1_2_2_3_2.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_2_2_3_2.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_1_1_2_2_3_2.setBounds(64, 528, 177, 38);
		contentPane.add(lblNewLabel_1_1_2_2_3_2);
		
		JLabel lblDate = new JLabel("3/7/2022");
		lblDate.setForeground(new Color(255, 51, 102));
		lblDate.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblDate.setBounds(251, 530, 177, 38);
		contentPane.add(lblDate);
		
		JButton btnSaveOrders = new JButton("Save Orders");
		btnSaveOrders.addActionListener(orderAction);
		btnSaveOrders.setForeground(Color.WHITE);
		btnSaveOrders.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnSaveOrders.setBackground(new Color(255, 51, 102));
		btnSaveOrders.setBounds(92, 595, 149, 39);
		contentPane.add(btnSaveOrders);
		
		JButton btnViewOrders = new JButton("View Orders");
		btnViewOrders.addActionListener(orderAction);
		btnViewOrders.setForeground(Color.WHITE);
		btnViewOrders.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnViewOrders.setBackground(new Color(255, 51, 102));
		btnViewOrders.setBounds(279, 595, 149, 39);
		contentPane.add(btnViewOrders);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(orderAction);
		btnHome.setForeground(Color.WHITE);
		btnHome.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnHome.setBackground(new Color(255, 51, 102));
		btnHome.setBounds(182, 666, 149, 39);
		contentPane.add(btnHome);
		
		orderTable = new JTable();
		orderTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Num", "Product", "Quantity", "UPrice", "Total"
			}
		));
		this.orderModel = (DefaultTableModel) this.orderTable.getModel();
		JScrollPane scrollPane_1_1 = new JScrollPane(orderTable);
		scrollPane_1_1.setBounds(614, 547, 559, 184);
		contentPane.add(scrollPane_1_1);
		
		JLabel lblNewLabel_1_1_2_2_1_1 = new JLabel("TOTAL :");
		lblNewLabel_1_1_2_2_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_2_2_1_1.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblNewLabel_1_1_2_2_1_1.setBounds(800, 741, 79, 38);
		contentPane.add(lblNewLabel_1_1_2_2_1_1);
		
		JLabel lblTotal = new JLabel("10 000");
		lblTotal.setForeground(new Color(255, 51, 102));
		lblTotal.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblTotal.setBounds(897, 741, 79, 38);
		contentPane.add(lblTotal);
		
		JButton btnAddToOrders = new JButton("Add To Orders");
		btnAddToOrders.addActionListener(orderAction);
		btnAddToOrders.setForeground(Color.WHITE);
		btnAddToOrders.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnAddToOrders.setBackground(new Color(255, 51, 102));
		btnAddToOrders.setBounds(843, 477, 164, 39);
		contentPane.add(btnAddToOrders);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 51, 102));
		panel_1.setBounds(0, 789, 1173, 21);
		contentPane.add(panel_1);
	}

	public JTable getTable() {
		return customerTable;
	}

	public void setTable(JTable table) {
		this.customerTable = table;
	}

	public JTable getTable_1() {
		return productTable;
	}

	public void setTable_1(JTable table_1) {
		this.productTable = table_1;
	}

	public JTextField getTxtOrderId() {
		return txtOrderId;
	}

	public void setTxtOrderId(JTextField txtOrderId) {
		this.txtOrderId = txtOrderId;
	}

	public JTextField getTxtOrderPrice() {
		return txtOrderPrice;
	}

	public void setTxtOrderPrice(JTextField txtOrderPrice) {
		this.txtOrderPrice = txtOrderPrice;
	}

	public JTextField getTxtOrderQuantity() {
		return txtOrderQuantity;
	}

	public void setTxtOrderQuantity(JTextField txtOrderQuantity) {
		this.txtOrderQuantity = txtOrderQuantity;
	}

	public JTable getTable_2() {
		return orderTable;
	}

	public void setTable_2(JTable table_2) {
		this.orderTable = table_2;
	}

	public List<OrderDTO> getOrdersDTOList() {
		return ordersDTOList;
	}

	public void setOrdersDTOList(List<OrderDTO> ordersDTOList) {
		this.ordersDTOList = ordersDTOList;
	}

	public List<ProductDTO> getProductsDTOList() {
		return productsDTOList;
	}

	public void setProductsDTOList(List<ProductDTO> productsDTOList) {
		this.productsDTOList = productsDTOList;
	}

	public List<CustomerDTO> getCustomersDTOList() {
		return customersDTOList;
	}

	public void setCustomersDTOList(List<CustomerDTO> customersDTOList) {
		this.customersDTOList = customersDTOList;
	}

	public List<CustomerOrderDTO> getCustomersOrdersList() {
		return customersOrdersList;
	}

	public void setCustomersOrdersList(List<CustomerOrderDTO> customersOrdersList) {
		this.customersOrdersList = customersOrdersList;
	}
	
	
}
