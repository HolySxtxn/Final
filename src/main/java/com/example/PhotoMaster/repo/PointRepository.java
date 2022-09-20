package com.example.PhotoMaster.repo;

import com.example.PhotoMaster.models.Point;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointRepository extends CrudRepository <Point, Long> {
    List<Point> findByStreetContains (String street);
}
