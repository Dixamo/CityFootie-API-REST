package org.cityfootie.service;

import org.cityfootie.dao.FootballMatchDAO;
import org.cityfootie.entity.FootballMatch;
import org.cityfootie.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Service
public class FootballMatchService {
    @Autowired
    private FootballMatchDAO footballMatchDAO;

    /**
     * Método encargado de obtener el partido que hay en una pista (a través de latitud y longitud).
     *
     * @param latitude
     * @param longitude
     * @return FootballMatch
     */
    public FootballMatch getFootballMatchByLatLng(double latitude, double longitude) {
        return footballMatchDAO.findByLatitudeAndLongitude(latitude, longitude);
    }


    /**
     * Método encargado de crear un partido nuevo.
     *
     * @param footballMatch
     * @return boolean
     */
    public boolean createFootballMatch(FootballMatch footballMatch) {
        if (!footballMatchDAO.existsByLatitudeAndLongitude(footballMatch.getLatitude(), footballMatch.getLongitude())) {
            if (footballMatch.getDate().after(new Timestamp(System.currentTimeMillis()))) {
                footballMatchDAO.save(footballMatch);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Método encargado de unir un jugador a un partido (vinculándolo con la tabla intermedia)
     *
     * @param player
     * @param footballMatch
     * @return boolean
     */
    public boolean joinPlayerToFootballMatch(Player player, FootballMatch footballMatch) {
        Set<Player> footballMatchPlayers = footballMatch.getPlayers();
        if (!footballMatchPlayers.contains(player) || footballMatchPlayers.size() >= footballMatch.getNumberMax()) {
            footballMatchPlayers.add(player);
            footballMatch.setPlayers(footballMatchPlayers);
            footballMatch.setNumberPlayers(footballMatch.getPlayers().size());
            Set<FootballMatch> playerFootballMatches = player.getFootballMatches();
            playerFootballMatches.add(footballMatch);
            player.setFootballMatches(playerFootballMatches);
            footballMatchDAO.save(footballMatch);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método encargado de borrar el partido cuando haya pasado medio minuto.
     */
    @Scheduled(fixedRate = 30000)
    public void deleteExpiredMatchesScheduled() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        List<FootballMatch> expiredFootballMatches = footballMatchDAO.findByDateBefore(currentTime);
        footballMatchDAO.deleteAll(expiredFootballMatches);
    }
}
