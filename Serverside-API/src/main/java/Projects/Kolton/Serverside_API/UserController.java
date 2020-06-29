package Projects.Kolton.Serverside_API;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Projects.Kolton.Serverside_API.Exceptions.UserNotFoundException;
import Projects.Kolton.Serverside_API.dao.UserDAO;
import Projects.Kolton.Serverside_API.domain.User;

@RestController
@PreAuthorize("isAuthenticated()")
public class UserController {
	
	private UserDAO userDAO;
	
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public List<User> getListOfUsers(){
		return userDAO.getListOfUsers();
	}
	@PreAuthorize("permitAll")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/users", method = RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		userDAO.addUser(user);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/users", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
		userDAO.updateUserInfo(user);
	}
	
	@RequestMapping(path = "/users/{username}", method = RequestMethod.GET)
	public User getUserInfo(@PathVariable String username) throws UserNotFoundException {
		return userDAO.getUserInfo(username);
	}
	@PreAuthorize("permitAll")
	@RequestMapping(path = "/validate", method = RequestMethod.POST)
	public boolean validatePassword(@Valid @RequestBody User user) throws UserNotFoundException {
		String username = user.getUsername();
		String password = user.getPassword();
		return userDAO.validatePassword(username, password);
	}
	@PreAuthorize("permitAll")
	@RequestMapping(path = "/validate/{username}", method = RequestMethod.GET)
	public boolean validateUsername(@PathVariable String username) {
		return userDAO.validateUsername(username);
	}
	
}
