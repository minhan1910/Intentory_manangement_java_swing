package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.CategoryController;
import model.dto.CategoryDTO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CategoryView extends JFrame implements View<CategoryDTO> {

	private JPanel contentPane;
	private JTextField txtCategoryId;
	private JTextField txtCategoryName;
	private DefaultTableModel model;
	private List<CategoryDTO> categoriesList;
	private JTable table; 
	public Long currentNumber;
	private CategoryView categoryView = this;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryView frame = new CategoryView();
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
	public CategoryView() {
		initComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.resetAllTextField();
		// Selected Row in table		
		this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int firstIndex = e.getFirstIndex();
				if(!e.getValueIsAdjusting()) { //prevent dupliacted event	
					if (firstIndex >= 0 && firstIndex < model.getRowCount()) { // for Refresh
						int i_row = table.getSelectedRow();
						String idSelectedRow	= model.getValueAt(i_row, 0).toString();
						String categoryId 		= model.getValueAt(i_row, 1).toString();
						String categoryName 	= model.getValueAt(i_row, 2).toString();
						currentNumber = Long.parseLong(idSelectedRow);
						txtCategoryId.setText(categoryId);
						txtCategoryName.setText(categoryName);
					} 
				}
			}
		});
		
		this.renderFirstCategory();
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
		lblNewLabel_1.setBounds(326, 23, 515, 38);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("MANAGE CATEGORIES");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(403, 71, 330, 38);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 0=yes, 1=no, 2=cancel
				int isExit = JOptionPane.showConfirmDialog(categoryView, "Are you want to exit ?");
				if(isExit == 0)
					dispose();
			}
		});
		lblClose.setForeground(Color.WHITE);
		lblClose.setFont(new Font("Century Gothic", Font.PLAIN, 34));
		lblClose.setBounds(1086, 16, 32, 49);
		panel.add(lblClose);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("CatId");
		lblNewLabel_1_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1_1.setBounds(29, 200, 106, 31);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtCategoryId = new JTextField();
		txtCategoryId.setColumns(10);
		txtCategoryId.setBounds(179, 200, 254, 31);
		contentPane.add(txtCategoryId);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("CatName");
		lblNewLabel_1_1_1_1.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel_1_1_1_1.setBounds(29, 256, 114, 39);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		txtCategoryName = new JTextField();
		txtCategoryName.setColumns(10);
		txtCategoryName.setBounds(179, 264, 254, 31);
		contentPane.add(txtCategoryName);
		
		// Category Action Listener
		CategoryController categoryAction = new CategoryController(this);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(categoryAction);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnAdd.setBackground(new Color(255, 51, 102));
		btnAdd.setBounds(29, 326, 114, 39);
		contentPane.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(categoryAction);
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnEdit.setBackground(new Color(255, 51, 102));
		btnEdit.setBounds(182, 326, 114, 39);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(categoryAction);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnDelete.setBackground(new Color(255, 51, 102));
		btnDelete.setBounds(319, 326, 114, 39);
		contentPane.add(btnDelete);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Cat Id", "Cat Name"
			}
		));
		this.model = (DefaultTableModel) table.getModel();
		table.setSelectionBackground(new Color(255, 51, 102));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(464, 200, 653, 399);
		contentPane.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 51, 102));
		panel_1.setBounds(0, 667, 1141, 20);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("CATEGORIES LIST");
		lblNewLabel_1_1_2.setForeground(new Color(255, 51, 102));
		lblNewLabel_1_1_2.setFont(new Font("Century Gothic", Font.BOLD, 26));
		lblNewLabel_1_1_2.setBounds(684, 152, 215, 38);
		contentPane.add(lblNewLabel_1_1_2);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(categoryAction);
		btnHome.setForeground(Color.WHITE);
		btnHome.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnHome.setBackground(new Color(255, 51, 102));
		btnHome.setBounds(182, 391, 114, 39);
		contentPane.add(btnHome);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(categoryAction);
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnRefresh.setBackground(new Color(255, 51, 102));
		btnRefresh.setBounds(732, 609, 114, 39);
		contentPane.add(btnRefresh);
	}
	
	private void renderFirstCategory() {
		if(categoriesList.size() > 0 || categoriesList != null)
			this.showData(categoriesList, model);
	}
	
	public void resetAllTextField() {
		this.txtCategoryId.setText("");
		this.txtCategoryName.setText("");
	}
	
	@Override
	public void showData(List<CategoryDTO> list, DefaultTableModel model) {
		model.getDataVector().removeAllElements();
		model.fireTableChanged(null);			
		
		list.stream()
			.forEach(item -> model.addRow(new Object[] {
					item.getId(), item.getCategoryId(), item.getName()
			}));
	}

	public JTextField getTxtCategoryId() {
		return txtCategoryId;
	}

	public void setTxtCategoryId(JTextField txtCategoryId) {
		this.txtCategoryId = txtCategoryId;
	}

	public JTextField getTxtCategoryName() {
		return txtCategoryName;
	}

	public void setTxtCategoryName(JTextField txtCategoryName) {
		this.txtCategoryName = txtCategoryName;
	}

	public List<CategoryDTO> getCategoriesList() {
		return categoriesList;
	}

	public void setCategoriesList(List<CategoryDTO> categoriesList) {
		this.categoriesList = categoriesList;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

}
