package cl.sanaesencia.catalogo.service;

import cl.sanaesencia.catalogo.model.Producto;
import cl.sanaesencia.catalogo.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> actualizar(Integer id, Producto detalles) {
        return productoRepository.findById(id).map(existente -> {
            if (detalles.getNombre() != null) existente.setNombre(detalles.getNombre());
            if (detalles.getCategoria() != null) existente.setCategoria(detalles.getCategoria());
            if (detalles.getDescripcion() != null) existente.setDescripcion(detalles.getDescripcion());
            if (detalles.getPrecio() != null) existente.setPrecio(detalles.getPrecio());
            if (detalles.getStock() != null) existente.setStock(detalles.getStock());
            if (detalles.getImagenResId() != null) existente.setImagenResId(detalles.getImagenResId());
            if (detalles.getImagenUrl() != null) existente.setImagenUrl(detalles.getImagenUrl());
            return productoRepository.save(existente);
        });
    }

    public boolean descontarStock(Integer id, Integer cantidad) {
        Optional<Producto> opt = productoRepository.findById(id);
        if (opt.isPresent()) {
            Producto producto = opt.get();
            if (producto.getStock() >= cantidad) {
                producto.setStock(producto.getStock() - cantidad);
                productoRepository.save(producto);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(Integer id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
