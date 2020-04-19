package model.bo.exercicio1;

import java.util.ArrayList;

import model.dao.exercicio1.TelefoneDAO;
import model.vo.exercicio1.Telefone;

public class TelefoneBO {

	private TelefoneDAO dao = new TelefoneDAO();

	public String salvar(Telefone novoTelefone) {
		String mensagem = "";

		novoTelefone = dao.salvar(novoTelefone);

		if (novoTelefone.getId() > 0) {
			mensagem = "Telefone cadastrado com sucesso";
		} else {
			mensagem = "Erro ao cadastrar telefone";
		}
		return mensagem;
	}
	
	public ArrayList<Telefone> listarTelefones () {
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		ArrayList<Telefone> telefones =  telefoneDAO.consultarTodos();
		
		return telefones;
	}
	
	public boolean possuiDono (String numero) {
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		boolean possuiDono = telefoneDAO.possuiDono(numero);
		return possuiDono;
	}
	
	public boolean alterarDono(String numero, int idCliente) {
		boolean alterou = false;
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		if (telefoneDAO.alterarDono(numero, idCliente)) {
			alterou = true;
		}
		
		return alterou;
	}

	public String excluirTelefonePorId(int id) {
		String mensagem = "";
		TelefoneDAO dao = new TelefoneDAO();
		mensagem = dao.excluirTelefonePorId(id);
		return mensagem;
	}

}