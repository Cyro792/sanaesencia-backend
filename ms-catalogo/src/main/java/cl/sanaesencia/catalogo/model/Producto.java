package cl.sanaesencia.catalogo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCTOS")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "CATEGORIA", nullable = false)
    private String categoria;

    @Column(name = "DESCRIPCION", nullable = false, length = 1000)
    private String descripcion;

    @Column(name = "PRECIO", nullable = false)
    private Double precio;

    @Column(name = "STOCK", nullable = false)
    private Integer stock;

    @Column(name = "IMAGEN_RES_ID")
    private Integer imagenResId;

    @Column(name = "IMAGEN_URL")
    private String imagenUrl;

    public Producto() {
    }

    public Producto(Integer id, String nombre, String categoria, String descripcion,
                    Double precio, Integer stock, Integer imagenResId, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.imagenResId = imagenResId;
        this.imagenUrl = imagenUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getImagenResId() {
        return imagenResId;
    }

    public void setImagenResId(Integer imagenResId) {
        this.imagenResId = imagenResId;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
