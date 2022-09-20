package com.example.PhotoMaster.repo;

import com.example.PhotoMaster.models.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusRepository extends CrudRepository <Status, Long> {
    List<Status> findByNameContains (String name);
    Status findBySum (Integer sum);
}
