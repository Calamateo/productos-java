package org.generation.ecommercedb.service;

import org.generation.ecommercedb.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Buscar mediante el nombre
     * @param nombre
     * @return Producto de un nombre en comun
     */
    @Query("SELECT p FROM Producto p WHERE p.nombre=?1")
    Optional<Producto> findByNombre(String nombre);
}
