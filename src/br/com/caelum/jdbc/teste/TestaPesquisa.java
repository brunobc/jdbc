package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaPesquisa {

	public static void main(String[] args) {
		ContatoDAO dao = new ContatoDAO();
		Contato contato = dao.pesquisar(1);
		
		System.out.println("Nome: " + contato.getNome());
		System.out.println("Email: " + contato.getEmail());
		System.out.println("Endereço: " + contato.getEndereco());
		System.out.println("Data de Nascimento: "
				+ contato.getDataNascimento().getTime() + "\n");

	}

}
