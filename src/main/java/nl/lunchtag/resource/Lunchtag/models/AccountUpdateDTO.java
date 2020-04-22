package nl.lunchtag.resource.Lunchtag.models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AccountUpdateDTO {
    private String password;

    @Size(min = 4, max = 4)
    private Integer pincode;

    private String firstName;
    private String lastName;
}
