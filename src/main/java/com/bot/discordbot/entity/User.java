package com.bot.discordbot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "discord_bot_users")
@Component
@Data
@NoArgsConstructor
public class User {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "discord_id")
    private Long userDiscordId;

    @Column(name = "refresh_token_youtube")
    private String refreshTokenYouTube;

    @Column(name = "refresh_token_discord")
    private String refreshTokenDiscord;

    @Column(name = "role")
    private String role;

    @Column(name = "active")
    private Boolean active;

    public User(Long userDiscordId,  String refreshTokenDiscord, String role, Boolean active) {
        this.userDiscordId = userDiscordId;
        this.refreshTokenDiscord = refreshTokenDiscord;
        this.role = role;
        this.active = active;
    }
}
