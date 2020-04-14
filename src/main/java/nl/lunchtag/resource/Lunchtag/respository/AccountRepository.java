package nl.lunchtag.resource.Lunchtag.respository;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findAccountByEmail(String email);
}
