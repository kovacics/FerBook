package hr.fer.ppp.ferbook.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.fer.ppp.ferbook.api.rest.services.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class TokenCreateAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private UsersService usersService;

    public TokenCreateAuthenticationFilter(AuthenticationManager authenticationManager, UsersService usersService) {
        this.authenticationManager = authenticationManager;
        this.usersService = usersService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        UsernamePasswordAuthenticationToken authenticationToken = null;

        try {
            LoginModel credentials = new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);
            authenticationToken = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException {

        CustomUserDetails user = (CustomUserDetails) authResult.getPrincipal();
        String rolesString = user.getAuthorities().toString();
        String role = rolesString.substring("[ROLE_".length(), rolesString.length() - 1); //extracting role

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", usersService.getUserByUsername(user.getUsername()).getID())
                .withClaim("role", role)
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));

        // stavlja token u response body
        response.getWriter().write(token);
    }
}
