package service;

import java.util.List;

import model.dto.CategoryDTO;
import model.dto.ProductDTO;

public interface ProductService {
	List<ProductDTO> insert(ProductDTO productDTO,final String fileName);
	
	List<ProductDTO> findAll(final String fileName);
	
	List<ProductDTO> findById(final Long id, final String fileName);
	
	List<ProductDTO> updateById(ProductDTO productDTO, final String fileName);

	List<ProductDTO> deleteById(ProductDTO productDTO, final String fileName);
	
	List<CategoryDTO> getAllCategory(final String fileName);
}
