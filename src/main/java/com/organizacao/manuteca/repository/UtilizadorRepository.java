package com.organizacao.manuteca.repository;

import com.organizacao.manuteca.model.condominium.UtilizadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizadorRepository extends JpaRepository<UtilizadorEntity, Long> {

    UtilizadorEntity findUserByUsername(String username);
}
