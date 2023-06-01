package org.cityfootie.service;

import org.cityfootie.dao.PlayerDAO;
import org.cityfootie.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerDAO playerDAO;

    public boolean registerPlayer(Player player) {
        if (!playerDAO.existsByEmail(player.getEmail().toLowerCase())) {
            if (!playerDAO.existsByUsername(player.getUsername().toLowerCase())) {
                player.setEmail(player.getEmail().toLowerCase());
                player.setUsername(player.getUsername().toLowerCase());
                playerDAO.save(player);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public Player loginPlayer(String email, String password) {
        return playerDAO.findByEmailAndPassword(email.toLowerCase(), password);
    }

    public boolean updatePlayer(Player toUpdatePlayer, String name, String surnames, int number) {
        if (playerDAO.existsByEmail(toUpdatePlayer.getEmail())) {
            toUpdatePlayer.setName(name);
            toUpdatePlayer.setSurnames(surnames);
            toUpdatePlayer.setNumber(number);
            toUpdatePlayer.setEmail(toUpdatePlayer.getEmail().toLowerCase());
            toUpdatePlayer.setUsername(toUpdatePlayer.getUsername().toLowerCase());
            playerDAO.save(toUpdatePlayer);
            return true;
        }
        else {
            return false;
        }
    }

    public Player getPlayerByEmail(String playerEmail) {
        return playerDAO.findByEmail(playerEmail.toLowerCase());
    }

    public List<Player> getAllPlayers() {
        return playerDAO.findAll();
    }

    public Player getByNumber(int number) {
        return playerDAO.findByNumber(number);
    }

    public Player getPlayerById(Integer playerId) {
        if (playerDAO.existsById(playerId)) {
            return playerDAO.getReferenceById(playerId);
        }
        else {
            return null;
        }
    }
}
