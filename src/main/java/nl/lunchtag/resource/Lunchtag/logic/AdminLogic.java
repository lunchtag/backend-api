package nl.lunchtag.resource.Lunchtag.logic;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.models.LunchDTO;
import nl.lunchtag.resource.Lunchtag.service.AdminService;
import nl.lunchtag.resource.Lunchtag.service.LunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminLogic {

    private final AdminService adminService;
    private final LunchService lunchService;

    @Autowired
    public AdminLogic(AdminService adminService, LunchService lunchService) {
        this.adminService = adminService;
        this.lunchService = lunchService;
    }

    public Lunch addLunch(Account account, LunchDTO lunchDTO) {
        // Create new Lunch Instance
        Lunch lunch = new Lunch(lunchDTO.getDate(), account);

        // Add lunch to database
        this.adminService.createOrUpdate(lunch);

        // Return lunch instance
        return lunch;

    }
}
