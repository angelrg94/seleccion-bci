package com.bci.usuarios.services;

import com.bci.usuarios.dto.UserRequestDTO;
import com.bci.usuarios.dto.UserResponseDTO;
import com.bci.usuarios.entities.Phone;
import com.bci.usuarios.entities.User;
import com.bci.usuarios.repository.UserRepository;
import com.bci.usuarios.security.JwtTokenUtil;
import com.bci.usuarios.exceptions.DuplicateEmailException;
import com.bci.usuarios.exceptions.DuplicateUserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    /**
     * Este metodo guarda un usuario en la base de datos en memoria.
     * Valida si el usuario o el email existen y en caso positivo arroja excepciones para que sean manejadas por el ControllerAdvice
     * @param  userRequest  datos del usuario que se va a guardar
     */
    @Override
    public UserResponseDTO save(UserRequestDTO userRequest) throws Exception{
        log.info("Se procede a guardar el usuario en base de datos");
        User userSaved;
        if (isDuplicateEmail(userRequest.getEmail())) {

                if (isDuplicateUser(userRequest.getName())) {

                    User user = User.builder().name(userRequest.getName()).
                            email(userRequest.getEmail()).
                            password(Base64.getEncoder().encodeToString(userRequest.getPassword().getBytes(StandardCharsets.UTF_8))).
                            phones(   userRequest.
                                    getPhones().
                                    stream().
                                    map(phoneRequestDTO -> {return Phone.builder().
                                            citycode(phoneRequestDTO.getCitycode()).
                                            countrycode(phoneRequestDTO.getCountrycode()).
                                            number(phoneRequestDTO.getNumber()).build();
                                    }).collect(Collectors.toList())).
                            dateOfCreation(LocalDateTime.now()).
                            dateOfModified(LocalDateTime.now()).token(jwtTokenUtil.generateToken(userRequest)).
                            lastLogin(LocalDateTime.now()).password(userRequest.getPassword()).
                            isActive(true).
                            build();
                    userSaved = userRepository.save(user);
                    log.info("Usuario guardado exitosamente ");
                    return UserResponseDTO.builder().
                            id(userSaved.getId()).
                            name(userSaved.getName()).
                            dateOfCreation(userSaved.getDateOfCreation()).
                            dateOfModified(userSaved.getDateOfModified()).
                            lastLogin(userSaved.getLastLogin()).
                            token(userSaved.getToken()).
                            isActive(userSaved.isActive()).
                            build();
                } else {
                    log.info("Usuario existe, se arroja exception");
                    throw new DuplicateUserException();
                }
            } else {
                log.info("Email existe, se arroja exception");
                throw new DuplicateEmailException();
            }
    }

    /**
     * Este metodo valida si un email existe en la base de datos en memoria
     * @param  email el email a validar
     */
    private boolean isDuplicateEmail(String email) {

        int counter =userRepository.countByEmail(email);

        return counter <= 0;

    }

    /**
     * Este metodo valida si un nombre de usuario existe en la base de datos en memoria
     * @param  userName el usuario a validar
     */
    private boolean isDuplicateUser(String userName) {

        int counter=userRepository.countByName(userName);

        return counter <= 0;

    }
}
