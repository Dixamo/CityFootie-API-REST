package org.cityfootie.dao;


import org.cityfootie.entity.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface FootballMatchDAO extends JpaRepository<FootballMatch, Integer> {
    boolean existsByLatitudeAndLongitude(double latitude, double longitude);

    FootballMatch findByLatitudeAndLongitude(double latitude, double longitude);

    List<FootballMatch> findByDateBefore(Timestamp currentTime);

}