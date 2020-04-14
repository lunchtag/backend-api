package nl.lunchtag.resource.Lunchtag.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private UUID id;
    private String name;
}
