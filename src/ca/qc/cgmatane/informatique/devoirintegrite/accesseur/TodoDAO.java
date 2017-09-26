package ca.qc.cgmatane.informatique.devoirintegrite.accesseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.informatique.devoirintegrite.modele.Todo;

public class TodoDAO {
	
	public String BASEDEDONNEES_DRIVER = "org.postgresql.Driver";
	public String BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/todo_database";
	public String BASEDEDONNEES_USAGER = "postgres";
	public String BASEDEDONNEES_MOTDEPASSE = "test";
	
	private Connection connection = null;
	
	public TodoDAO()
	{
		try {
			Class.forName(BASEDEDONNEES_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(BASEDEDONNEES_URL, BASEDEDONNEES_USAGER, BASEDEDONNEES_MOTDEPASSE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//connection.close();			

		
	}
	
	public List<Todo> listerTousLesTodos()
	{
		List<Todo> listeDesTodos = new ArrayList<Todo>();
		
			Statement requeteListeTodos = null;
			try {
				requeteListeTodos = connection.createStatement();
				ResultSet curseurTodo = requeteListeTodos.executeQuery("SELECT * FROM todo");
				
				while(curseurTodo.next())
				{
					int id_todo = curseurTodo.getInt("id_todo");
					String titre = curseurTodo.getString("titre");
					String date = curseurTodo.getString("date_de_realisation");
					String heure = curseurTodo.getString("heure");
					String description = curseurTodo.getString("description");
					String url = curseurTodo.getString("url");
					
					Todo todo = new Todo();
					todo.setId(id_todo);
					todo.setTitre(titre);
					todo.setDaterealisation(date);
					todo.setHeure(heure);
					todo.setDescription(description);
					todo.setUrl(url);
					
					
					listeDesTodos.add(todo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return listeDesTodos;
	}

	public void modifierTodo(Todo todo)
	{		
		try {
	        String SQL_MODIFIER_TODO = "UPDATE todo SET titre = ? " +
	                ", date_de_realisation = ? " +
	                ", heure = ? " +
	                ", description = ? " +
	                ", url = ? " +
	                "WHERE ? = id_todo";
	        
			PreparedStatement requete = connection.prepareStatement(SQL_MODIFIER_TODO);
			requete.setString(1, todo.getTitre());
			requete.setString(2, todo.getDaterealisation());
			requete.setString(3, todo.getHeure());
			requete.setString(4, todo.getDescription());
			requete.setString(5, todo.getUrl());
			requete.setInt(6, todo.getId());
			requete.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void effacerTodo(Todo todo)
	{		
		try {
			String SQL_EFFACER_TODO = "DELETE FROM todo WHERE ? = id_todo";
	        
			PreparedStatement requete = connection.prepareStatement(SQL_EFFACER_TODO);
			requete.setInt(1, todo.getId());
			requete.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void ajouterTodo(Todo todo)
	{		
		try {
			String SQL_AJOUTER_TODO = "INSERT INTO todo(titre, date_de_realisation, heure, " +
	                "description, url) VALUES(?,?,?,?,?)";
	        
			PreparedStatement requete = connection.prepareStatement(SQL_AJOUTER_TODO);
			requete.setString(1, todo.getTitre());
			requete.setString(2, todo.getDaterealisation());
			requete.setString(3, todo.getHeure());
			requete.setString(4, todo.getDescription());
			requete.setString(5, todo.getUrl());
			requete.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Todo lireTodo(int id_todo)
	{		
		Todo todo = null;
		try {
			String SQL_DEMANDE_PAR_ID = "SELECT * FROM todo WHERE id_todo = ?";
			PreparedStatement requete = connection.prepareStatement(SQL_DEMANDE_PAR_ID);
			requete.setInt(1, id_todo);
			ResultSet resultat = requete.executeQuery();
			
			
			resultat.next();
			int id = resultat.getInt("id_todo");
			String titre = resultat.getString("titre");
			String date = resultat.getString("date_de_realisation");
			String heure = resultat.getString("heure");
			String description = resultat.getString("description");
			String url = resultat.getString("url");
				
			todo = new Todo();
			todo.setId(id);
			todo.setTitre(titre);
			todo.setDaterealisation(date);
			todo.setHeure(heure);
			todo.setDescription(description);
			todo.setUrl(url);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return todo;
	}

}

