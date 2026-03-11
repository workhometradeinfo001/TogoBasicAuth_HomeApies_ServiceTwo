package org.togo.homeapies.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GlobalRepo<T, ID> extends JpaRepository<T, ID> {
}
