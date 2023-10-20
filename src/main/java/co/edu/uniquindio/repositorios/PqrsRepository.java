package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.modelo.Pqrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PqrsRepository extends JpaRepository<Pqrs, String> {
}
