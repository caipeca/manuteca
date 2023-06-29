package com.organizacao.manuteca.service;

import com.organizacao.manuteca.dto.UtilizadorDTO;
import com.organizacao.manuteca.exception.EmailExistException;
import com.organizacao.manuteca.exception.UsernameExistException;
import com.organizacao.manuteca.model.condominium.UtilizadorEntity;
import com.organizacao.manuteca.repository.UtilizadorRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
@Qualifier("utilizadorPersistenceService")
public class UtilizadorPersistenceService  {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private UtilizadorRepository utilizadorRepository;
    private LoginAttemptService loginAttemptService;
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UtilizadorPersistenceService(UtilizadorRepository utilizadorRepository, BCryptPasswordEncoder passwordEncoder, LoginAttemptService loginAttemptService) {
        this.utilizadorRepository = utilizadorRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginAttemptService = loginAttemptService;
    }

    /*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtilizadorEntity utilizador = utilizadorRepository.findUserByUsername(username);
        if (utilizador == null){
            LOGGER.error("NO_USER_FOUND_NY_USER_NAME" + username);
            throw new UsernameNotFoundException("NO_USER");
        }else{
            validateLoginAttempt(utilizador);
            utilizador.setLastLoginDateDisplay(utilizador.getLastLoginDate());
            utilizador.setLastLoginDate(new Date());
            utilizadorRepository.save(utilizador);

            UserPrincipal utilizadorPr = new UserPrincipal(utilizador);
            LOGGER.info(username);
            return utilizadorPr;
        }
    }


     */
    private void validateLoginAttempt(UtilizadorEntity utilizador) {
        if (utilizador.isNotLocked()){
            if (loginAttemptService.hasExceededMaxAttempts(utilizador.getUsername())){
                utilizador.setNotLocked(false);
            }
        }else{
            loginAttemptService.evictUserFromLoginAttemptCache(utilizador.getUsername());
        }
    }


    public UtilizadorEntity register(UtilizadorDTO utilizadorDTO) throws EmailExistException, UsernameExistException {
        validateNewUsernameAndEmail(StringUtils.EMPTY,utilizadorDTO.getUsername(),utilizadorDTO.getEmail());
        UtilizadorEntity utilizador = new UtilizadorEntity();


        return null;
    }


    public List<UtilizadorEntity> getUsers() {
        return utilizadorRepository.findAll();
    }


    public UtilizadorEntity findUserByUsername(String username) {
        return utilizadorRepository.findUserByUsername(username);
    }


    public UtilizadorEntity findUserByEmail(String email) {
        return null;
    }


    public UtilizadorEntity addNewUser() throws IOException {
        return null;
    }


    public UtilizadorEntity updateUser() throws IOException {
        return null;
    }


    public void deleteUser(long id) {
        utilizadorRepository.deleteById(id);
    }


    public void resetPassword(String email) throws MessagingException {

    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateUserid() {
        return RandomStringUtils.randomNumeric(10);
    }

    private UtilizadorEntity validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UsernameExistException, EmailExistException {
        UtilizadorEntity userByNewUsername = findUserByUsername(newUsername);
        UtilizadorEntity userByNewEmail = findUserByEmail(newEmail);
        if (StringUtils.isNotBlank(currentUsername)){
            UtilizadorEntity currentUser = findUserByUsername(currentUsername);
            if (currentUser == null){
                throw new UsernameNotFoundException("NO_USER_FOUND_BY_USER_NAME" + currentUsername);
            }
            if (userByNewUsername != null && currentUser.getId().equals(userByNewUsername.getId())){
                throw new UsernameExistException("USERNAME_ALREADY_EXISTS");
            }
            if (userByNewEmail != null && currentUser.getId().equals(userByNewEmail.getId())){
                throw new EmailExistException("EMAIL_ALREADY_EXISTS");
            }
            return currentUser;
        } else {
            if (userByNewUsername != null ){
                throw new UsernameExistException("USERNAME_ALREADY_EXISTS");
            }
            if (userByNewEmail != null ){
                throw new EmailExistException("EMAIL_ALREADY_EXISTS");
            }
            return null;
        }
    }
}
