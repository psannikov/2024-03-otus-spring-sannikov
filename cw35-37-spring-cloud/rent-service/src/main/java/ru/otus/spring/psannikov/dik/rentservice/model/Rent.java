package ru.otus.spring.psannikov.dik.rentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String animal;

    private String customer;

    @Column(name="startdate")
    private String startDate;

    @Column(name="enddate")
    private String endDate;
}
