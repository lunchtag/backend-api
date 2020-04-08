package nl.lunchtag.resource.Lunchtag.DOMAIN.Services;

import nl.lunchtag.resource.Lunchtag.DAL.Repository.AccountRepository;
import nl.lunchtag.resource.Lunchtag.DAL.Repository.LunchRepository;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Account;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Lunch;
import nl.lunchtag.resource.Lunchtag.WEB.API.DTO.LunchDTO;
import nl.lunchtag.resource.Lunchtag.WEB.API.DTO.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LunchRepository lunchRepository;

    @BeforeEach
    public void setup() {
        accountService = new AccountService(accountRepository, lunchRepository);
    }


    @Test
    void addLunchDate() {
//        //Arrange
//        UserDTO user = new UserDTO();
//        user.setId(1);
//        user.setName("Karin");
//        accountService.register(user);
//        LunchDTO lunch = new LunchDTO();
//
//        //Act
//        Lunch lunch1 = accountService.addLunchDate(user.getId(), lunch);
//        int actualsize = lunchRepository.findAll().size();
//        int expected =1;
//
//        //Arrange
//        assertEquals(expected, actualsize);
    }

    @Test
    void removeLunch() {
    }

    @Test
    void getAllLunchesByID() {
    }

    @Test
    void getALlLunches() {
    }

    @Test
    void getAccountByID() {
    }


    @Test
    void register() {
    }
}