package com.bot.discordbot.configs;

import com.bot.discordbot.dto.DiscordConfigsDTO;
import com.bot.discordbot.dto.YouTbConfigsDTO;
import com.bot.discordbot.listeners.CommandsListener;
import com.bot.discordbot.listeners.JoinListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URL;

@Configuration
public class MainBotConfigs {

    @Autowired
    CommandsListener commandsListener;

    @Autowired
    JoinListener joinListener;

    public static DiscordConfigsDTO secretDiscord;
    public static JDA jda;

    public static String authUrlYoutube;
    public static YouTbConfigsDTO secretYoutube;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public void initDiscordConfigs(){
        URL configJSON = MainBotConfigs.class.getResource("/client_secret_discord.json");
        try {
            secretDiscord = objectMapper.readValue(configJSON, DiscordConfigsDTO.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Bean
    public void initYoutubeConfigs(){
        URL configJSON = MainBotConfigs.class.getResource("/client_secret_youtube.json");
        try {

            secretYoutube = objectMapper.readValue(configJSON, YouTbConfigsDTO.class);

            authUrlYoutube = String.format("https://accounts.google.com/o/oauth2/v2/auth?" +
                    "scope=%s"+
                    "&access_type=offline" +
                    "&include_granted_scopes=true" +
                    "&state=state_parameter_passthrough_value" +
                    "&redirect_uri=" + "%s" +
                    "&response_type=code" +
                    "&prompt=select_account" +
                    "&client_id="+ "%s","https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fyoutube.readonly", secretYoutube.getClient_secret(), secretYoutube.getClient_id());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public void createJDA() {
        jda=JDABuilder.createDefault(secretDiscord.getBot_token())
                .addEventListeners(commandsListener)
                .addEventListeners(joinListener)
                .enableIntents(GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.MESSAGE_CONTENT)
                .enableCache(CacheFlag.VOICE_STATE)
                .setMemberCachePolicy(MemberCachePolicy.VOICE)
                .build();
        jda.updateCommands().addCommands(
                Commands.slash("play", "This command can start playing you music!")
                        .addOption(OptionType.STRING, "trek_name", "Music name", true, true),
                Commands.slash("auth", "This command can authorize you in YouTube")
        ).queue();

    }



}
