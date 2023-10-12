package co.edu.uniquindio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniquindio.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
