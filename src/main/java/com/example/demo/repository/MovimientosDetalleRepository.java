package com.example.demo.repository;

import com.example.demo.model.MovimientosDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientosDetalleRepository extends JpaRepository<MovimientosDetalle,Long> {

    List<MovimientosDetalle> findByIdMovimientoId(Long idMovimiento);

}
