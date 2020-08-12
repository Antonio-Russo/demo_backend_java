package com.example.demo_be.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "presence")
@JsonAutoDetect
@IdClass(PresenceKey.class)

public class Presence {

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Id
    @Column
    private int idTelegram;

    @Column
    @Temporal(TemporalType.TIMESTAMP) //data con timestamp
    private Date dataCreation;

    @Column
    private String name;

    @Id
    @Column
    @Temporal(TemporalType.DATE) //solo la data
    private Date dateOfPresence;

    @Column
    private int workingHours;

}
