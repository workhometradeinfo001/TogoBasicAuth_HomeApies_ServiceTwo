package org.togo.homeapies.controllers.auth_validation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.togo.homeapies.components.JwtUtils;

@RestController
@RequestMapping("/auth-validation")
@RequiredArgsConstructor
public class AuthValidationCheck {

    private final JwtUtils jwtUtils;

    @GetMapping
    public ResponseEntity<HttpStatus> checkValidationCode(@RequestParam String token){
        boolean b = jwtUtils.validateToken(token);
        if (b){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
