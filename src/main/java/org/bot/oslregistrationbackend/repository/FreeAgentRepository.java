package org.bot.oslregistrationbackend.repository;

import org.bot.oslregistrationbackend.entity.FreeAgent;
import org.bot.oslregistrationbackend.entity.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FreeAgentRepository extends CrudRepository<FreeAgent, Long> {
    Optional<FreeAgent> findFreeAgentByPlayer_DiscordId(long id);
    void deleteFreeAgentByPlayer_DiscordId(long id);

    boolean existsFreeAgentByPlayer_DiscordId(long id);
}
