package com.bot.discordbot.services.youtube;

import com.bot.discordbot.controllers.MainWebSocketController;
import com.bot.discordbot.listeners.tools.CommandsList;
import jakarta.servlet.ServletContext;
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
public class YouTubeOathProvider {
    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest httpServletRequest;

//    public void createNewUser(Map<String, String> tokens){
//        userService.saveNewUser(CommandsList.discordId, tokens.get("refresh_token"));
//        UserDetails userDetails = userService.loadUserByUsername(CommandsList.discordId);
//        Authentication authenticationUser = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authenticationUser);
//
//    }

    public void checkUserOnActive(){

    }

}
