package com.bot.discordbot.filters;

import com.bot.discordbot.entity.DiscordTokens;
import com.bot.discordbot.entity.User;
import com.bot.discordbot.exceptions.AuthException;
import com.bot.discordbot.exceptions.BadRequest;
import com.bot.discordbot.repositories.DiscordTokenRepository;
import com.bot.discordbot.repositories.UserRepository;
import com.bot.discordbot.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class FilterProvider {

    @Autowired
    DiscordTokenRepository discordTokenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    public DiscordTokens getRefreshToken(Cookie[] cookies) throws BadRequest {
            String refresh = null;
            for (Cookie el : cookies) {
                if (el.getName().equals("user_data")) {
                    refresh = el.getValue();
                    break;
                }
            }
            if(refresh == null){
                throw new BadRequest("Cookies are damaged");
            }
            else
                return discordTokenRepository.getDiscordTokensByRefreshTokenDiscord(refresh);
    }

    public void authUser(DiscordTokens discordTokens){
        User user = discordTokens.getUser();
        UserDetails userDetails = userService.loadUserByUsername(String.valueOf(user.getUserDiscordId()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public boolean getAccessToken(HttpServletRequest httpServletRequest) throws BadRequest {
        String accessToken = httpServletRequest.getHeader("Authorization");
        if(accessToken != null && accessToken.startsWith("Bearer_")){
            return true;
        }
        else{
            throw new BadRequest("Access token absent");
        }
    }

    public boolean checkAccess(DiscordTokens discordTokens) throws AuthException {
        if (discordTokens.getAccessTokenDeadTime().getTime() >= (new Date()).getTime()){
            throw new AuthException("Access token is not valid");
        }
        else
            return true;
    }

}
