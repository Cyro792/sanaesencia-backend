package cl.sanaesencia.catalogo.config;

import cl.sanaesencia.catalogo.model.Especialidad;
import cl.sanaesencia.catalogo.model.Especialista;
import cl.sanaesencia.catalogo.model.Producto;
import cl.sanaesencia.catalogo.repository.EspecialistaRepository;
import cl.sanaesencia.catalogo.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository productoRepository;
    private final EspecialistaRepository especialistaRepository;

    public DataLoader(ProductoRepository productoRepository, EspecialistaRepository especialistaRepository) {
        this.productoRepository = productoRepository;
        this.especialistaRepository = especialistaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productoRepository.count() == 0) {
            Producto prod1 = new Producto(
                    null,
                    "Aceite Esencial de Lavanda",
                    "Aceites Esenciales",
                    "Aceite esencial 100% puro y orgánico ideal para aromaterapia, promueve el descanso y reduce el estrés.",
                    12990.0,
                    20,
                    null,
                    "https://example.com/productos/lavanda.jpg"
            );

            Producto prod2 = new Producto(
                    null,
                    "Gotas Flores de Bach Rescue",
                    "Flores de Bach",
                    "Fórmula de rescate tradicional para situaciones de alto estrés o crisis emocional pasajera.",
                    15990.0,
                    20,
                    null,
                    "https://example.com/productos/rescue.jpg"
            );

            Producto prod3 = new Producto(
                    null,
                    "Infusión Relajante Melisa y Pasiflora",
                    "Infusiones",
                    "Mezcla de hierbas medicinales orgánicas seleccionadas para conciliar el sueño y calmar los nervios.",
                    8990.0,
                    20,
                    null,
                    "https://example.com/productos/infusion.jpg"
            );

            Producto prod4 = new Producto(
                    null,
                    "Crema Corporal Caléndula",
                    "Cosmética Natural",
                    "Crema regeneradora e hidratante de caléndula recomendada para pieles sensibles o irritadas.",
                    14990.0,
                    20,
                    null,
                    "https://example.com/productos/crema.jpg"
            );

            productoRepository.saveAll(List.of(prod1, prod2, prod3, prod4));
        }

        if (especialistaRepository.count() == 0) {
            Especialista esp1 = new Especialista(
                    null,
                    "Camila",
                    "Andrea",
                    "Silva",
                    "Morales",
                    List.of(Especialidad.PSICOLOGIA, Especialidad.FLORES_BACH),
                    new HashSet<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY)),
                    "Psicóloga clínica especializada en terapias florales e integrativas con 8 años de experiencia.",
                    "camila.silva@sanaesencia.cl",
                    "+56911223344",
                    null
            );

            Especialista esp2 = new Especialista(
                    null,
                    "Sebastián",
                    "Andrés",
                    "Rojas",
                    "Muñoz",
                    List.of(Especialidad.ACUPUNTURA, Especialidad.MEDICINA_INTEGRATIVA),
                    new HashSet<>(Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY)),
                    "Especialista en medicina tradicional china, acupuntura clínica y manejo del dolor crónico.",
                    "sebastian.rojas@sanaesencia.cl",
                    "+56955667788",
                    null
            );

            Especialista esp3 = new Especialista(
                    null,
                    "Valentina",
                    "Paz",
                    "Contreras",
                    "Díaz",
                    List.of(Especialidad.NUTRICION, Especialidad.KINESIOLOGIA),
                    new HashSet<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)),
                    "Nutricionista holística y kinesióloga enfocada en salud digestiva y rehabilitación física integral.",
                    "valentina.contreras@sanaesencia.cl",
                    "+56999887766",
                    null
            );

            especialistaRepository.saveAll(List.of(esp1, esp2, esp3));
        }
    }
}
