package com.bot.discordbot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_meta")
@Data
@NoArgsConstructor
public class Meta {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "discord_logo_id")
    private String userDiscordLogo;

    @Column(name = "discord_username")
    private String userDiscordName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    public Meta(String userDiscordLogo, String userDiscordName, User user) {
        this.userDiscordLogo = userDiscordLogo;
        this.userDiscordName = userDiscordName;
        this.user = user;
    }
}
