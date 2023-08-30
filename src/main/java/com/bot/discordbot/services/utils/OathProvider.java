package com.bot.discordbot.services.utils;

import com.bot.discordbot.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
//import Spring Session!
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OathProvider {
    @Autowired
    UserService userService;

    public void registryNewUser(Map<String, String> tokens){
//        userService.saveNewUser(CommandsList.discordId, tokens.get("refresh_token")); //?
//        UserDetails userDetails = userService.loadUserByUsername(CommandsList.discordId);
//        Authentication authenticationUser = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authenticationUser);

    }

    public void addYoutubeToken(){

    }

    public void checkUserOnActive(){

    }

}
