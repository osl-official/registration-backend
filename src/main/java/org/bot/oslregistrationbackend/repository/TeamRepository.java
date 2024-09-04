package org.bot.oslregistrationbackend.repository;

import org.bot.oslregistrationbackend.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Iterator;

@Repository
public interface TeamRepository extends CrudRepository<Team, String> {
    Iterable<Team> findAllByLeagueIsNull();
}
