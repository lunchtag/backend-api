package nl.lunchtag.resource.Lunchtag.respository;

import nl.lunchtag.resource.Lunchtag.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LogRepository extends JpaRepository<Log, UUID> {
}
