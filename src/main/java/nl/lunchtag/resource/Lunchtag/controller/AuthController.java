package nl.lunchtag.resource.Lunchtag.controller;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.logic.AccountLogic;
import nl.lunchtag.resource.Lunchtag.logic.PasswordHelper;
import nl.lunchtag.resource.Lunchtag.logic.mailservice.SendEmailService;
import nl.lunchtag.resource.Lunchtag.models.LoginDTO;
import nl.lunchtag.resource.Lunchtag.models.RegisterDTO;
import nl.lunchtag.resource.Lunchtag.config.jwt.TokenProvider;
import nl.lunchtag.resource.Lunchtag.logic.Exceptions.AuthResponse;
import nl.lunchtag.resource.Lunchtag.models.pincodeDTO;
import nl.lunchtag.resource.Lunchtag.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AccountService accountService;
    private final PasswordHelper passwordHelper;
    private final AccountLogic accountLogic;
    private final SendEmailService sendEmailService;

    @Autowired
    public AuthController(TokenProvider tokenProvider, AccountService accountService, PasswordHelper passwordHelper, AccountLogic accountLogic, SendEmailService emailService) {
        this.tokenProvider = tokenProvider;
        this.accountService = accountService;
        this.passwordHelper = passwordHelper;
        this.accountLogic = accountLogic;
        this.sendEmailService = emailService;
    }

    @PostMapping("/pincode")
    public ResponseEntity loginByPincode(@Valid @RequestBody pincodeDTO pincodeModel) {
        Optional<Account> user = accountService.findAccountByEmail(pincodeModel.getEmail());

        if(!user.isPresent()) {
            return new ResponseEntity<>(AuthResponse.WRONG_CREDENTIALS.toString(), HttpStatus.BAD_REQUEST);
        }

        if(!this.accountLogic.isPincodeMatch(pincodeModel.getPincode(), user.get())) {
            return new ResponseEntity<>(AuthResponse.WRONG_CREDENTIALS.toString(), HttpStatus.BAD_REQUEST);
        }

        if(!user.get().isAccountNonLocked()) {
            return new ResponseEntity<>(AuthResponse.DISABLED.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            Map<Object, Object> model = new LinkedHashMap<>();
            model.put("token", tokenProvider.createToken(user.get().getId(), user.get().getName(), user.get().getLastName(), user.get().getRole()));
            model.put("user", user.get());
            return ok(model);
        } catch(AuthenticationException ex) {
            return new ResponseEntity<>(AuthResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDTO loginModel) {
        Optional<Account> user = accountService.findAccountByEmail(loginModel.getEmail());

        if(!user.isPresent()) {
            return new ResponseEntity<>(AuthResponse.WRONG_CREDENTIALS.toString(), HttpStatus.BAD_REQUEST);
        }

        String password = user.get().getPassword();
        if(!this.passwordHelper.isMatch(loginModel.getPassword(), password)) {
            return new ResponseEntity<>(AuthResponse.WRONG_CREDENTIALS.toString(), HttpStatus.BAD_REQUEST);
        }

        if(!user.get().isAccountNonLocked()) {
            return new ResponseEntity<>(AuthResponse.DISABLED.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            Map<Object, Object> model = new LinkedHashMap<>();
            model.put("token", tokenProvider.createToken(user.get().getId(), user.get().getName(), user.get().getLastName(), user.get().getRole()));
            model.put("user", user.get());
            return ok(model);
        } catch(AuthenticationException ex) {
            return new ResponseEntity<>(AuthResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterDTO registerModel) {
        if(accountService.findAccountByEmail(registerModel.getEmail()).isPresent()) {
            return new ResponseEntity<>(AuthResponse.USER_ALREADY_EXISTS.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            Account user = new Account();
            int pinCode = this.accountLogic.generatePincode();

            user.setEmail(registerModel.getEmail());
            user.setName(registerModel.getFirstName());
            user.setLastName(registerModel.getLastName());
            user.setPassword(passwordHelper.hash(registerModel.getPassword()));
            user.setPincode(passwordHelper.hashPincoded(pinCode));
            Account createdUser = accountService.createOrUpdate(user);

            Map<Object, Object> model = new LinkedHashMap<>();
            model.put("token", tokenProvider.createToken(createdUser.getId(), createdUser.getName(), createdUser.getLastName(), createdUser.getRole()));
            model.put("user", createdUser);

            sendEmailService.SendEmail(registerModel.getEmail(), Integer.toString(pinCode));
            return ok(model);
        } catch(Exception ex) {
            return new ResponseEntity<>(AuthResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
