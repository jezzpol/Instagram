package com.Futkaradze.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class jwtValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstant.JWT_HEADER); // получает JWT токен из заголовка запроса. Заголовок должен иметь имя, указанное в константе JwtConstant.JWT_HEADER.

        if (jwt != null) {

            try {

                String email = JwtProvider.getEmailFromJwtToken(jwt); // JwtProvider.getEmailFromJwtToken(jwt) проверяет валидность токена и извлекает из него email пользователя. Если токен недействителен, то метод выбросит исключение Exception.

                List<GrantedAuthority> authorities = new ArrayList<>(); //  создает пустой список прав доступа, который будет использоваться для создания объекта Authentication.

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities); // создает объект Authentication с указанным email и пустым списком прав доступа.

                SecurityContextHolder.getContext().setAuthentication(authentication); // устанавливает объект Authentication в контекст безопасности приложения.

            } catch (Exception e) {

                throw new BadCredentialsException("Invalid token …");

            }
        }

        filterChain.doFilter(request, response); // передает запрос дальше по цепочке фильтров.

    }
}