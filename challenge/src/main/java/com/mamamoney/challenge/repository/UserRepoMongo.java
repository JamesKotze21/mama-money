package com.mamamoney.challenge.repository;

import com.mamamoney.challenge.models.domain.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepoMongo extends MongoRepository <UserData, String> {

    

}
