package nl.lunchtag.resource.Lunchtag.service;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.respository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> findAccountByEmail(String email) {
        return this.accountRepository.findAccountByEmail(email);
    }

    public Account createOrUpdate(Account account) {
        return this.accountRepository.save(account);
    }

    public Optional<Account> findByPincode(Integer pincode) {
        return this.accountRepository.findByPincode(pincode);
    }

}
