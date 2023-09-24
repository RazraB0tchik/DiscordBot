package com.bot.discordbot.controllers;

import com.bot.discordbot.configs.MainBotConfigs;
import com.bot.discordbot.controllers.utils.DiscordUserDataSerialize;
import com.bot.discordbot.dto.OauthCode;
import com.bot.discordbot.dto.UpdateData;
import com.bot.discordbot.services.DiscordService;
import com.bot.discordbot.services.OauthService;
import com.bot.discordbot.services.utils.CookieProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    OauthService oauthService;

    @Autowired
    DiscordService discordService;

    @Value("${url.google}")
    URL urlYoutube;

    @Value("${url.discord}")
    URL urlDiscord;

    @Autowired
    CookieProvider cookieProvider;

    @PostMapping(value = "/youtube_redirect")
    public void youtubeAuth(@RequestBody OauthCode oauthCode) {
        oauthService.generateAllTokens(oauthCode.getCode(), urlYoutube, MainBotConfigs.secretYoutube, oauthCode.getFingerprint());
//        return ResponseEntity.ok("success!");
    }

    @PostMapping(value = "/discord_redirect")
    public ResponseEntity<DiscordUserDataSerialize> discordAuth(@RequestBody OauthCode oauthCode, HttpServletResponse response) throws MalformedURLException {
        Map<String, String> tokensInfo = oauthService.generateAllTokens(oauthCode.getCode(), new URL(urlDiscord + "/oauth2/token"), MainBotConfigs.secretDiscord, oauthCode.getFingerprint());
        response.addCookie(cookieProvider.createNewCookie(tokensInfo));
        return ResponseEntity.ok(new DiscordUserDataSerialize(tokensInfo.get("access_token"), new Date().getTime()));
    }

    @PutMapping(value = "/update_access")
    public void updateAccess(HttpServletRequest httpServletRequest){

    }

    @GetMapping("/get_csrf")
    public CsrfToken getCsrfToken(CsrfToken csrfToken){
         return csrfToken;
    }
}
