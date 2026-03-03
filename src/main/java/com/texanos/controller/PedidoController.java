package com.texanos.controller;

import com.texanos.model.Pedido;
import com.texanos.service.PedidoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://tudominio.com")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    

    @PostMapping("/Crear")
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.guardarPedido(pedido);
    }

    @DeleteMapping("/Limpiar")
    public String limpiarTablas() {
        pedidoService.limpiarTablas();
        return "Tablas limpiadas correctamente";
    }

    @GetMapping("/pendientes")
    public List<Pedido> listarPendientes() {
        return pedidoService.obtenerPedidosPendientes();
    }

   /*  @PutMapping("/completar/{id}")
    public ResponseEntity<?> completar(@PathVariable Long id) {

        pedidoService.completarPedido(id);

        return ResponseEntity.ok().build();
    } */

    
}
