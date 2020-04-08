package nl.lunchtag.resource.Lunchtag.WEB.API.Controllers;

import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Account;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Services.UserService;
import nl.lunchtag.resource.Lunchtag.DOMAIN.logic.PasswordHelper;
import nl.lunchtag.resource.Lunchtag.WEB.API.DTO.LoginDTO;
import nl.lunchtag.resource.Lunchtag.WEB.API.DTO.RegisterDTO;
import nl.lunchtag.resource.Lunchtag.WEB.CONFIG.jwt.TokenProvider;
import nl.lunchtag.resource.Lunchtag.WEB.Exceptions.AuthResponse;
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
    private final UserService userService;
    private final PasswordHelper passwordHelper;

    @Autowired
    public AuthController(TokenProvider tokenProvider, UserService userService, PasswordHelper passwordHelper) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.passwordHelper = passwordHelper;
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDTO loginModel) {
        Optional<Account> user = userService.findUserByEmail(loginModel.getEmail());

        if(!user.isPresent()) {
            return new ResponseEntity<>(AuthResponse.WRONG_CREDENTIALS.toString(), HttpStatus.BAD_REQUEST);
        }

        String password = user.get().getPassword();
        if(!this.passwordHelper.isMatch(loginModel.getPassword(), password)) {
            return new ResponseEntity<>(AuthResponse.WRONG_CREDENTIALS.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            Map<Object, Object> model = new LinkedHashMap<>();
            model.put("token", tokenProvider.createToken(user.get().getAccountID(), user.get().getName(), user.get().getLastName(), user.get().getRole()));
            model.put("user", user.get());
            return ok(model);
        } catch(AuthenticationException ex) {
            return new ResponseEntity<>(AuthResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterDTO registerModel) {
        if(userService.findUserByEmail(registerModel.getEmail()).isPresent()) {
            return new ResponseEntity<>(AuthResponse.USER_ALREADY_EXISTS.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            Account user = new Account();

            user.setEmail(registerModel.getEmail());
            user.setName(registerModel.getFirstName());
            user.setLastName(registerModel.getLastName());
            user.setPassword(passwordHelper.hash(registerModel.getPassword()));

            Account createdUser = userService.createOrUpdate(user);

            Map<Object, Object> model = new LinkedHashMap<>();
            model.put("token", tokenProvider.createToken(createdUser.getAccountID(), createdUser.getName(), createdUser.getLastName(), createdUser.getRole()));
            model.put("user", createdUser);
            return ok(model);
        } catch(Exception ex) {
            return new ResponseEntity<>(AuthResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
