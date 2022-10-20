package org.generation.ecommercedb.model;

import javax.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String nombre;
    private String descripcion;
    @Column(name = "imagen_url")
    private String  URL_Imagen;
    private Double precio;

    public Producto(Long id, String nombre, String descripcion, String URL_Imagen, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.URL_Imagen = URL_Imagen;
        this.precio = precio;
    }

    public Producto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getURL_Imagen() {
        return URL_Imagen;
    }

    public void setURL_Imagen(String URL_Imagen) {
        this.URL_Imagen = URL_Imagen;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", URL_Imagen='" + URL_Imagen + '\'' +
                ", precio=" + precio +
                '}';
    }
}
