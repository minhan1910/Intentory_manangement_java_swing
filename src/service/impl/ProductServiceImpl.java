package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.transform.stream.StreamSource;

import model.dto.CategoryDTO;
import model.dto.ProductDTO;
import repository.ProductRepository;
import repository.entity.CategoryEntity;
import repository.entity.ProductEntity;
import repository.impl.ProductRepositoryImpl;
import service.CategoryService;
import service.ProductService;

public class ProductServiceImpl implements ProductService {

	// ở đây handle chuyển từ Product mà trên view đem xuống là DTO hay input rùi
	// chuyển nó sang
	// List các ProudctEntity mới ghi xuống file được vì này là lưu object
	// sau khi lưu xong thì read lên lấy được List<ProductEntity> chuyển thành
	// Output hay DTO gì đó
	private List<ProductEntity> productsEntityList = new ArrayList<ProductEntity>();
	private List<ProductDTO> productsDTOList = new ArrayList<ProductDTO>();
	private final static CategoryService categoryService = new CategoryServiceImpl();
	private final static ProductRepository<ProductEntity> productRepository = new ProductRepositoryImpl();

	@Override
	public List<ProductDTO> insert(ProductDTO productDTO, final String fileName) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(productDTO.getId());
		productEntity.setName(productDTO.getName());
		productEntity.setProductId(productDTO.getProductId());
		productEntity.setQuantity(productDTO.getQuantity());
		productEntity.setDescription(productDTO.getDescription());
		productEntity.setCategoryName(productDTO.getCategoryName());

		// Add into products entity List
		productsEntityList.add(productEntity);

		productRepository.writeToFile(productsEntityList, fileName);
		productsEntityList = productRepository.readDataFromFile(fileName);

		return this.convertProductsEntityListIntoProductsDTOList(productsEntityList);
	}
	

	@Override
	public List<ProductDTO> insertList(List<ProductDTO> list, String fileName) {
		this.productsEntityList = convertProductsDTOListIntoProductsEntityList(list);
		productRepository.writeToFile(this.productsEntityList, fileName);
		this.productsEntityList = productRepository.readDataFromFile(fileName);
		return this.convertProductsEntityListIntoProductsDTOList(this.productsEntityList);
	}

	@Override
	public List<CategoryDTO> getAllCategory(final String fileName) {
		return categoryService.findAll(fileName);
	}

	@Override
	public List<ProductDTO> findAll(final String fileName) {
		productsEntityList = productRepository.readDataFromFile(fileName);
		productsDTOList = productsEntityList.stream().map(item -> this.convertProuductEntityIntoProductDTO(item))
				.collect(Collectors.toList());
		return productsDTOList;
	}

	// Temporary List for rendering or other purposes
	@Override
	public List<ProductDTO> findById(Long id, final String fileName) {
		List<ProductDTO> productsDTOListTemp = this.findAll(fileName);
		productsDTOListTemp = productsDTOListTemp.stream().filter(item -> item.getId().equals(id))
				.collect(Collectors.toList());
		return productsDTOListTemp;
	}

	@Override
	public List<ProductDTO> updateById(ProductDTO productDTO, final String fileName) {
		/**
		 * 1. Find All 2. Find By Id 3. Remove current object in list of finding all 4.
		 * Remove and save into file
		 */

		// 1. Find All
		productsDTOList = this.findAll(fileName);

		// 2. Find by id
		List<ProductDTO> resultFindById = this.findById(productDTO.getId(), fileName);

		// 3. Update Object
		ProductDTO productsIsSelected = resultFindById.get(0);
		if (!isProductFieldIdentical(productsIsSelected.getProductId(), productDTO.getProductId()))
			productsIsSelected.setProductId(productDTO.getProductId());
		if (!isProductFieldIdentical(productsIsSelected.getName(), productDTO.getName()))
			productsIsSelected.setName(productDTO.getName());
		if (!isProductFieldIdentical(productsIsSelected.getQuantity().toString(), productDTO.getQuantity().toString()))
			productsIsSelected.setQuantity(productDTO.getQuantity());
		if (!isProductFieldIdentical(productsIsSelected.getDescription(), productDTO.getDescription()))
			productsIsSelected.setName(productDTO.getDescription());
		if (!isProductFieldIdentical(productsIsSelected.getCategoryName(), productDTO.getCategoryName()))
			productsIsSelected.setName(productDTO.getCategoryName());

		// 4. Update current List
		int productsDTOListSize = productsDTOList.size();
		for (int i = 0; i < productsDTOListSize; ++i) {
			if (productsDTOList.get(i).getId().equals(productsIsSelected.getId())) {
				productsDTOList.set(i, productsIsSelected);
			}
		}

		// 5. save into file
		boolean isSuccess = productRepository.removeFile(fileName);
		if (isSuccess) {
			List<ProductEntity> newCategoryEntityList = convertProductsDTOListIntoProductsEntityList(productsDTOList);
			productRepository.writeToFile(newCategoryEntityList, fileName);
		} else
			return null; // need handling this null
		return productsDTOList;
	}

	@Override
	public List<ProductDTO> deleteById(ProductDTO productDTO, final String fileName) {
		/**
		 * 1. Find All 2. Find By Id 3. Remove current object in list of finding all 4.
		 * Remove and save into file
		 */
		// 1. Find All
		productsDTOList = this.findAll(fileName);

		// 2. Find by id
		List<ProductDTO> resultFindById = this.findById(productDTO.getId(), fileName);
		
		// 3. Find Object 
		ProductDTO productsIsSelected = resultFindById.get(0);

		// 4. Delete current List
		productsDTOList = productsDTOList.stream()
				.filter(item -> !item.getId().equals(productsIsSelected.getId()))
				.collect(Collectors.toList());

		// 5. save into file
		boolean isSuccess = productRepository.removeFile(fileName);
		if (isSuccess) {
			List<ProductEntity> newProductEntityList = convertProductsDTOListIntoProductsEntityList(productsDTOList);
			productRepository.writeToFile(newProductEntityList, fileName);
		} else
			return null; // need handling this null
		return productsDTOList;
	}

	private List<ProductDTO> convertProductsEntityListIntoProductsDTOList(final List<ProductEntity> list) {
		productsDTOList = list.stream().map(productEntity -> {
			ProductDTO productDTO = convertProuductEntityIntoProductDTO(productEntity);
			return productDTO;
		}).collect(Collectors.toList());

		return productsDTOList;
	}

	private ProductDTO convertProuductEntityIntoProductDTO(final ProductEntity productEntity) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(productEntity.getId());
		productDTO.setName(productEntity.getName());
		productDTO.setProductId(productEntity.getProductId());
		productDTO.setQuantity(productEntity.getQuantity());
		productDTO.setDescription(productEntity.getDescription());
		productDTO.setCategoryName(productEntity.getCategoryName());
		return productDTO;
	}

	private List<ProductEntity> convertProductsDTOListIntoProductsEntityList(final List<ProductDTO> list) {
		return list.stream().map(item -> {
			ProductEntity productEntity = new ProductEntity();
			productEntity.setId(item.getId());
			productEntity.setName(item.getName());
			productEntity.setProductId(item.getProductId());
			productEntity.setQuantity(item.getQuantity());
			productEntity.setDescription(item.getDescription());
			productEntity.setCategoryName(item.getCategoryName());
			return productEntity;
		}).collect(Collectors.toList());
	}

	private boolean isProductFieldIdentical(final String primaryField, final String secondField) {
		return primaryField.equalsIgnoreCase(secondField);
	}


}
