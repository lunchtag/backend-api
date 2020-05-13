package nl.lunchtag.resource.Lunchtag.controller;

import nl.lunchtag.resource.Lunchtag.controller.enums.AccountResponse;
import nl.lunchtag.resource.Lunchtag.entity.Account;

import nl.lunchtag.resource.Lunchtag.logic.AccountLogic;
import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.logic.LunchLogic;
import nl.lunchtag.resource.Lunchtag.models.AccountLunchDTO;

import nl.lunchtag.resource.Lunchtag.models.AccountUpdateDTO;
import nl.lunchtag.resource.Lunchtag.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final LunchLogic lunchLogic;
    private final AccountLogic accountLogic;

    @Autowired
    public AccountController(AccountService accountService, LunchLogic lunchLogic, AccountLogic accountLogic) {
        this.accountService = accountService;
        this.lunchLogic = lunchLogic;
        this.accountLogic = accountLogic;
    }

    @GetMapping
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        Map<Object, Object> model = new LinkedHashMap<>();
        model.put("user", userDetails);
        model.put("disabled", userDetails.isAccountNonLocked());
        return ok(model);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/disable/{accountId}")
    public ResponseEntity disableUser(@Valid @PathVariable String accountId) {
        if(this.accountLogic.disableAccount(UUID.fromString(accountId))) {
            return ok("Successfully disabled");
        }

        return new ResponseEntity<>(AccountResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity updateUser(@AuthenticationPrincipal Account account, @RequestBody AccountUpdateDTO accountUpdateDTO) {
        Account updatedAccount = this.accountLogic.updateUser(account.getId(),accountUpdateDTO);

        if(updatedAccount != null) {
            return ok(updatedAccount);
        }

        return new ResponseEntity<>(AccountResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{accountId}")
    public ResponseEntity updateUser(@PathVariable String accountId, @RequestBody AccountUpdateDTO accountUpdateDTO) {
        Account updatedAccount = this.accountLogic.updateUser(UUID.fromString(accountId),accountUpdateDTO);

        if(updatedAccount != null) {
            return ok(updatedAccount);
        }

        return new ResponseEntity<>(AccountResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity getAllUsers(@AuthenticationPrincipal UserDetails userDetails) {
        // IMPROVE METHOD
        List<Account> userList = accountService.getAllUsers();

        if(userList.isEmpty()){
            return new ResponseEntity<>(AccountResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(userList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getSingleUser/{accountId}")
    public ResponseEntity getSingleUserById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String accountId){
        // IMPROVE METHOD
        Account account = accountService.getUserById(UUID.fromString(accountId));

        if(account == null){
            return new ResponseEntity<>(AccountResponse.NO_ACCOUNTS, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(account);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteSingleUser/{accountId}")
    public ResponseEntity removeUserById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String accountId){
        // IMPROVE METHOD
        accountService.removeUserById(UUID.fromString(accountId));

        return ResponseEntity.ok("Deleted");
    }


    @GetMapping("/alluserlunches")
    public ResponseEntity getAllUserWithLunches(@AuthenticationPrincipal UserDetails userDetails){
        // IMPROVE METHOD
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
