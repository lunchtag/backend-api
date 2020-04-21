package nl.lunchtag.resource.Lunchtag.logic;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Optional;

@Component
public class AccountLogic {
    private final AccountService accountService;

    @Autowired
    public AccountLogic(AccountService accountService) {
        this.accountService = accountService;
    }

    public Integer generatePincode() {
        SecureRandom random = new SecureRandom();

        Integer newPincode = random.nextInt(100000);
        String formatted = String.format("%05d", newPincode);

        return Integer.parseInt(formatted);
    }
}
