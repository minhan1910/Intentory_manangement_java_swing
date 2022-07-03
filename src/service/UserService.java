package service;

import java.util.List;

import model.dto.UserDTO;

public interface UserService {
	List<UserDTO> insert(UserDTO userDTO, final String fileName);

	List<UserDTO> findAll(final String fileName);

	List<UserDTO> findById(final Long id, final String fileName);

	List<UserDTO> findByUsername(final String name, final String fileName);

	List<UserDTO> updateById(UserDTO userDTO, final String fileName);

	List<UserDTO> deleteById(UserDTO userDTO, final String fileName);
}
