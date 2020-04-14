package nl.lunchtag.resource.Lunchtag.logic;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.models.LunchDTO;
import nl.lunchtag.resource.Lunchtag.service.AccountService;
import nl.lunchtag.resource.Lunchtag.service.LunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class LunchLogic {

    private final LunchService lunchService;
    private final AccountService accountService;

    @Autowired
    public LunchLogic(LunchService lunchService, AccountService accountService) {
        this.lunchService = lunchService;
        this.accountService = accountService;
    }

    public Lunch addLunch(Account account, LunchDTO lunchDTO) {
        // Create new Lunch Instance
        Lunch lunch = new Lunch(lunchDTO.getDate(), account);

        // Add lunch to database
        this.lunchService.createOrUpdate(lunch);

        // Return lunch instance
        return lunch;
    }

    public boolean deleteLunch(UUID id) {
        Optional<Lunch> lunch = this.lunchService.findById(id);

        if(lunch.isPresent()) {
            this.lunchService.delete(lunch.get());
            return true;
        }

        return false;
    }

    public List<Lunch> findAll() {
        return this.lunchService.findAll();
    }

    public List<Lunch> findAllByAccountId(UUID accountId) {
        return this.lunchService.findAllByAccountId(accountId);
    }

    public Optional<Lunch> findById(UUID id) {
        return this.lunchService.findById(id);
    }
}
