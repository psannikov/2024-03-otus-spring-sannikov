package ru.otus.spring.psannikov.jpql_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitySalary {
    private String city;
    private Double salary;

}
