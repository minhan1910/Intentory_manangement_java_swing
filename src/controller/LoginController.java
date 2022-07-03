package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import constant.LoginConstant;
import constant.SystemConstant;
import service.LoginService;
import service.impl.LoginServiceImpl;
import view.HomeForm;
import view.LoginView;

public class LoginController implements ActionListener {

	private final static LoginService loginService = new LoginServiceImpl();
	private List<Map<String, String>> usersList = new ArrayList<>();
	private LoginView loginView = null;
	
	public LoginController(LoginView loginView) {
		this.loginView = loginView;
		this.usersList = loginService.findAll(SystemConstant.USER_FILE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String loginAction = e.getActionCommand();
		
		switch (loginAction) {
			case LoginConstant.LOGIN: {
				String userId = this.loginView.getTxtUserId().getText();
				String password = this.loginView.getTxtPassword().getText();
				boolean isLogin = false;
				
				isLogin = this.usersList.stream()
					.anyMatch(item -> item.containsKey(userId) && item.containsValue(password));
				System.out.println(isLogin);
				
				if(isLogin) {
					JOptionPane.showMessageDialog(loginView, "Login Successfully!");
					new HomeForm().setVisible(true);
					this.loginView.dispose();
				} else {
					JOptionPane.showMessageDialog(loginView, "UserId or Password incorrect!");
				}
				
				break;
			}
			
			case LoginConstant.CLEAR: {
				this.loginView.resetAllField();
				JOptionPane.showMessageDialog(loginView, "Clear Successfully!");
				break;
			}

		default:
			JOptionPane.showMessageDialog(loginView, "This feature is coming soon");
			break;
		}
	}
	
}
