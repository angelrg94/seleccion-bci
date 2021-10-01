package services

import com.bci.usuarios.dto.PhoneRequestDTO
import com.bci.usuarios.dto.UserRequestDTO
import com.bci.usuarios.dto.UserResponseDTO
import com.bci.usuarios.entities.Phone
import com.bci.usuarios.entities.User
import com.bci.usuarios.repository.UserRepository
import com.bci.usuarios.security.JwtTokenUtil
import com.bci.usuarios.services.UserServiceImpl
import fixture.UsuariosFixture
import spock.lang.Specification

import java.time.LocalDateTime

class UserServiceSpec extends Specification {
    private UserServiceImpl userService
    private UserRepository userRepository
    private JwtTokenUtil jwtTokenUtil

    def setup() {
        this.userRepository = Stub(UserRepository.class)
        this.jwtTokenUtil = Stub(JwtTokenUtil.class)
        this.userService= new UserServiceImpl(userRepository,jwtTokenUtil)
    }
    def "test 01- crear usuairo"() {
        given: "se ingresan los datos del usuario a registrar"
        this.userRepository.save(_)>>{ UsuariosFixture.getUserEntity()}

        this.jwtTokenUtil.generateToken(_)>>{"123ljhlk2ij13"}
        UserRequestDTO userRequestDTO = UsuariosFixture.getUserRequestDTO()

        when: "se invoca al registro del usuario"
        UserResponseDTO responseDTO=this.userService.save(userRequestDTO)

        then: "se evalua la respuesta del registro"
        responseDTO.getId()!=null
        responseDTO.getToken()!=null
        responseDTO.getName() == userRequestDTO.getName()
        responseDTO.getDateOfCreation()!=null
        responseDTO.getDateOfModified()!=null
        responseDTO.getLastLogin()!=null
        responseDTO.isActive()
    }
}
