package org.cityfootie.service;

import org.cityfootie.dao.FootballMatchDAO;
import org.cityfootie.entity.FootballMatch;
import org.cityfootie.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FootballMatchService {
    @Autowired
    private FootballMatchDAO footballMatchDAO;

    public FootballMatch getFootballMatchByLatLng(double latitude, double longitude) {
        return footballMatchDAO.findByLatitudeAndLongitude(latitude, longitude);
    }


    public boolean createFootballMatch(FootballMatch footballMatch) {
        if (!footballMatchDAO.existsByLatitudeAndLongitude(footballMatch.getLatitude(), footballMatch.getLongitude())) {
            footballMatchDAO.save(footballMatch);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean joinPlayerToFootballMatch(Player player, FootballMatch footballMatch) {
        Set<Player> footballMatchPlayers = footballMatch.getPlayers();
        if (!footballMatchPlayers.contains(player)) {
            footballMatchPlayers.add(player);
            footballMatch.setPlayers(footballMatchPlayers);
            Set<FootballMatch> playerFootballMatches = player.getFootballMatches();
            playerFootballMatches.add(footballMatch);
            player.setFootballMatches(playerFootballMatches);
            footballMatchDAO.save(footballMatch);
            return true;
        }
        else {
            return false;
        }
        /*if (footballMatch.getPlayers() == null) {
            Set<Player> footballMatchPlayers = new HashSet<>();
            footballMatchPlayers.add(player);
            footballMatch.setPlayers(footballMatchPlayers);
            Set<FootballMatch> footballMatches = new HashSet<>();
            footballMatches.add(footballMatch);
            player.setFootballMatches(footballMatches);
        }
        else {

        }*/
    }
    /*public FootballMatch getFootballMatchByStreet(String street) {
        return footballMatchDAO.findByStreet(street);
    }*/

    public FootballMatch getFootballMatchByDate(Timestamp date) {
        return footballMatchDAO.getFootballMatchByDate(date);
    }

    public List<FootballMatch> getAllFootballmatches() {
        return footballMatchDAO.findAll();
    }

    public FootballMatch getFootballMatchById(Integer footballMatchId) {
        if (footballMatchDAO.existsById(footballMatchId)) {
            return footballMatchDAO.getReferenceById(footballMatchId);
        }
        else {
            return null;
        }
    }

    /*public boolean updateFootballMatch(Integer footballMatchId, FootballMatch footballMatch) {
        if (footballMatchDAO.existsById(footballMatchId)) {
            if (!footballMatchDAO.existsByStreet(footballMatch.getStreet().toLowerCase())) {
                footballMatch.setId(footballMatchId);
                footballMatch.setNumberMax(footballMatch.getNumberMax());
                footballMatchDAO.save(footballMatch);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }*/
}
