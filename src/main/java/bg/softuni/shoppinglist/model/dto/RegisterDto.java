package bg.softuni.shoppinglist.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    private String username;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    private String password;

    private String confirmPassword;

    @Email(message = "Please enter a valid email address")
    private String email;

}
