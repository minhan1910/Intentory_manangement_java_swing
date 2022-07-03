package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import constant.HomeConstant;
import view.HomeForm;
import view.LoginView;

public class HomeController implements ActionListener, MouseListener  {
	
	private HomeForm homeView = null;
	
	public HomeController(HomeForm homeView) {
		this.homeView = homeView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String homeAction = e.getActionCommand();
		
		switch (homeAction) {
		case HomeConstant.LOGOUT:
			this.homeView.dispose();
			JOptionPane.showMessageDialog(homeView, "Logout Successfully!");
			new LoginView().setVisible(true);;
			break;

		default:
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int clicked = e.getClickCount();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
