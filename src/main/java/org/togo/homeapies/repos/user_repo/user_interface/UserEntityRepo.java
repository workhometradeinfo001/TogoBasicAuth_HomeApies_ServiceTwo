package org.togo.homeapies.repos.user_repo.user_interface;

import org.togo.homeapies.entities.user_entity.usub_entity.FullNameEntity;
import org.togo.homeapies.records.post_record.UserNameRecord;
import org.togo.homeapies.repos.GlobalRepo;

import java.util.Optional;

public interface UserEntityRepo extends GlobalRepo<FullNameEntity, Long> {
    Optional<UserNameRecord> findFirstNameAndLastNameByCreateBy(Long id);

}
