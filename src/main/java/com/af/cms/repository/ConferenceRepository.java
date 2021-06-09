package com.af.cms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.af.cms.model.Conference;


@Repository
public interface ConferenceRepository extends MongoRepository<Conference,Integer> {

}
