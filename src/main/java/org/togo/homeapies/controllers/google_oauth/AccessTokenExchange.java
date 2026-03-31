package org.togo.homeapies.controllers.google_oauth;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togo.homeapies.records.access_token.EmailForNewAccessToken;
import org.togo.homeapies.services.get_new_gat.ExchangeNewAToken;

@RestController
@RequestMapping("/exchange-at")
@RequiredArgsConstructor
public class AccessTokenExchange {

    private static final Logger log = LoggerFactory.getLogger(AccessTokenExchange.class);
    private final ExchangeNewAToken newAToken;

    @PostMapping
    public String exchangingToken(@RequestBody EmailForNewAccessToken emailForNewAccessToken){
        try{
            String email = emailForNewAccessToken.email();
            return newAToken.getNewAccessToken(email);
        }catch (Exception e){
            log.error("Throwing a exception!", e);
            return null;
        }
    }

}
