package nl.lunchtag.resource.Lunchtag.controller;

import nl.lunchtag.resource.Lunchtag.controller.enums.AccountResponse;
import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.logic.LunchLogic;
import nl.lunchtag.resource.Lunchtag.models.AccountLunchDTO;
import nl.lunchtag.resource.Lunchtag.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final LunchLogic lunchLogic;

    @Autowired
    public AccountController(AccountService accountService, LunchLogic lunchLogic) {
        this.accountService = accountService;
        this.lunchLogic = lunchLogic;
    }

    @GetMapping
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        Map<Object, Object> model = new LinkedHashMap<>();
        model.put("user", userDetails);
        return ok(model);
    }

    @GetMapping("/all")
    public ResponseEntity getAllUsers(@AuthenticationPrincipal UserDetails userDetails) {
        List<Account> userList = accountService.getAllUsers();

        if(userList.isEmpty()){
            return new ResponseEntity<>(AccountResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(userList);
    }

    @GetMapping("/getSingleUser/{accountId}")
    public ResponseEntity getSingleUserById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String accountId){
        Account account = accountService.getUserById(UUID.fromString(accountId));

        if(account == null){
            return new ResponseEntity<>(AccountResponse.NO_ACCOUNTS, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/deleteSingleUser/{accountId}")
    public ResponseEntity removeUserById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String accountId){
        accountService.removeUserById(UUID.fromString(accountId));

        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/alluserlunches")
    public ResponseEntity getAllUserWithLunches(@AuthenticationPrincipal UserDetails userDetails){
        List<Account> userList = accountService.getAllUsers();
        if(userList.isEmpty()){
            return new ResponseEntity<>(AccountResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }

        List<AccountLunchDTO> accountLunchDTOList = new ArrayList<>();

        for(Account a : userList){
            List<Lunch> lunches = lunchLogic.findAllByAccountId(a.getId());

            AccountLunchDTO accountLunchDTO = new AccountLunchDTO();
            accountLunchDTO.setAccount(a);
            accountLunchDTO.setLunches(lunches);

            accountLunchDTOList.add(accountLunchDTO);
        }

        return ResponseEntity.ok(accountLunchDTOList);
    }

}
