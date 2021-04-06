package edu.eam.ingesoft.ejemploback.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transacciones")
public class Transaccion implements Serializable {

    @Id
    @Column(name = "numero")
    private String numero;

    @Column(name = "numerocuenta")
    private String numerocuenta;

    @Column(name = "numerocuenta2")
    private String numerocuenta2;

    @Column(name ="tipocuenta")
    private String tipocuenta;

    @Column(name ="monto")
    private double monto;

    @Column(name = "fecha")
    private Date fecha;

    public Transaccion() { fecha= new Date();}

    public Transaccion(String numero, String numerocuenta, String numerocuenta2, String tipocuenta, double monto, Date fecha) {
        this.numero = numero;
        this.numerocuenta = numerocuenta;
        this.numerocuenta2 = numerocuenta2;
        this.tipocuenta = tipocuenta;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuenta(String numerocuenta) {
        this.numerocuenta = numerocuenta;
    }

    public String getNumerocuenta2() { return numerocuenta2;}

    public void setNumerocuenta2(String numerocuenta2) {
        this.numerocuenta2 = numerocuenta2;
    }

    public String getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(String tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
