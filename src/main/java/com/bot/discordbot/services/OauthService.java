package com.bot.discordbot.services;

import com.bot.discordbot.dto.ConfigsDTO;
import com.bot.discordbot.errors.BadAuthCode;
import com.bot.discordbot.services.utils.OathProvider;
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

import static com.bot.discordbot.configs.MainBotConfigs.secretYoutube;


@Service
public class OauthService {
    private String authParams;
    private String updateParams;
    private StringBuilder tokens = new StringBuilder();

    @Autowired
    OathProvider oathProvider;

    public void generateAllTokens(String code, URL url, ConfigsDTO oauthDTO){

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
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while (bufferedReader.read() != -1) {
                    tokens.append(bufferedReader.readLine());
                }
                bufferedReader.close();

                Map<String, String> authResult = new HashMap<>();
                for(String elem: tokens.toString().split(",")){
                    String[] elemMap = elem.split(":");
                    authResult.put(elemMap[0].replaceAll("\"", "").trim(), elemMap[1].replaceAll("\"", "").trim());
                }

                if(SecurityContextHolder.getContext().getAuthentication() == null){

                }

//                youTubeOathProvider.createNewUser(authResult);
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
