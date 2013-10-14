package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaRemove {
	
	public static void main(String[] args) {
		
		ContatoDAO dao = new ContatoDAO();
		Contato contato = dao.pesquisar(1);
		dao.remove(contato);
		
		System.out.println("Removido");
	}


}
