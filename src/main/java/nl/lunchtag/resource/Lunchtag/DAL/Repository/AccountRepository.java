package nl.lunchtag.resource.Lunchtag.DAL.Repository;

import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    public Optional<Account> findAccountByEmail(String email);
}
