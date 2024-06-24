package com.example.online_store_1.configuration;

import com.example.online_store_1.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public TokenFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader!=null&&authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
            try{
                username=jwtUtil.getTokenAndData(token).getUsername();
            }catch (ExpiredJwtException e){
                System.out.println(e.getMessage());
            }catch (SignatureException s){
                System.out.println(s.getMessage());
            }
        }
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    jwtUtil.getRoles(token).stream().map(SimpleGrantedAuthority::new).toList()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request,response);
    }
}
