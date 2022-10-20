package org.generation.ecommercedb.service;

import org.generation.ecommercedb.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    public Producto getProducto(Long id) {
        return productoRepository.findById(id).orElseThrow(() -> new IllegalStateException("El producto con el ID: " + id + " no existe."));
    }

    public void deleteProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new IllegalStateException("El producto con el ID: " + id + " no existe.");
        }
    }

    public void addProducto(Producto producto) {
        Optional<Producto> prodByName = productoRepository.findByNombre(producto.getNombre());
        if (prodByName.isPresent()) {
            throw new IllegalStateException("El producto con el nombre: " + producto.getNombre() + " ya existe.");
        } else {
            productoRepository.save(producto);
        }
    }

    public void updateProducto(Long id, String nombre, String descripcion, Double precio, String URL_Imagen) {
        if (productoRepository.existsById(id)) {
            Producto p = productoRepository.getReferenceById(id);
            if (nombre != null) p.setNombre(nombre);
            if (descripcion != null) p.setDescripcion(descripcion);
            if (precio != null ) p.setPrecio(precio);
            System.out.println("getPrecio: " + p.getPrecio());
            if (URL_Imagen !=null) p.setURL_Imagen(URL_Imagen);
            productoRepository.save(p);
        } else {
            throw new IllegalStateException("El producto con el id " + id + " no existe.");
        }
    }
}
