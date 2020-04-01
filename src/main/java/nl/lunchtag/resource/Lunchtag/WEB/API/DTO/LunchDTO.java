package nl.lunchtag.resource.Lunchtag.WEB.API.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class LunchDTO {
    private String name;
    private Date date;
}
