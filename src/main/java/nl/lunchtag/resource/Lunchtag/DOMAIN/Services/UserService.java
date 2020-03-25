package nl.lunchtag.resource.Lunchtag.DOMAIN.Services;

import lombok.Getter;
import nl.lunchtag.resource.Lunchtag.DAL.Repository.LunchRepository;
import nl.lunchtag.resource.Lunchtag.DAL.Repository.UserRepository;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Lunch;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.LunchDate;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.User;
import nl.lunchtag.resource.Lunchtag.WEB.API.DTO.LunchDTO;
import nl.lunchtag.resource.Lunchtag.WEB.Exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class UserService {
    private UserRepository userRepository;
    private LunchRepository lunchRepository;

    public UserService(UserRepository userrepository, LunchRepository lunchRepository) {
        this.userRepository = userrepository;
        this.lunchRepository = lunchRepository;
    }

    public User getUserByID(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
    }

    public void addLunchDate(long userID, LunchDTO lunchDTO) {

    }

    public void removeLunch(long userID, long lunchID)
    {

    }
    public List<LunchDate> getAllLunches(long userID)
    {
      return  getUserByID(userID).getLunchDates();
    }

}
