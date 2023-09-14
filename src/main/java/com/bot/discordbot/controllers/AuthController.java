package com.bot.discordbot.controllers;

import com.bot.discordbot.configs.MainBotConfigs;
import com.bot.discordbot.dto.OauthCode;
import com.bot.discordbot.services.OauthService;
import com.bot.discordbot.services.utils.CookieProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    OauthService oauthService;

    @Value("${url.google}")
    URL urlYoutube;

    @Value("${url.discord}")
    URL urlDiscord;

    @Autowired
    CookieProvider cookieProvider;

    @PostMapping(value = "/youtubeAuthRedirect")
    public void youtubeAuth(@RequestBody OauthCode oauthCode) {
        oauthService.generateAllTokens(oauthCode.getCode(), urlYoutube, MainBotConfigs.secretYoutube, oauthCode.getFingerprint());
//        return ResponseEntity.ok("success!");
    }

    @PostMapping(value = "/discordAuthRedirect")
    public DiscordUserDataSerialize discordAuth(@RequestBody OauthCode oauthCode, HttpServletResponse response) throws MalformedURLException {
        Map<String, String> userInfo = oauthService.generateAllTokens(oauthCode.getCode(), new URL(urlDiscord + "/oauth2/token"), MainBotConfigs.secretDiscord, oauthCode.getFingerprint());
        response.addCookie(cookieProvider.createNewCookie(userInfo));
        return new DiscordUserDataSerialize(userInfo.get("access_token"), new Date().getTime());
    }
}
