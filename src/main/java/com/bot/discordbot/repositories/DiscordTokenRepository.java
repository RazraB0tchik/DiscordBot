package com.bot.discordbot.repositories;

import com.bot.discordbot.entity.DiscordTokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscordTokenRepository extends JpaRepository<DiscordTokens, Integer> {
     DiscordTokens getDiscordRefreshTokenById(int id);

     DiscordTokens getDiscordTokensByRefreshTokenDiscord(String refreshToken);
     DiscordTokens getDiscordRefreshTokenByFingerprint(String fingerprint);

}
