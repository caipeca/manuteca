package com.organizacao.manuteca.service;

import com.organizacao.manuteca.dto.UserDTO;
import com.organizacao.manuteca.enumeration.Role;
import com.organizacao.manuteca.exception.EmailExistException;
import com.organizacao.manuteca.exception.UsernameExistException;
import com.organizacao.manuteca.model.UserPrincipal;
import com.organizacao.manuteca.model.condominium.EmployeeEntity;
import com.organizacao.manuteca.model.condominium.ResidentEntity;
import com.organizacao.manuteca.model.condominium.UserEntity;
import com.organizacao.manuteca.repository.UserRepository;
import com.organizacao.manuteca.service.implementacao.EmployeePersistenceService;
import com.organizacao.manuteca.service.implementacao.ResidentPersistenceService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

import static com.organizacao.manuteca.enumeration.Role.*;

@Service
@Transactional
@Qualifier("userPersistenceService")
public class UserPersistenceService implements UserDetailsService {


    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private UserRepository userRepository;

    private EmployeePersistenceService employeePersistenceService;
    private ResidentPersistenceService residentPersistenceService;
    private LoginAttemptService loginAttemptService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserPersistenceService(UserRepository userRepository, LoginAttemptService loginAttemptService, BCryptPasswordEncoder passwordEncoder, EmployeePersistenceService employeePersistenceService, ResidentPersistenceService residentPersistenceService) {
        this.userRepository = userRepository;
        this.loginAttemptService = loginAttemptService;
        this.passwordEncoder = passwordEncoder;
        this.employeePersistenceService = employeePersistenceService;
        this.residentPersistenceService = residentPersistenceService;
    }

    public UserEntity register(UserDTO userDTO) throws EmailExistException, UsernameExistException {
        validateNewUsernameAndEmail(StringUtils.EMPTY,userDTO.getUsername(),StringUtils.EMPTY);
        UserEntity user = new UserEntity();

        if (userDTO.getEmployeeId() != null || userDTO.getResidentId() ==null ){
            EmployeeEntity employee = employeePersistenceService.findById(userDTO.getEmployeeId());
            user.setEmployee(employee);
            user.setResident(null);
        }
        else {
            ResidentEntity resident = residentPersistenceService.findById(userDTO.getResidentId());
            user.setEmployee(null);
            user.setResident(resident);
        }


        String password = generatePassword();
        user.setUsername(userDTO.getUsername());
        user.setPassword(encodePassword(password));
        user.setActive(true);
        user.setNotLocked(true);
        user.setRole(ROLE_USER.name());
        user.setAuthorities(ROLE_USER.getAuthorities());
        userRepository.save(user);
        LOGGER.info("New user password: " + password);
        return user;
    }


    public UserEntity findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserByUsername(username);
        if (user == null){
            LOGGER.error("NO_USER_FOUND_NY_USER_NAME" + username);
            throw new UsernameNotFoundException("NO_USER");
        }else{
            validateLoginAttempt(user);
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);

            UserPrincipal userPr = new UserPrincipal(user);
            LOGGER.info(username);
            return userPr;
        }
    }

    private void validateLoginAttempt(UserEntity user) {
        if (user.isNotLocked()){
            if (loginAttemptService.hasExceededMaxAttempts(user.getUsername())){
                user.setNotLocked(false);
            }
        }else{
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }

    private UserEntity validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UsernameExistException, EmailExistException {
        UserEntity userByNewUsername = findUserByUsername(newUsername);
        if (StringUtils.isNotBlank(currentUsername)){
            UserEntity currentUser = findUserByUsername(currentUsername);
            if (currentUser == null){
                throw new UsernameNotFoundException("NO_USER_FOUND_BY_USER_NAME" + currentUsername);
            }
            if (userByNewUsername != null && currentUser.getId().equals(userByNewUsername.getId())){
                throw new UsernameExistException("USERNAME_ALREADY_EXISTS");
            }
            return currentUser;
        } else {
            if (userByNewUsername != null ){
                throw new UsernameExistException("USERNAME_ALREADY_EXISTS");
            }
            return null;
        }
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private Role getRoleEnumName(String role) {
        return valueOf(role.toUpperCase());
    }

}
