package com.af.cms.service;

import com.af.cms.model.User;
import com.af.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInt {

    @Autowired
    UserRepository userRepository;

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
            Iterable<User> user1 = userRepository.findAllById(Collections.singleton(id));

            if (user1 == null) {
                return false;
            } else {
                userRepository.save(user);
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

}
