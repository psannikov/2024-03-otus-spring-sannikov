package ru.otus.spring.psannikov.ormdemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "avatars")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String photoUrl;

    @OneToOne(mappedBy = "avatar")
    private OtusStudent student;
}
