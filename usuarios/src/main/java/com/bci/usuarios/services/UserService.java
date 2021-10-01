package com.bci.usuarios.services;

import com.bci.usuarios.dto.UserRequestDTO;
import com.bci.usuarios.dto.UserResponseDTO;


public interface UserService {

    UserResponseDTO save(UserRequestDTO userRequest) throws Exception;

}
