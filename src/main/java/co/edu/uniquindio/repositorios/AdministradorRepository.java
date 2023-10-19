package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.modelo.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, String> {
    Optional<Administrador> findByCodigoAndUsuario_Password(String codigo, String password);
}
