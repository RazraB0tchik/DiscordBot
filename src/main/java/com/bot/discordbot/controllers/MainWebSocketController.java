package com.bot.discordbot.controllers;

import com.bot.discordbot.dto.OauthCode;
import com.bot.discordbot.services.youtube.YouTubeOauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainWebSocketController {

    @Autowired
    YouTubeOauth youTubeOauth;

    @MessageMapping(value = "/getAuth")
    public void simpleTest(@RequestBody OauthCode oauthCode){
        youTubeOauth.generateTokens(oauthCode.getCode());
    }

}
