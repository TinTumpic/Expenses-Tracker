package com.tintumpic.expenses_web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceSubCategoryId")
    private Long num;

    @DecimalMin(value = "0", message = "Item price can not be negative!")
    @NotNull(message = "Price can not be empty!")
    private BigDecimal price;

    @NotEmpty(message = "There must be a description!")
    private String description;

    @NotNull(message = "You must insert date of purchase!")
    private LocalDate dop;

}
