package org.bot.oslregistrationbackend.controller;

import org.bot.oslregistrationbackend.entity.TeamPlayer;
import org.bot.oslregistrationbackend.repository.TeamPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/team-player")
public class TeamPlayerController {

    private final TeamPlayerRepository repository;

    @Autowired
    public TeamPlayerController(TeamPlayerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<TeamPlayer> findAll() {
        return repository.findAll();
    }

    @GetMapping("/team/{id}")
    public Optional<Iterable<TeamPlayer>> findAllById(@PathVariable("id") String id) {
        return repository.findAllByTeam_TeamID(id);
    }

    @GetMapping("/{id}")
    public Optional<TeamPlayer> findById(@PathVariable("id") long id) {
        return repository.findTeamPlayerByPlayer_DiscordId(id);
    }

    @PostMapping
    public TeamPlayer save(@RequestBody TeamPlayer teamPlayer) {
        return repository.save(teamPlayer);
    }

    @DeleteMapping
    @Transactional
    public void remove(@RequestBody TeamPlayer teamPlayer) {
        repository.delete(teamPlayer);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void removeById(@PathVariable("id") long id) {
        repository.deleteTeamPlayerByPlayer_DiscordId(id);
    }

    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable("id") long id) {
        return repository.existsTeamPlayerByPlayer_DiscordId(id);
    }
}
