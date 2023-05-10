package org.cityfootie.service;

import org.cityfootie.dao.FootballMatchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FootballMatchService {
    @Autowired
    private FootballMatchDAO footballMatchDAO;

}
