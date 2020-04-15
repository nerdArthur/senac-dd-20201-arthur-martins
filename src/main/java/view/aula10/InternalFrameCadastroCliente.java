package view.aula10;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.exercicio1.ClienteController;
import controller.exercicio1.EnderecoController;
import model.vo.exercicio1.Endereco;

public class InternalFrameCadastroCliente extends JInternalFrame {

	
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private EnderecoController enderecoController;
	private ClienteController clienteController;
	private JTextField txtCpf;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalFrameCadastroCliente frame = new InternalFrameCadastroCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InternalFrameCadastroCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(287, 49, 71, 16);
		contentPane.add(lblSobrenome);

		txtNome = new JTextField();
		txtNome.setBounds(97, 46, 160, 22);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel label = new JLabel("Nome:");
		label.setBounds(44, 49, 38, 16);
		contentPane.add(label);

		txtSobrenome = new JTextField();
		txtSobrenome.setColumns(10);
		txtSobrenome.setBounds(373, 46, 160, 22);
		contentPane.add(txtSobrenome);

		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(57, 95, 25, 16);
		contentPane.add(lblCpf);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(300, 95, 56, 16);
		contentPane.add(lblTelefone);

		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(24, 138, 58, 16);
		contentPane.add(lblEndereco);

		ClienteController clienteController = new ClienteController();
		ArrayList<Endereco> listaEnderecos = clienteController.preencherEndereco();
		Object enderecos[] = listaEnderecos.toArray();
		final JComboBox cbEndereco = new JComboBox(enderecos);
		cbEndereco.setBounds(97, 135, 439, 22);
		contentPane.add(cbEndereco);
		

		final JComboBox cbTelefones = new JComboBox();
		clienteController.preencherTelefones(cbTelefones);
		cbTelefones.setBounds(373, 92, 160, 22);
		contentPane.add(cbTelefones);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ClienteController controller = new ClienteController();
				controller.salvarCliente(txtNome.getText(), txtSobrenome.getText(), txtCpf.getText(),
						cbEndereco.getSelectedItem(), cbTelefones);
			}
		});

		btnCadastrar.setBounds(239, 183, 97, 25);
		contentPane.add(btnCadastrar);

		txtCpf = new JTextField();
		txtCpf.setBounds(97, 92, 116, 22);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);
	}

}
