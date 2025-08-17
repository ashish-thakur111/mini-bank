package com.ashisht.mini_bank.repository;

import com.ashisht.mini_bank.entity.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationTypeRepo extends JpaRepository<OperationType, Integer> {
    boolean existsByDescription(String description);
}
