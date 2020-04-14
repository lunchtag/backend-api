package nl.lunchtag.resource.Lunchtag.DAL.Repository;

import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Account;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LunchRepository extends JpaRepository<Lunch, Long> {
    public List<Lunch> findAllByAccount(Account account);
}