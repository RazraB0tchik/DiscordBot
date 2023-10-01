package com.bot.discordbot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Entity
@Component
@Table(name = "users_discord_token")
@Data
@NoArgsConstructor
public class DiscordTokens {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fingerprint")
    private String fingerprint;

    @Column(name = "refresh_token_discord")
    private String refreshTokenDiscord;

    @Column(name = "access_dead_time")
    private Date accessTokenDeadTime;

    @Column(name = "active_time")
    private Date generateTime;

    @Column(name = "dead_time")
    private Date deadTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public DiscordTokens(String fingerprint, String refreshTokenDiscord, Date accessTokenDeadTime, Date generateTime, Date deadTime, User user) {
        this.fingerprint = fingerprint;
        this.refreshTokenDiscord = refreshTokenDiscord;
        this.accessTokenDeadTime = accessTokenDeadTime;
        this.generateTime = generateTime;
        this.deadTime = deadTime;
        this.user = user;
    }
}
