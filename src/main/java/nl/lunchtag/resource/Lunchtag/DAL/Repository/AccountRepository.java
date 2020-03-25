package nl.lunchtag.resource.Lunchtag.DAL.Repository;

import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Account;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
