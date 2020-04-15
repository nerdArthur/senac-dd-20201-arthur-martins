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
	
	private int limitadorDeTelas = 0;

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
		setJMenuBar(menuBar);
		
		JMenu menuCliente = new JMenu("Clientes");
		menuCliente.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/icons8_user_50px.png")));
		menuBar.add(menuCliente);
		
		final JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JMenuItem menuItemCadastroCliente = new JMenuItem("Cadastrar cliente");
		menuItemCadastroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(limitadorDeTelas == 0) {
					limitadorDeTelas += 1;
					InternalFrameCadastroCliente cadastroCliente = new InternalFrameCadastroCliente();
					desktopPane.add(cadastroCliente);
					cadastroCliente.show();
				}
			}
		});
		menuItemCadastroCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
		menuItemCadastroCliente.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/icons8_add_user_male_80px.png")));
		menuCliente.add(menuItemCadastroCliente);
		
		JMenu menuAutores = new JMenu("Autores");
		menuBar.add(menuAutores);
		
		
	}
}
