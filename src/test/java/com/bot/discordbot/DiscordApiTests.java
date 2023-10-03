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
       String refreshToken = "NtMFXDIK1CDL7v3leFD0MVf3lvM1F";
       Map<String, String> test_result = discordService.updateAccessToken(refreshToken);
       for(Map.Entry <String, String> elem : test_result.entrySet()){
           System.out.println(elem.getKey() + elem.getValue());
       }
       assert !test_result.isEmpty() : "Тут где то ошибка";
   }

}
