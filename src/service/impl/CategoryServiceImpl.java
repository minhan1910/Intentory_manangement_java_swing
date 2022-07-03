package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.dto.CategoryDTO;
import repository.CategoryRepository;
import repository.entity.CategoryEntity;
import repository.impl.CategoryRepositoryImpl;
import service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	private List<CategoryEntity> categoryEntityList = new ArrayList<CategoryEntity>();
	private List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
	private final  CategoryRepository<CategoryEntity> categoryRepository = new CategoryRepositoryImpl();
	
	@Override
	public List<CategoryDTO> insert(final CategoryDTO categoryDTO, final String fileName) {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setId(categoryDTO.getId());
		categoryEntity.setCategoryId(categoryDTO.getCategoryId());
		categoryEntity.setName(categoryDTO.getName());
	
		categoryEntityList.add(categoryEntity);
		
		// Write and Read
		categoryRepository.writeToFile(categoryEntityList, fileName);
		categoryEntityList = categoryRepository.readDataFromFile(fileName);
		
		// Convert into category list DTO to render view
		categoryDTOList = categoryEntityList.stream()
			.map(catEntity -> new CategoryDTO(catEntity.getId(), catEntity.getCategoryId(), catEntity.getName()))
			.collect(Collectors.toList());
		
		return categoryDTOList;
	}

	@Override
	public List<CategoryDTO> findAll(final String fileName) {
		categoryEntityList = categoryRepository.readDataFromFile(fileName);
		categoryDTOList = categoryEntityList.stream()
				.map(catEntity -> new CategoryDTO(catEntity.getId(), catEntity.getCategoryId(), catEntity.getName()))
				.collect(Collectors.toList());
		return categoryDTOList;
	}
	
	// temporary list for finding by id
	@Override
	public List<CategoryDTO> findById(Long id, String fileName) {
		List<CategoryDTO> categoriesInList = this.findAll(fileName);
		List<CategoryDTO> categoryMatched = categoriesInList.stream()
				.filter(i -> i.getId().equals(id))
				.collect(Collectors.toList());
		return categoryMatched;
	}

	@Override
	public List<CategoryDTO> updateById(CategoryDTO categoryDTO, String fileName) {
		
		/**
		 * 1. Find by all
		 * 2. Find by id
		 * 3. Update object 
		 * 4. update object in current list table
		 * 5. Remove File and Save into file
		 */
		// 1. Find All
		categoryDTOList = this.findAll(fileName);
		
		// 2. Find by id
		List<CategoryDTO> resultFindById = this.findById(categoryDTO.getId(), fileName);
		
		// 3. Update Object
		CategoryDTO categoryIsSelected = resultFindById.get(0);
		if(!categoryIsSelected.getCategoryId().equalsIgnoreCase(categoryDTO.getCategoryId()))
			categoryIsSelected.setCategoryId(categoryDTO.getCategoryId());
		if(!categoryIsSelected.getName().equalsIgnoreCase(categoryDTO.getName()))
			categoryIsSelected.setName(categoryDTO.getName());
		
		// 4. Update current List
		categoryDTOList = categoryDTOList.stream()
			.map(item -> {
				if(item.getId().equals(categoryIsSelected.getId())) {
					item.setCategoryId(categoryIsSelected.getCategoryId());
					item.setId(categoryIsSelected.getId());
					item.setName(categoryIsSelected.getName());
				}
				return item;
			})
			.collect(Collectors.toList());
		
		// 5. save into file
		boolean isSuccess = categoryRepository.removeFile(fileName);
		if(isSuccess) {
			List<CategoryEntity> newCategoryEntityList = convertCategoryDTOListIntoCategoryEntityList(categoryDTOList);
			categoryRepository.writeToFile(newCategoryEntityList, fileName);
		}
		else 
			return null; // need handling this null
		return categoryDTOList;
	}
	
	public List<CategoryEntity> convertCategoryDTOListIntoCategoryEntityList(List<CategoryDTO> list) {
		List<CategoryEntity> categoryEntityList = new ArrayList<CategoryEntity>();
		
		categoryEntityList = list.stream()
			.map(item -> {
				CategoryEntity categoryEntity = new CategoryEntity();
				categoryEntity.setCategoryId(item.getCategoryId());
				categoryEntity.setId(item.getId());
				categoryEntity.setName(item.getName());
				return categoryEntity;
			})
			.collect(Collectors.toList());
		
		return categoryEntityList;
	}

	@Override
	public List<CategoryDTO> deleteById(CategoryDTO categoryDTO, String fileName) {
		/**
		 * 1. Find All
		 * 2. Find By Id
		 * 3. Remove current object in list of finding all
		 * 4. Remove and save into file
		 */
		// 1. Find All
		categoryDTOList = this.findAll(fileName);
		
		// 2. Find by id
		List<CategoryDTO> resultFindById = this.findById(categoryDTO.getId(), fileName);
		
		// 3. Remove current object in list of finding all
		CategoryDTO categoryIsSelected = resultFindById.get(0);
		
		categoryDTOList = categoryDTOList.stream()
			.filter(item -> !item.getId().equals(categoryIsSelected.getId()))
			.collect(Collectors.toList());
			
		// 4. save into file
		boolean isSuccess = categoryRepository.removeFile(fileName);
		if(isSuccess) {
			List<CategoryEntity> newCategoryEntityList = convertCategoryDTOListIntoCategoryEntityList(categoryDTOList);
			categoryRepository.writeToFile(newCategoryEntityList, fileName);
		}
		else 
			return null; // need handling this null
		return categoryDTOList;
	}

	@Override
	public List<CategoryDTO> findByName(final String name, String fileName) {
		categoryDTOList = this.findAll(fileName);
		List<CategoryDTO> categoryMatched = categoryDTOList.stream()
				.filter(item -> item.getName().equals(name))
				.collect(Collectors.toList());
		return categoryMatched;
	}
}
