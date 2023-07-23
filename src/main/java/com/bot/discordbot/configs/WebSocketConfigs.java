package com.bot.discordbot.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigs implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); //брокер перенаправит сообщения содержащие /topic всем подписанным клиентам
        registry.setApplicationDestinationPrefixes("/app"); //методы будут срабаотывать только в том случае, если есть префикс (/app/hello)
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/music_giga_bot").setAllowedOrigins("http://localhost:5173").withSockJS();; //конечная точка, по которой клиенты будут подключаться с сервером
    }
}
