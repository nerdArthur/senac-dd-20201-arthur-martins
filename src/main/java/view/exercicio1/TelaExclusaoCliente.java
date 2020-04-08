package view.exercicio1;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.exercicio1.ClienteController;
import model.vo.exercicio1.Cliente;

import javax.swing.JComboBox;

public class TelaExclusaoCliente extends JFrame {

	private JPanel contentPane;
	private JLabel lblCliente;
	private JComboBox cbCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaExclusaoCliente frame = new TelaExclusaoCliente();
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
	public TelaExclusaoCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClienteController controller = new ClienteController();
				String mensagem = controller.excluirPorComboBox(cbCliente.getSelectedItem());
				if ( !mensagem.isEmpty() ) {
					JOptionPane.showMessageDialog(null, mensagem);
				} else {
					controller.atualizarComboBox(cbCliente, cbCliente.getSelectedItem());
				}
			}
		});
		btnExcluir.setBounds(164, 148, 97, 25);
		contentPane.add(btnExcluir);
		
		lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(12, 72, 44, 16);
		contentPane.add(lblCliente);
		
		ClienteController controller = new ClienteController();
		ArrayList<Cliente> listaClientes = controller.listarTodosOsClientes();
		Object [] clientes = listaClientes.toArray();
		cbCliente = new JComboBox(clientes);
		cbCliente.setBounds(68, 69, 328, 22);
		contentPane.add(cbCliente);
	}
}
