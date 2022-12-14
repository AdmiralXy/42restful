package com.admiralxy.restful.repositories;

import com.admiralxy.restful.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UsersRepository extends JpaRepository<User, Long> {
}
