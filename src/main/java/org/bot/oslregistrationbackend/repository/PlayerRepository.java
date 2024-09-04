package org.bot.oslregistrationbackend.repository;

import org.bot.oslregistrationbackend.entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
