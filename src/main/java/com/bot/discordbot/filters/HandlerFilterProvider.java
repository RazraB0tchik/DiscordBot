//package com.bot.discordbot.filters;
//
//import com.bot.discordbot.entity.User;
//import com.bot.discordbot.entity.UserDetailsElem;
//import com.bot.discordbot.repositories.DiscordTokenRepository;
//import com.bot.discordbot.repositories.UserRepository;
//import com.bot.discordbot.services.UserService;
//import jakarta.servlet.http.Cookie;
//import org.apache.tomcat.util.descriptor.web.ContextHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//@Component
//public class HandlerFilterProvider {
//
//    @Autowired
//    DiscordTokenRepository discordTokenRepository;
//
//    @Autowired
//    UserService userService;
//
//    public boolean checkRefToken(Cookie[] cookies, String fingerprint) {
//            String refresh = null;
//            for (Cookie el : cookies) {
//                if (el.getName().equals("user_data")) {
//                    refresh = el.getValue();
//                    break;
//                }
//            }
//        return discordTokenRepository.getDiscordRefreshTokenByFingerprint(fingerprint).equals(refresh);
//    }
//
//    public void authUser(String fingerprint){
//        User user = discordTokenRepository.getDiscordRefreshTokenByFingerprint(fingerprint).getUser();
//        UserDetails userDetails = userService.loadUserByUsername(String.valueOf(user.getUserDiscordId()));
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
//}
