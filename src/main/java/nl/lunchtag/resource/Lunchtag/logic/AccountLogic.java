package nl.lunchtag.resource.Lunchtag.logic;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.models.AccountUpdateDTO;
import nl.lunchtag.resource.Lunchtag.service.AccountService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.UUID;

@Component
public class AccountLogic {
    private final AccountService accountService;
    private final PasswordHelper passwordHelper;

    @Autowired
    public AccountLogic(AccountService accountService, PasswordHelper passwordHelper) {
        this.accountService = accountService;
        this.passwordHelper = passwordHelper;
    }

    public Integer generatePincode() {
        SecureRandom random = new SecureRandom();

        Integer newPincode = random.nextInt(100000);
        String formatted = String.format("%04d", newPincode);

        return Integer.parseInt(formatted);
    }

    public Boolean isPincodeMatch(Integer pincode, Account user) {
        if(passwordHelper.isMatch(Integer.toString(pincode), user.getPincode())){
            return true;
        }

        return false;
    }

    public Optional<Account> findById(UUID accountId) {
        return this.accountService.findById(accountId);
    }

    public Boolean disableAccount(UUID accountId) {
        Optional<Account> account = this.findById(accountId);

        boolean nonLocked = true;

        if(account.isPresent()) {
            if(account.get().getIsNonLocked()) {
                nonLocked = false;
            }

            account.get().setIsNonLocked(nonLocked);
            this.accountService.createOrUpdate(account.get());

            return true;
        }

        return false;
    }

    public Account updateUser(UUID accountId, AccountUpdateDTO accountUpdateDTO) {
        Optional<Account> foundAccount = this.findById(accountId);

        if(foundAccount.isPresent()) {
            if(accountUpdateDTO.getPassword() != null) {
                foundAccount.get().setPassword(passwordHelper.hash(Jsoup.parse(accountUpdateDTO.getPassword()).text()));
            }

            if(accountUpdateDTO.getFirstName() != null) {
                foundAccount.get().setName(Jsoup.parse(accountUpdateDTO.getFirstName()).text());
            }

            if(accountUpdateDTO.getLastName() != null) {
                foundAccount.get().setLastName(Jsoup.parse(accountUpdateDTO.getLastName()).text());
            }

            if(accountUpdateDTO.getPincode() != null) {
                foundAccount.get().setPincode(passwordHelper.hash(Jsoup.parse(Integer.toString(accountUpdateDTO.getPincode())).text()));
            }

            this.accountService.createOrUpdate(foundAccount.get());

            return foundAccount.get();
        }

        return null;
    }
}
