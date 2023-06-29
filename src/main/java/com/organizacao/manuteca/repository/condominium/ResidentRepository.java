package com.organizacao.manuteca.repository.condominium;

import com.organizacao.manuteca.model.condominium.ResidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<ResidentEntity, Long> {

    ResidentEntity findByEmail(String email);
}
