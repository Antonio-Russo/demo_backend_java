package com.example.demo_be.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonAutoDetect
// le colonne devono essere chiamate tutte con iniziale minuscolo se vuoi usare i metodi di JPA tipo findOneBy... etc.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(unique = true)
    @NotBlank(message = "Inserire username non può essere vuoto")
    private String username;
    @Column
    @Size(min = 8, max = 30, message = "Inserire password minimo 8 caratteri e massimo 30")
    private String password;
}

