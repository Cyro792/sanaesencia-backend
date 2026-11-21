package cl.sanaesencia.catalogo.service;

import cl.sanaesencia.catalogo.model.Especialista;
import cl.sanaesencia.catalogo.repository.EspecialistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialistaService {

    private final EspecialistaRepository especialistaRepository;

    public EspecialistaService(EspecialistaRepository especialistaRepository) {
        this.especialistaRepository = especialistaRepository;
    }

    public List<Especialista> listarTodos() {
        return especialistaRepository.findAll();
    }

    public Optional<Especialista> obtenerPorId(Integer id) {
        return especialistaRepository.findById(id);
    }

    public Especialista crear(Especialista especialista) {
        return especialistaRepository.save(especialista);
    }

    public Optional<Especialista> actualizar(Integer id, Especialista detalles) {
        return especialistaRepository.findById(id).map(existente -> {
            if (detalles.getPrimerNombre() != null) existente.setPrimerNombre(detalles.getPrimerNombre());
            if (detalles.getSegundoNombre() != null) existente.setSegundoNombre(detalles.getSegundoNombre());
            if (detalles.getApellidoPaterno() != null) existente.setApellidoPaterno(detalles.getApellidoPaterno());
            if (detalles.getApellidoMaterno() != null) existente.setApellidoMaterno(detalles.getApellidoMaterno());
            if (detalles.getEspecialidades() != null) existente.setEspecialidades(detalles.getEspecialidades());
            if (detalles.getDiasAtencion() != null) existente.setDiasAtencion(detalles.getDiasAtencion());
            if (detalles.getDescripcion() != null) existente.setDescripcion(detalles.getDescripcion());
            if (detalles.getEmail() != null) existente.setEmail(detalles.getEmail());
            if (detalles.getTelefono() != null) existente.setTelefono(detalles.getTelefono());
            if (detalles.getImagenResId() != null) existente.setImagenResId(detalles.getImagenResId());
            return especialistaRepository.save(existente);
        });
    }

    public boolean eliminar(Integer id) {
        if (especialistaRepository.existsById(id)) {
            especialistaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
