package com.bot.discordbot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "user_youtube_token")
@Component
@Data
@NoArgsConstructor
public class YouTubeRefreshTokens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "youtube_token")
    private String refreshYoutubeToken;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public YouTubeRefreshTokens(String refreshYoutubeToken) {
        this.refreshYoutubeToken = refreshYoutubeToken;
    }
}
