package com.bot.discordbot.listeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateCommunityUpdatesChannelEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.springframework.stereotype.Component;

@Component
public class JoinListener extends ListenerAdapter {

//    @Override
//    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
//       if(event.getMember().getUser().getId().equals(event.getJDA())){
//          AudioChannelUnion audioChannelUnion = event.getChannelJoined();
//
//        }
//    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getAuthor().isBot()){
            System.out.println("is bot");
        }
        Guild guild = event.getGuild();
        System.out.println("Пользователь: " + event.getAuthor().getName());
        System.out.println("Кеш: " + event.getChannel().getJDA().getUserCache().getElementById(event.getAuthor().getId()));

        System.out.println(event.getGuild().getVoiceChannels());
        VoiceChannel voiceChannel = event.getChannel().asVoiceChannel();

            if(voiceChannel!=null && event.getMessage().getContentRaw().equals("start")){
                System.out.println("Канал: " + voiceChannel.getName());
                AudioManager audio = guild.getAudioManager();
//                audio.setSendingHandler();
                audio.openAudioConnection(voiceChannel);
            }
        }

    }

//    @Override
//    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
//        super.onGuildMemberJoin(event);
//    }
