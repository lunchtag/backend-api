package nl.lunchtag.resource.Lunchtag.WEB.API.Controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Lunch;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Services.AccountService;
import nl.lunchtag.resource.Lunchtag.WEB.API.DTO.LunchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Api(tags = "Accounts")
@RestController
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @ApiOperation(value = "AddLunch")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK!, Succesfull")
    })
    @PostMapping("/accounts/{accountID}/lunches")
    public ResponseEntity<Lunch> addLunch(@RequestBody LunchDTO lunchDTO, @PathVariable UUID accountID) {
        Lunch lunch = accountService.addLunchDate(accountID, lunchDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(lunch);
    }

    @ApiOperation(value = "RemoveLunch")
    @DeleteMapping("/accounts/{accountID}/lunches/{lunchID}")
    public ResponseEntity removeLunch(@PathVariable UUID accountID, @PathVariable long lunchID) {
        accountService.removeLunch(lunchID);
        return ResponseEntity.ok("Deleted");
    }

    @ApiOperation(value = "GetAllLunchesByID")
    @GetMapping("/accounts/{accountID}/lunches")
    public ResponseEntity<Set<Lunch>> getAllLunchesByID(@PathVariable UUID accountID) {
        Set<Lunch> list = accountService.getAllLunchesByID(accountID);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @ApiOperation(value = "GetAllLunches")
    @GetMapping("/lunches")
    public ResponseEntity<List<Lunch>> getAllLunches() {
        List<Lunch> list = accountService.getALlLunches();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

//-	Als medewerker wil ik een overzicht zien van wanneer ik heb mee geluncht zodat ik kan zien of ik vergeten ben een dag in te vullen.
//
//-	Als medewerker wil ik kunnen aangeven wanneer ik heb mee geluncht zodat de secretaresse dit weet.

    //-	Als medewerker wil ik een overzicht van mijn geschiedenis kunnen zien zodat ik weet of ik alle data correct heb ingevuld.


}
