package com.bci.usuarios.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
@Setter
@Getter
@Builder
@AllArgsConstructor
public class UserRequestDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Pattern(regexp ="^(.+)@(.+)$",message="El email no cumple con la validación de forma")
    @NotNull
    private String email;
    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Pattern(regexp ="((?=.*\\d{2})(?=.*[a-z])(?=.*[A-Z])(^[a-zA-Z0-9]{1,}$))" , message="El password no cumple con la validación de forma")
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PhoneRequestDTO> phones;

}
