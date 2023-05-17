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
    public Player loginPlayer(String email, String password) {
        return playerDAO.findByEmailAndPassword(email.toLowerCase(), password);
    }

    public boolean registerPlayer(Player player) {
        if (!playerDAO.existsByEmail(player.getEmail().toLowerCase())) {
            playerDAO.save(player);
            return true;
        }
        else {
            return false;
        }
    }
    public List<Player> getAllPlayers() {
        return playerDAO.findAll();
    }

    public Player getByNumber(int number) {
        return playerDAO.findByNumber(number);
    }
}
