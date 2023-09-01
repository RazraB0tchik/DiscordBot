package com.bot.discordbot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Entity
@Component
@Table(name = "users_tokens")
@Data
@NoArgsConstructor
public class DiscordRefreshTokens {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fingerprint")
    private String fingerprint;

    @Column(name = "refresh_token_discord")
    private String refreshTokenDiscord;

    @Column(name = "active_time")
    private Date lastActiveTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public DiscordRefreshTokens(String fingerprint, String refreshTokenDiscord, Date lastActiveTime) {
        this.fingerprint = fingerprint;
        this.refreshTokenDiscord = refreshTokenDiscord;
        this.lastActiveTime = lastActiveTime;
    }
}
