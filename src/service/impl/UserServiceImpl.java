package service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.dto.UserDTO;
import repository.UserRepository;
import repository.entity.UserEntity;
import repository.impl.UserRepositoryImpl;
import service.UserService;

public class UserServiceImpl implements UserService {
	private List<UserEntity> usersEntityList = new ArrayList<UserEntity>();
	private List<UserDTO> usersDTOList = new ArrayList<UserDTO>();
	private final static UserRepository<UserEntity> userRepository = new UserRepositoryImpl();
	
	@Override
	public List<UserDTO> insert(UserDTO userDTO, final String fileName) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userDTO.getId());
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setPhone(userDTO.getPhone());
		
		this.usersEntityList.add(userEntity);
		
		userRepository.writeToFile(this.usersEntityList, fileName);
	
		this.usersDTOList = this.usersEntityList.stream()
				.map(item -> new UserDTO(item.getId(), item.getUsername(), item.getPassword(), item.getPhone()))
				.collect(Collectors.toList());
		return this.usersDTOList;
	}

	@Override
	public List<UserDTO> findAll(final String fileName) {
		this.usersEntityList = userRepository.readDataFromFile(fileName);
		this.usersDTOList = this.usersEntityList.stream()
				.map(item -> new UserDTO(item.getId(), item.getUsername(), item.getPassword(), item.getPhone()))
				.collect(Collectors.toList());
		return this.usersDTOList;
	}

	@Override
	public List<UserDTO> findById(final Long id, final String fileName) {
		List<UserDTO> usersDTOListTemp = this.findAll(fileName);
		usersDTOListTemp = usersDTOListTemp.parallelStream()
				.filter(item -> item.getId().equals(id))
				.collect(Collectors.toList());
		return usersDTOListTemp;
	}
	
	public List<UserDTO> findByIdWithoutFile(List<UserDTO> list, Long id) {
		List<UserDTO> usersDTOListTemp = new ArrayList<UserDTO>(list);
		usersDTOListTemp = list.parallelStream()
				.filter(item -> item.getId().equals(id))
				.collect(Collectors.toList());
		return usersDTOListTemp;
	}

	@Override
	public List<UserDTO> findByUsername(final String username, final String fileName) {
		List<UserDTO> usersDTOListTemp = this.findAll(fileName);
		usersDTOListTemp = usersDTOListTemp.parallelStream()
				.filter(item -> item.getUsername().equals(username))
				.collect(Collectors.toList());
		return usersDTOListTemp;
	}

	@Override
	public List<UserDTO> updateById(UserDTO userDTO, final String fileName) {
		/*
		 * 1. Find All
		 * 2. Find By idfindByIdWithoutFile
		 * 3. Get Object and Update Object
		 * 4. Update current object in list
		 * 5. Delete old file and write a new file with new list
		 */
		
		// 1. Find All
		this.usersDTOList = this.findAll(fileName);
		
		// 2. Find by id
		List<UserDTO> resultFindById = this.findByIdWithoutFile(this.usersDTOList, userDTO.getId());
		
		// 3. Get Object and Update Object
		UserDTO userIsSelected = resultFindById.get(0);
		
		if(!isUserFieldIdentical(userIsSelected.getUsername(), userDTO.getUsername()))
			userIsSelected.setUsername(userDTO.getUsername());
		if(!isUserFieldIdentical(userIsSelected.getPassword(), userDTO.getPassword()))
			userIsSelected.setPassword(userDTO.getPassword());
		if(!isUserFieldIdentical(userIsSelected.getPhone(), userDTO.getPhone()))
			userIsSelected.setPhone(userDTO.getPhone());
		
		// 4. Update current object in list
		this.usersDTOList = this.usersDTOList.stream()
				.map(item -> {
					if(item.getId().equals(userIsSelected.getId()))  {
						item.setUsername(userIsSelected.getUsername());
						item.setPassword(userIsSelected.getPassword());
						item.setPhone(userIsSelected.getPhone());
					}
					return item;
				})
				.collect(Collectors.toList());
		
		// 5. Delete old file and write a new file with new list
		boolean isSuccess = userRepository.removeFile(fileName);
		if(isSuccess) {
			List<UserEntity> usersEntityListTemp = this.convertUsersDTOListIntoUsersEntityList(this.usersDTOList);
			userRepository.writeToFile(usersEntityListTemp, fileName);
		} else
			return null;
		
		return this.usersDTOList;
	}

	@Override
	public List<UserDTO> deleteById(UserDTO userDTO, final String fileName) {
		/*
		 * 1. Find All
		 * 2. Find By idfindByIdWithoutFile
		 * 3. Get Object and Update Object
		 * 4. Update current object in list
		 * 5. Delete old file and write a new file with new list
		 */
		
		// 1. Find All
		this.usersDTOList = this.findAll(fileName);
		
		// 2. Find by id
		List<UserDTO> resultFindById = this.findByIdWithoutFile(this.usersDTOList, userDTO.getId());
		
		// 3. Get Object and Update Object
		UserDTO userIsSelected = resultFindById.get(0);
		
		// 4. Update current object in list
		this.usersDTOList = this.usersDTOList.stream()
				.filter(item -> !item.getId().equals(userIsSelected.getId()))
				.collect(Collectors.toList());
		
		// 5. Delete old file and write a new file with new list
		boolean isSuccess = userRepository.removeFile(fileName);
		if(isSuccess) {
			List<UserEntity> usersEntityListTemp = this.convertUsersDTOListIntoUsersEntityList(this.usersDTOList);
			userRepository.writeToFile(usersEntityListTemp, fileName);
		} else
			return null;
		
		return this.usersDTOList;
	}
	
	private boolean isUserFieldIdentical(final String fieldOne, final String fieldTwo) {
		return fieldOne.equals(fieldTwo);
	}
	
	private List<UserEntity> convertUsersDTOListIntoUsersEntityList(final List<UserDTO> list) {
		return list.stream()
				.map(item -> {
					UserEntity userEntity = new UserEntity();
					userEntity.setId(item.getId());
					userEntity.setUsername(item.getUsername());
					userEntity.setPassword(item.getPassword());
					userEntity.setPhone(item.getPhone());
					return userEntity;
				})
				.collect(Collectors.toList());
	}
}
