package nl.lunchtag.resource.Lunchtag.DOMAIN.Services;

import lombok.Getter;
import lombok.Setter;
import nl.lunchtag.resource.Lunchtag.DAL.Repository.LunchRepository;
import nl.lunchtag.resource.Lunchtag.DAL.Repository.AccountRepository;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Account;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Lunch;
import nl.lunchtag.resource.Lunchtag.WEB.API.DTO.LunchDTO;
import nl.lunchtag.resource.Lunchtag.WEB.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
@Getter
@Setter
public class AccountService {
    private AccountRepository accountRepository;
    private LunchRepository lunchRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, LunchRepository lunchRepository) {
        this.accountRepository = accountRepository;
        this.lunchRepository = lunchRepository;
    }

    public Account getAccountByID(long accountID) {
        return accountRepository.findById(accountID).orElseThrow(() -> new NotFoundException("Not found"));
    }

    public void addLunchDate(long userID, LunchDTO lunchDTO) {

    }

    public void removeLunch(long userID, long lunchID) {

    }

    public Set<Lunch> getAllLunches(long accountID) {
        return getAccountByID(accountID).getAllLunch();
    }

}
