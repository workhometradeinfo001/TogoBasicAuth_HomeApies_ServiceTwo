package org.togo.homeapies.repos.user_repo.mysql_repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.togo.homeapies.entities.user_entity.usub_entity.EmailEntity;
import org.togo.homeapies.repos.GlobalRepo;
import java.util.Optional;

@Repository
public interface MysqlEmailRepo extends GlobalRepo<EmailEntity, Long> {

    @Query(value = "SELECT uemt.user_id FROM user_emails_table uemt WHERE uemt.email = ?1;", nativeQuery = true)
    Long findUserIdViaEmail(String email);

    Optional<EmailEntity> findByEmail(String email);

}
