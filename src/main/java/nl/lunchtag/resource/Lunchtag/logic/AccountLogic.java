package nl.lunchtag.resource.Lunchtag.logic;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.UUID;

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
        String formatted = String.format("%04d", newPincode);

        return Integer.parseInt(formatted);
    }

    public Boolean isPincodeMatch(Integer pincode) {
        Optional<Account> foundAccount = this.accountService.findByPincode(pincode);

        return foundAccount.isPresent();
    }

    public Optional<Account> findById(UUID accountId) {
        return this.accountService.findById(accountId);
    }

    public Boolean disableAccount(UUID accountId) {
        Optional<Account> account = this.findById(accountId);

        if(account.isPresent()) {
            account.get().setIsNonLocked(false);
            this.accountService.createOrUpdate(account.get());

            return true;
        }

        return false;
    }
}
