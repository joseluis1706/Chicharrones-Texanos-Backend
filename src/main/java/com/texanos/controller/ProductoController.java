package com.texanos.controller;

import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.texanos.model.Producto;
import com.texanos.repository.ProductoRepository;
import com.texanos.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
// @CrossOrigin(origins = "https://tudominio.com")
@RequiredArgsConstructor
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    private final ProductoService service;

    @PostMapping("/Crear")
    public Producto crear(@RequestBody Producto p) {
        return service.guardarProducto(p);
    }

    @GetMapping("/Listar")
    public List<Producto> listar() {
        return service.listarProductos();
    }


  /*   @PutMapping("/{id}")
    public Producto actualizaProducto(int id, Producto p) {
        Optional<Producto> value = productoRepository.findById((long) id);
        if (value.isEmpty()) {
            throw new RuntimeException("Producto no encontrado");
        }
        value.get().setNombre(p.getNombre());
        value.get().setPrecio(p.getPrecio());
        value.get().setDescripcion(p.getDescripcion());
        value.get().setImagen(p.getImagen());
        value.get().setStock(p.getStock());
        value.get().setActivo(p.getActivo());
        productoRepository.save(value.get());
        return (value.get());
    } */

        @PutMapping("/Actualizar/{id}")
         public Producto editar(@PathVariable Long id, @RequestBody Producto producto) {

         Producto existente = productoRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Producto no existe"));

      existente.setNombre(producto.getNombre());
      existente.setPrecio(producto.getPrecio());
      existente.setDescripcion(producto.getDescripcion());
        existente.setImagen(producto.getImagen());
        existente.setStock(producto.getStock());
        existente.setActivo(producto.getActivo());
      return productoRepository.save(existente);
  }

    @DeleteMapping("/Eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }

    /* @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return service.guardar(producto);
    } */
}
