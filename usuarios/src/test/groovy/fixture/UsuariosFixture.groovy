package fixture

import com.bci.usuarios.dto.PhoneRequestDTO
import com.bci.usuarios.dto.UserRequestDTO
import com.bci.usuarios.entities.Phone
import com.bci.usuarios.entities.User

import java.time.LocalDateTime

class UsuariosFixture {

    static  User getUserEntity(){
        return User.builder().
                id("").
                name("Angel").
                email("angel.gonzalez@gmail.com").
                password("Bci.2021").
                phones(getPhoneList()).
                dateOfCreation(LocalDateTime.now()).
                dateOfModified(LocalDateTime.now()).
                lastLogin(LocalDateTime.now()).
                token("123ljhlk2ij13").
                isActive(true).
                build()
    }

    static List <Phone> getPhoneList(){
        List <Phone> phones  = new ArrayList<Phone>()
        phones.add(Phone.builder().countrycode("56").citycode("9").number("93091060").build())
        return phones

    }

    static getUserRequestDTO(){
        return  UserRequestDTO.builder().
                name("Angel").
                email("angel.gonzalez@gmail.com").
                password("Bci.2021").
                phones(getPhoneDTOList()).
                build()
    }
    static getPhoneDTOList(){
        List <PhoneRequestDTO> phones  = new ArrayList<PhoneRequestDTO>()
        phones.add(PhoneRequestDTO.builder().countrycode("56").citycode("9").number("976876860").build())
        phones.add(PhoneRequestDTO.builder().countrycode("56").citycode("9").number("453651258").build())
        return phones;
    }
}
