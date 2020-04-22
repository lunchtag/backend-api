package nl.lunchtag.resource.Lunchtag.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Data
public class LunchDTO {
    private Date date;
}
