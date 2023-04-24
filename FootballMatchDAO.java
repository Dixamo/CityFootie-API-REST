package org.cityfootie.dao;


import org.cityfootie.entity.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballMatchDAO extends JpaRepository<FootballMatch, Integer> {
}