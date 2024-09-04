package org.bot.oslregistrationbackend.controller;

import org.bot.oslregistrationbackend.entity.Team;
import org.bot.oslregistrationbackend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamRepository repository;

    @Autowired
    public TeamController(TeamRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public Iterable<Team> findAll() {
        return repository.findAll();
    }

    @GetMapping("/empty")
    public Iterable<Team> findAllByLeagueIsNull() {
        return repository.findAllByLeagueIsNull();
    }

    @GetMapping("/taken")
    public Iterable<Team> findAllByLeagueIsNotNull() {
        List<Team> teams = (List<Team>) repository.findAll();
        return teams.stream().filter(team -> team.getLeague() != null).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<Team> findById(@PathVariable String id) {
        return this.repository.findById(id);
    }

    @PostMapping
    public Team save(@RequestBody Team team) {
        return this.repository.save(team);
    }

    @DeleteMapping
    public void remove(@RequestBody Team team) {
        this.repository.delete(team);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable String id) {
        this.repository.deleteById(id);
    }

    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable String id) {
        return this.repository.existsById(id);
    }
}
