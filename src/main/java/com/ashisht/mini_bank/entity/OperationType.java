package com.ashisht.mini_bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OperationType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer operationTypeId;
    private String description;
}

