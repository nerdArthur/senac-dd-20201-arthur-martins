package controller.exercicio1;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.bo.exercicio1.ClienteBO;
import model.bo.exercicio1.TelefoneBO;
import model.vo.exercicio1.Cliente;
import model.vo.exercicio1.Telefone;

public class TelefoneController {

	private TelefoneBO bo = new TelefoneBO();

	public ArrayList<Telefone> preencherTelefone() {
		TelefoneBO telefoneBO = new TelefoneBO();
		ArrayList<Telefone> telefones = telefoneBO.listarTelefones();
		return telefones;
	}

	public void preencherClientes(JComboBox comboBox) {
		ClienteBO clienteBO = new ClienteBO();
		ArrayList<Cliente> clientes = clienteBO.consultarClientes();
		comboBox.addItem("Selecione um ítem");
		for (Cliente cliente : clientes) {
			comboBox.addItem((Cliente) cliente);
		}
	}

	public void atualizarComboBox(JComboBox comboBox, Object item) {
		comboBox.removeItem(item);
	}

	public String validarCamposNumericos(String txtCodigoPais, String txtDdd, String txtNumero, JRadioButton rbMovel,
			JRadioButton rbAtivo) {
		String mensagem = "";

		mensagem += validarDdd(txtDdd);
		mensagem += validarCodigoPais(txtCodigoPais);
		mensagem += validarNumero(txtNumero, rbMovel, rbAtivo);

		return mensagem;
	}

	public String validarNumero(String txtNumero, JRadioButton rbMovel, JRadioButton rbAtivo) {
		String mensagem = "";

		if (txtNumero.isEmpty()) {
			mensagem = "O campo de número do telefone deve ser preenchido.\n";
		} else if (txtNumero.length() < 8) {
			mensagem = "O telefone deve possuir pelo menos 8 dígitos.\n";
		}

		char caracteres[] = txtNumero.toCharArray();
		for (char c : caracteres) {
			if (Character.isAlphabetic(c)) {
				mensagem += "O número do telefone deve possuir apenas números.\n";
			}
		}

		return mensagem;
	}

	public String validarCodigoPais(String txtCodigoPais) {
		String mensagem = "";

		if (txtCodigoPais.isEmpty()) {
			mensagem = "O campo de código do país deve ser preenchido.\n";
		} else if (txtCodigoPais.length() != 2) {
			mensagem = "O código do pais deve conter apensa dois números.\n";
		}

		char caracteres[] = txtCodigoPais.toCharArray();
		for (char c : caracteres) {
			if (Character.isAlphabetic(c)) {
				mensagem += "O código do país deve possuir apenas números.\n";
			}
		}

		return mensagem;
	}

	public String validarDdd(String txtDdd) {
		String mensagem = "";
		if (txtDdd.isEmpty()) {
			mensagem = "O campo de DDD deve ser preenchido.\n";
		} else if (txtDdd.length() != 2) {
			mensagem = "O DDD deve conter 2 números.\n";
		}

		char caracteres[] = txtDdd.toCharArray();
		for (char c : caracteres) {
			if (Character.isAlphabetic(c)) {
				mensagem += "O DDD deve possuir apenas números.\n";
			}
		}

		return mensagem;
	}

	public Telefone construirTelefone(JTextField txtCodigoPais, JTextField txtDdd, JTextField txtNumero,
			JRadioButton rbMovel, JRadioButton rbAtivo, JComboBox clienteObj) {

		String mensagem = clienteObj.toString();
		Cliente cliente = new Cliente();
		if (!mensagem.equalsIgnoreCase(mensagem)) {
			cliente = (Cliente) clienteObj.getSelectedItem();
		}

		Telefone telefone = new Telefone();
		telefone.setAtivo(rbAtivo.isSelected());
		telefone.setCodigoPais(txtCodigoPais.getText());
		telefone.setDdd(txtDdd.getText());
		telefone.setDono(cliente);
		telefone.setNumero(txtNumero.getText());
		telefone.setMovel(rbMovel.isSelected());

		return telefone;
	}

	public String salvar(Telefone novoTelefone, JTextField txtCodigoPais, JTextField txtDdd, JTextField txtNumero,
			JRadioButton rbMovel, JRadioButton rbAtivo) {
		String mensagemValidacao = validarCamposNumericos(txtCodigoPais.getText(), txtDdd.getText(),
				txtNumero.getText(), rbMovel, rbAtivo);

		if (mensagemValidacao.isEmpty()) {
			mensagemValidacao = bo.salvar(novoTelefone);
		}
		return mensagemValidacao;
	}

	public String validarTelefone(String txtNumero) {
		String mensagem = "";
		//
		
		if (txtNumero.length() < 8) {
			mensagem += "O telefone deve conter pelo menos 8 números.\n";
		} else {
			TelefoneBO telefoneBO = new TelefoneBO();
			if (telefoneBO.possuiDono(txtNumero)) {
				mensagem += "Este telefone já possui dono.\n";
			}
		}
		return mensagem;
	}

}