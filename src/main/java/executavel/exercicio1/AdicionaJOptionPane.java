package executavel.exercicio1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.dao.exercicio1.ClienteDAO;
import model.dao.exercicio1.TelefoneDAO;
import model.vo.exercicio1.Cliente;
import model.vo.exercicio1.Telefone;

public class AdicionaJOptionPane {

	public static void adicionar() {
		String codigoPais = JOptionPane.showInputDialog(null,"Digite o código do país do telefone: ", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        String ddd = JOptionPane.showInputDialog(null,"Digite o ddd do telefone: ", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        String numero = JOptionPane.showInputDialog(null,"Digite o número do telefone: ", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        int isMovel = JOptionPane.showConfirmDialog(null, "Seu telefone é móvel?", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        Boolean foiCancelado = false;
        boolean movel = false;
        //  0 = sim /  1 = não / 2 = cancelar;
        if(isMovel == 0) {
            System.out.println("O telefone é móvel");
            movel = true;
        } else if(isMovel == 1) {
            System.out.println("O telefone é fixo");
            movel = false;
        } else {
        	foiCancelado = true;
            System.out.println("Erro, processo cancelado.");
            JOptionPane.showMessageDialog(null, "Erro, processo cancelado.");
        }
       // passar id do cliente como null
        int isAtivo = JOptionPane.showConfirmDialog(null, "Seu telefone é ativo?", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        boolean ativo = true;
        if(isAtivo == 0) {
            System.out.println("O telefone é ativo");
            ativo = true;
        } else if(isAtivo == 1) {
            System.out.println("O telefone é ativo");
            ativo = true;
        } else {
        	foiCancelado = true;
            System.out.println("Erro, processo cancelado.");
            JOptionPane.showMessageDialog(null, "Erro, processo cancelado.");
        }
        
        Cliente cliente = new Cliente();
        Telefone novoTelefone = new Telefone(0, cliente, codigoPais, ddd, numero, movel, ativo);
        
        TelefoneDAO telefoneDAO = new TelefoneDAO();
        if(foiCancelado != true) {
        	telefoneDAO.salvar(novoTelefone);
        }
	}
	
	public static void adicionarComDono() {
		String codigoPais = JOptionPane.showInputDialog(null,"Digite o código do país do telefone: ", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        String ddd = JOptionPane.showInputDialog(null,"Digite o ddd do telefone: ", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        String numero = JOptionPane.showInputDialog(null,"Digite o número do telefone: ", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        int isMovel = JOptionPane.showConfirmDialog(null, "Seu telefone é móvel?", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        Boolean foiCancelado = false;
        boolean movel = false;

        if(isMovel == JOptionPane.YES_OPTION) {
            System.out.println("O telefone é móvel");
            movel = true;
        } else if(isMovel == JOptionPane.NO_OPTION) {
            System.out.println("O telefone é fixo");
            movel = false;
        } else {
        	foiCancelado = true;
            System.out.println("Erro, processo cancelado.");
        }

        int isAtivo = JOptionPane.showConfirmDialog(null, "Seu telefone é ativo?", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        boolean ativo = true;
        if(isAtivo == JOptionPane.YES_OPTION) {
            System.out.println("O telefone é ativo");
            ativo = true;
        } else if(isAtivo == JOptionPane.NO_OPTION) {
            System.out.println("O telefone é ativo");
            ativo = true;
        } else {
        	foiCancelado = true;
            System.out.println("Erro, processo cancelado.");
        }
        //pegar o dono
        
        int possuiDono = JOptionPane.showConfirmDialog(null, "Esse telefone possui dono?", "Telefone", JOptionPane.INFORMATION_MESSAGE);

        
        if (possuiDono == JOptionPane.YES_OPTION) {
        	System.out.println("O telefone possui dono.");
        	
        	ClienteDAO clienteDAO = new ClienteDAO();
        	ArrayList<Cliente> clientes = clienteDAO.consultarTodos();
        	
        	Object[] valores = clientes.toArray();
        	Cliente dono = (Cliente) JOptionPane.showInputDialog(null, "Selecione o dono", "telefone", JOptionPane.QUESTION_MESSAGE
            		, null, valores, valores);
            System.out.println(dono);
        	
        } else if (possuiDono == JOptionPane.NO_OPTION) {
        	System.out.println("O telefone não possui dono.");
        	
        } else {
        	foiCancelado = true;
        	System.out.println("Erro, processo cancelado.");
        }
        
        
        
      /*  Cliente cliente = new Cliente();
        Telefone novoTelefone = new Telefone(0, cliente, codigoPais, ddd, numero, movel, ativo);
        
        TelefoneDAO telefoneDAO = new TelefoneDAO();
        if(foiCancelado != true) {
        	telefoneDAO.salvar(novoTelefone);
        } */
	}
	
}
