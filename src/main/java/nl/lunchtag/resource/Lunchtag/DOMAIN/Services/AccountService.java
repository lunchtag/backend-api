package nl.lunchtag.resource.Lunchtag.DOMAIN.Services;

import lombok.Getter;
import lombok.Setter;
import nl.lunchtag.resource.Lunchtag.DAL.Repository.LunchRepository;
import nl.lunchtag.resource.Lunchtag.DAL.Repository.AccountRepository;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Account;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Lunch;
import nl.lunchtag.resource.Lunchtag.WEB.API.DTO.LunchDTO;
import nl.lunchtag.resource.Lunchtag.WEB.API.DTO.UserDTO;
import nl.lunchtag.resource.Lunchtag.WEB.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;
import java.util.UUID;

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

//HEAD
    public Account register(UserDTO userDTO) {
        Account account = new Account();
        account.setName(userDTO.getName());
        account.setAccountID(userDTO.getId());
        account.register(account);
        accountRepository.save(account);

        return account;
    }

    public Account getAccountByID(long accountID) {
=======
    public Account getAccountByID(UUID accountID) {
>>>>>>> dbb14d693a9b985c044efbca6b0a032cae618aca
        return accountRepository.findById(accountID).orElseThrow(() -> new NotFoundException("Not found"));
    }

    public Lunch addLunchDate(UUID accountID, LunchDTO lunchDTO) {
        Lunch lunch = new Lunch();
        lunch.setDate(lunchDTO.getDate());
        Account account = getAccountByID(accountID);
        account.addLunch(lunch);
        lunchRepository.save(lunch);
        return lunch;
    }

    public void removeLunch(UUID userID, long lunchID) {
        Account account = getAccountByID(userID);
        account.removeLunchDayByID(lunchID);
    }

    public Set<Lunch> getAllLunchesByID(UUID accountID) {
        return getAccountByID(accountID).getLunches();
    }

    public List<Lunch> getALlLunches() {
        return lunchRepository.findAll();
    }

}
