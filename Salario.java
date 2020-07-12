package persistencia;

import java.util.List;

public class Salario {
    private int id;
	private double valor;
	private List<Pessoa> funcionarios;
	
	public Salario() { }
	
	public Salario(int id, double valor, List<Pessoa> funcionarios) {		
		this.id = id;
		this.valor = valor;
		this.funcionarios = funcionarios;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getvalor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public List<Pessoa> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Pessoa> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@Override
	public String toString() {
		return "Salario [id=" + id + ", valor=" + valor + "]";
	}
	
	
}
