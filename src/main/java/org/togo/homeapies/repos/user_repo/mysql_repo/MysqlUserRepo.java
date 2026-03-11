package org.togo.homeapies.repos.user_repo.mysql_repo;

import org.springframework.stereotype.Repository;
import org.togo.homeapies.entities.user_entity.UserEntity;
import org.togo.homeapies.repos.GlobalRepo;
import java.util.Optional;

@Repository
public interface MysqlUserRepo extends GlobalRepo<UserEntity, Long> {

   Optional<UserEntity> findById(long id);

    @Override
    <S extends UserEntity> S save(S entity);
}
