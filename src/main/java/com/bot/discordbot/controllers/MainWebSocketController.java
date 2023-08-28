package com.bot.discordbot.controllers;

import com.bot.discordbot.dto.OauthCode;
import com.bot.discordbot.services.youtube.YouTubeOauthTokens;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainWebSocketController {

    @Autowired
    YouTubeOauthTokens youTubeOauth;
    @MessageMapping(value = "/getAuth")
    public void simpleTest(@RequestBody OauthCode oauthCode){
        youTubeOauth.generateAllTokens(oauthCode.getCode());
    }

}
