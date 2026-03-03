package com.texanos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.texanos.model.Producto;
import com.texanos.repository.ProductoRepository;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository repository;

    public List<Producto> listarProductos() {
        return repository.findAll();
    }

    public Producto guardarProducto(Producto producto) {
        return repository.save(producto);
    }

    public boolean eliminarProducto(int producto) {
        if (!repository.existsById((long) producto)) {
            return false;
        }
        repository.deleteById((long) producto);
        return true;
    }

    
}