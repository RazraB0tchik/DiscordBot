package com.bot.discordbot.services;

import com.bot.discordbot.configs.MainBotConfigs;
import com.bot.discordbot.configs.SpringSecurityConfigs;
import com.bot.discordbot.exceptions.BadRequest;
import com.bot.discordbot.services.utils.OauthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Service
public class DiscordService {

    @Value("${url.discord}")
    URL urlDiscord;

    @Autowired
    OauthProvider oauthProvider;


    public Map<String, String> getUserInfo(String accessToken){
        try {
            URL urlRequest = new URL(urlDiscord+"/users/@me");
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlRequest.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Authorization", "Bearer "+accessToken);
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return (oauthProvider.getDataFromResponseApi(httpURLConnection));
            }
            else {
                throw new BadRequest("The server cannot process the request, response having status: " + httpURLConnection.getResponseCode());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (BadRequest e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> updateAccessToken(String refreshToken){
        try {
            String updateParams = String.format("client_id=" +  "%s" +
                    "&client_secret=" + "%s" + "&grant_type=access_token" + "&refresh_token=" + "%s", MainBotConfigs.secretDiscord.getClient_id(),
                    MainBotConfigs.secretDiscord.getClient_secret(), refreshToken);

            byte bytes[] = updateParams.getBytes();

            URL urlRefreshRequest = new URL(urlDiscord + "/oauth2/token");
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlRefreshRequest.openConnection();
            httpURLConnection.setRequestProperty("Host", "www.googleapis.com");
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.getOutputStream().write(bytes);
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                return oauthProvider.getDataFromResponseApi(httpURLConnection);
            }
            else {
                throw new BadRequest("The server cannot process the request, response having status: " + httpURLConnection.getResponseCode());
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (BadRequest e) {
            throw new RuntimeException(e);
        }
    }

//    public Map<String, String> generateUrl() throws MalformedURLException {
//        UUID state = UUID.randomUUID();
//        String redirectLink = "&state=" + state;
//        Map<String, String> urlParams = Map.of("url", redirectLink, "state", state.toString());
//        return urlParams;
//    }

}
