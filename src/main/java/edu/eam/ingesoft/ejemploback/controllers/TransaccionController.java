package edu.eam.ingesoft.ejemploback.controllers;


import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.Transaccion;
import edu.eam.ingesoft.ejemploback.services.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/transacciones")
    public void crear(@RequestBody Transaccion transaccion){
        transaccionService.crearTransaccion(transaccion);
    }

    @PostMapping("/transacciones/consignar")
    public void consignar(@RequestBody Transaccion transaccion){
        transaccionService.consignarDinero(transaccion);
    }

    @PostMapping("/transacciones/retirar")
    public void retirar(@RequestBody Transaccion transaccion){
        transaccionService.retirarDinero(transaccion);
    }

    @PostMapping("/transacciones/transferir")
    public void transferir(@RequestBody Transaccion transaccion){
        transaccionService.transferirDinero(transaccion);
    }

    @GetMapping("/accounts/{id}/transacciones")
    public List<Transaccion> listarTransaccionesCuenta(@PathVariable String id) {
        return transaccionService.listarTransaccionesCuenta(id);
    }
}
