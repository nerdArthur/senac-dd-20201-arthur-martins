package view.aula10;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.exercicio1.ClienteController;
import view.exercicio1.cadastro.TelaCadastroTelefone;

public class InternalFrameCadastroTelefone extends JInternalFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroTelefone frame = new TelaCadastroTelefone();
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
	public InternalFrameCadastroTelefone() {
		setClosable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 388, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCodigoPais = new JLabel("Código do país:");
		lblCodigoPais.setBounds(12, 16, 94, 16);
		contentPane.add(lblCodigoPais);

		final JTextField txtCodigoPais = new JTextField();
		txtCodigoPais.setBounds(118, 13, 23, 22);
		contentPane.add(txtCodigoPais);

		JLabel lblDdd = new JLabel("DDD:");
		lblDdd.setBounds(72, 51, 34, 16);
		contentPane.add(lblDdd);

		final JTextField txtDdd = new JTextField();
		txtDdd.setBounds(118, 48, 23, 22);
		contentPane.add(txtDdd);

		JLabel lblNumero = new JLabel("Número:");
		lblNumero.setBounds(165, 19, 56, 16);
		contentPane.add(lblNumero);

		final JTextField txtNumero = new JTextField();
		txtNumero.setBounds(236, 16, 82, 22);
		contentPane.add(txtNumero);

		final JRadioButton rbMovel = new JRadioButton("Movel");
		rbMovel.setBounds(165, 51, 61, 25);
		rbMovel.setSelected(true);
		contentPane.add(rbMovel);

		final JRadioButton rbAtivo = new JRadioButton("Ativo");
		rbAtivo.setBounds(240, 51, 61, 25);
		rbAtivo.setSelected(true);
		contentPane.add(rbAtivo);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					setClosed(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCancelar.setBounds(204, 149, 97, 25);
		contentPane.add(btnCancelar);

		JLabel lblDono = new JLabel("Dono:");
		lblDono.setBounds(12, 99, 34, 16);
		contentPane.add(lblDono);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(61, 96, 257, 22);
		contentPane.add(comboBox);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(84, 149, 97, 25);
		contentPane.add(btnCadastrar);

	}
}
