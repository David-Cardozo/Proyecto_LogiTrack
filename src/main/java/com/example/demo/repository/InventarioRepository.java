package com.example.demo.repository;

import com.example.demo.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Long> {

    boolean existsByIdBodega_IdAndIdProducto_Id(Long idBodega, Long idProducto);

    Inventario findByIdBodega_IdAndIdProducto_Id(Long idBodega, Long idProducto);
}
