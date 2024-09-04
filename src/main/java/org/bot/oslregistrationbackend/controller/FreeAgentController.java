package org.bot.oslregistrationbackend.controller;

import org.bot.oslregistrationbackend.entity.FreeAgent;
import org.bot.oslregistrationbackend.entity.Player;
import org.bot.oslregistrationbackend.enums.League;
import org.bot.oslregistrationbackend.repository.FreeAgentRepository;
import org.bot.oslregistrationbackend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/free-agent")
public class FreeAgentController {
    @Autowired
    private final FreeAgentRepository repository;
    @Autowired
    private final PlayerRepository playerRepository;

    public FreeAgentController(FreeAgentRepository repository, PlayerRepository playerRepository) {
        this.repository = repository;
        this.playerRepository = playerRepository;
    }

    @GetMapping()
    public Iterable<FreeAgent> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<FreeAgent> findById(@PathVariable long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.flatMap(p -> this.repository.findFreeAgentByPlayer_DiscordId(p.getDiscordId()));
    }

    @PostMapping
    public FreeAgent save(@RequestBody FreeAgent freeAgent) {
        return this.repository.save(freeAgent);
    }

    @PostMapping("/{id}/{league}")
    public FreeAgent save(@PathVariable long id, @PathVariable String league) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent()) {
            FreeAgent freeAgent = new FreeAgent();
            freeAgent.setPlayer(player.get());
            freeAgent.setLeague(League.valueOf(league));

            return this.repository.save(freeAgent);
        } else {
            throw new NullPointerException("Player not found with ID provided");
        }
    }

    @DeleteMapping
    @Transactional
    public void remove(@RequestBody FreeAgent freeAgent) {
        this.repository.delete(freeAgent);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void removeById(@PathVariable long id) {
        Optional<Player> player = playerRepository.findById(id);
        player.ifPresent(p -> this.repository.deleteFreeAgentByPlayer_DiscordId(p.getDiscordId()));
    }

    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.filter(p -> this.repository.existsFreeAgentByPlayer_DiscordId(p.getDiscordId())).isPresent();
    }
}
