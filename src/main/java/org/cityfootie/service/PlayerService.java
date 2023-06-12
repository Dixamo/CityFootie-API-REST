package org.cityfootie.service;

import org.cityfootie.dao.PlayerDAO;
import org.cityfootie.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Player loginPlayer(String email, String password) {
        return playerDAO.findByEmailAndPassword(email.toLowerCase(), password);
    }

    public boolean updatePlayer(Player toUpdatePlayer, String name, String surnames, String username, int number) {
        if (!toUpdatePlayer.getUsername().equalsIgnoreCase(username)) {
            if (!playerDAO.existsByUsername(username)) {
                toUpdatePlayer.setName(name);
                toUpdatePlayer.setSurnames(surnames);
                toUpdatePlayer.setUsername(username.toLowerCase());
                toUpdatePlayer.setNumber(number);
                playerDAO.save(toUpdatePlayer);
                return true;
            } else {
                return false;
            }
        } else {
            toUpdatePlayer.setName(name);
            toUpdatePlayer.setSurnames(surnames);
            toUpdatePlayer.setNumber(number);
            playerDAO.save(toUpdatePlayer);
            return true;
        }
    }

    public Player getPlayerByEmail(String playerEmail) {
        return playerDAO.findByEmail(playerEmail.toLowerCase());
    }
}
