package org.bot.oslregistrationbackend.data;

import org.bot.oslregistrationbackend.entity.FreeAgent;
import org.bot.oslregistrationbackend.entity.Player;
import org.bot.oslregistrationbackend.entity.Team;
import org.bot.oslregistrationbackend.entity.TeamPlayer;
import org.bot.oslregistrationbackend.enums.League;
import org.bot.oslregistrationbackend.repository.FreeAgentRepository;
import org.bot.oslregistrationbackend.repository.PlayerRepository;
import org.bot.oslregistrationbackend.repository.TeamPlayerRepository;
import org.bot.oslregistrationbackend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.ArrayList;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private TeamPlayerRepository teamPlayerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private FreeAgentRepository freeAgentRepository;

    @Override
    public void run(String... args) throws Exception {
        Player player1 = new Player(1L);
        Player player2 = new Player(2L);
        Player player3 = new Player(3L);
        Player player4 = new Player(4L);
        Player player5 = new Player(5L);

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);

        FreeAgent freeAgent = new FreeAgent();
        freeAgent.setPlayer(player5);
        freeAgent.setLeague(League.INTERMEDIATE);

        Team team1 = new Team();
        team1.setTeamName("Such Is Life");
        team1.setTeamID("SIL");

        Team team2 = new Team();
        team2.setTeamName("This is a Team");
        team2.setTeamID("TIT");

        TeamPlayer teamPlayer1 = new TeamPlayer();
        teamPlayer1.setTeam(team1);
        teamPlayer1.setPlayer(player3);
        teamPlayer1.setCaptain(false);

        playerRepository.saveAll(players);
        freeAgentRepository.save(freeAgent);
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamPlayerRepository.save(teamPlayer1);
    }
}
