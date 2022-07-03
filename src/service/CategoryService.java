package service;

import java.util.List;

import model.dto.CategoryDTO;

public interface CategoryService {
	List<CategoryDTO> insert(CategoryDTO categoryDTO,final String fileName);
	
	List<CategoryDTO> findAll(final String fileName);
	
	List<CategoryDTO> findById(final Long id, final String fileName);
	
	List<CategoryDTO> findByName(final String name, final String fileName);
	
	List<CategoryDTO> updateById(CategoryDTO categoryDTO, final String fileName);

	List<CategoryDTO> deleteById(CategoryDTO categoryDTO, final String fileName);

	
}
