package com.bot.discordbot.services;

import com.bot.discordbot.entity.*;
import com.bot.discordbot.repositories.DiscordTokenRepository;
import com.bot.discordbot.repositories.MetaRepository;
import com.bot.discordbot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DiscordTokenRepository discordTokenRepository;

    @Autowired
    MetaRepository metaRepository;

    private Collection<GrantedAuthority> authorities;

    public UserService(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String discordId) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserDiscordId(Long.parseLong(discordId));
        if(user==null){
            throw new UsernameNotFoundException("User with discordId: " + discordId + " not found!");
        }
        else{
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            UserDetailsElem userDetailsElem = new UserDetailsElem(discordId, "", authorities, user.getActive());
            return userDetailsElem;
        }
    }

    public void saveNewUser(String discordId, String refreshTokenDiscord, String fingerprint, String discordLogoId, String discordUsername){
        User user = new User(Long.parseLong(discordId), Roles.USER.toString(), true);
        DiscordRefreshToken discordRefreshToken = new DiscordRefreshToken(fingerprint, refreshTokenDiscord, new Date(), user);
        Meta userMeta = new Meta(discordLogoId, discordUsername, user);
        userRepository.save(user);
        discordTokenRepository.save(discordRefreshToken);
        metaRepository.save(userMeta);

    }
}
