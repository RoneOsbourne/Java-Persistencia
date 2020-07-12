package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import persistencia.Salario;

public class SalarioDao implements IDao<Salario>{
   public SalarioDao() {
		try {
			createTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createTable() throws SQLException {
		final String sqlCreate = "IF NOT EXISTS (" 
				+ "SELECT * FROM sys.tables t JOIN sys.schemas s ON " 
				+ "(t.schema_id = s.schema_id) WHERE s.name = 'dbo'" 
				+ "AND t.name = 'Salario')"
				+ "CREATE TABLE Salario"
				+ " (id	int	IDENTITY,"
				+ "  valor	double,"
				+ "  PRIMARY KEY (id))";
		
		Connection conn = DatabaseAccess.getConnection();
		
		Statement stmt = conn.createStatement();
		stmt.execute(sqlCreate);
	}

	public List<Salario> getAll() {
		Connection conn = DatabaseAccess.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Salario> salarios = new ArrayList<Salario>();
		
		try {
			stmt = conn.createStatement();
			
			String SQL = "SELECT * FROM Salario"; 
	        rs = stmt.executeQuery(SQL); 
	        
	        while (rs.next()) {
	        	Salario d = getSalarioFromRs(rs);
	        	
	        	salarios.add(d);
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[getAllSalarios] Erro ao selecionar todos os valores de salarios", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return salarios;		
	}
	
	public Salario getById(int id) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Salario salario = null;
		
		try {
			String SQL = "SELECT * FROM Salario WHERE id = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			
	        rs = stmt.executeQuery(); 
	        
	        while (rs.next()) {
	        	salario = getSalarioFromRs(rs);
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[getSalarioById] Erro ao selecionar o salario por id", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return salario;		
	}
	
	public void insert(Salario salario) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		try {
			String SQL = "INSERT INTO Salario (nome) VALUES (?)";
			stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
	    	stmt.setString(1, salario.getNome()); 
	    	
			
	        stmt.executeUpdate(); 
	        
	        rs = stmt.getGeneratedKeys();
	        
	        if (rs.next()) {
	        	salario.setId(rs.getInt(1));
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[insereSalario] Erro ao inserir o salario", e);
		} finally {
			close(conn, stmt, rs);
		}
				
	}
	
	public void delete(int id) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
			
		try {
			String SQL = "DELETE Salario WHERE id=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			
	        stmt.executeUpdate(); 			
		} catch (SQLException e) {
			throw new RuntimeException("[deleteSalario] Erro ao remover o Salario por id", e);
		} finally {
			close(conn, stmt, null);
		}
	}
	
	public void update(Salario salario) {
		Connection conn = DatabaseAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		try {
			String SQL = "UPDATE Salario SET valor = ? WHERE id=?";
			stmt = conn.prepareStatement(SQL);
	    	stmt.setString(1, salario.getNome()); 
	    	
	    	stmt.setInt(2, salario.getId());
	    	
	        stmt.executeUpdate(); // executa o UPDATE			
		} catch (SQLException e) {
			throw new RuntimeException("[updateSalario] Erro ao atualizar o salario", e);
		} finally {
			close(conn, stmt, rs);
		}
				
	}
	
	private Departamento getSalarioFromRs(ResultSet rs) throws SQLException {
		Salario d = new Salario(); 
		d.setId(rs.getInt("id")); 
		d.setNome(rs.getString("valor")); 
		
		return d;
	}
	
	private void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) { rs.close(); }
			if (stmt != null) { stmt.close(); }
			if (conn != null) { conn.close(); }
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar recursos.", e);
		}
	}
}
