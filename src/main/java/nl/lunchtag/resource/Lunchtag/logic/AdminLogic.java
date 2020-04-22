package nl.lunchtag.resource.Lunchtag.logic;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.models.LunchDTO;
import nl.lunchtag.resource.Lunchtag.service.AdminService;
import nl.lunchtag.resource.Lunchtag.service.LunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AdminLogic {

    private final AdminService adminService;
    private final LunchService lunchService;

    @Autowired
    public AdminLogic(AdminService adminService, LunchService lunchService) {
        this.adminService = adminService;
        this.lunchService = lunchService;
    }

    public Lunch addLunch(String accountId, LunchDTO lunchDTO) {
        // Create new Lunch Instance

        Account account = new Account(UUID.fromString(accountId));
        Lunch lunch = new Lunch(lunchDTO.getDate(), account);

        // Add lunch to database
        this.lunchService.createOrUpdate(lunch);

        // Return lunch instance
        return lunch;
    }
}
