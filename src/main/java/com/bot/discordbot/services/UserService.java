package com.bot.discordbot.services;

import com.bot.discordbot.entity.Roles;
import com.bot.discordbot.entity.User;
import com.bot.discordbot.entity.UserDetailsElem;
import com.bot.discordbot.repositories.UserRepository;
import net.dv8tion.jda.api.requests.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static org.hibernate.cfg.AvailableSettings.USER;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

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

    public void saveNewUser(String discordId, String refreshTokenDiscord){
        User user = new User(Long.parseLong(discordId), refreshTokenDiscord, Roles.USER.toString(), true);
        userRepository.save(user);
    }
}
