package nl.lunchtag.resource.Lunchtag.models;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class pincodeDTO {
    @NotEmpty(message = "Please provide: email")
    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Please provide: pincode")
    private Integer pincode;
}
