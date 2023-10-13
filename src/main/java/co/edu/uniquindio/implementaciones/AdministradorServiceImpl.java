package co.edu.uniquindio.implementaciones;

import co.edu.uniquindio.modelo.Administrador;
import co.edu.uniquindio.repositorios.AdministradorRepository;
import co.edu.uniquindio.servicios.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public Administrador guardarAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @Override
    public Optional<Administrador> obtenerAdministradorPorCodigo(String codigo) {
        return administradorRepository.findById(codigo);
    }

    @Override
    public List<Administrador> obtenerTodosLosAdministradores() {
        return administradorRepository.findAll();
    }

    @Override
    public Administrador actualizarAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @Override
    public void eliminarAdministrador(String codigo) {
        administradorRepository.deleteById(codigo);
    }
}
