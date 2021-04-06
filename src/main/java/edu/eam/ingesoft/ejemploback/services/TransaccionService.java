package edu.eam.ingesoft.ejemploback.services;

import edu.eam.ingesoft.ejemploback.model.Cliente;
import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.Transaccion;
import edu.eam.ingesoft.ejemploback.repositories.CuentaRepository;
import edu.eam.ingesoft.ejemploback.repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {


    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public void crearTransaccion(Transaccion transaccion){

        //Validar que existe la cuenta
        Cuenta cuenta = cuentaRepository.findById(transaccion.getNumerocuenta()).orElse(null);

        if (cuenta==null){
            throw new RuntimeException("No existe la cuenta");
        }

        //Validar que la transaccion no exista
        Transaccion transaccion1 = transaccionRepository.findById(transaccion.getNumero()).orElse(null);

        if (transaccion1!=null){
            throw new RuntimeException("Ya existe la transaccion");
        }

        transaccionRepository.save(transaccion);
    }

    public void consignarDinero(Transaccion transaccion){

        //Validar que la cuenta exista
        Cuenta cuenta = cuentaRepository.findById(transaccion.getNumerocuenta()).orElse(null);

        if (cuenta==null){
            throw new RuntimeException("No existe la cuenta");
        }

        //Validar que la transaccion no exista
        Transaccion transaccion1 = transaccionRepository.findById(transaccion.getNumero()).orElse(null);

        if (transaccion1!=null){
            throw new RuntimeException("Ya existe la transaccion");
        }

        //Se obtiene el monto de cuenta y transaccion para luego operarlos
        double dineroC = cuenta.getAmount();
        double dineroT = transaccion.getMonto();
        double dineroTotal= dineroC + dineroT;

        cuenta.setAmount(dineroTotal);

        transaccionRepository.save(transaccion);
    }

    public void retirarDinero(Transaccion transaccion){

        ////Validar que la cuenta exista
        Cuenta cuenta = cuentaRepository.findById(transaccion.getNumerocuenta()).orElse(null);

        if (cuenta==null){
            throw new RuntimeException("No existe la cuenta");
        }

        //Validar que la transaccion no exista
        Transaccion transaccion1 = transaccionRepository.findById(transaccion.getNumero()).orElse(null);

        if (transaccion1!=null){
            throw new RuntimeException("Ya existe la transaccion");
        }

        //Se obtiene el monto de cuenta y transaccion para luego operarlos
        double dineroC = cuenta.getAmount();
        double dineroT = transaccion.getMonto();
        double dineroTotal= dineroC - dineroT;

        cuenta.setAmount(dineroTotal);

        transaccionRepository.save(transaccion);
    }

    public void transferirDinero(Transaccion transaccion){

        //Validar Existencia de la cuenta remitente
        Cuenta cuentaRemite = cuentaRepository.findById(transaccion.getNumerocuenta()).orElse(null);

        if (cuentaRemite==null){
            throw new RuntimeException("No existe la cuenta remitente ");
        }

        //Validar Existencia de la cuenta destino
        Cuenta cuentaDestino = cuentaRepository.findById(transaccion.getNumerocuenta2()).orElse(null);

        if (cuentaDestino==null){
            throw new RuntimeException("No existe la cuenta destino ");
        }

        //Validar que no exista transaccion
        Transaccion transaccion1 = transaccionRepository.findById(transaccion.getNumero()).orElse(null);

        if (transaccion1!=null){
            throw new RuntimeException("Ya existe la transaccion");
        }

        // Se obtiene el dinero de las cuentas y el de transaccion
        double dineroCR = cuentaRemite.getAmount();
        double dineroCD = cuentaDestino.getAmount();
        double dineroT = transaccion.getMonto();

        //Se aplica el descuento y suma a las cuentas
        double dineroResta= dineroCR - dineroT;
        double dineroSuma= dineroCD + dineroT;

        cuentaRemite.setAmount(dineroResta);
        cuentaDestino.setAmount(dineroSuma);

        //Guardar la transaccion
        transaccionRepository.save(transaccion);
    }

    //Listar transacciones de la cuenta
    public List<Transaccion> listarTransaccionesCuenta(String id) {
        return transaccionRepository.buscarTransaccionesCuenta(id);
    }
}






