package ca.qc.cgmatane.informatique.devoirintegrite.accesseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.informatique.devoirintegrite.modele.Personne;
import ca.qc.cgmatane.informatique.devoirintegrite.modele.Todo;

public class PersonnesDAO {
	public String BASEDEDONNEES_DRIVER = "org.postgresql.Driver";
	public String BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/todo_database";
	public String BASEDEDONNEES_USAGER = "postgres";
	public String BASEDEDONNEES_MOTDEPASSE = "test";
	
	private Connection connection = null;
	
	public PersonnesDAO()
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
	
	public List<Personne> listerToutesLesPersonnes()
	{
		List<Personne> listeDesPersonnes = new ArrayList<Personne>();
		
			Statement requeteListePersonnes = null;
			try {
				requeteListePersonnes = connection.createStatement();
				ResultSet curseurPersonne = requeteListePersonnes.executeQuery("SELECT * FROM personnes");
				
				while(curseurPersonne.next())
				{
					int id_personne = curseurPersonne.getInt("id_personne");
					String nom = curseurPersonne.getString("nom");
					String prenom = curseurPersonne.getString("prenom");
					int nombre_de_todo_fait = curseurPersonne.getInt("nombre_de_todo_fait");
					
					Personne personne = new Personne();
					personne.setId_personnes(id_personne);
					personne.setNom(nom);
					personne.setPrenom(prenom);
					personne.setNombre_de_todo_fait(nombre_de_todo_fait);
					
					
					listeDesPersonnes.add(personne);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return listeDesPersonnes;
	}

	public void modifierPersonne(Personne personne)
	{		
		try {
	        String SQL_MODIFIER_PERSONNE = "UPDATE personnes SET nom = ? " +
	                ", prenom = ? " +
	                ", nombre_de_todo_fait = ? " +
	                "WHERE ? = id_personne";
	        
			PreparedStatement requete = connection.prepareStatement(SQL_MODIFIER_PERSONNE);
			requete.setString(1, personne.getNom());
			requete.setString(2, personne.getPrenom());
			requete.setInt(3, personne.getNombre_de_todo_fait());
			requete.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void effacerPersonne(Personne personne)
	{		
		try {
			String SQL_EFFACER_PERSONNE = "DELETE FROM personnes WHERE ? = id_personne";
	        
			PreparedStatement requete = connection.prepareStatement(SQL_EFFACER_PERSONNE);
			requete.setInt(1, personne.getId_personnes());
			requete.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void ajouterPersonne(Personne personne)
	{		
		try {
			String SQL_AJOUTER_PERSONNE = "INSERT INTO personnes(nom, prenom, nombre_de_todo_fait)" +
	                " VALUES(?,?,?)";
	        
			PreparedStatement requete = connection.prepareStatement(SQL_AJOUTER_PERSONNE);
			requete.setString(1, personne.getNom());
			requete.setString(2, personne.getPrenom());
			requete.setInt(3, personne.getNombre_de_todo_fait());
			requete.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Personne lirePersonne(int id_personne)
	{		
		Personne personne = null;
		try {
			String SQL_DEMANDE_PAR_ID = "SELECT * FROM personnes WHERE id_personne = ?";
			PreparedStatement requete = connection.prepareStatement(SQL_DEMANDE_PAR_ID);
			requete.setInt(1, id_personne);
			ResultSet resultat = requete.executeQuery();
			
			
			resultat.next();
			int id = resultat.getInt("id_personne");
			String nom = resultat.getString("nom");
			String prenom = resultat.getString("prenom");
			int nombre_todo_fait = resultat.getInt("nombre_de_todo_fait");
				
			personne = new Personne();
			personne.setId_personnes(id);
			personne.setNom(nom);
			personne.setPrenom(prenom);
			personne.setNombre_de_todo_fait(nombre_todo_fait);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personne;
	}

}
