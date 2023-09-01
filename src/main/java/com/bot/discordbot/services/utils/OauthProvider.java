package com.bot.discordbot.services.utils;

import com.bot.discordbot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import Spring Session!
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class OauthProvider {

    @Autowired
    UserService userService;

    public void registryNewUser(Map<String, String> tokens, Map<String, String> userInfo, String fingerprint){
        userService.saveNewUser(userInfo.get("id"), tokens.get("refresh_token"), fingerprint, userInfo.get("avatar"), userInfo.get("username")); //?
        UserDetails userDetails = userService.loadUserByUsername(userInfo.get("id"));
        Authentication authenticationUser = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationUser);
    }

    public void addYoutubeToken(){

    }

    public void checkUserOnActive(){

    }

    public Map<String, String> getDataFromResponseApi(HttpURLConnection connection){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            Map<String, String> userInfo = new HashMap<>();
            while (reader.read() != -1){
                String[] line = reader.readLine().split(":");
                userInfo.put(line[0].replaceAll("\"", "").trim(), line[1].replaceAll("\"", "").trim());
            }
            return userInfo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
