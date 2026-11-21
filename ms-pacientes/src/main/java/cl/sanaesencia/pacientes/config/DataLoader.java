package cl.sanaesencia.pacientes.config;

import cl.sanaesencia.pacientes.model.Paciente;
import cl.sanaesencia.pacientes.repository.PacienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final PacienteRepository pacienteRepository;

    public DataLoader(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (pacienteRepository.count() == 0) {
            Paciente p1 = new Paciente(
                    null,
                    "20781681",
                    "7",
                    "Exequiel",
                    "Ignacio",
                    "Pulgar",
                    "Cerda",
                    "exequiel@sanaesencia.cl",
                    "+56912345678",
                    "1998-05-14",
                    "Av. Providencia 1234, Santiago",
                    "https://example.com/fotos/exequiel.jpg",
                    "123456"
            );

            Paciente p2 = new Paciente(
                    null,
                    "18456789",
                    "K",
                    "María",
                    "Constanza",
                    "Soto",
                    "Rojas",
                    "maria.soto@sanaesencia.cl",
                    "+56987654321",
                    "1993-11-20",
                    "Calle Las Condes 5678, Santiago",
                    "https://example.com/fotos/maria.jpg",
                    "654321"
            );

            pacienteRepository.save(p1);
            pacienteRepository.save(p2);
        }
    }
}
