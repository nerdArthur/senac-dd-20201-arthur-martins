package model.dao.exercicio1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.Banco;
import model.vo.exercicio1.Cliente;
import model.vo.exercicio1.Endereco;
import model.vo.exercicio1.Telefone;

public class ClienteDAO {

	public Cliente salvar(Cliente novoCliente) {
		Connection conn = Banco.getConnection();
		String sql = " INSERT INTO CLIENTE(NOME, SOBRENOME, CPF, IDENDERECO) " + " VALUES (?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		ResultSet rs = null;
		try {
			stmt.setString(1, novoCliente.getNome());
			stmt.setString(2, novoCliente.getSobrenome());
			stmt.setString(3, novoCliente.getCpf());
			stmt.setInt(4, novoCliente.getEndereco().getId());
			stmt.execute();

			rs = stmt.getGeneratedKeys();
			int refIdGerado = 0;

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				refIdGerado = idGerado;
				novoCliente.setId(idGerado);
			}
			
			//TODO trocar o dono do telefone criado pelo idGerado.
			if (!novoCliente.getTelefones().isEmpty()) {
					TelefoneDAO telefoneDAO = new TelefoneDAO();
					telefoneDAO.alterarDono(novoCliente.getTelefones().get(0).getNumero(), refIdGerado);
					//telefoneDAO.ativarTelefones(novoCliente, novoCliente.getTelefones());
				}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo cliente.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return novoCliente;
	}

	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM CLIENTE WHERE ID= " + id;
		Statement stmt = Banco.getStatement(conn);
		
		int quantidadeLinhasAfetadas = 0;
		try {
			quantidadeLinhasAfetadas = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		boolean excluiu = quantidadeLinhasAfetadas > 0;

		if (excluiu) {
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.desativarTelefones(id);
		}

		return excluiu;
	}

	public boolean excluirPorCpf(String cpf) {
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM CLIENTE WHERE CPF = '" + cpf + "'";
		Statement stmt = Banco.getStatement(conn);
		int quantidadeLinhasAfetadas = 0;
		try {
			quantidadeLinhasAfetadas = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente por meio do cpf.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return quantidadeLinhasAfetadas > 0;
	}

	public boolean alterar(Cliente cliente) {
		Connection conn = Banco.getConnection();
		String sql = " UPDATE CLIENTE" + "SET NOME=?, SOBRENOME=?, CPF=?, IDENDERECO=? " + " WHERE ID = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		int registrosAlterados = 0;
		try {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getSobrenome());
			stmt.setString(3, cliente.getCpf());
			stmt.setInt(4, cliente.getEndereco().getId());
			stmt.setInt(5, cliente.getId());
			registrosAlterados = stmt.executeUpdate();


		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo cliente.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return registrosAlterados > 0;
	}

	public Cliente consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		String sql = "SELECT * FROM cliente WHERE id = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet rs = null;
		Cliente cliente = new Cliente();
		
		try {
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				cliente.setId(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setSobrenome(rs.getString(3));
				cliente.setCpf(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return cliente;
	}

	public ArrayList<Cliente> consultarTodos() {
		Connection conn = Banco.getConnection();
		String sql = " SELECT * FROM CLIENTE ";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet rs = null;

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			rs = stmt.executeQuery();
			while (rs.next()) {
				Cliente c = construirClienteDoResultSet(rs);
				clientes.add(c);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar clientes.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return clientes;
	}
	/**
	 * 
	 * Constrói um objeto do tipo Cliente a partir de um registro do resultSet
	 * 
	 * @param resultadoDaConsulta o item do resultSet (isto é, um registro da tabela
	 *                            Cliente)
	 * @return um objeto do tipo Cliente
	 * 
	 */
	private Cliente construirClienteDoResultSet(ResultSet rs) {
		Cliente c = new Cliente();
		try {
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setSobrenome(rs.getString("sobrenome"));
			c.setCpf(rs.getString("cpf"));

			EnderecoDAO enderecoDAO = new EnderecoDAO();
			Endereco end = enderecoDAO.consultarPorId(rs.getInt("idendereco"));
			c.setEndereco(end);

			TelefoneDAO telefoneDAO = new TelefoneDAO();
			ArrayList<Telefone> telefones = telefoneDAO.consultarTodosPorIdCliente(rs.getInt("id"));
			c.setTelefones(telefones);
		} catch (SQLException e) {
			System.out.println("Erro ao construir cliente a partir do ResultSet. Causa: " + e.getMessage());
		}

		return c;
	}

	public boolean cpfJaUtilizado(String cpf) {

		Connection conn = Banco.getConnection();
		String sql = " select id from cliente c " + "where c.cpf = '" + cpf + "'";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet rs = null;
		boolean cpfUsado = false;

		try {
			rs = stmt.executeQuery();
			cpfUsado = rs.next();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se CPF já foi usado. Causa: " + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return cpfUsado;
	}
	
	public boolean telefoneExistente(String txtTelefone) {
		Connection conn = Banco.getConnection();
		String sql = "Select id from telefone where numero = '" + txtTelefone + "'";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet rs = null;
		boolean telefoneExistente = false;
		try {
			rs = stmt.executeQuery(sql);
			telefoneExistente = rs.next();
		} catch(SQLException e) {
			System.out.println("Erro ao verificar se o telefone já existe. Causa: " + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return telefoneExistente;
	}

}