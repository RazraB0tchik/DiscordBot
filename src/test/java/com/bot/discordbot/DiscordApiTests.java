package com.bot.discordbot;

import com.bot.discordbot.entity.DiscordTokens;
import com.bot.discordbot.repositories.DiscordTokenRepository;
import com.bot.discordbot.services.DiscordService;
import com.bot.discordbot.services.utils.OauthProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiscordApiTests {

    @Autowired
    DiscordService discordService;

    @Autowired
    DiscordTokenRepository discordTokenRepository;

   @Test
   void getRefreshTokenTest(){
       DiscordTokens discordTokens = discordTokenRepository.getDiscordRefreshTokenById(18);
       Map<String, String> test_result = discordService.updateAccessToken(discordTokens.getRefreshTokenDiscord());
       for(Map.Entry <String, String> elem : test_result.entrySet()){
           System.out.println(elem.getKey() + elem.getValue());
       }
       assert !test_result.isEmpty() : "Тут где то ошибка";
   }

}
