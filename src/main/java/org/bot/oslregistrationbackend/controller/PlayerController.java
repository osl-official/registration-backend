package org.bot.oslregistrationbackend.controller;

import org.bot.oslregistrationbackend.entity.Player;
import org.bot.oslregistrationbackend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerRepository repository;

    @Autowired
    public PlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Player> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Player> findById(@PathVariable long id) {
        return repository.findById(id);
    }

    @PostMapping("/add")
    public Player save(@RequestBody Player player) {
        return repository.save(player);
    }

    @DeleteMapping("/delete")
    @Transactional
    public void remove(@RequestBody Player player) {
        repository.delete(player);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public void removeById(@PathVariable long id) {
        repository.deleteById(id);
    }

    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable long id) {
        return repository.existsById(id);
    }
}
