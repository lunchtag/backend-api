package nl.lunchtag.resource.Lunchtag.service;

import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.respository.LunchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LunchService {

    private final LunchRepository lunchRepository;

    @Autowired
    public LunchService(LunchRepository lunchRepository) {
        this.lunchRepository = lunchRepository;
    }

    public Optional<Lunch> findById(UUID id) {
        return this.lunchRepository.findById(id);
    }

    public Lunch createOrUpdate(Lunch lunch) {
        return this.lunchRepository.save(lunch);
    }

    public void delete(Lunch lunch) {
        this.lunchRepository.delete(lunch);
    }

    public Optional<Lunch> findByIdAndAccountId(UUID id, UUID accountId) {
        return this.lunchRepository.findByIdAndAccountId(id, accountId);
    }

    public List<Lunch> findAll() {
        return this.lunchRepository.findAll();
    }

    public List<Lunch> findAllByAccountId(UUID accountId) {
        return this.lunchRepository.findAllByAccountId(accountId);
    }
}
