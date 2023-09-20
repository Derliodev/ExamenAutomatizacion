package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaccion")
public class Transaccion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
	private Integer id;
	private Integer rutCliente;
	private Integer rutDueño;
	private Integer idCuenta;
	private Integer montoTranferecia;
	private Integer cuentaTranferencia;
    private String tipoCuenta;
	
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRutCliente() {
		return rutCliente;
	}
	public void setRutCliente(Integer rutCliente) {
		this.rutCliente = rutCliente;
	}
	public Integer getRutDueño() {
		return rutDueño;
	}
	public void setRutDueño(Integer rutDueño) {
		this.rutDueño = rutDueño;
	}
	public Integer getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}
	public Integer getMontoTranferecia() {
		return montoTranferecia;
	}
	public void setMontoTranferecia(Integer montoTranferecia) {
		this.montoTranferecia = montoTranferecia;
	}
	public Integer getCuentaTranferencia() {
		return cuentaTranferencia;
	}
	public void setCuentaTranferencia(Integer cuentaTranferencia) {
		this.cuentaTranferencia = cuentaTranferencia;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
    
    
}
