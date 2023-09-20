package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cuentaCorriente")
public class CuentaCorriente {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCuenta;
	private Integer rutCliente;
	private Integer monto;
    private String ejecutivo;
	
    public Integer getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}
	public Integer getRutCliente() {
		return rutCliente;
	}
	public void setRutCliente(Integer rutCliente) {
		this.rutCliente = rutCliente;
	}
	public Integer getMonto() {
		return monto;
	}
	public void setMonto(Integer monto) {
		this.monto = monto;
	}
	public String getEjecutivo() {
		return ejecutivo;
	}
	public void setEjecutivo(String ejecutivo) {
		this.ejecutivo = ejecutivo;
	}
    
    
    
    
}
