package edu.eam.ingesoft.ejemploback.services;

import edu.eam.ingesoft.ejemploback.model.Cliente;
import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.Transaccion;
import edu.eam.ingesoft.ejemploback.repositories.ClienteRepository;
import edu.eam.ingesoft.ejemploback.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public  Cuenta crearCuenta(Cuenta cuenta) {
        Cliente cliente = clienteRepository.findById(cuenta.getCedulaCliente()).orElse(null);

        if (cliente == null) {
            throw new RuntimeException("no existe el cliente");
        }

        List<Cuenta> cuentasCliente = cuentaRepository.buscarCuentasCliente(cuenta.getCedulaCliente());

        if (cuentasCliente.size() == 3) {
            throw new RuntimeException("supero el limite de cuentas");
        }

        cuentaRepository.save(cuenta);

        return cuenta;
    }

    public List<Cuenta> listarCuentasCliente(String cedula) {
        return cuentaRepository.buscarCuentasCliente(cedula);
    }

    public void cancelarCuenta( String id ){

        //se obtiene la cuenta por id
        Cuenta cuentaBD = cuentaRepository.getOne(id);

        //Validar que la cuenta no tenga saldo
        if (cuentaBD.getAmount()==0){
            cuentaRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("La cuenta tiene saldo");
        }
    }

    public void eliminarCliente(String cedulaCliente){

        //Se genera una lista de cuentas por cedula del cliente
        List<Cuenta> cuentasCliente = cuentaRepository.buscarCuentasCliente(cedulaCliente);

        //Si el cliente no tiene cuentas
        if(cuentasCliente.size() == 0){
            clienteRepository.deleteById(cedulaCliente);
        }
        else{
            throw new RuntimeException("El cliente tiene cuentas");
        }

    }

}