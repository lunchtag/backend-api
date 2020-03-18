package nl.lunchtag.resource.Lunchtag.DAL.Repository;

import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchRepository extends JpaRepository<Lunch, Long> {
}
