package eu.epitech.java.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {
    private static final String ERROR_MSG = "Error while parsing JSON authentication data";

    public AuthFilter() {
    }

    static private class AuthPLY {
        public String username;
        public String password;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse resp) throws AuthenticationException {
        try {
            StringBuilder buf = new StringBuilder();
            BufferedReader fd = req.getReader();
            try {
                String current;
                while ((current = fd.readLine()) != null) {
                    buf
                            .append(current)
                            .append('\n');
                }
            } finally {
                fd.close();
            }
            ObjectMapper mapper = new ObjectMapper();
            AuthPLY payload = mapper.readValue(buf.toString(), AuthPLY.class);
            if (payload.username == null) {
                payload.username = "";
            }
            if (payload.password == null) {
                payload.password = "";
            }
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(payload.username, payload.password);
            this.setDetails(req, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException ex) {
            System.out.println(ERROR_MSG);
            throw new InternalAuthenticationServiceException(ERROR_MSG, ex);
        }
    }
}
