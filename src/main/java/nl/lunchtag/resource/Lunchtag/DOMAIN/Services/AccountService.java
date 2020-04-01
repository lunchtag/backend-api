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


import java.util.List;
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

    public Lunch addLunchDate(long accountID, LunchDTO lunchDTO) {
        Lunch lunch = new Lunch();
        lunch.setDate(lunchDTO.getDate());
        Account account = getAccountByID(accountID);
        account.addLunch(lunch);
        lunchRepository.save(lunch);
        return lunch;
    }

    public void removeLunch(long userID, long lunchID) {
        Account account = getAccountByID(userID);
        account.removeLunchDayByID(lunchID);
    }

    public Set<Lunch> getAllLunchesByID(long accountID) {
        return getAccountByID(accountID).getAllLunch();
    }

    public List<Lunch> getALlLunches()
    {
        return lunchRepository.findAll();
    }

}
