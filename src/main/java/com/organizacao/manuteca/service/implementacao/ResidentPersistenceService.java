package com.organizacao.manuteca.service.implementacao;

import com.organizacao.manuteca.dto.ResidentDTO;
import com.organizacao.manuteca.exception.EmailExistException;
import com.organizacao.manuteca.model.condominium.ResidentEntity;
import com.organizacao.manuteca.repository.condominium.ResidentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidentPersistenceService {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private ResidentRepository repository;

    @Autowired
    public ResidentPersistenceService(ResidentRepository repository) {
        this.repository = repository;
    }

    public ResidentEntity register(ResidentDTO residentDTO) throws EmailExistException {
        ResidentEntity resident = repository.findByEmail(residentDTO.getEmail());
        if (resident  != null){
            throw new EmailExistException("EMAIL_ALREADY_EXISTS");
        } else {
            resident.setBi(residentDTO.getBi());
            resident.setName(residentDTO.getName());
            resident.setEmail(residentDTO.getEmail());
            repository.save(resident);
            return resident;
        }
    }

    public ResidentEntity findById(Long id){
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("RESIDENT DOESNT EXIST"));
    }
}
