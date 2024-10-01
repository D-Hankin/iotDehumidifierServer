package com.iotdehumidifier.iotdehumidifier.repositories;

import org.springframework.stereotype.Repository;

import com.iotdehumidifier.iotdehumidifier.models.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email); 
}
