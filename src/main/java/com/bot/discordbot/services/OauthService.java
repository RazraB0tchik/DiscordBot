package com.bot.discordbot.services;

import com.bot.discordbot.dto.ConfigsDTO;
import com.bot.discordbot.errors.BadAuthCode;
import com.bot.discordbot.repositories.UserRepository;
import com.bot.discordbot.services.utils.OauthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@Service
public class OauthService {
    private String authParams;
    private String updateParams;

    @Autowired
    OauthProvider oauthProvider;

    @Autowired
    DiscordService discordService;

    @Autowired
    UserRepository userRepository;

    public void generateAllTokens(String code, URL url, ConfigsDTO oauthDTO, String fingerprint){

        authParams = "code="+code+"&client_id="+oauthDTO.getClient_id()+"&client_secret="+oauthDTO.getClient_secret()+
                "&redirect_uri="+oauthDTO.getRedirect_url()+"&grant_type=authorization_code";

        byte[] bytes = authParams.getBytes();

        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Host", "www.googleapis.com");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.getOutputStream().write(bytes);
            httpURLConnection.connect();


            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){

                Map<String, String> tokensInfo = oauthProvider.getDataFromResponseApi(httpURLConnection);

                if(SecurityContextHolder.getContext().getAuthentication() == null){
                    Map <String, String> userInfo = discordService.getUserDiscordInfo(tokensInfo.get("access_token"));

                    if(userRepository.getUserByUserDiscordId(Long.parseLong(userInfo.get("id"))) == null){
                        oauthProvider.registryNewUser(tokensInfo, userInfo, fingerprint);
                    }

                }
            }
            else{
                throw new BadAuthCode("Bad auth code, response having status: " + httpURLConnection.getResponseCode());
            }

        } catch (IOException | BadAuthCode e) {
            throw new RuntimeException(e);
        }

    }

    public void updateAccessToken(){

    }
}
