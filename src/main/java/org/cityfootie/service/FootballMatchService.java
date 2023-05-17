package org.cityfootie.service;

import org.cityfootie.dao.FootballMatchDAO;
import org.cityfootie.entity.FootballMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Service
public class FootballMatchService {
    @Autowired
    private FootballMatchDAO footballMatchDAO;

    public boolean createFootballmatch(FootballMatch footballMatch) {
        if (!footballMatchDAO.existsById(footballMatch.getId())) {
            footballMatchDAO.save(footballMatch);
            return true;
        }
        else {
            return false;
        }
    }
    public FootballMatch getFootballMatchByStreet(String street) {
        return footballMatchDAO.findByStreet(street);
    }

    public FootballMatch getFootballMatchByDate(Timestamp date) {
        return footballMatchDAO.getFootballMatchByDate(date);
    }

    public List<FootballMatch> getAllFootballmatches() {
        return footballMatchDAO.findAll();
    }
}
