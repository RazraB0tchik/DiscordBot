package com.bot.discordbot.controllers;

import com.bot.discordbot.dto.UserDiscordBot;
import net.dv8tion.jda.api.utils.SessionController;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSiteController{

    @MessageMapping("/hello")
    public void helloWorld(@RequestBody UserDiscordBot userDiscordBot){
        System.out.println(userDiscordBot.getUserId());
    }

}
