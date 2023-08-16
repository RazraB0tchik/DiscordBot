package com.bot.discordbot.services.youtube;

import com.bot.discordbot.configs.YouTubeConfigs;
import com.bot.discordbot.dto.YouTbConfigsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.bot.discordbot.configs.YouTubeConfigs.secret;

@Service
public class YouTubeOauth {

    private String authParams;

    private String updateParams;
    private StringBuilder tokens = new StringBuilder();

    public void generateTokens(String code){

        authParams = "code="+code+"&client_id="+secret.getClient_id()+"&client_secret="+secret.getClient_secret()+
                "&redirect_uri="+secret.getRedirect_uris().get(0)+"&grant_type=authorization_code";

        byte[] bytes = authParams.getBytes();

        try {
            URL authUrl = new URL("https://oauth2.googleapis.com/token");
            HttpURLConnection httpURLConnection = (HttpURLConnection) authUrl.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Host", "www.googleapis.com");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.getOutputStream().write(bytes);
            httpURLConnection.connect();


            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while (bufferedReader.read() != -1){
                    tokens.append(bufferedReader.readLine());
                }
                bufferedReader.close();
            }
            else{
                System.out.println(httpURLConnection.getResponseCode());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
