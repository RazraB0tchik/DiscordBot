package com.bot.discordbot.services;

import com.bot.discordbot.dto.Configs;
import com.bot.discordbot.entity.DiscordRefreshToken;
import com.bot.discordbot.exceptions.BadAuthCode;
import com.bot.discordbot.exceptions.DiscordTokensNotFound;
import com.bot.discordbot.exceptions.UserAlreadyExists;
import com.bot.discordbot.repositories.DiscordTokenRepository;
import com.bot.discordbot.repositories.UserRepository;
import com.bot.discordbot.services.utils.OauthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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

    @Autowired
    DiscordTokenRepository discordTokenRepository;

    public Map<String, String> generateAllTokens(String code, URL url, Configs oauthDTO, String fingerprint){

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

                  if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser"){
                    Map <String, String> userInfo = discordService.getUserInfo(tokensInfo.get("access_token"));
                    if(userRepository.getUserByUserDiscordId(Long.parseLong(userInfo.get("id"))) == null) {
                        oauthProvider.registryNewUser(tokensInfo, userInfo, fingerprint);
                        return tokensInfo;
                    }
                    else{
                        throw new UserAlreadyExists("User with id - " + userRepository.getUserByUserDiscordId(Long.parseLong(userInfo.get("id"))) + "already exist!");
                    }
                }
            }
            else{
                throw new BadAuthCode("Bad auth code, response having status: " + httpURLConnection.getResponseCode());
            }

        } catch (IOException | BadAuthCode | UserAlreadyExists e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateAccessToken(String fingerprint) throws DiscordTokensNotFound {
        DiscordRefreshToken discordRefreshToken = discordTokenRepository.getDiscordRefreshTokenByFingerprint(fingerprint);
        if(discordRefreshToken != null){
//            if()
        }
        else {
            throw new DiscordTokensNotFound("Discord tokens not found, user not exist!");
        }
    }
}
