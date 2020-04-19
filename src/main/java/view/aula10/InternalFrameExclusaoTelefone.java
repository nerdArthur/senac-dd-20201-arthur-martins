package view.aula10;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import view.exercicio1.exclusao.TelaExclusaoTelefone;

public class InternalFrameExclusaoTelefone extends JInternalFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaExclusaoTelefone frame = new TelaExclusaoTelefone();
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
	public InternalFrameExclusaoTelefone() {
		setClosable(true);
		setTitle("Exclusão de telefone.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 366, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId.setBounds(128, 58, 27, 27);
		contentPane.add(lblId);

		JLabel lblNewLabel = new JLabel("Informe o id do telefone a ser excluído");
		lblNewLabel.setBounds(57, 13, 221, 16);
		contentPane.add(lblNewLabel);

		final JFormattedTextField txtId = new JFormattedTextField();
		try {
			MaskFormatter maskFormatter = new MaskFormatter("####");
			maskFormatter.install(txtId);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtId.setBounds(155, 62, 49, 22);
		contentPane.add(txtId);

		JButton btnExcluir = new JButton("Excluir");

		btnExcluir.setBounds(127, 98, 97, 25);
		contentPane.add(btnExcluir);

	}
}
