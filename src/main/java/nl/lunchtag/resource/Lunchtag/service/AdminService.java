package nl.lunchtag.resource.Lunchtag.service;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.models.LunchDTO;
import nl.lunchtag.resource.Lunchtag.respository.LunchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final LunchRepository lunchRepository;

    @Autowired
    public AdminService(LunchRepository lunchRepository) {
        this.lunchRepository = lunchRepository;
    }


    public Lunch createOrUpdate(Lunch lunch) {
        return this.lunchRepository.save(lunch);
    }
}
