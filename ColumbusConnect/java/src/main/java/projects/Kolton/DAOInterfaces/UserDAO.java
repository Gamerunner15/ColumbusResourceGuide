package projects.Kolton.DAOInterfaces;

import java.util.List;

import project.Kolton.objects.Address;
import project.Kolton.objects.User;

public interface UserDAO {
	
	
	public boolean validateUsername(String username);
	public boolean validatePassword(String username, String password);
	public void addUser(User user);
	public void updateUserAddress(Address address, User user);
	public void updateUserInfo(User user);
	public User getUserInfo(String username);
	public List<User> getListOfUsers();
	
	

}
