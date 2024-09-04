package org.bot.oslregistrationbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bot.oslregistrationbackend.enums.League;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "teams")
public class Team {

    @Id
    @Column(length = 4, nullable = false, unique = true)
    private String teamID;

    @Column(nullable = false)
    private String teamName;

    @Enumerated(EnumType.STRING)
    private League league;

    public Team(String teamID, String teamName, League league) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.league = league;
    }
}
