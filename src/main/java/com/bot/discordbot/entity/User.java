package com.bot.discordbot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

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

    @Column(name = "role")
    private String role;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<DiscordRefreshToken> discordRefreshTokens;

    public User(Long userDiscordId, String role, Boolean active) {
        this.userDiscordId = userDiscordId;
        this.role = role;
        this.active = active;
    }
}
