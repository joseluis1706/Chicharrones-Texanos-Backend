package com.texanos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texanos.model.Pedido;
import com.texanos.repository.PedidoRepository;
import com.texanos.service.PedidoService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")

public class AdminController {

    @Autowired
    private PedidoService pedidoService;

    private PedidoRepository pedidoRepository;

    @DeleteMapping("/reset-pedidos")
    public ResponseEntity<String> resetPedidos() {
        pedidoService.resetPedidos();
        return ResponseEntity.ok("Pedidos reiniciados correctamente");
    }

    @GetMapping("/pedidos/pendientes")
    public List<Pedido> pendientes() {
        return pedidoRepository.findByEstado("PENDIENTE");
    }

    @GetMapping("/reportes/dias-mas-ventas")
    public List<Object[]> diasTop() {
        return pedidoRepository.diasConMasVentas();
    }
    
}
