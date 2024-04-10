package com.example.springbootwithmongo.repository;

import com.example.springbootwithmongo.entity.Account;
import com.example.springbootwithmongo.model.UpdateAccountRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends MongoRepository<Account,String> {
}
