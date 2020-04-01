package nl.lunchtag.resource.Lunchtag.WEB.API.Controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Lunch;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Services.AccountService;
import nl.lunchtag.resource.Lunchtag.WEB.API.DTO.LunchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Api(tags = "Accounts")
@RestController
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ApiOperation(value = "AddLunch")
    @PostMapping("/accounts/{accountID}/lunches")
    public ResponseEntity<Lunch> addLunch(@RequestBody LunchDTO lunchDTO, @PathVariable long accountID) {
      Lunch lunch =  accountService.addLunchDate(accountID, lunchDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(lunch);
    }

    @ApiOperation(value = "RemoveLunch")
    @DeleteMapping("/accounts/{accountID}/lunches/lunchID")
    public ResponseEntity<Lunch> removeLunch(@PathVariable long accountID, long lunchID)
    {
        accountService.removeLunch(accountID, lunchID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @ApiOperation(value = "GetAllLunches")
    @GetMapping("/accounts/{accountID}/lunches")
    public ResponseEntity<Set<Lunch>> getAllLunches(@PathVariable long accountID) {
        Set<Lunch> list = accountService.getAllLunches(accountID);
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

}
