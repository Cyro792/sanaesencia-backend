package cl.sanaesencia.catalogo.controller;

import cl.sanaesencia.catalogo.model.Especialista;
import cl.sanaesencia.catalogo.service.EspecialistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialistas")
@CrossOrigin(origins = "*")
public class EspecialistaController {

    private final EspecialistaService especialistaService;

    public EspecialistaController(EspecialistaService especialistaService) {
        this.especialistaService = especialistaService;
    }

    @GetMapping
    public ResponseEntity<List<Especialista>> listarTodos() {
        return ResponseEntity.ok(especialistaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialista> obtenerPorId(@PathVariable Integer id) {
        return especialistaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Especialista> crear(@RequestBody Especialista especialista) {
        Especialista nuevo = especialistaService.crear(especialista);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialista> actualizar(@PathVariable Integer id, @RequestBody Especialista especialista) {
        return especialistaService.actualizar(id, especialista)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (especialistaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
