package com.bot.discordbot.services.youtube;

import com.bot.discordbot.listeners.tools.CommandsList;
import com.bot.discordbot.repositories.UserRepository;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void createNewUser(Map<String, String> tokens){
        userService.saveNewUser(CommandsList.discordId, tokens.get("refresh_token"));
        UserDetails userDetails = userService.loadUserByUsername(CommandsList.discordId);
        Authentication authenticationUser = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationUser);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal().toString());
    }

    public void checkUserOnActive(){

    }

}
