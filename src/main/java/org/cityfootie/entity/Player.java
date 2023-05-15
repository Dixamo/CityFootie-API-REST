package org.cityfootie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "player")
public class Player implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surnames")
    private String surnames;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "pass")
    private String password;
    @Column(name = "number")
    private int number;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "footballMatch_player",
            joinColumns = {@JoinColumn(name = "player")},
            inverseJoinColumns = {@JoinColumn(name = "footballMatch")})
    private Set<FootballMatch> footballMatches;
}
