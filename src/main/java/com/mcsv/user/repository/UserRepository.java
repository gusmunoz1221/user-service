package com.mcsv.user.repository;

import com.mcsv.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,String> {

    boolean existsByEmail(String email);
}
