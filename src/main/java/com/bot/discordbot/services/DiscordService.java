package com.bot.discordbot.services;

import com.bot.discordbot.exceptions.BadRequest;
import com.bot.discordbot.services.utils.OauthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
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

    public void updateAccessToken(){

    }

//    public Map<String, String> generateUrl() throws MalformedURLException {
//        UUID state = UUID.randomUUID();
//        String redirectLink = "&state=" + state;
//        Map<String, String> urlParams = Map.of("url", redirectLink, "state", state.toString());
//        return urlParams;
//    }

}
