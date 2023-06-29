package com.organizacao.manuteca.repository.condominium;

import com.organizacao.manuteca.model.condominium.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    EmployeeEntity findByEmail(String email);
}
