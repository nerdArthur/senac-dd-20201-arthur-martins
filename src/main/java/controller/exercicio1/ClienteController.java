package controller.exercicio1;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.bo.exercicio1.ClienteBO;
import model.bo.exercicio1.EnderecoBO;
import model.bo.exercicio1.TelefoneBO;
import model.dao.exercicio1.ClienteDAO;
import model.vo.exercicio1.Cliente;
import model.vo.exercicio1.Endereco;
import model.vo.exercicio1.Telefone;

public class ClienteController {

	private ClienteDAO dao = new ClienteDAO();

	private ClienteBO bo = new ClienteBO();

	public ArrayList<Cliente> listarTodosOsClientes() {
		return bo.consultarClientes();
	}

	public String validarCampos(String txtCpf, String txtNome, String txtSobrenome, JComboBox cbTelefones) {
		String mensagem = "";

		mensagem += verificarTelefone(cbTelefones);

		mensagem += validarNome(txtNome);

		mensagem += validarCpf(txtCpf);

		mensagem += validarSobrenome(txtSobrenome);
		return mensagem;
	}

	public String excluirPorComboBox(Object comboBox) {
		String mensagem = "";

		Cliente cliente = new Cliente();
		cliente = (Cliente) comboBox;
		int id = cliente.getId();
		ArrayList<Telefone> telefones = cliente.getTelefones();
		if (!telefones.isEmpty()) {
			mensagem += "Não é possível excluir o cliente, pois ele possui telefone.";
		} else {
			ClienteBO bo = new ClienteBO();
			if (bo.excluirPorId(id)) {
				mensagem = "";
			} else {
				mensagem += "Não foi possível excluir o cliente.";
			}
		}

		return mensagem;
	}

	public String excluirPorCpf(String txtCpf) {
		String mensagem = validarCpf(txtCpf);
		if (mensagem.isEmpty()) {
			ClienteBO bo = new ClienteBO();
			bo.excluir(txtCpf);
		}

		return mensagem;
	}

	public String validarNome(String txtNome) {
		String mensagem = "";
		if (txtNome.length() < 3) {
			mensagem += "O nome deve conter pelo menos 3 caracteres.\n";
		} else {
			for (int i = 0; i < txtNome.length(); i++) {
				char a = txtNome.charAt(i);
				if (!Character.isLetter(a)) {
					mensagem += "O nome não pode conter números.\n";
					break;
				}
			}
		}

		return mensagem;
	}

	public String validarSobrenome(String txtSobrenome) {
		String mensagem = "";

		if (txtSobrenome.isEmpty()) {
			mensagem += "O campo de sobrenome deve ser preenchido.";
		} else {
			for (int i = 0; i < txtSobrenome.length(); i++) {
				char a = txtSobrenome.charAt(i);
				if (!Character.isLetter(a)) {
					mensagem += "O sobrenome não pode conter números.\n";
					break;
				}
			}
		}
		return mensagem;
	}

	public String cpfExistente(String txtCpf) {
		String mensagem = "";

		ClienteBO cliente = new ClienteBO();
		if (cliente.existeCpf(txtCpf)) {
			mensagem += "Este cpf já está sendo utilizado.\n";
		}
		return mensagem;
	}

	public String validarCpf(String txtCpf) {
		String mensagem = "";

		if (txtCpf.length() != 11) {
			mensagem += "O cpf deve possuir 11 dígitos.\n";
		}
		if (txtCpf.isEmpty()) {
			mensagem += "O campo do cpf não foi preenchido.\n";
		}
		mensagem += cpfExistente(txtCpf);

		return mensagem;
	}

	// clientes que possuem telefones não podem ser excluídos
	public String possuiTelefone(String txtTelefone) {
		String mensagem = "";
		ClienteBO clienteBO = new ClienteBO();
		if (clienteBO.telefoneExistente(txtTelefone)) {
			mensagem += "Este telefone já existe.";
		}
		return mensagem;
	}

	public int selecionarIdEndereco(Object endereco) {
		Endereco idEndereco = (Endereco) endereco;
		return idEndereco.getId();
	}

	public String validarExclusao(String txtCpf) {
		String mensagem = "";

		mensagem += validarCpf(txtCpf);
		if (mensagem.isEmpty()) {
			ClienteBO bo = new ClienteBO();
		}

		return mensagem;
	}

	public String salvarCliente(String txtNome, String txtSobrenome, String txtCpf, Object txtEndereco,
			JComboBox cbTelefones) {
		String mensagem = "";

		mensagem += validarCampos(txtCpf, txtNome, txtSobrenome, cbTelefones);
		if (!mensagem.isEmpty()) {
			JOptionPane.showMessageDialog(null, mensagem);
		} else {

			ClienteBO bo = new ClienteBO();
			Cliente cliente = criarCliente(txtNome, txtSobrenome, txtCpf, txtEndereco, cbTelefones);
			bo.salvar(cliente);

		}
		return mensagem;
	}

	public Cliente criarCliente(String txtNome, String txtSobrenome, String txtCpf, Object txtEndereco,
			JComboBox cbTelefones) {
		Cliente cliente = new Cliente();
		
		String validacao = cbTelefones.getSelectedItem().toString();
		Telefone telefone = new Telefone();
		if (!validacao.equalsIgnoreCase("Selecione um ítem")) {
			telefone = (Telefone) cbTelefones.getSelectedItem();
		}
		
		cliente.setNome(txtNome);
		cliente.setSobrenome(txtSobrenome);
		cliente.setCpf(txtCpf);

		Endereco endereco = (Endereco) txtEndereco;
		cliente.setEndereco(endereco);

		ArrayList<Telefone> telefones = new ArrayList<Telefone>();
		telefones.add(telefone);
		cliente.setTelefones(telefones);

		return cliente;
	}

	public void atualizarComboBox(JComboBox comboBox, Object item) {
		comboBox.removeItem(item);
	}

	public String verificarTelefone(JComboBox cbTelefones) {
		String mensagem = "";
		String validacao = cbTelefones.getSelectedItem().toString();
		Telefone telefone = new Telefone();
		if (validacao.equalsIgnoreCase("Selecione um ítem")) {
		} else {
			telefone = (Telefone) cbTelefones.getSelectedItem();
			String txtNumero = telefone.getNumero();
			TelefoneController telefoneController = new TelefoneController();
			if (!telefoneController.validarTelefone(txtNumero).isEmpty()) {
				mensagem += telefoneController.validarTelefone(txtNumero);
			}
		}
		return mensagem;
	}

	public void preencherClientes(JComboBox comboBox) {
		ClienteBO clienteBO = new ClienteBO();
		ArrayList<Cliente> clientes = clienteBO.consultarClientes();
		comboBox.addItem("Selecione um ítem");
		for (Cliente cliente : clientes) {
			comboBox.addItem((Cliente) cliente);
		}
	}

}