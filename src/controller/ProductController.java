package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import constant.ProductConstant;
import constant.SystemConstant;
import model.dto.CategoryDTO;
import model.dto.ProductDTO;
import service.ProductService;
import service.impl.ProductServiceImpl;
import view.ProductView;

public class ProductController implements ActionListener {

	private final static ProductService productService = new ProductServiceImpl();
	private List<ProductDTO> productsDTOList = new ArrayList<ProductDTO>();
	private List<String> categoriesNameList = new ArrayList<String>();

	private ProductView productView = null;

	public ProductController(final ProductView productView) {
		this.productView = productView;
		this.showCategoriesList();
		productsDTOList = this.getAllProductsDTOList();
		if (productsDTOList.size() > 0 || productsDTOList != null)
			this.productView.setProductsDTOList(productsDTOList);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String productAction = e.getActionCommand();
		switch (productAction) {
		case ProductConstant.ADD: {
			ProductDTO productDTO = setProductDTO();

			productsDTOList = productService.insert(productDTO, SystemConstant.PRODUCT_FILE);
			boolean isSuccess = showMessageDialog("The operation is not activity", "Add Product Successfully!");
			if(!isSuccess)
				break;
			
			this.productView.setProductsDTOList(productsDTOList);
			this.productView.showData(productsDTOList, this.productView.getModel());
			break;
		}
		
		case ProductConstant.EDIT: {
			ProductDTO productDTO = setProductDTO();
			
			// For editing feature
			Long productNumber = this.productView.currentNumber;
			productDTO.setId(productNumber);
			
			productsDTOList = productService.updateById(productDTO, SystemConstant.PRODUCT_FILE);
			
			boolean isSuccess = showMessageDialog("The operation is not activity", "Edit Product Successfully!");
			if(!isSuccess)
				break;
			
			this.productView.setProductsDTOList(productsDTOList);
			this.productView.showData(productsDTOList, this.productView.getModel());
			break;
		}
		
		case ProductConstant.DELETE: {
			ProductDTO productDTO = setProductDTO();
			
			Long productNumber = this.productView.currentNumber;
			productDTO.setId(productNumber);
			
			productsDTOList = productService.deleteById(productDTO, SystemConstant.PRODUCT_FILE);
			
			boolean isSuccess = showMessageDialog("The operation is not activity", "Delete Product Successfully!");
			if(!isSuccess)
				break;
			
			this.productView.setProductsDTOList(productsDTOList);
			this.productView.showData(productsDTOList, this.productView.getModel());
			this.productView.resetAllTextField();
			break;
		}

		case ProductConstant.REFRESH: {
			this.productView.resetAllTextField();
			productsDTOList = productService.findAll(SystemConstant.PRODUCT_FILE);
			this.productView.setProductsDTOList(productsDTOList);
			this.productView.showData(productsDTOList, this.productView.getModel());
			this.productView.resetAllTextField();
			break;
		}

		default:
			JOptionPane.showMessageDialog(productView, "The feature is already updating");
			break;
		}
	}

	private ProductDTO setProductDTO() {
		String productId 	= this.productView.getTxtProductId().getText();
		String name 		= this.productView.getTxtName().getText();
		String quantity 	= this.productView.getTxtQuantity().getText();
		String description 	= this.productView.getTxtDescription().getText();
		String categoryName = this.productView.getComboCategory().getSelectedItem().toString();
		
		ProductDTO productDTO = new ProductDTO();
		int productDTOListSize = productsDTOList.size();
		if (productDTOListSize > 0)
			productDTO.setId(productsDTOList.get(productDTOListSize - 1).getId() + 1);
		productDTO.setProductId(productId);
		productDTO.setName(name);
		productDTO.setQuantity(Long.parseLong(quantity));
		productDTO.setDescription(description);
		productDTO.setCategoryName(categoryName);
		
		return productDTO;
	}
	
	private void showCategoriesList() {
		List<CategoryDTO> categoriesDTOList = productService.getAllCategory(SystemConstant.CATEGORY_FILE);
		categoriesDTOList.stream().forEach(item -> categoriesNameList.add(item.getName()));
		this.productView.setCategoriesNameList(categoriesNameList);
	}

	private List<ProductDTO> getAllProductsDTOList() {
		return productService.findAll(SystemConstant.PRODUCT_FILE);
	}
	
	private boolean showMessageDialog(String messageError, String message) {
		if(productsDTOList == null) {
			JOptionPane.showMessageDialog(productView, messageError);
			return false;
		}
		JOptionPane.showMessageDialog(productView, message);
		return true;
	}
}
