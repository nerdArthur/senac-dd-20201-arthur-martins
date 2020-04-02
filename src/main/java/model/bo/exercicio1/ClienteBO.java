package model.bo.exercicio1;

import java.util.ArrayList;

import model.dao.exercicio1.ClienteDAO;
import model.vo.exercicio1.Cliente;

public class ClienteBO {

	private ClienteDAO dao = new ClienteDAO();
	
	public String salvar(Cliente cliente) {
		String mensagem = "";
		
		if(dao.cpfJaUtilizado(cliente.getCpf())) {
			mensagem = "CPF informado (" + cliente.getCpf() + ") já foi utilizado";
		}else {
			cliente = dao.salvar(cliente);
			
			if(cliente.getId() > 0) {
				mensagem = "Cliente salvo com sucesso";
			}else {
				mensagem = "Erro ao salvar cliente";
			}
		}
		return mensagem;
	}
	
	public ArrayList<Cliente> consultarClientes () {
		return dao.consultarTodos();
	}
	
	public String excluir (String cpf) {
		String mensagem = "";
		boolean retorno = false;
		if (dao.cpfJaUtilizado(cpf)) { 
			retorno = dao.excluirPorCpf(cpf);
			if (retorno == true) {
				mensagem = "Cliente excluido com sucesso.";
			}
		} else {
			mensagem = "Este CPF não existe.";
		}
		return mensagem;
	}
	
	public boolean telefoneExistente(String txtTelefone) {
		ClienteDAO dao = new ClienteDAO();
		if(dao.telefoneExistente(txtTelefone)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean existeCpf(String txtCpf) {
		ClienteDAO cliente = new ClienteDAO();
		if(cliente.cpfJaUtilizado(txtCpf)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean excluirPorId(int id) {
		ClienteDAO dao = new ClienteDAO();
		if ( dao.excluir(id) ){
			return true;
		} else {
			return false;
		}
	}
	
}