package nl.lunchtag.resource.Lunchtag.DOMAIN.Services;

import lombok.Getter;
import nl.lunchtag.resource.Lunchtag.DAL.Repository.UserRepository;
import nl.lunchtag.resource.Lunchtag.WEB.API.DTO.LoginDTO;
import org.springframework.stereotype.Service;

@Service
@Getter
public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void logIn(LoginDTO loginDTO)
    {

    }
}
