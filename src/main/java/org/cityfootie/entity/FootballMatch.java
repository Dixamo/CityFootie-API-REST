package org.cityfootie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "football_match")
public class FootballMatch implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "number_max")
    private int numberMax;
    @Column(name = "number_players")
    private int numberPlayers;
    @Column(name = "date")
    private Timestamp date;
    @ManyToMany(mappedBy = "footballMatches")
    private Set<Player> players;
}
