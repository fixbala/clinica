package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.modelo.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String> {
}
