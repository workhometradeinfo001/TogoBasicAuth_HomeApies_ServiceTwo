package org.togo.homeapies.components;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.togo.homeapies.records.access_token.UserRecordFromOAuth;
import org.togo.homeapies.services.mysql_user_service.MysqlUserService;

import javax.crypto.SecretKey;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {
    // IMPORTANT: Store this securely in application.properties or environment variables
    private static final String JWT_SECRET = "amd82197_alamin_first_app_jwt_secret";
    private final MysqlUserService userService;
    @Value("${GoogleOAuth.googleClientId}")
    private String gClientId;
    private UserRecordFromOAuth userRecord;
    // Create the SecretKey once to reuse it
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public boolean validateToken(String token) {
        try {
            if (!"google".equals(token.substring(0, 6))) {
                Jwts.parser()
                        .verifyWith(getSigningKey())
                        .build()
                        .parseSignedClaims(token);// New: replaced parseClaimsJws
            }else {
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    String cleanToken = token.substring(6);
                    String url = UriComponentsBuilder.fromUri(URI.create("https://www.googleapis.com/oauth2/v3/tokeninfo"))
                            .queryParam("access_token", cleanToken)
                            .toUriString();
                    ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
                    Map<String, Object> body = response.getBody();
                    String aud = (String) body.get("aud");
                    userRecord = new UserRecordFromOAuth((String) body.get("email"));
                    return gClientId.equals(aud);

                } catch (HttpClientErrorException e) {
                    System.err.println("Response Body: " + e.getResponseBodyAsString());
                    return false;
                } catch (Exception e) {
                    // Catches network issues or null pointers
                    e.printStackTrace();
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            log.error("Validation fail!", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()        // New: replaced getBody()
                .getSubject();               // Standard "sub" claim
    }

    public Long getUserIdFromToken(String token) {
        if (!"google".equals(token.substring(0, 6))){
            Claims claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            if(claims.get("mongoUserId") != null){
                Object obj = claims.get("mongoUserId");
                if (obj instanceof Integer integer){
                    return integer.longValue();
                }else if(obj instanceof Long lng){
                    return lng;
                }
            }
            if (claims.get("userId") != null){
                Object obj = claims.get("userId");
                if (obj instanceof Integer integer){
                    return integer.longValue();
                }else if(obj instanceof Long lng){
                    return lng;
                }
            }
        }else {
            return userService.getUserIdFromMysql(userRecord.email());
        }
        return null;
    }
}
