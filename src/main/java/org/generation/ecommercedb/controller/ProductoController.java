package org.generation.ecommercedb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.generation.ecommercedb.model.Producto;
import org.generation.ecommercedb.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//Lo identifica como un controllador de REST
@RequestMapping("/api/productos/")//mapping de donde se mandara llamar este endpoint
@Api(tags = "Administrador de los productos del ecommerce", description = "Operaciones CRUD con productos")
public class ProductoController {

    @Autowired
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @ApiOperation(value="Ver las lista de todos los productos disponibles", response=List.class)
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Ok"),
            @ApiResponse(code=404, message="Not Found"),
            @ApiResponse(code=500, message="Server Error")
    })
    @GetMapping
    public List<Producto> getProductos() {
        return productoService.getProductos();
    }

    @ApiOperation(value="Ver un producto específico", response=Producto.class)
    @GetMapping("{id}")
    public  Producto getProducto(@PathVariable("id") Long id){
        return productoService.getProducto(id);
    }

    @ApiOperation(value="Eliminar un producto específico")
    @DeleteMapping("{id}")
    public void deleteProducto(@PathVariable("id") Long id) {
        productoService.deleteProducto(id);
    }

    @ApiOperation(value="Crear un nuevo producto")
    @PostMapping()
    public void addProducto(@RequestBody Producto producto) {
        productoService.addProducto(producto);
    }

    @ApiOperation(value="Actualizar un producto específico")
    @PutMapping("{id}")
    public void updateProducto(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) String URL_Imagen,
            @RequestParam(required = false) Double precio
    ) {
        productoService.updateProducto(id, nombre, descripcion,
                precio, URL_Imagen);
    }

}
