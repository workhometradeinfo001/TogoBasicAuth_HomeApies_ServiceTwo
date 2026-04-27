package org.togo.homeapies.services.mysql_user_service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.togo.homeapies.d_t_o_s.user_dto.UserDto;
import org.togo.homeapies.entities.user_entity.UserEntity;
import org.togo.homeapies.entities.user_entity.usub_entity.*;
import org.togo.homeapies.repos.user_repo.mysql_repo.MysqlEmailRepo;
import org.togo.homeapies.repos.user_repo.mysql_repo.MysqlUserRepo;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlUserService {

    private final MysqlUserRepo mysqlUserRepo;
    private final MysqlEmailRepo mysqlEmailRepo;

    @Transactional
    public boolean insertDataSql(UserDto userDto){
        UserEntity user = new UserEntity();
        user.setRole(userDto.getRole());
        try{
            Optional<EmailEntity> isEmailExists = mysqlEmailRepo.findByEmail(userDto.getEmail());
            if (isEmailExists.isPresent()){
                return false;
            }else {
                PasswordEntity password = new PasswordEntity();
                UserNameEntity userName = new UserNameEntity();
                ccAndEmail(userDto, user);
                fullNameFun(userDto, user);
                mbNmbAndMuid(userDto, user);
                password.setPassword(userDto.getPassword());
                password.setUserPassword(user);
                user.setPassword(password);
                userName.setUserName(userDto.getUserName());
                userName.setUserUserName(user);
                user.setUserName(userName);
                mysqlUserRepo.save(user);
                return true;
            }
        }catch (Exception e){
            log.error("Data can't transfer!", e);
            e.printStackTrace();
            return false;
        }
    }
    public void mbNmbAndMuid(UserDto userDto, UserEntity user){
        MbNmbEntity mbNmb = new MbNmbEntity();
        MUIdEntity muId = new MUIdEntity();
        mbNmb.setMobileNumber(userDto.getPhoneNumber());
        mbNmb.setUserMNumber(user);
        user.setMobileNumber(mbNmb);
        muId.setMongoUserId(userDto.getMonoUserId());
        muId.setUserIdFromMongo(user);
        user.setMuIdEntity(muId);
    }
    public void ccAndEmail(UserDto userDto, UserEntity user){
        CountryCodeEntity countryCode = new CountryCodeEntity();
        EmailEntity email = new EmailEntity();
        countryCode.setCountryCode(userDto.getNumCountryCode());
        countryCode.setUserCountryCode(user);
        user.setCountryCode(countryCode);
        email.setEmail(userDto.getEmail());
        email.setUserEmail(user);
        user.setEmail(email);
    }
    public void fullNameFun(UserDto userDto, UserEntity user){
        FullNameEntity fullName = new FullNameEntity();
        fullName.setFirstName(userDto.getFirstName());
        fullName.setLastName(userDto.getLastName());
        fullName.setUserFullName(user);
        user.setFullName(fullName);
    }

    public Long getUserIdFromMysql(String email){
        return mysqlEmailRepo.findUserIdViaEmail(email);
    }

}
