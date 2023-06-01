package org.cityfootie.dao;


import org.cityfootie.entity.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface FootballMatchDAO extends JpaRepository<FootballMatch, Integer> {
    boolean existsByLatitudeAndLongitude(double latitude, double longitude);
    FootballMatch findByLatitudeAndLongitude(double latitude, double longitude);

    //FootballMatch findByStreet(String street);

    FootballMatch getFootballMatchByDate(Timestamp date);

    //boolean existsByStreet(String lowerCase);
}