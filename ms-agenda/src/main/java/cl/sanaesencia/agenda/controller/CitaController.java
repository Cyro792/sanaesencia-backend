package cl.sanaesencia.agenda.controller;

import cl.sanaesencia.agenda.model.Cita;
import cl.sanaesencia.agenda.model.EstadoCita;
import cl.sanaesencia.agenda.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public ResponseEntity<List<Cita>> listarTodas() {
        return ResponseEntity.ok(citaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerPorId(@PathVariable Integer id) {
        return citaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Cita>> listarPorPaciente(@PathVariable Integer pacienteId) {
        return ResponseEntity.ok(citaService.listarPorPaciente(pacienteId));
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<List<Cita>> listarDisponibilidad(
            @RequestParam("especialistaId") Integer especialistaId,
            @RequestParam("fecha") String fecha) {
        return ResponseEntity.ok(citaService.listarPorEspecialistaYFecha(especialistaId, fecha));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Cita cita) {
        Optional<Cita> creada = citaService.crear(cita);
        if (creada.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(creada.get());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El horario solicitado ya se encuentra ocupado por otra cita.");
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Cita> actualizarEstado(
            @PathVariable Integer id,
            @RequestParam("estado") EstadoCita estado) {
        return citaService.actualizarEstado(id, estado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (citaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
