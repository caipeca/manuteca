package com.organizacao.manuteca.repository;

import com.organizacao.manuteca.model.condominium.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Long> {

    UserEntity findUserByUsername(String username);
}
