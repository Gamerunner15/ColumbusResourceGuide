package project.Kolton.logic;

import java.text.DecimalFormat;

import org.apache.commons.dbcp2.BasicDataSource;

import project.Kolton.objects.Address;
import project.Kolton.objects.User;

import javax.sql.DataSource;

import projects.Kolton.DAO.JDBCUserDAO;
import projects.Kolton.DAO.WebServiceUserDAO;

import projects.Kolton.DAOInterfaces.UserDAO;
import projects.Kolton.view.MainMenu;

public class Controller {
	
	
	private DecimalFormat decimalFormat = new DecimalFormat("##.00");
	private MainMenu mainMenu;
	
	public Controller(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		
	}
	
	private UserDAO userDAO = new WebServiceUserDAO();
	
	
	//--------------------Start controller methods--------------
	public boolean validateUsername(String username) {
		return userDAO.validateUsername(username);
	}
	
	public boolean validatePassword(String username, String password) {
		return userDAO.validatePassword(username, password);
	}
	
	public void createUser(User user) {
		userDAO.addUser(user);
	}
	
	public User getUserInfo(String username) {
		return userDAO.getUserInfo(username);
	}
	
	
	public void updateUserAddress(Address address, User user) {
		userDAO.updateUserAddress(address, user);
		
	}

	
	public void updateUserInfo(User user) {
		userDAO.updateUserInfo(user);
		
	}
	
	
	
	
	

}
