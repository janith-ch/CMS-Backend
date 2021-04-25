package com.af.cms.repository;

import com.af.cms.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

         //userRepository extend by mongoRepository
}
