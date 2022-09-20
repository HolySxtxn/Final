package com.example.PhotoMaster.repo;

import com.example.PhotoMaster.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository <User, Long> {

}
