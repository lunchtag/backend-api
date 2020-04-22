package nl.lunchtag.resource.Lunchtag.models;

import lombok.Data;
import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Lunch;

import java.util.List;

@Data
public class AccountLunchDTO {
    private Account account;
    private List<Lunch> lunches;
}
