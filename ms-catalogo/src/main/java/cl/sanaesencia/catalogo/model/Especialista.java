package cl.sanaesencia.catalogo.model;

import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ESPECIALISTAS")
public class Especialista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PRIMER_NOMBRE", nullable = false)
    private String primerNombre;

    @Column(name = "SEGUNDO_NOMBRE")
    private String segundoNombre;

    @Column(name = "APELLIDO_PATERNO", nullable = false)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", nullable = false)
    private String apellidoMaterno;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ESPECIALISTA_ESPECIALIDADES", joinColumns = @JoinColumn(name = "ESPECIALISTA_ID"))
    @Column(name = "ESPECIALIDAD")
    @Enumerated(EnumType.STRING)
    private List<Especialidad> especialidades = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ESPECIALISTA_DIAS", joinColumns = @JoinColumn(name = "ESPECIALISTA_ID"))
    @Column(name = "DIA_SEMANA")
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> diasAtencion = new HashSet<>();

    @Column(name = "DESCRIPCION", length = 1000)
    private String descripcion = "";

    @Column(name = "EMAIL")
    private String email = "";

    @Column(name = "TELEFONO")
    private String telefono = "";

    @Column(name = "IMAGEN_RES_ID")
    private Integer imagenResId;

    public Especialista() {
    }

    public Especialista(Integer id, String primerNombre, String segundoNombre,
                        String apellidoPaterno, String apellidoMaterno,
                        List<Especialidad> especialidades, Set<DayOfWeek> diasAtencion,
                        String descripcion, String email, String telefono, Integer imagenResId) {
        this.id = id;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidades = especialidades != null ? especialidades : new ArrayList<>();
        this.diasAtencion = diasAtencion != null ? diasAtencion : new HashSet<>();
        this.descripcion = descripcion != null ? descripcion : "";
        this.email = email != null ? email : "";
        this.telefono = telefono != null ? telefono : "";
        this.imagenResId = imagenResId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public Set<DayOfWeek> getDiasAtencion() {
        return diasAtencion;
    }

    public void setDiasAtencion(Set<DayOfWeek> diasAtencion) {
        this.diasAtencion = diasAtencion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getImagenResId() {
        return imagenResId;
    }

    public void setImagenResId(Integer imagenResId) {
        this.imagenResId = imagenResId;
    }
}
