package com.bot.discordbot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "users_youtube_token")
@Component
@Data
@NoArgsConstructor
public class YouTubeRefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "youtube_token")
    private String refreshYoutubeToken;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    public YouTubeRefreshToken(String refreshYoutubeToken, User user) {
        this.refreshYoutubeToken = refreshYoutubeToken;
        this.user = user;
    }
}
