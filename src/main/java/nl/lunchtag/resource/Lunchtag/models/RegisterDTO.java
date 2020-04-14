package nl.lunchtag.resource.Lunchtag.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDTO {

    @NotEmpty(message = "Please provide: email")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Please provide: password")
    private String password;

    @NotEmpty(message = "Please provide: firstName")
    private String firstName;

    @NotEmpty(message = "Please provide: lastName")
    private String lastName;
}
