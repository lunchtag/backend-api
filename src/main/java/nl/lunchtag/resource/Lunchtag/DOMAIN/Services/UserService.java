package nl.lunchtag.resource.Lunchtag.DOMAIN.Services;

import lombok.Getter;
import nl.lunchtag.resource.Lunchtag.DAL.Repository.LunchRepository;
import nl.lunchtag.resource.Lunchtag.DAL.Repository.UserRepository;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Lunch;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.User;
import nl.lunchtag.resource.Lunchtag.WEB.API.DTO.LunchDTO;
import nl.lunchtag.resource.Lunchtag.WEB.Exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@Getter
public class UserService {
    private UserRepository userRepository;
    private LunchRepository lunchRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public User getUserByID(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
    }

    public void addLunch(long userID, LunchDTO lunchDTO) {
        Lunch lunch = new Lunch();
        lunch.setDate(lunchDTO.getDate());
        User user = getUserByID(userID);
        user.addLunch(lunch);
        lunchRepository.save(lunch);
    }

    public void removeLunch(long userID, long lunchID)
    {
        User user = getUserByID(userID);
        user.removeLunch(lunchID);
    }

}
