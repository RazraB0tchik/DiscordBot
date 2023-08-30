package com.bot.discordbot.controllers;

import com.bot.discordbot.configs.MainBotConfigs;
import com.bot.discordbot.dto.OauthCode;
import com.bot.discordbot.services.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URL;

@Controller
public class MainWebSocketController {

    @Autowired
    OauthService oauthService;

    @Value("${url.google}")
    URL urlYoutube;

    @Value("${url.discord}")
    URL urlDiscord;

    @MessageMapping(value = "/youtubeAuthRedirect")
    public void youtubeAuth(@RequestBody OauthCode oauthCode){
        oauthService.generateAllTokens(oauthCode.getCode(), urlYoutube, MainBotConfigs.secretYoutube);
    }

    @MessageMapping(value = "/discordAuthRedirect")
    public void discordAuth(@RequestBody OauthCode oauthCode){
        oauthService.generateAllTokens(oauthCode.getCode(), urlDiscord, MainBotConfigs.secretDiscord);
    }

}
