package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import constant.CategoryConstant;
import constant.SystemConstant;
import model.dto.CategoryDTO;
import service.CategoryService;
import service.impl.CategoryServiceImpl;
import view.CategoryView;


public class CategoryController implements ActionListener {
	private final static CategoryService categoryService = new CategoryServiceImpl();
	public List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
	private CategoryView categoryView = null;

	public CategoryController(CategoryView categoryView) {
		this.categoryView =  categoryView;
		// When you first entered CategoryView, you will get all categories list and render it into table
		categoryDTOList = this.findAll();
		if(categoryDTOList.size() > 0 || categoryDTOList != null) 			
			this.categoryView.setCategoriesList(categoryDTOList);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String categoryAction = e.getActionCommand();
		
		switch (categoryAction) {
			case CategoryConstant.ADD: {
				String categoryId = this.categoryView.getTxtCategoryId().getText();
				String categoryName = this.categoryView.getTxtCategoryName().getText();
				
				CategoryDTO categoryDTO = new CategoryDTO();
				
				Integer categoryDTOListSize = categoryDTOList.size();
				if(categoryDTOListSize > 0) 
					categoryDTO.setId(categoryDTOList.get(categoryDTOListSize - 1).getId() + 1);
				categoryDTO.setCategoryId(categoryId);
				categoryDTO.setName(categoryName);
				
				categoryDTOList = categoryService.insert(categoryDTO, SystemConstant.CATEGORY_FILE);
				JOptionPane.showMessageDialog(categoryView, "Add Category Successfully !");
				
				// Tranfer into view
				this.categoryView.setCategoriesList(categoryDTOList); // update categoriesList in view
				this.categoryView.showData(categoryDTOList, this.categoryView.getModel());
				break;
			}
			
			case CategoryConstant.EDIT: {
				String categoryId = this.categoryView.getTxtCategoryId().getText();
				String categoryName = this.categoryView.getTxtCategoryName().getText();
				
				if(categoryId.isBlank() || categoryName.isBlank()) {
					JOptionPane.showMessageDialog(categoryView, "Please select one of rows in table for editting");
				} else {
					// save information into file
					CategoryDTO categoryDTO = new CategoryDTO();
					categoryDTO.setCategoryId(categoryId);
					categoryDTO.setName(categoryName);
					categoryDTO.setId(this.categoryView.currentNumber);
					List<CategoryDTO> newCategoryDTOList = categoryService.updateById(categoryDTO, SystemConstant.CATEGORY_FILE);
					this.categoryView.setCategoriesList(newCategoryDTOList);
					this.categoryView.showData(newCategoryDTOList, this.categoryView.getModel());
				}
				break;
			}
			
			case CategoryConstant.DELETE: {
				String categoryId = this.categoryView.getTxtCategoryId().getText();
				String categoryName = this.categoryView.getTxtCategoryName().getText();
				
				if(categoryId.isBlank() || categoryName.isBlank()) {
					JOptionPane.showMessageDialog(categoryView, "Please select one of rows in table for editting");
				} else {
					// save information into file
					CategoryDTO categoryDTO = new CategoryDTO();
					categoryDTO.setCategoryId(categoryId);
					categoryDTO.setName(categoryName);
					categoryDTO.setId(this.categoryView.currentNumber);
					List<CategoryDTO> newCategoryDTOList = categoryService.deleteById(categoryDTO, SystemConstant.CATEGORY_FILE);

					this.categoryView.setCategoriesList(newCategoryDTOList);
					this.categoryView.showData(newCategoryDTOList, this.categoryView.getModel());
				}
				break;
			}
			
			case CategoryConstant.REFRESH: {
				this.categoryView.resetAllTextField();
				categoryDTOList = categoryService.findAll(SystemConstant.CATEGORY_FILE);
				this.categoryView.setCategoriesList(categoryDTOList);
				this.categoryView.showData(categoryDTOList, this.categoryView.getModel());
				break;
			}
			
			default: 
				JOptionPane.showMessageDialog(categoryView, "The feature is already updating");
		}
	}
	
	private List<CategoryDTO> findAll() {
		return categoryService.findAll(SystemConstant.CATEGORY_FILE);
	}
	
	private List<CategoryDTO> findById(final Long id) {
		List<CategoryDTO> findByCategoryIdList = categoryService.findById(id, SystemConstant.CATEGORY_FILE);
		 return findByCategoryIdList;
	}
	
	
	/**
	 * Testing successfully !
	 */
	private void testFindById() {
		System.out.println(this.categoryView.currentNumber);
		 List<CategoryDTO> findByCategoryIdList = categoryService.findById(this.categoryView.currentNumber, SystemConstant.CATEGORY_FILE);
		 if(findByCategoryIdList.size() > 0 && findByCategoryIdList != null) {		
			 this.categoryView.setCategoriesList(findByCategoryIdList);
			 this.categoryView.showData(findByCategoryIdList, this.categoryView.getModel());
		 }
	}
	
	/**
	 * Testing successfully !
	 */
	private void canGetCategoriesList() {
		categoryService.findAll(SystemConstant.CATEGORY_FILE).forEach(i -> System.out.println(i.toString()));
	}
}
