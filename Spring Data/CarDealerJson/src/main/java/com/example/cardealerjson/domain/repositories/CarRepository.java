package com.example.cardealerjson.domain.repositories;

import com.example.cardealerjson.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
   Set<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);
}
