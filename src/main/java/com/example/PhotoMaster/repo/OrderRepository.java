package com.example.PhotoMaster.repo;

import com.example.PhotoMaster.models.Bill;
import com.example.PhotoMaster.models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository <Orders, Long> {
Iterable<Orders> findByBill(Bill bill);
}
