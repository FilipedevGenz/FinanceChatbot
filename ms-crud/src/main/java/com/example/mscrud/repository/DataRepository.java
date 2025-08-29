package com.example.mscrud.repository;

import com.example.mscrud.model.Data;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DataRepository extends MongoRepository<Data, String> {
    List<Data> findByFrom(String from);

}
