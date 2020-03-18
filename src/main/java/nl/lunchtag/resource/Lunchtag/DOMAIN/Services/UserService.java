package nl.lunchtag.resource.Lunchtag.DOMAIN.Services;

import lombok.Getter;
import nl.lunchtag.resource.Lunchtag.DAL.Repository.UserRepository;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.User;
import nl.lunchtag.resource.Lunchtag.WEB.API.DTO.LunchDTO;
import org.springframework.stereotype.Service;

@Service
@Getter
public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void addLunch(long userID, LunchDTO lunchDTO)
    {

    }
    public User getUserByID(long id)
    {
        return repository.findById(id).orElseThrow(() -> new
    }
}
