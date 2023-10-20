package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.modelo.RespuestaPqrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaPqrsRepository extends JpaRepository<RespuestaPqrs, String> {
}
