package nl.lunchtag.resource.Lunchtag.controller;

import nl.lunchtag.resource.Lunchtag.controller.enums.AccountResponse;
import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.logic.AccountLogic;
import nl.lunchtag.resource.Lunchtag.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountLogic accountLogic;


    @Autowired
    public AccountController(AccountLogic accountLogic) {
        this.accountLogic = accountLogic;
    }

    @GetMapping
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        Map<Object, Object> model = new LinkedHashMap<>();
        model.put("user", userDetails);
        model.put("disabled", userDetails.isAccountNonLocked());
        return ok(model);
    }

    @PostMapping(value = "/disable/{accountId}")
    public ResponseEntity disableUser(@Valid @PathVariable String accountId) {
        if(this.accountLogic.disableAccount(UUID.fromString(accountId))) {
            return ok("Successfully disabled");
        }

        return new ResponseEntity<>(AccountResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
    }
}
