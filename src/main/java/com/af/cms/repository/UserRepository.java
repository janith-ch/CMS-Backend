package com.af.cms.repository;

import com.af.cms.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'userRole':{$eq:?0}}")
    List<User> findByUserRole(String userRole);

    //userRepository extend by mongoRepository
}
