package com.example.PhotoMaster.repo;

import com.example.PhotoMaster.models.Client;
import com.example.PhotoMaster.models.Loyalty;
import org.springframework.data.repository.CrudRepository;

public interface LoyaltyRepository extends CrudRepository <Loyalty, Long> {
Loyalty findByClient (Client client);
}
