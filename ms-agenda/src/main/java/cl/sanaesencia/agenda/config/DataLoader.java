package cl.sanaesencia.agenda.config;

import cl.sanaesencia.agenda.model.Cita;
import cl.sanaesencia.agenda.model.EstadoCita;
import cl.sanaesencia.agenda.repository.CitaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CitaRepository citaRepository;

    public DataLoader(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (citaRepository.count() == 0) {
            Cita cita1 = new Cita(
                    null,
                    1,
                    1,
                    "Camila Silva Morales",
                    "PSICOLOGIA",
                    "2026-11-25",
                    "10:00",
                    1,
                    "Consulta inicial de evaluación psicológica y ansiedad.",
                    25000.0,
                    EstadoCita.CONFIRMADA
            );

            Cita cita2 = new Cita(
                    null,
                    1,
                    1,
                    "Camila Silva Morales",
                    "FLORES_BACH",
                    "2026-12-02",
                    "11:00",
                    1,
                    "Sesión de seguimiento de terapia floral.",
                    20000.0,
                    EstadoCita.PENDIENTE_PAGO
            );

            citaRepository.save(cita1);
            citaRepository.save(cita2);
        }
    }
}
