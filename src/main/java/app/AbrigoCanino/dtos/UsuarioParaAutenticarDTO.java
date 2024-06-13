package app.AbrigoCanino.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioParaAutenticarDTO {
    @NotBlank(message = "O nome deve conter letrar e ou numeros")
    private String username;
    @NotBlank(message = "a senha deve conter letrar e ou numeros")
    private String password;
    private String role;
}
