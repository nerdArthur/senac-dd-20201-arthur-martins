package controller.exercicio1;

import java.util.ArrayList;

import model.bo.exercicio1.EnderecoBO;
import model.dao.exercicio1.EnderecoDAO;
import model.vo.exercicio1.Endereco;

public class EnderecoController {

	private EnderecoBO bo = new EnderecoBO();

	public String excluir(String textoIdSelecionado) {

		String mensagem = "";
		try {
			int idSelecionado = Integer.parseInt(textoIdSelecionado);
			mensagem = bo.excluir(idSelecionado);
		} catch (NumberFormatException ex) {
			mensagem = "Informe um número inteiro";
		}
		return mensagem;
	}
	
	public ArrayList<Endereco> consultarTodosEnderecos() {
		ArrayList<Endereco> enderecos = bo.consultarTodosEnderecos();
		return enderecos;
	}
	
	public ArrayList<Endereco> preencherEndereco() {
		EnderecoBO enderecoBO = new EnderecoBO();
		ArrayList<Endereco> enderecos = enderecoBO.consultarTodosEnderecos();
		return enderecos;
	}

}