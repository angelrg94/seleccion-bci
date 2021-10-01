package com.bci.usuarios.controllers;

import javax.validation.Valid;

import com.bci.usuarios.dto.UserRequestDTO;
import com.bci.usuarios.dto.UserResponseDTO;
import com.bci.usuarios.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @PostMapping("/user")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) throws Exception {
        log.info("Se procede al llamado del metodo POST : /user");
         return new ResponseEntity<>(userService.save(userRequestDTO), HttpStatus.CREATED);
       }


}