package Modelo;

public class Trabajo extends Visa{
	
	
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * La empresa en la que trabajaría el usuario que esta solicitando la visa de trabajo
	 */
	private String empresa;
	/**
	 * El cargo que ocuparía el usuario que esta solicitando la visa de trabajo
	 */
	private String cargo;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Crea una visa de tipo Trabajo para relacionarla con una solicitud determinada
	 * @param valor
	 * @param empresa
	 * @param cargo
	 */
	public Trabajo(double valor, String empresa, String cargo) {
		super(valor);
		this.empresa = empresa;
		this.cargo = cargo;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------


	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//GETTERS - SETTERS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
