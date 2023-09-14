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
public class DiscordRefreshToken {

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
    @JoinColumn(name = "user_id")
    private User user;

    public DiscordRefreshToken(String fingerprint, String refreshTokenDiscord, Date lastActiveTime, User user) {
        this.fingerprint = fingerprint;
        this.refreshTokenDiscord = refreshTokenDiscord;
        this.lastActiveTime = lastActiveTime;
        this.user = user;
    }
}
