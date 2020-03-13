package nl.lunchtag.resource.Lunchtag.WEB.API.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {

    private String username;
    private String password;
    private String repeatedpassword;
    private String name;
    private String lastname;
    private String email;
}
