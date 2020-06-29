package projects.Kolton.DAO;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import project.Kolton.objects.Address;
import project.Kolton.objects.User;
import projects.Kolton.DAOInterfaces.UserDAO;

//-----------------------------------Deprecated Class---------------------

public class JDBCUserDAO {

	private JdbcTemplate template;

	public JDBCUserDAO() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/ColumbusResourceGuide");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		template = new JdbcTemplate(dataSource);
	}

	public void addUser(User user) {
		String sql = "INSERT INTO app_user (username, password, first_name, last_name) VALUES (?, ?, ?, ?);";
		template.update(sql, user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName());
	}

	public void updateUserAddress(Address address, User user) {
		String sql1 = "INSERT INTO address (street_address, city, state, zipcode, building_type) VALUES (?, ?, ?, ?, ?) Returning id;";
		SqlRowSet addressId = template.queryForRowSet(sql1, address.getStreetAddress(), address.getCityName(),
				address.getStateName(), address.getZipCode(), address.getBuildingType());
		addressId.next();
		int id = addressId.getInt("id");
		String sql2 = "UPDATE app_user SET address_id = ? WHERE username = ?;";
		template.update(sql2, id, user.getUsername());
	}

	public User getUserInfo(String username, String password) {
		String sql = "SELECT * FROM address JOIN app_user ON app_user.address_id = address.id "
				+ "WHERE app_user.username = ? AND app_user.password = ?;";
		SqlRowSet row = template.queryForRowSet(sql, username, password);
		if (row.next()) {
			User user = new User(row.getString("username"), row.getString("password"));
			Address address = new Address(row.getString("street_address"), row.getString("city"),
					row.getString("State"), row.getInt("zipcode"));
			user.setFirstName(row.getString("first_name"));
			user.setLastName(row.getString("last_name"));
			user.setAddress(address);
			user.setAnnualIncome(row.getDouble("annual_income"));
			user.setGender(row.getString("gender"));
			user.setNeedsCellPhone(row.getBoolean("needs_phone"));
			user.setNeedsFood(row.getBoolean("needs_food"));
			user.setNeedsJob(row.getBoolean("needs_job"));
			user.setNeedsHousing(row.getBoolean("needs_housing"));
			return user;
		} else {
			String sql2 = "SELECT * FROM app_user " + "WHERE app_user.username = ? AND app_user.password = ?;";
			SqlRowSet row2 = template.queryForRowSet(sql2, username, password);
			if (row2.next()) {
				User newUser = new User(row2.getString("username"), row2.getString("password"));
				newUser.setFirstName(row.getString("first_name"));
				newUser.setLastName(row.getString("last_name"));
				return newUser;
			} else {
				return null;
			}
		}
	}

	public boolean validateUsername(String username) {
		String sql = "SELECT username FROM app_user WHERE username = ?;";
		SqlRowSet row = template.queryForRowSet(sql, username);
		if (row.next()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validatePassword(String username, String password) {
		String sql = "SELECT password FROM app_user WHERE username = ?";
		SqlRowSet row = template.queryForRowSet(sql, username);
		row.next();
		String actualPassword = row.getString("password");
		if (actualPassword.equals(password)) {
			return true;
		} else {
			return false;
		}

	}

	
	

	
	public void updateUserInfo(User user) {
		// TODO Auto-generated method stub
		
	}

	
	

}
