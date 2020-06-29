package Projects.Kolton.Serverside_API.dao;


import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import Projects.Kolton.Serverside_API.Exceptions.UserNotFoundException;
import Projects.Kolton.Serverside_API.domain.Address;
import Projects.Kolton.Serverside_API.domain.User;

@Component // Tells Spring this can be injected somewhere
public class JDBCUserDAO implements UserDAO {

	private JdbcTemplate template;

	public JDBCUserDAO(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public boolean validateUsername(String username) {
		String sql = "SELECT username FROM app_user WHERE username = ?;";
		SqlRowSet row = template.queryForRowSet(sql, username);
		if (row.next()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean validatePassword(String username, String password) throws UserNotFoundException {
		String sql = "SELECT password FROM app_user WHERE username = ?";
		SqlRowSet row = template.queryForRowSet(sql, username);
		if (row.next()) {
			String actualPassword = row.getString("password");
			if (actualPassword.equals(password)) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public User getUserInfo(String username) throws UserNotFoundException {
		String sql = "SELECT * FROM app_user " + "WHERE app_user.username = ?;";
		SqlRowSet row = template.queryForRowSet(sql, username);
		int addressId = 0;
		if (row.next()) {
			User newUser = new User(row.getString("username"), row.getString("password"));
			newUser.setFirstName(row.getString("first_name"));
			newUser.setLastName(row.getString("last_name"));
			newUser.setBirthDate(row.getString("birth_date"));
			newUser.setAnnualIncome(row.getDouble("annual_income"));
			newUser.setGender(row.getString("gender"));
			newUser.setNeedsCellPhone(row.getBoolean("needs_phone"));
			newUser.setNeedsFood(row.getBoolean("needs_food"));
			newUser.setNeedsJob(row.getBoolean("needs_job"));
			newUser.setNeedsHousing(row.getBoolean("needs_housing"));
			addressId = row.getInt("address_id");
			if (addressId > 0) {
				String sql2 = "SELECT * FROM address WHERE id = ?";
				SqlRowSet row2 = template.queryForRowSet(sql2, addressId);
				row2.next();
				Address address = new Address(row2.getString("street_address"), row2.getString("city"),
						row2.getString("State"), row2.getInt("zipcode"));
				address.setBuildingType(row2.getString("building_type"));
				newUser.setAddress(address);
			}
			return newUser;
		} else {
			throw new UserNotFoundException();
		}

	}

	@Override
	public void addUser(User user) {
		String sql = "INSERT INTO app_user (username, password, first_name, last_name, birth_date) VALUES (?, ?, ?, ?, CAST(? AS DATE))";
		template.update(sql, user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
				user.getBirthDate());

	}

	@Override
	public void updateUserAddress(Address address, User user) {
		String sql1 = "INSERT INTO address (street_address, city, state, zipcode, building_type) VALUES (?, ?, ?, ?, ?) Returning id;";
		SqlRowSet addressId = template.queryForRowSet(sql1, address.getStreetAddress(), address.getCityName(),
				address.getStateName(), address.getZipCode(), address.getBuildingType());
		addressId.next();
		int id = addressId.getInt("id");
		String sql2 = "UPDATE app_user SET address_id = ? WHERE username = ?;";
		template.update(sql2, id, user.getUsername());

	}

	@Override
	public void updateUserInfo(User user) {
		String sql = "UPDATE app_user " + "SET birth_date = CAST(? AS DATE), " + "gender = ?, " + "annual_income = ?, "
				+ "needs_phone = ?, " + "needs_job = ?, " + "needs_food = ?, " + "needs_housing = ? "
				+ "WHERE username = ?";
		template.update(sql, user.getBirthDate(), user.getGender(), user.getAnnualIncome(), user.getNeedsCellPhone(),
				user.getNeedsJob(), user.getNeedsFood(), user.getNeedsHousing(), user.getUsername());

	}

	@Override
	public List<User> getListOfUsers() {

		String sql = "SELECT * FROM app_user;";
		SqlRowSet rowset = template.queryForRowSet(sql);
		List<User> users = this.mapRowToUser(rowset);

		return users;
	}

	public List<User> mapRowToUser(SqlRowSet rowset) {
		List<User> users = new ArrayList<User>();
		while (rowset.next()) {
			User user = new User(rowset.getString("username"), rowset.getString("password"));
			user.setFirstName(rowset.getString("first_name"));
			user.setLastName(rowset.getString("last_name"));
			user.setAnnualIncome(rowset.getDouble("annual_income"));
			user.setGender(rowset.getString("gender"));
			user.setNeedsCellPhone(rowset.getBoolean("needs_phone"));
			user.setNeedsFood(rowset.getBoolean("needs_food"));
			user.setNeedsJob(rowset.getBoolean("needs_job"));
			user.setNeedsHousing(rowset.getBoolean("needs_housing"));
			users.add(user);
		}

		return users;
	}

}
