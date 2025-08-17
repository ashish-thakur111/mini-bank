package com.ashisht.mini_bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer operationTypeId;
    private String description;
}

