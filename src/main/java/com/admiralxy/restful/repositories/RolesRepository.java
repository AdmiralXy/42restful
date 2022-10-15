package com.admiralxy.restful.repositories;

import com.admiralxy.restful.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RolesRepository extends JpaRepository<Role, Long> {
}
