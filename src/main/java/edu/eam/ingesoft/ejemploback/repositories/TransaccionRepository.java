package edu.eam.ingesoft.ejemploback.repositories;


import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, String> {

    @Query("SELECT o FROM Transaccion o  WHERE o.numerocuenta = :id")
    List<Transaccion> buscarTransaccionesCuenta(String id);
}
