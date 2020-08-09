package com.example.demo_be.controllers;

import com.example.demo_be.models.JwtUser;
import com.example.demo_be.models.requests.JwtAuthRequest;
import com.example.demo_be.models.responses.JwtAuthResponse;
import com.example.demo_be.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Operation(summary = "methodAutenticate")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            //todo: da implementare il dettaglio utente se serve esempio la gestione dei ruoli o gestire l'abilitazione/disabilitazione
            JwtUser userDetails = new JwtUser(authenticationRequest.getUsername(), "", new ArrayList<>(), true);
            String jwt = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new JwtAuthResponse(jwt));
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/refresh-token", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            String token = request.getHeader("Authorization");
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            if (jwtUtils.canTokenBeRefreshed(token)) {
                String refreshedToken = jwtUtils.refreshToken(token);
                response.setHeader("Authorization", refreshedToken);
                return ResponseEntity.ok(new JwtAuthResponse(refreshedToken));
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}

