package view.aula10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class MenuPrincipal extends JFrame {

	private int limitadorDeTelaClientes = 0;
	private int limitadorDeTelaAutores = 0;
	private InternalFrameCadastroCliente cadastroCliente;
	private TelaSobreAutor sobreAutor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
					frame.setExtendedState(MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);

		JMenu menuCliente = new JMenu("Clientes");
		menuCliente.setForeground(Color.WHITE);
		menuCliente.setBackground(Color.DARK_GRAY);
		menuCliente.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/icons8_user_50px.png")));
		menuBar.add(menuCliente);

		final JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(desktopPane, BorderLayout.CENTER);

		JMenuItem menuItemCadastroCliente = new JMenuItem("Cadastrar cliente");
		menuItemCadastroCliente.setForeground(Color.WHITE);
		menuItemCadastroCliente.setBackground(Color.DARK_GRAY);
		menuItemCadastroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (limitadorDeTelaClientes == 0) {
					limitadorDeTelaClientes++;
					cadastroCliente = new InternalFrameCadastroCliente();
					desktopPane.add(cadastroCliente);
					cadastroCliente.show();
				}
				if (cadastroCliente.isClosed()) {
					limitadorDeTelaClientes--;
				}
			}
		});
		menuItemCadastroCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
		menuItemCadastroCliente
				.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/icons8_add_user_male_80px.png")));
		menuCliente.add(menuItemCadastroCliente);

		JMenu menuAutores = new JMenu("Autores");
		menuAutores.setForeground(Color.WHITE);
		menuAutores.setBackground(Color.DARK_GRAY);
		menuBar.add(menuAutores);

		JMenuItem menuItemSobre = new JMenuItem("Sobre");
		menuItemSobre.setForeground(Color.WHITE);
		menuItemSobre.setBackground(Color.DARK_GRAY);
		menuItemSobre.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/icons8_info_80px.png")));
		menuItemSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		menuItemSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (limitadorDeTelaAutores == 0) {
					limitadorDeTelaAutores++;
					sobreAutor = new TelaSobreAutor();
					sobreAutor.setVisible(true);
				}
				if (!sobreAutor.isDisplayable()) {
					limitadorDeTelaAutores--;
				}
			}
		});
		menuAutores.add(menuItemSobre);

	}
}
