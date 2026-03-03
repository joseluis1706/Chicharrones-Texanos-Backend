package com.texanos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.texanos.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

        List<Pedido> findByEstado(String estado);

        @Query("""
                        SELECT DATE(p.fecha), COUNT(p)
                        FROM Pedido p
                        GROUP BY DATE(p.fecha)
                        ORDER BY COUNT(p) DESC
                        """)
        List<Object[]> diasConMasVentas();

     @Modifying
    @Query(value = "DELETE FROM detalle_pedido", nativeQuery = true)
    void deleteDetallePedido();

    @Modifying
    @Query(value = "DELETE FROM pedido", nativeQuery = true)
    void deletePedido();

    @Modifying
    @Query(value = "ALTER TABLE detalle_pedido AUTO_INCREMENT = 1", nativeQuery = true)
    void resetDetalleAutoIncrement();

    @Modifying
    @Query(value = "ALTER TABLE pedido AUTO_INCREMENT = 1", nativeQuery = true)
    void resetPedidoAutoIncrement();

    @Query(value = """
            SELECT COUNT(*)
            FROM pedido
            WHERE YEAR(fecha) = YEAR(CURDATE())
            """, nativeQuery = true)
    int contarPedidosDelAnio();
}
