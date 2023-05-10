package org.cityfootie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    @Column(name = "name_player")
    private String namePlayer;
    @Column(name = "first_surname")
    private String firstSurname;
    @Column(name = "second_surname")
    private String secondSurname;
    @Column(name = "date_birth")
    private Date dateBirth;
    @Column(name = "name_user")
    private String nameUser;
    @Column(name = "email")
    private String email;
    @Column(name = "pass")
    private String password;
    @Column(name = "dorsal")
    private int dorsal;
    @Column(name = "favorite_player")
    private String favoritePlayer;
    @Column(name = "favorite_team")
    private String favoriteTeam;
}
