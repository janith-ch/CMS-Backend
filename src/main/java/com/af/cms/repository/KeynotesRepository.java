package com.af.cms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.af.cms.model.Keynotes;

@Repository
public interface KeynotesRepository extends MongoRepository<Keynotes,Integer> {
	

}
