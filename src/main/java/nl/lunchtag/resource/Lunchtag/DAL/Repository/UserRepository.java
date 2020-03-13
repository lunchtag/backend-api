package nl.lunchtag.resource.Lunchtag.DAL.Repository;

import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
