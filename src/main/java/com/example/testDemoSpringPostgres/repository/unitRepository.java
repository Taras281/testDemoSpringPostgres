package com.example.testDemoSpringPostgres.repository;

import com.example.testDemoSpringPostgres.model.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface unitRepository extends CrudRepository<Unit, Integer> {
}
