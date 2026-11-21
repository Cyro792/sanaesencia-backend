package cl.sanaesencia.pacientes.service;

import cl.sanaesencia.pacientes.model.Paciente;
import cl.sanaesencia.pacientes.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> obtenerPorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    public Optional<Paciente> buscarPorRun(String run) {
        return pacienteRepository.findByRun(run);
    }

    public Optional<Paciente> buscarPorEmail(String email) {
        return pacienteRepository.findByEmail(email);
    }

    public Paciente crear(Paciente paciente) {
        if (paciente.getPasswordHash() == null) {
            paciente.setPasswordHash("");
        }
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> actualizar(Integer id, Paciente pacienteDetalles) {
        return pacienteRepository.findById(id).map(pacienteExistente -> {
            if (pacienteDetalles.getRun() != null) {
                pacienteExistente.setRun(pacienteDetalles.getRun());
            }
            if (pacienteDetalles.getRunDv() != null) {
                pacienteExistente.setRunDv(pacienteDetalles.getRunDv());
            }
            if (pacienteDetalles.getPrimerNombre() != null) {
                pacienteExistente.setPrimerNombre(pacienteDetalles.getPrimerNombre());
            }
            if (pacienteDetalles.getSegundoNombre() != null) {
                pacienteExistente.setSegundoNombre(pacienteDetalles.getSegundoNombre());
            }
            if (pacienteDetalles.getApellidoPaterno() != null) {
                pacienteExistente.setApellidoPaterno(pacienteDetalles.getApellidoPaterno());
            }
            if (pacienteDetalles.getApellidoMaterno() != null) {
                pacienteExistente.setApellidoMaterno(pacienteDetalles.getApellidoMaterno());
            }
            if (pacienteDetalles.getEmail() != null) {
                pacienteExistente.setEmail(pacienteDetalles.getEmail());
            }
            if (pacienteDetalles.getTelefono() != null) {
                pacienteExistente.setTelefono(pacienteDetalles.getTelefono());
            }
            if (pacienteDetalles.getFechaNacimiento() != null) {
                pacienteExistente.setFechaNacimiento(pacienteDetalles.getFechaNacimiento());
            }
            if (pacienteDetalles.getDireccion() != null) {
                pacienteExistente.setDireccion(pacienteDetalles.getDireccion());
            }
            if (pacienteDetalles.getFotoUri() != null) {
                pacienteExistente.setFotoUri(pacienteDetalles.getFotoUri());
            }
            if (pacienteDetalles.getPasswordHash() != null) {
                pacienteExistente.setPasswordHash(pacienteDetalles.getPasswordHash());
            }
            return pacienteRepository.save(pacienteExistente);
        });
    }

    public boolean eliminar(Integer id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
