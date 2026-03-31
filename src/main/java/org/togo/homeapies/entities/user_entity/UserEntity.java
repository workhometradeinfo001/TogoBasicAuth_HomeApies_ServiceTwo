package org.togo.homeapies.entities.user_entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.togo.homeapies.entities.AbstractUserEntity;
import org.togo.homeapies.entities.news_feed_entity.nf_post_entity.NFPostEntity;
import org.togo.homeapies.entities.user_entity.usub_entity.*;
import java.util.List;

@Entity
@Table(name = "user_details_table")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends AbstractUserEntity<Long> {

    @OneToOne(mappedBy = "userIdFromMongo", cascade = CascadeType.ALL)
    private MUIdEntity muIdEntity;
    @OneToOne(mappedBy = "userFullName", cascade = CascadeType.ALL)
    private FullNameEntity fullName;
    @OneToOne(mappedBy = "userUserName", cascade = CascadeType.ALL)
    private UserNameEntity userName;
    @OneToOne(mappedBy = "userEmail", cascade = CascadeType.ALL)
    private EmailEntity email;
    @OneToOne(mappedBy = "userPassword", cascade = CascadeType.ALL)
    private PasswordEntity password;
    @OneToOne(mappedBy = "userMNumber", cascade = CascadeType.ALL)
    private MbNmbEntity mobileNumber;
    @OneToOne(mappedBy = "userCountryCode", cascade = CascadeType.ALL)
    private CountryCodeEntity countryCode;

    @OneToMany(mappedBy = "whoCreated")
    private List<NFPostEntity> nfPostEntity;

    @ElementCollection
    private List<String> role;

}
