package org.bot.oslregistrationbackend.repository;

import org.bot.oslregistrationbackend.entity.TeamPlayer;
import org.bot.oslregistrationbackend.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamPlayerRepository extends CrudRepository<TeamPlayer, Long> {
    Optional<Iterable<TeamPlayer>> findAllByTeam_TeamID(String id);
    Optional<TeamPlayer> findTeamPlayerByPlayer_DiscordId(long id);
    void deleteTeamPlayerByPlayer_DiscordId(long id);
    boolean existsTeamPlayerByPlayer_DiscordId(long id);
}
