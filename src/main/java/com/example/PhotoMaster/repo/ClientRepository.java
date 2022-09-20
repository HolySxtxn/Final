package com.example.PhotoMaster.repo;

import com.example.PhotoMaster.models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository <Client, Long> {
    Client findByPhone (String phone);
}
