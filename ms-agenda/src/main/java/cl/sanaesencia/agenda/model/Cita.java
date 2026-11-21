package cl.sanaesencia.agenda.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CITAS")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PACIENTE_ID", nullable = false)
    private Integer pacienteId;

    @Column(name = "ESPECIALISTA_ID", nullable = false)
    private Integer especialistaId;

    @Column(name = "NOMBRE_ESPECIALISTA", nullable = false)
    private String nombreEspecialista;

    @Column(name = "ESPECIALIDAD", nullable = false)
    private String especialidad;

    @Column(name = "FECHA", nullable = false)
    private String fecha;

    @Column(name = "HORA_INICIO", nullable = false)
    private String horaInicio;

    @Column(name = "BLOQUES_OCUPADOS", nullable = false)
    private Integer bloquesOcupados = 1;

    @Column(name = "MOTIVO", length = 1000)
    private String motivo = "";

    @Column(name = "PRECIO", nullable = false)
    private Double precio;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false)
    private EstadoCita estado = EstadoCita.PENDIENTE_PAGO;

    public Cita() {
    }

    public Cita(Integer id, Integer pacienteId, Integer especialistaId, String nombreEspecialista,
                String especialidad, String fecha, String horaInicio, Integer bloquesOcupados,
                String motivo, Double precio, EstadoCita estado) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.especialistaId = especialistaId;
        this.nombreEspecialista = nombreEspecialista;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.bloquesOcupados = bloquesOcupados != null ? bloquesOcupados : 1;
        this.motivo = motivo != null ? motivo : "";
        this.precio = precio;
        this.estado = estado != null ? estado : EstadoCita.PENDIENTE_PAGO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Integer getEspecialistaId() {
        return especialistaId;
    }

    public void setEspecialistaId(Integer especialistaId) {
        this.especialistaId = especialistaId;
    }

    public String getNombreEspecialista() {
        return nombreEspecialista;
    }

    public void setNombreEspecialista(String nombreEspecialista) {
        this.nombreEspecialista = nombreEspecialista;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getBloquesOcupados() {
        return bloquesOcupados;
    }

    public void setBloquesOcupados(Integer bloquesOcupados) {
        this.bloquesOcupados = bloquesOcupados;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }
}
