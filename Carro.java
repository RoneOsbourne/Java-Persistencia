package persistencia;

import java.util.List;

public class Carro {
    private int id;
	private String modelo;
	private List<Pessoa> funcionarios;
	
	public Carro() { }
	
	public Carro(int id, String modelo, List<Pessoa> funcionarios) {		
		this.id = id;
		this.modelo = modelo;
		this.funcionarios = funcionarios;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getmodelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public List<Pessoa> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Pessoa> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@Override
	public String toString() {
		return "Modelo [id=" + id + ", modelo=" + modelo + "]";
	}
	
	
}
