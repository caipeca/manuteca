package com.organizacao.manuteca.controller;

import com.organizacao.manuteca.dto.UserDTO;
import com.organizacao.manuteca.exception.EmailExistException;
import com.organizacao.manuteca.exception.UsernameExistException;
import com.organizacao.manuteca.model.UserPrincipal;
import com.organizacao.manuteca.model.condominium.UserEntity;
import com.organizacao.manuteca.service.UserPersistenceService;
import com.organizacao.manuteca.utility.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static com.organizacao.manuteca.constant.SecurityConstant.JWT_TOKEN_HEADER;

@RestController
@RequestMapping(path = { "/", "/user"})
public class UserController {

    public static final String EMAIL_SENT = "An email with a new password was sent to: ";
    public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
    private AuthenticationManager authenticationManager;
    private UserPersistenceService userPersistenceService;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserPersistenceService userPersistenceService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userPersistenceService = userPersistenceService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody UserEntity user){
        this.authenticate(user.getUsername(),user.getPassword());
        UserEntity userLogin = userPersistenceService.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(userLogin);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(userLogin, jwtHeader, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserDTO userDTO) throws EmailExistException, UsernameExistException {
        UserEntity newUser = userPersistenceService.register(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
        return headers;
    }
    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
