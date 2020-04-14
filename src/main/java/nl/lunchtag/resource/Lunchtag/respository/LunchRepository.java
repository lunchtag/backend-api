package nl.lunchtag.resource.Lunchtag.respository;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LunchRepository extends JpaRepository<Lunch, UUID> {
    List<Lunch> findAllByAccountId(UUID accountId);
    Optional<Lunch> findByIdAndAccountId(UUID id, UUID accountId);
}