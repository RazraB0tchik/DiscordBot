//package com.bot.discordbot.configs;
//
//import com.vk.api.sdk.client.TransportClient;
//import com.vk.api.sdk.client.VkApiClient;
//import com.vk.api.sdk.client.actors.GroupActor;
//import com.vk.api.sdk.client.actors.UserActor;
//import com.vk.api.sdk.exceptions.ApiException;
//import com.vk.api.sdk.exceptions.ClientException;
//import com.vk.api.sdk.httpclient.HttpTransportClient;
//import com.vk.api.sdk.objects.UserAuthResponse;
//import com.vk.api.sdk.objects.users.User;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//@Component
//public class VkConfig {
//
//    @Value("${vk.id}")
//    private Integer appId;
//
//    @Value("${vk.secret}")
//    private String secretKey;
//
//    private String authKey;
//
//
//
//    public VkConfig(String authKey) {
//        this.authKey = authKey;
//    }
//
//    public VkConfig() {
//    }
//
//    public String getAuthKey() {
//        return authKey;
//    }
//
//    public void setAuthKey(String authKey) {
//        this.authKey = authKey;
//    }
//
//    private static TransportClient transportClient = new HttpTransportClient();
//    public VkApiClient myApi = new VkApiClient(transportClient);
//
//    public VkApiClient getMyApi() {
//        return myApi;
//    }
//
//    public void setMyApi(VkApiClient myApi) {
//        this.myApi = myApi;
//    }
//
//    public UserActor authUserWithOAuth2() throws ClientException, ApiException {
//        UserAuthResponse userAuthResponse = myApi.oAuth().userAuthorizationCodeFlow(appId, authKey, "http://localhost:5173/", "audio").execute();
//
//        UserActor actor = new UserActor(userAuthResponse.getUserId(), userAuthResponse.getAccessToken());
//
//        return actor;
//    }
//}
