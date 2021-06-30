package com.af.cms.service;

import com.af.cms.dto.Authenticate;
import com.af.cms.dto.Login;
import com.af.cms.model.ResearchPaper;
import com.af.cms.model.User;
import com.af.cms.repository.UserRepository;
import com.mongodb.lang.Nullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInt {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	//save user
	@Override
	public void createUser(User user) {
		try {
			userRepository.save(user);
		} catch (Exception e) {
			throw new RuntimeException("error getting the save user " + e);
		}
	}

	// find all users
	@Override
	public List<User> users() {
		try {
			return userRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException("error getting list of users " + e);
		}
	}

	//find single user
	@Override
	public Optional<User> getSingleUser(String id) {
		try {
			Optional<User> user = userRepository.findById(id);
			return user;
		} catch (Exception e) {
			throw new RuntimeException("error getting find a single user " + e);
		}

	}

	//update user
	@Override
	public boolean editUser(User user, String id) {
		try {
			Optional<User> user2 = userRepository.findById(id);
			if (user2 == null) {
				return false;
			} else {
				User u1 = user2.get();
				u1.setFirstName(user.getFirstName());
				u1.setLastName(user.getLastName());
				u1.setEmail(user.getEmail());
				u1.setUserRole(user.getUserRole());
				u1.setRequestedUserRole(user.getRequestedUserRole());
				u1.setPassword(user.getPassword());
				u1.setCountry(user.getCountry());

				userRepository.save(u1);
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException("error getting update user " + e);
		}

	}

	//delete user
	@Override
	public boolean deleteUser(String id) {
		try {
			if (id == null) {
				return false;
			} else {
				userRepository.deleteById(id);
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException("error getting delete user " + e);
		}
	}
	@Override
	public List<User> userWithStatus(String status[]) {
		return( userRepository.findByUserRole(status));

	}
	//update user Role
	@Override
	public boolean editRequestUserRole(User user, String id) {
		try {
			Optional<User> user2 = userRepository.findById(id);
			if (user2 == null) {
				return false;
			} else {
				User u1 = user2.get();
				u1.setRequestedUserRole(user.getRequestedUserRole());
				userRepository.save(u1);
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException("error getting update requested userRole " + e);
		}
	}

	@Override
	public boolean editUserRole(User user, String id) {
		try {
			Optional<User> user2 = userRepository.findById(id);
			if (user2 == null) {
				return false;
			} else {
				User u1 = user2.get();
				u1.setUserRole(user.getUserRole());
				userRepository.save(u1);
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException("error getting update userRole " + e);
		}
	}

	public Authenticate authenticateUser(Login login) {
		
		String username = login.getUsername();
		String password = login.getPassword();

		User user  = new User();
		Authenticate  authenticate = new Authenticate();
		try {

			user = mongoTemplate.findOne(Query.query(Criteria.where("email").is(username)), User.class);

			String email = user.getEmail(); 
			String key = user.getPassword(); 

			if(email.equals(username) && password.equals(key)) {


				authenticate.setUserRole(user.getUserRole());
				authenticate.setEmail(user.getEmail());
				authenticate.setMessage("valid credentials!!");
				return authenticate;
			}else {
				authenticate.setMessage("Invalid credentials!!");
				authenticate.setUserRole(null);
				authenticate.setEmail(null);
				return authenticate;
			}

		}catch (Exception e) {

			authenticate.setMessage("User not found !!");
			authenticate.setUserRole(null);
			authenticate.setEmail(null);
			return authenticate;

		}





	}
}