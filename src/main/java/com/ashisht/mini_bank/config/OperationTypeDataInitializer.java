package com.ashisht.mini_bank.config;

import com.ashisht.mini_bank.entity.OperationType;
import com.ashisht.mini_bank.repository.OperationTypeRepo;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class OperationTypeDataInitializer {
    private final OperationTypeRepo operationTypeRepo;

    public OperationTypeDataInitializer(OperationTypeRepo operationTypeRepo) {
        this.operationTypeRepo = operationTypeRepo;
    }

    @PostConstruct
    public void init() {
        List<String> types = Arrays.asList(
                "CASH PURCHASE",
                "INSTALLMENT PURCHASE",
                "WITHDRAWAL",
                "PAYMENT"
        );
        for (String desc : types) {
            if (!operationTypeRepo.existsByDescription(desc)) {
                OperationType op = new OperationType();
                op.setDescription(desc);
                operationTypeRepo.save(op);
            }
        }
    }
}

