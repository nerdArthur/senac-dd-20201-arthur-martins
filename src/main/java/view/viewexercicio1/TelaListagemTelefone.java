package view.viewexercicio1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.exercicio1.TelefoneController;
import model.dao.exercicio1.ClienteDAO;
import model.vo.exercicio1.Telefone;

public class TelaListagemTelefone extends JFrame {

	private JPanel contentPane;
	private ArrayList<Telefone> telefones;
	private String[] nomeColunas = { "Código País", "DDD", "Numero", "Dono" };
	private JTable tblTelefones;

	private void limparTabela() {
		tblTelefones.setModel(new DefaultTableModel(new Object[][] { nomeColunas, }, nomeColunas));
	}

	private void atualizarTabela() {
		limparTabela();
		DefaultTableModel model = (DefaultTableModel) tblTelefones.getModel();
		for (Telefone telefone : telefones) {
			String teste = "teste";
			Object[] novaLinha = new Object[4];
			
			novaLinha[0] = telefone.getCodigoPais();
			novaLinha[1] = telefone.getDdd();
			novaLinha[2] = telefone.getNumero();
			
			if (telefone.getDono() == null) {
				novaLinha[3] = teste;
			} else {
				if (telefone.getDono().getId() == 0) {
					novaLinha[3] = "Nenhum";
				}
				novaLinha[3] = telefone.getDono().getNome();
			}

			model.addRow(novaLinha);
		}
	
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemTelefone frame = new TelaListagemTelefone();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaListagemTelefone() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 68, 408, 133);
		contentPane.add(panel);

		tblTelefones = new JTable();
		panel.add(tblTelefones);

		JLabel lblListagem = new JLabel("Listagem de Telefones");
		lblListagem.setBounds(143, 25, 129, 16);
		contentPane.add(lblListagem);

		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelefoneController controller = new TelefoneController();
				telefones = controller.preencherTelefone();
				atualizarTabela();
			}
		});
		btnListar.setBounds(175, 214, 97, 25);
		contentPane.add(btnListar);
	}
}
