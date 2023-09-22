package com.bot.discordbot.repositories;

import com.bot.discordbot.entity.DiscordRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscordTokenRepository extends JpaRepository<DiscordRefreshToken, Integer> {
     DiscordRefreshToken getDiscordRefreshTokenById(int id);

     DiscordRefreshToken getDiscordRefreshTokenByFingerprint(String fingerprint);
}
