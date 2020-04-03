package model.dao.exercicio1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.Banco;
import model.dao.BaseDAO;
import model.vo.exercicio1.Endereco;

public class EnderecoDAO implements BaseDAO<Endereco> {
	
	public Endereco salvar(Endereco novaEntidade) {
		Connection conn = Banco.getConnection();

		String sql = " INSERT INTO ENDERECO (CEP, ESTADO, CIDADE, RUA, BAIRRO, NUMERO) " + " VALUES ( "
				+ novaEntidade.getCep() + ", " + novaEntidade.getEstado() + "," + novaEntidade.getCidade() + ", "
				+ novaEntidade.getRua() + "," + novaEntidade.getBairro() + "," + novaEntidade.getNumero() + ")";

		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet rs = null;
		try {
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		 rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				novaEntidade.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println(" Erro ao salvar novo endereço. Causa: " + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return novaEntidade;
	}

	public boolean excluir(int id) {
		String sql = " DELETE FROM endereco WHERE id = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		boolean excluiu = false;
		try {
			stmt.setInt(1, id);
			int codigoRetornoUpdate = stmt.executeUpdate();

			excluiu = (codigoRetornoUpdate == Banco.CODIGO_RETORNO_SUCESSO_EXCLUSAO);
		} catch (SQLException ex) {
			System.out.println(" Erro ao excluir endereço. Id: " + id + " .Causa: " + ex.getMessage());
		}finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return excluiu;
	}

	public boolean alterar(Endereco entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	public Endereco consultarPorId(int id) {
		String sql = " SELECT * FROM endereco WHERE id = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet rs = null;
		Endereco enderecoConsultado = null;
		try {
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				enderecoConsultado = construirEnderecoDoResultSet(rs);
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar endereço. Id: " + id + " .Causa: " + ex.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return enderecoConsultado;
	}

	private Endereco construirEnderecoDoResultSet(ResultSet conjuntoResultante) {
		Endereco e = new Endereco();
		try {
			e.setId(conjuntoResultante.getInt("id"));
			e.setCep(conjuntoResultante.getString("cep"));
			e.setBairro(conjuntoResultante.getString("bairro"));
			e.setCidade(conjuntoResultante.getString("cidade"));
			e.setEstado(conjuntoResultante.getString("estado"));
			e.setRua(conjuntoResultante.getString("rua"));
			e.setNumero(conjuntoResultante.getString("numero"));
		} catch (SQLException ex) {
			System.out.println(" Erro ao construir endereço a partir do ResultSet. Causa: " + ex.getMessage());
		}
		return e;
	}

	public ArrayList<Endereco> consultarTodos() {
		String sql = " SELECT * FROM endereco ";

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet rs = null;
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		try {
			rs = stmt.executeQuery();

			while (rs.next()) {
				Endereco enderecoConsultado = construirEnderecoDoResultSet(rs);
				enderecos.add(enderecoConsultado);
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar endereços. Causa: " + ex.getMessage());
		}finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return enderecos;
	}

	public boolean temEnderecoCadastradoComId(int idSelecionado) {
		String sql = " SELECT id FROM endereco WHERE id = " + idSelecionado;

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet rs = null;

		boolean enderecoJaCadastrado = false;
		try {
			rs = stmt.executeQuery();
			enderecoJaCadastrado = rs.next();
		} catch (SQLException ex) {
			System.out.println(" Erro ao verificar se endereço consta no banco. Causa: " + ex.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return enderecoJaCadastrado;
	}

}