package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;

public class ContatoDAO {
	
	private Connection connection;
	
	public ContatoDAO() {
		this.connection =  new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Contato contato) {
		String sql = "insert into contatos " +
				"(nome, email, endereco, dataNascimento)" +
				"values (?,?,?,?)";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			
			st.setString(1, contato.getNome());
			st.setString(2, contato.getEmail());
			st.setString(3, contato.getEndereco());
			st.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			
			st.execute();
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Contato> getLista() {
		try {
			List<Contato> contatos = new ArrayList<>();
			PreparedStatement preparedStatement = this.connection.prepareStatement("select *from contatos");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Contato contato = new Contato();
				contato.setId(resultSet.getLong("id"));
				contato.setNome(resultSet.getString("nome"));
				contato.setEmail(resultSet.getString("email"));
				contato.setEndereco(resultSet.getString("endereco"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(resultSet.getDate("dataNascimento"));
				contato.setDataNascimento(data);
				
				contatos.add(contato);
			}
			resultSet.close();
			preparedStatement.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void altera(Contato contato) {
		String sql = "update contatos set nome=?, email=?, endereco=?, dataNascimento=? where id=?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, contato.getNome());
			preparedStatement.setString(2, contato.getEmail());
			preparedStatement.setString(3, contato.getEndereco());
			preparedStatement.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			preparedStatement.setLong(5, contato.getId());
			
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Contato pesquisar(int id) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("select * from contatos where id=" + id);
			ResultSet rs = stmt.executeQuery();
			
			Contato contato = new Contato();
			while (rs.next()) {
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
			}
			rs.close();
			stmt.close();
			return contato;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Contato contato) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from contatos where id=?");
			preparedStatement.setLong(1, contato.getId());
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
