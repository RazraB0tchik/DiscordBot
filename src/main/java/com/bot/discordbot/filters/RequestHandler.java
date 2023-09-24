//package com.bot.discordbot.filters;
//
//import com.bot.discordbot.entity.DiscordRefreshToken;
//import com.bot.discordbot.exceptions.BadRequest;
//import com.bot.discordbot.repositories.DiscordTokenRepository;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.CookieValue;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//@Component
//public class RequestHandler extends OncePerRequestFilter {
//
//    @Autowired
//    HandlerFilterProvider handlerFilterProvider;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        Cookie[] cookies = request.getCookies();
//        if (cookies!=null && request.getParameter("print")!=null) {
//            if(handlerFilterProvider.checkRefToken(cookies,request.getParameter("print"))){
//                handlerFilterProvider.authUser(request.getParameter("print"));
//            }
//            else{
//                throw new BadRequest("Incorrect data in cookie!");
//            }
//        }
//        else {
//            throw new BadRequest("Cookie or request parameters incorrect!");
//        }
//    }
//}
