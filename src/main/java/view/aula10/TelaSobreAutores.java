package view.aula10;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class TelaSobreAutores extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSobreAutores frame = new TelaSobreAutores();
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
	public TelaSobreAutores() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 728, 416);
		setTitle("Sobre o autor");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome: Arthur Martins da Silva");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNome.setBounds(106, 82, 207, 16);
		contentPane.add(lblNome);
		
		JLabel lblCurso = new JLabel("Curso: An\u00E1lise e Desenvolvimento de Sistemas");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCurso.setBounds(106, 137, 310, 16);
		contentPane.add(lblCurso);
		
		JLabel lblData = new JLabel("Data: 22/04/2020");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblData.setBounds(106, 194, 128, 16);
		contentPane.add(lblData);
		
		JLabel lblCidade = new JLabel("Cidade: Florian\u00F3polis");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCidade.setBounds(106, 250, 135, 16);
		contentPane.add(lblCidade);
	}
}
