package cl.sanaesencia.agenda.service;

import cl.sanaesencia.agenda.model.Cita;
import cl.sanaesencia.agenda.model.EstadoCita;
import cl.sanaesencia.agenda.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> obtenerPorId(Integer id) {
        return citaRepository.findById(id);
    }

    public List<Cita> listarPorPaciente(Integer pacienteId) {
        return citaRepository.findByPacienteId(pacienteId);
    }

    public List<Cita> listarPorEspecialistaYFecha(Integer especialistaId, String fecha) {
        return citaRepository.findByEspecialistaIdAndFecha(especialistaId, fecha);
    }

    public boolean existeConflictoHorario(Integer especialistaId, String fecha, String horaInicio) {
        List<Cita> citasExistentes = citaRepository.findByEspecialistaIdAndFecha(especialistaId, fecha);
        return citasExistentes.stream()
                .anyMatch(c -> c.getEstado() != EstadoCita.CANCELADA && c.getHoraInicio().equalsIgnoreCase(horaInicio));
    }

    public Optional<Cita> crear(Cita cita) {
        if (existeConflictoHorario(cita.getEspecialistaId(), cita.getFecha(), cita.getHoraInicio())) {
            return Optional.empty();
        }
        if (cita.getEstado() == null) {
            cita.setEstado(EstadoCita.PENDIENTE_PAGO);
        }
        if (cita.getBloquesOcupados() == null) {
            cita.setBloquesOcupados(1);
        }
        if (cita.getMotivo() == null) {
            cita.setMotivo("");
        }
        return Optional.of(citaRepository.save(cita));
    }

    public Optional<Cita> actualizarEstado(Integer id, EstadoCita nuevoEstado) {
        return citaRepository.findById(id).map(cita -> {
            cita.setEstado(nuevoEstado);
            return citaRepository.save(cita);
        });
    }

    public boolean eliminar(Integer id) {
        if (citaRepository.existsById(id)) {
            citaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
