package com.example.PhotoMaster.repo;

import com.example.PhotoMaster.models.Bill;
import com.example.PhotoMaster.models.Loyalty;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository <Bill, Long> {
Iterable<Bill> findByLoyalty (Loyalty loyalty);
}
