package com.icrn.yamr.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Ingredient {

    private String Name;
    private Double amount;
    private Measurement measurement;

    public Ingredient(String name, Double amount, Measurement measurement) {
        Name = name;
        this.amount = amount;
        this.measurement = measurement;
    }
}
