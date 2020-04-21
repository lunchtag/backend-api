package nl.lunchtag.resource.Lunchtag.service;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.respository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public List<Account> getAllUsers() {
        return this.accountRepository.findAll();
    }

    public Account getUserById(UUID id){
        return this.accountRepository.getOne(id);
    }

    public void removeUserById(UUID id){
        this.accountRepository.deleteById(id);
    }

    public Account createOrUpdate(Account account) {
        return this.accountRepository.save(account);
    }
}
