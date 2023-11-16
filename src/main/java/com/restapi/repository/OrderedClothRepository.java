package com.restapi.repository;

import com.restapi.model.OrderedCloth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedClothRepository extends JpaRepository<OrderedCloth, Long> {
}
