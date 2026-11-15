package fr.volkan.back.security.jwt;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Extrait le token et demande sa validation.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // On récupère l'autorisation du header de la requête
        String authorization = request.getHeader("Authorization");

        // Si on n'a pas d'autorisation ou si on n'a pas le "Bearer", on ne laisse pas passer
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.substring(7);

        try {

            // Si l'utilisateur n'existe pas dans le contexte, on le créé
            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                //On récupère le username du token
                String username = jwtService.extractUsername(token);

                // On récupère l'utilisateur à partir du username
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Si le token est valide
                if (jwtService.isTokenValid(token, userDetails)) {

                    // On crée l'authenticationToken qu'on mettra dans le context
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // On le complète avec les infos venant de la requête
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // On injecte l'authenticationToken dans le context
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (JwtException | IllegalArgumentException | UsernameNotFoundException e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);

    }
}
