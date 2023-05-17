package org.cityfootie.dao;

import org.cityfootie.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDAO extends JpaRepository<Player, Integer> {
    Player findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    Player findByNumber(int number);
}