package com.example.PhotoMaster.repo;

import com.example.PhotoMaster.models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository <Employee, Long> {
    List<Employee> findByInnContains (String inn);
}
