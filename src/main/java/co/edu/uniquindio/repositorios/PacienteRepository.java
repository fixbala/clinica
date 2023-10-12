package co.edu.uniquindio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniquindio.modelo.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String> {
    // Puedes agregar consultas personalizadas si es necesario
}
