package service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.dto.UserDTO;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.LoginService;
import service.UserService;

public class LoginServiceImpl implements LoginService {

	private final static UserService userService = new UserServiceImpl();
	private List<Map<String, String>> usersList = new ArrayList<>(); 
	
	
	@Override
	public List<Map<String, String>> findAll(String fileName) {
		List<UserDTO> usersDTOList = userService.findAll(fileName);
	    this.usersList = Arrays.asList(usersDTOList.stream()
				.collect(Collectors.toMap(UserDTO::getUsername, UserDTO::getPassword)));
		return this.usersList;
	}
	
}
