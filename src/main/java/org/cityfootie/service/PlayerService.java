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

    public boolean updatePlayer(Integer playerId, Player player) {
        if (playerDAO.existsById(playerId)) {
            if (!playerDAO.existsByUsername(player.getUsername().toLowerCase())) {
                player.setId(playerId);
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

}
