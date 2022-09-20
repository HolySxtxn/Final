package com.example.PhotoMaster.repo;

import com.example.PhotoMaster.models.Price;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PriceRepository extends CrudRepository <Price, Long> {
    List<Price> findByNameContains (String name);
    Price findByName (String name);

}
