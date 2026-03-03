package com.texanos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.texanos.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
