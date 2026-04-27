package org.togo.homeapies.services.get_new_gat;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExchangeNewAToken {

    @Value("${GoogleOAuth.googleClientId}")
    private String googleClientId;
    @Value("${GoogleOAuth.googleClientSecret}")
    private String googleClientSecret;
    private final MongoTemplate mongoTemplate;

    public String getNewAccessToken(String email){
        Query query = new Query(Criteria.where("email").is(email.trim()));
        List<Document> documents = mongoTemplate.find(query, Document.class, "refresh_token");
        if (documents.isEmpty()){
            return null;
        }else {
            Document document = documents.get(0);
            Object refreshToken = document.get("refresh_token");
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://oauth2.googleapis.com/token";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("client_id", googleClientId);
            map.add("client_secret", googleClientSecret);
            map.add("refresh_token", refreshToken.toString());
            map.add("grant_type", "refresh_token");
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            try {
                ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
                // This will return a new access_token (and usually NOT a new refresh_token)
                assert response.getBody() != null;
                return (String) response.getBody().get("access_token");
            } catch (NullPointerException n) {
                // Handle case where refresh token is revoked or expired
                return n.getMessage();
            }
        }
    }

}
