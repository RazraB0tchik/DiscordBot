package com.bot.discordbot.filters;

import com.bot.discordbot.entity.DiscordTokens;
import com.bot.discordbot.entity.User;
import com.bot.discordbot.exceptions.AuthException;
import com.bot.discordbot.exceptions.BadRequest;
import com.bot.discordbot.services.OauthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokensHandler extends OncePerRequestFilter {

    @Autowired
    FilterProvider filterProvider;

    @Autowired
    OauthService oauthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       try{
            DiscordTokens discordTokens = filterProvider.getRefreshToken(request.getCookies());
            if (discordTokens != null && filterProvider.getAccessToken(request) && filterProvider.checkAccess(discordTokens)) {
                filterProvider.authUser(discordTokens);
            }
        }
        catch (BadRequest badRequest){
            response.setStatus(400);
            response.getWriter().write(badRequest.getMessage());
        }
       catch (AuthException authException){
           response.setStatus(401);
           response.getWriter().write(authException.getMessage());
       }
      filterChain.doFilter(request, response);
    }
}
