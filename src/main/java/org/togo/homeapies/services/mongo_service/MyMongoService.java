package org.togo.homeapies.services.mongo_service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.togo.homeapies.d_t_o_s.user_dto.UserDto;
import org.togo.homeapies.entities.mongo_ue.MongoUserEntity;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyMongoService {

    private final MongoTemplate mongoTemplate;

    public UserDto isDataPresent(String email){
        Query query  = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        List<MongoUserEntity> mongoUserData = mongoTemplate.find(query, MongoUserEntity.class);
        if (!mongoUserData.isEmpty()){
            MongoUserEntity mongoUser = mongoUserData.get(0);
            return getUserDto(mongoUser);
        }
        return null;
    }
    private static @NonNull UserDto getUserDto(MongoUserEntity mongoUser) {
        UserDto userDto = new UserDto();
        userDto.setMonoUserId(mongoUser.getId());
        userDto.setEmail(mongoUser.getEmail());
        userDto.setUserName(mongoUser.getUsername());
        userDto.setFirstName(mongoUser.getFirstName());
        userDto.setLastName(mongoUser.getLastName());
        userDto.setPassword(mongoUser.getPassword());
        userDto.setPhoneNumber(mongoUser.getPhoneNumber());
        userDto.setNumCountryCode(mongoUser.getNumCountryCode());
        userDto.setRole(mongoUser.getRole());
        return userDto;
    }


}
