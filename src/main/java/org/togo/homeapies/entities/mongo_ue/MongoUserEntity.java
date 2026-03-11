package org.togo.homeapies.entities.mongo_ue;

import lombok.*;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "togo_user_database")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityScan
public class MongoUserEntity {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String numCountryCode;
    private List<String> role;

}
