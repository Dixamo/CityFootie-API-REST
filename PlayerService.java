package org.cityfootie.service;

import org.cityfootie.dao.FootballMatchDAO;
import org.cityfootie.dao.PlayerDAO;
import org.cityfootie.entity.FootballMatch;
import org.cityfootie.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private FootballMatchDAO footballMatchDAO;

    public List<Player> getAllPlayers() {
        return playerDAO.findAll();
    }

    public List<Player> listPlayers(String email, String password) {
        List<Player> players;
        if (email == null && password != null) {
            players = playerDAO.findByEmailIgnoreCase(password);
        } else if (email != null && password == null) {
            players = playerDAO.findByPasswordIgnoreCase(email);
        } else {
            players = playerDAO.findByEmailIgnoreCaseAndPasswordIgnoreCase(email, password);
        }
        return players;
    }

    public FootballMatch getFootballMatch(
            String footballMatchId
    ) {
        return footballMatchDAO.findById(Integer.valueOf(footballMatchId)).orElse(null);
    }
    public List<FootballMatch> getAllFootballmatches() {
        return footballMatchDAO.findAll();
    }
}
