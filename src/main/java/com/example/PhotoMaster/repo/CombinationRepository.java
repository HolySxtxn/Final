package com.example.PhotoMaster.repo;

import com.example.PhotoMaster.models.Combination;
import com.example.PhotoMaster.models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CombinationRepository extends CrudRepository <Combination, Long> {
    List<Combination> findByEmployee (Employee employee);
}
