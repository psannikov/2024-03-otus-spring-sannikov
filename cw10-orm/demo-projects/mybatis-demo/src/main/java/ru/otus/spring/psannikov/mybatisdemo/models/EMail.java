package ru.otus.spring.psannikov.mybatisdemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EMail {
    private long id;
    private String email;
}
