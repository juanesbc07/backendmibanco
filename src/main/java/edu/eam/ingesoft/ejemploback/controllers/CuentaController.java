package edu.eam.ingesoft.ejemploback.controllers;

import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping("/accounts")
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta){
        return cuentaService.crearCuenta(cuenta);
    }

    @GetMapping("/customers/{cedula}/accounts")
    public List<Cuenta> listarCuentasCliente(@PathVariable String cedula) {
        return cuentaService.listarCuentasCliente(cedula);
    }

    @DeleteMapping("/accounts/{id}")
    public void borrarCuenta(@PathVariable String id)
    {
        cuentaService.cancelarCuenta(id);
    }

    @DeleteMapping("/accounts/customer/delete/{cedulaCliente}")
    public void eliminarCliente(@PathVariable String cedulaCliente){
        cuentaService.eliminarCliente(cedulaCliente);
    }

}
