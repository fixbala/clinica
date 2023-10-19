package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.modelo.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String> {

    List<Medico> findByEspecialidad(String especialidad);

    Optional<Medico> findByCodigoAndUsuario_Password(String codigo, String password);
}
