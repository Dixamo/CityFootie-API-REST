package org.cityfootie.service;

import org.cityfootie.dao.PlayerDAO;
import org.cityfootie.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    private PlayerDAO playerDAO;

    /**
     * Método encargado de registrar un nuevo usuario.
     *
     * @param player
     * @return boolean
     */
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

    /**
     * Método encargado de iniciar sesión con el email y la contraseña.
     *
     * @param email
     * @param password
     * @return Player
     */
    public Player loginPlayer(String email, String password) {
        return playerDAO.findByEmailAndPassword(email.toLowerCase(), password);
    }

    /**
     * Método encargado de modificar los datos del usuario registrado en la BBDD.
     * (excepto la contraseña, ya que habría que seguir otro proceso para cambiarla con seguridad).
     *
     * @param toUpdatePlayer
     * @param name
     * @param surnames
     * @param username
     * @param number
     * @return boolean
     */
    public boolean updatePlayer(Player toUpdatePlayer, String name, String surnames, String username, int number) {
        if (!toUpdatePlayer.getUsername().equalsIgnoreCase(username)) {
            if (!playerDAO.existsByUsername(username.toLowerCase())) {
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

    /**
     * Método encargado de obtener el usuario a través del email
     * (evitando que la contraseña genere vulnerabilidad, circulando por todas las pantallas).
     *
     * @param playerEmail
     * @return Player
     */
    public Player getPlayerByEmail(String playerEmail) {
        return playerDAO.findByEmail(playerEmail.toLowerCase());
    }
}
