package org.togo.homeapies.repos.user_repo.mysql_repo;

import org.springframework.stereotype.Repository;
import org.togo.homeapies.entities.user_entity.usub_entity.EmailEntity;
import org.togo.homeapies.repos.GlobalRepo;

import java.util.Optional;

@Repository
public interface MysqlEmailRepo extends GlobalRepo<EmailEntity, Long> {

    Optional<EmailEntity> findByEmail(String email);

}
