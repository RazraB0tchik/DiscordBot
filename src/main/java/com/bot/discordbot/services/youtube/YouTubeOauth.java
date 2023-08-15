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

import static com.bot.discordbot.configs.YouTubeConfigs.secret;

@Service
public class YouTubeOauth {

    private String authJson;

    private StringBuilder tokens;


    public void generateTokens(String code){

        System.out.println(code);

//            authJson = "{\"code\"= \"" + code + "\"," + "\"client_id=\" \"" + secret.getClient_id() + "\","
//                    + "\"client_secret=\" \"" + secret.getClient_secret() + "\"," + "\"redirect_uri=\"" +
//                    secret.getRedirect_uris().get(0) + "\"," + "\"grant_type=\""+ "\"authorization_code\"";

        authJson = "code="+code+"&"+"client_id="+secret.getClient_id()+"&"+"client_secret="+secret.getClient_secret()+"&"+
                "redirect_uri="+secret.getRedirect_uris()+"&"+"grant_type=authorization_code";

        System.out.println(authJson);

        try {
            URL authUrl = new URL("https://oauth2.googleapis.com/token HTTP/1.1");
            HttpURLConnection httpURLConnection = (HttpURLConnection) authUrl.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Host", "www.googleapis.com");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setDoOutput(true);


            byte[] bytes = authJson.getBytes();
            httpURLConnection.getOutputStream().write(bytes, 0, bytes.length);
            httpURLConnection.connect();
          //  objectMapper.writeValue(new File("src/main/resources/code.json"), authJson);


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

//    public void authUser(){
//        try {
//            URL url = new URL(urlSt);
//
//            System.out.println(url);
//
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
//            int response = connection.getResponseCode();
//
//            System.out.println("Response code: " + response);
//
//            if(response == HttpURLConnection.HTTP_OK){
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream())); //читает данные из открытого соединения
//                String inputLine;
//                StringBuilder stringBuilder = new StringBuilder();
//
//                while ((inputLine = bufferedReader.readLine()) != null){
//                    stringBuilder.append(inputLine);
//                }
//
//                System.out.println(stringBuilder);
//            }
//
//
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
