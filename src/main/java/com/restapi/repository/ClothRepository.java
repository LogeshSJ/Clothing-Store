package com.restapi.repository;

import com.restapi.model.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {
}
