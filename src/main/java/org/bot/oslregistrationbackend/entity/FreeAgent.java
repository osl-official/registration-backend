package org.bot.oslregistrationbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.bot.oslregistrationbackend.enums.League;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FreeAgent {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "discordID", referencedColumnName = "discordID")
    private Player player;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private League league;
}