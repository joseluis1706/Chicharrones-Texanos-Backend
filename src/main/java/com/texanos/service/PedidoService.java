package com.texanos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texanos.model.*;
import com.texanos.repository.*;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public Pedido guardarPedido(Pedido pedido) {

        String codigo = generarCodigoPedido();
        pedido.setCodigo(codigo);

        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("PENDIENTE");
        pedido.setCostoDomicilio(0.0);

        double subtotal = 0;

        for (DetallePedido detalle : pedido.getDetalles()) {

            Producto producto = productoRepository
                    .findById(detalle.getProducto().getId())
                    .orElseThrow();

            detalle.setProducto(producto);
            detalle.setPedido(pedido); // 🔥 CLAVE
            detalle.setPrecio(producto.getPrecio());

            subtotal += producto.getPrecio() * detalle.getCantidad();
        }

        pedido.setSubtotal(subtotal);
        pedido.setTotal(subtotal);

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void limpiarTablas() {

        // Primero eliminar detalles (tabla hija)
        detallePedidoRepository.deleteAll();

        // Luego eliminar pedidos (tabla padre)
        pedidoRepository.deleteAll();
    }

    @Transactional
    public void resetPedidos() {

        pedidoRepository.deleteDetallePedido();
        pedidoRepository.deletePedido();

        pedidoRepository.resetDetalleAutoIncrement();
        pedidoRepository.resetPedidoAutoIncrement();
    }

    public String generarCodigoPedido() {

    int total = pedidoRepository.contarPedidosDelAnio() + 1;
    String anio = String.valueOf(LocalDate.now().getYear());
    String consecutivo = String.format("%03d", total);
    return "TEX-" + anio + "-" + consecutivo;
   }

   public List<Pedido> obtenerPedidosPendientes() {
       return pedidoRepository.findByEstado("PENDIENTE");
   }

   public Pedido buscarPorId(Long id) {
       return pedidoRepository.findById(id).orElse(null);
   }

   @Transactional
   public void completarPedido(Long id) {

       Pedido pedido = pedidoRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

       pedido.setEstado("PENDIENTE");

       pedidoRepository.save(pedido);
   }
}
