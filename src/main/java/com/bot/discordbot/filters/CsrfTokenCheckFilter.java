package com.bot.discordbot.filters;

import com.bot.discordbot.configs.SpringSecurityConfigs;
import com.bot.discordbot.listeners.tools.CustomCsrfToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CsrfTokenCheckFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //при каждом подключении проверяем жив ли csrf? время жизни 24 часа, если помер, то генерим новый! при обмене сообзениями не проверяем csrf

        if(request.getHeader("Connection").equals("keep-alive")){
            String csrfTokenRequest =  request.getHeader("X-CSRF-TOKEN");
            if(csrfTokenRequest == null){
                CsrfToken newCsrfToken = SpringSecurityConfigs.httpSessionCsrfTokenRepository.generateToken(request);
                response.addHeader(newCsrfToken.getHeaderName(), newCsrfToken.getToken());
            }
            else{
                CsrfToken tokenForCheck = new CustomCsrfToken(csrfTokenRequest, "X-CSRF-TOKEN");
                tokenForCheck.getToken();
            }
        }
        filterChain.doFilter(request, response);
    }
}
