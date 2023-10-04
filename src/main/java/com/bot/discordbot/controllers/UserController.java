package com.bot.discordbot.controllers;

import com.bot.discordbot.controllers.utils.BaseUserSerialize;
import com.bot.discordbot.entity.User;
import com.bot.discordbot.repositories.DiscordTokenRepository;
import com.bot.discordbot.repositories.MetaRepository;
import com.bot.discordbot.repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    DiscordTokenRepository discordTokenRepository;

    @GetMapping("/getUser")
    public ResponseEntity<BaseUserSerialize> getTestUserInfo(HttpServletResponse httpServletResponse){
        String fingerprint = httpServletResponse.getHeader("fingerprint");
        User user = discordTokenRepository.getDiscordRefreshTokenByFingerprint(fingerprint).getUser();
        return ResponseEntity.ok(new BaseUserSerialize(user.getUserDiscordId()));
    }

}
