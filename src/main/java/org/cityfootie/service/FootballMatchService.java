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

    public FootballMatch getFootballMatchByLatLng(double latitude, double longitude) {
        return footballMatchDAO.findByLatitudeAndLongitude(latitude, longitude);
    }


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

    @Scheduled(fixedRate = 30000)
    public void deleteExpiredMatchesScheduled() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        List<FootballMatch> expiredFootballMatches = footballMatchDAO.findByDateBefore(currentTime);
        footballMatchDAO.deleteAll(expiredFootballMatches);
    }
}
