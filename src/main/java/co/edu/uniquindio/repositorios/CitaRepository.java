package co.edu.uniquindio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniquindio.modelo.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, String> {
}
