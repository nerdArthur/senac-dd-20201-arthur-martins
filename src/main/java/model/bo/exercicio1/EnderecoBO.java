package model.bo.exercicio1;

import java.util.ArrayList;

import model.dao.exercicio1.EnderecoDAO;
import model.vo.exercicio1.Endereco;

public class EnderecoBO {

	private EnderecoDAO dao = new EnderecoDAO();

	public String excluir(int idSelecionado) {
		String mensagem = "";
		if (dao.temEnderecoCadastradoComId(idSelecionado)) {
			if (dao.excluir(idSelecionado)) {
				mensagem = "Excluído com sucesso";
			} else {
				mensagem = "Erro ao excluir";
			}
		} else {
			mensagem = "Id informado (" + idSelecionado + ") não existe no banco.";
		}

		return mensagem;
	}
	
	public ArrayList<Endereco> consultarTodosEnderecos () {
		EnderecoDAO dao = new EnderecoDAO();
		ArrayList<Endereco> enderecos = dao.consultarTodos();
		
		return enderecos;
	}
	
}