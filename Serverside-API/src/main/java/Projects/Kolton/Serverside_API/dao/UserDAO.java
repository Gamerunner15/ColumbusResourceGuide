package Projects.Kolton.Serverside_API.dao;

import java.util.List;

import Projects.Kolton.Serverside_API.Exceptions.UserNotFoundException;
import Projects.Kolton.Serverside_API.domain.Address;
import Projects.Kolton.Serverside_API.domain.User;

public interface UserDAO {
	
	public boolean validateUsername(String username);
	public boolean validatePassword(String username, String password) throws UserNotFoundException;
	public User getUserInfo(String username) throws UserNotFoundException;
	public void addUser(User user);
	public void updateUserAddress(Address address, User user);
	public void updateUserInfo(User user);
	public List<User> getListOfUsers();
	
	
	
	
	
	
	
}
