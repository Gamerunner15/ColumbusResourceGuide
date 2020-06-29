package projects.Kolton.DAO;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import project.Kolton.objects.Address;
import project.Kolton.objects.User;
import projects.Kolton.DAOInterfaces.UserDAO;

public class WebServiceUserDAO implements UserDAO{
	private String BASE_URL = "http://localhost:8080";
	RestTemplate template;
	public WebServiceUserDAO() {
		template = new RestTemplate();
		
	}
	//Helper Method
	public HttpEntity<User> makeEntity(User user){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<User> entity = new HttpEntity<User>(user, headers);
		return entity;
	}
	
	//---------------------Start DAO Methods------------------
	
	@Override
	public boolean validateUsername(String username) {
		return template.getForObject(BASE_URL + "/validate/" + username, Boolean.class);
		
	}

	@Override
	public boolean validatePassword(String username, String password) {
		User user = new User(username, password);
		HttpEntity<User> entity = this.makeEntity(user);
		ResponseEntity<Boolean> response = template.postForEntity(BASE_URL + "/validate", entity, Boolean.class);
		boolean realResponse = Boolean.valueOf(response.getBody());
		return realResponse;
	}
	
	public void addUser(User user) {
		HttpEntity<User> entity = this.makeEntity(user);
		template.postForObject(BASE_URL + "/users", entity, User.class);
	}

	@Override
	public User getUserInfo(String username) {
		return template.getForObject(BASE_URL + "/users/" + username, User.class);
		
	}

	@Override
	public void updateUserAddress(Address address, User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserInfo(User user) {
		HttpEntity<User> entity = this.makeEntity(user);
		template.put(BASE_URL + "/users/", entity);
		
	}
	@Override
	public List<User> getListOfUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
