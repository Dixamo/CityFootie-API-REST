package org.cityfootie.dao;

import org.cityfootie.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerDAO extends JpaRepository<Player, Integer> {
    List<Player> findByEmailIgnoreCase(String password);

    List<Player> findByPasswordIgnoreCase(String email);

    List<Player> findByEmailIgnoreCaseAndPasswordIgnoreCase(String email, String password);
}
