package org.togo.homeapies.entities.user_entity.usub_entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.togo.homeapies.entities.AbstractGlobalEntity;
import org.togo.homeapies.entities.user_entity.UserEntity;

@Entity
@Table(name = "user_country_code_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryCodeEntity extends AbstractGlobalEntity<Long> {

    private String countryCode;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userCountryCode;

}
