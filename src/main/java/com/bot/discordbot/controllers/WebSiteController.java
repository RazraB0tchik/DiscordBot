package com.bot.discordbot.controllers;

import com.bot.discordbot.dto.UserDiscordBot;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class WebSiteController{
        @GetMapping(value = "/getUserId")
        public void getUserId(@RequestParam UserDiscordBot userDiscordBot){
            System.out.println(userDiscordBot.getUserId());
        }

        @GetMapping(value = "/getText")
        public void getText(){
            System.out.println("asdas");
        }
}
