package com.mcsv.user.user_service.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoRequest {

    @NotBlank(message = "Debe agregar un nombre")
    @Size(max = 20, message = "El nombre no puede tener mas de 20 caracteres")
    private String name;

    @NotBlank(message = "Debe agregar el correo electronico")
    @Pattern(
            regexp = "^[A-Za-z0-9]+([._%+-]?[A-Za-z0-9]+)*@[A-Za-z0-9]+([.-]?[A-Za-z0-9]+)*(\\.[A-Za-z]{2,6})$",
            message = "El correo no es válido"
    )
    private String email;

    @NotBlank(message = "Debe agregar una descripcion")
    @Size(max = 100, message = "la descripcion debe ser menor a 100 caracteres")
    private String description;
}
