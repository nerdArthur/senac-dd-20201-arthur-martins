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
	private int limitadorExclusaoTelefones = 0;
	private int limitadorListagemTelefones = 0;
	private int limitadorCadastroTelefones = 0;
	private InternalFrameCadastroCliente cadastroCliente;
	private InternalFrameCadastroTelefone cadastroTelefone;
	private InternalFrameExclusaoTelefone exclusaoTelefone;
	private InternalFrameListagemTelefone listagemTelefone;
	private TelaSobreAutores sobreAutor;

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

		JMenu menuSobre = new JMenu("Sobre");
		menuSobre.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/icons8_info_80px.png")));
		menuSobre.setForeground(Color.WHITE);
		menuSobre.setBackground(Color.DARK_GRAY);
		menuBar.add(menuSobre);

		JMenuItem menuItemAutores = new JMenuItem("Autores");
		menuItemAutores.setForeground(Color.WHITE);
		menuItemAutores.setBackground(Color.DARK_GRAY);					
		menuItemAutores.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/icons8_hand_with_pen_80px.png")));
		menuItemAutores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		menuItemAutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (limitadorDeTelaAutores == 0) {
					limitadorDeTelaAutores++;
					sobreAutor = new TelaSobreAutores();
					sobreAutor.setVisible(true);
				}
				if (!sobreAutor.isDisplayable()) {
					limitadorDeTelaAutores--;
				}
			}
		});
		menuSobre.add(menuItemAutores);
		
		JMenu menuListagemTelefone = new JMenu("Telefones");
		menuListagemTelefone.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/icons8_phone_80px_5.png")));
		menuListagemTelefone.setBackground(Color.DARK_GRAY);
		menuListagemTelefone.setForeground(Color.WHITE);
		menuBar.add(menuListagemTelefone);
		
		JMenuItem menuItemListarTodos = new JMenuItem("Listar todos");
		menuItemListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (limitadorListagemTelefones == 0) {
					limitadorListagemTelefones++;
					listagemTelefone = new InternalFrameListagemTelefone();
					desktopPane.add(listagemTelefone);
					listagemTelefone.show();
				}
				if (listagemTelefone.isClosed()) {
					limitadorListagemTelefones--;
				}
			}
		});
		menuItemListarTodos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
		menuItemListarTodos.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/icons8_address_book_80px.png")));
		menuItemListarTodos.setForeground(Color.WHITE);
		menuItemListarTodos.setBackground(Color.DARK_GRAY);
		menuListagemTelefone.add(menuItemListarTodos);
		
		JMenuItem menuItemExcluirTelefone = new JMenuItem("Excluir telefone");
		menuItemExcluirTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (limitadorExclusaoTelefones == 0) {
					limitadorExclusaoTelefones++;
					exclusaoTelefone = new InternalFrameExclusaoTelefone();
					desktopPane.add(exclusaoTelefone);
					exclusaoTelefone.show();
				}
				if (exclusaoTelefone.isClosed()) {
					limitadorExclusaoTelefones--;
				}
			}
		});
		menuItemExcluirTelefone.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0));
		menuItemExcluirTelefone.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/icons8_no_phones_80px.png")));
		menuItemExcluirTelefone.setForeground(Color.WHITE);
		menuItemExcluirTelefone.setBackground(Color.DARK_GRAY);
		menuListagemTelefone.add(menuItemExcluirTelefone);
		
		JMenuItem menuItemCadastrarTelefone = new JMenuItem("Cadastrar Telefone");
		menuItemCadastrarTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (limitadorCadastroTelefones == 0) {
					limitadorCadastroTelefones++;
					cadastroTelefone = new InternalFrameCadastroTelefone();
					desktopPane.add(cadastroTelefone);
					cadastroTelefone.show();
				}
				if (cadastroTelefone.isClosed()) {
					limitadorCadastroTelefones--;
				}
			}
		});
		menuItemCadastrarTelefone.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		menuItemCadastrarTelefone.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/icons8_add_phone_80px.png")));
		menuItemCadastrarTelefone.setForeground(Color.WHITE);
		menuItemCadastrarTelefone.setBackground(Color.DARK_GRAY);
		menuListagemTelefone.add(menuItemCadastrarTelefone);

	}
}
