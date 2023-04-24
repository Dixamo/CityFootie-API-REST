package org.cityfootie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "footballmatch")
public class FootballMatch implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "calle")
    private String calle;
    @Column(name = "number_max")
    private int numberMax;
    @Column(name = "number_player")
    private int numberPlayers;
}
