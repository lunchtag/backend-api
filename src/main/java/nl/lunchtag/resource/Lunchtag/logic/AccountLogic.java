package nl.lunchtag.resource.Lunchtag.logic;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.service.AccountService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountLogic {
    private final AccountService accountService;

    @Autowired
    public AccountLogic(AccountService accountService) {
        this.accountService = accountService;
    }

    public Boolean generatePincode(Account account) {
        Optional<Account> foundAccount = this.accountService.findAccountByEmail(account.getEmail());

        if(foundAccount.isPresent()) {
            while (true) {
                Integer newPincode = RandomUtils.nextInt(1, 7);
                Optional<Account> pincodeCheck = this.accountService.findByPincode(newPincode);

                if(!pincodeCheck.isPresent()) {
                    foundAccount.get().setPincode(newPincode);
                    this.accountService.createOrUpdate(foundAccount.get());
                    return true;
                }
            }
        }

        return false;
    }
}
