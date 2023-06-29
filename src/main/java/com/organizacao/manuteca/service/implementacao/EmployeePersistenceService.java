package com.organizacao.manuteca.service.implementacao;

import com.organizacao.manuteca.dto.EmployeeDTO;
import com.organizacao.manuteca.exception.EmailExistException;
import com.organizacao.manuteca.model.condominium.EmployeeEntity;
import com.organizacao.manuteca.repository.condominium.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeePersistenceService {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeePersistenceService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity register(EmployeeDTO employeeDTO) throws EmailExistException {
        EmployeeEntity employee = employeeRepository.findByEmail(employeeDTO.getEmail());
        if (employee != null){
            throw new EmailExistException("EMAIL_ALREADY_EXISTS");
        } else {
            employee.setName(employeeDTO.getName());
            employee.setContact(employeeDTO.getContact());
            employee.setEmail(employeeDTO.getEmail());
            employeeRepository.save(employee);
            return employee;
        }
    }

    public EmployeeEntity update(EmployeeDTO employeeDTO){
        EmployeeEntity employee = employeeRepository.getById(employeeDTO.getId());
        if (employee == null){
            throw new UsernameNotFoundException("USER_NOT_FOUND");
        }else {
            employee.setEmail(employeeDTO.getEmail());
            employee.setName(employeeDTO.getName());
            employee.setContact(employeeDTO.getContact());
            employeeRepository.save(employee);
            return employee;
        }
    }

    public List<EmployeeEntity> getEmployee(){
        return employeeRepository.findAll();
    }

    public EmployeeEntity findById(Long id){
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("EMPLOYEE NOT EXIST"));
    }
}
