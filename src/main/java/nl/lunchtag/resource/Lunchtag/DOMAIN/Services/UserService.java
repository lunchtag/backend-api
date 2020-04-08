package nl.lunchtag.resource.Lunchtag.DOMAIN.Services;

import nl.lunchtag.resource.Lunchtag.DAL.Repository.AccountRepository;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final AccountRepository accountRepository;

    @Autowired
    public UserService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> findUserByEmail(String email) {
        return this.accountRepository.findAccountByEmail(email);
    }

    public Account createOrUpdate(Account account) {
        return this.accountRepository.save(account);
    }
}
