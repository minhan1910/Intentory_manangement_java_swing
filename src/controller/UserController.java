package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import constant.SystemConstant;
import constant.UserConstant;
import model.dto.UserDTO;
import service.UserService;
import service.impl.UserServiceImpl;
import view.UserView;

public class UserController implements ActionListener {

	private List<UserDTO> usersDTOList = new ArrayList<>();
	private final static UserService userService = new UserServiceImpl();
	private UserView userView = null;

	public UserController(UserView userView) {
		this.userView = userView;

		this.usersDTOList = this.initUsersData();
		if (this.usersDTOList.size() > 0 || this.usersDTOList != null)
			this.userView.setUsersDTOList(usersDTOList);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String userAction = e.getActionCommand();
		switch (userAction) {
		case UserConstant.ADD: {

			UserDTO userDTO = this.initUserDTO();

			this.usersDTOList = userService.insert(userDTO, SystemConstant.USER_FILE);
			JOptionPane.showMessageDialog(userView, "Add User Successfully!");

			this.userView.setUsersDTOList(usersDTOList);
			this.userView.showData(usersDTOList, this.userView.getModel());
			break;
		}

		case UserConstant.EDIT: {
			UserDTO userDTO = this.initUserDTO();

			// Using for editing
			userDTO.setId(this.userView.getCurrentNumber());

			this.usersDTOList = userService.updateById(userDTO, SystemConstant.USER_FILE);

			this.userView.setUsersDTOList(this.usersDTOList);
			this.userView.showData(this.usersDTOList, this.userView.getModel());
			break;
		}

		case UserConstant.DELETE: {
			UserDTO userDTO = this.initUserDTO();

			// Using for editing
			userDTO.setId(this.userView.getCurrentNumber());

			this.usersDTOList = userService.deleteById(userDTO, SystemConstant.USER_FILE);

			this.userView.setUsersDTOList(this.usersDTOList);
			this.userView.showData(this.usersDTOList, this.userView.getModel());
			break;
		}

		case UserConstant.REFRESH: {
			this.userView.resetAllField();
			this.usersDTOList = userService.findAll(SystemConstant.USER_FILE);
			this.userView.setUsersDTOList(this.usersDTOList);
			this.userView.showData(this.usersDTOList, this.userView.getModel());
			break;
		}

		case UserConstant.HOME: {

			break;
		}

		default: {
			break;
		}
		}
	}

	private UserDTO initUserDTO() {
		String username = this.userView.getTxtUsername().getText();
		String password = this.userView.getTxtPassword().getText();
		String phone = this.userView.getTxtPhone().getText();

		UserDTO userDTO = new UserDTO();
		int usersDTOListSize = this.usersDTOList.size();
		if (usersDTOListSize > 0)
			userDTO.setId(this.usersDTOList.get(usersDTOListSize - 1).getId() + 1);
		userDTO.setUsername(username);
		userDTO.setPassword(password);
		userDTO.setPhone(phone);

		return userDTO;
	}

	private List<UserDTO> initUsersData() {
		return userService.findAll(SystemConstant.USER_FILE);
	}

}
