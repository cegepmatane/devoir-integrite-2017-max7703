package ca.qc.cgmatane.informatique.devoirintegrite.accesseur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ca.qc.cgmatane.informatique.devoirintegrite.modele.Personne;
import ca.qc.cgmatane.informatique.devoirintegrite.modele.Todo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonneDAO {
    public static Personne lirePersonne (int id_personne) throws SQLException, ClassNotFoundException {
    	String SQL_UNE_PERSONNE = "SELECT * FROM personnes WHERE id_personne=?";

        PreparedStatement requete = null;
        ResultSet resultat = null;
        Personne personne;
        try {
        	BaseDeDonnee.ConnexionBDD();
        	requete = BaseDeDonnee.connexion.prepareStatement(SQL_UNE_PERSONNE);
        	requete.setInt(1, id_personne);
        	resultat = requete.executeQuery();
        	personne = null;
            if (resultat.next()) {
            	personne = new Personne();
            	personne.setPersonneId(resultat.getInt("id_personne"));
            	personne.setPersonneNom(resultat.getString("nom"));
            	personne.setPersonnePrenom(resultat.getString("prenom"));
            	personne.setPersonneNombreTodoFait(resultat.getInt("nombre_de_todo_fait"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e);
            throw e;
        } finally {
            if (requete != null) {
            	requete.close();
            }
            BaseDeDonnee.DeconnexionBDD();
        }
        return personne;
    }
    public static ObservableList<Personne> listerToutesLesPersonnes() throws SQLException, ClassNotFoundException {
    	String SQL_LISTER_PERSONNE = "SELECT * FROM personnes";
        
    	ObservableList<Personne> listePersonne = FXCollections.observableArrayList();
    	 
        PreparedStatement requete = null;
        ResultSet resultat = null;
        try {
        	BaseDeDonnee.ConnexionBDD();
        	requete = BaseDeDonnee.connexion.prepareStatement(SQL_LISTER_PERSONNE);
        	resultat = requete.executeQuery();
            while (resultat.next()) {
            	Personne personne = new Personne();
            	personne.setPersonneId(resultat.getInt("id_personne"));
            	personne.setPersonneNom(resultat.getString("nom"));
            	personne.setPersonnePrenom(resultat.getString("prenom"));
            	personne.setPersonneNombreTodoFait(resultat.getInt("nombre_de_todo_fait"));

            	listePersonne.add(personne);
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e);
            throw e;
        } finally {
            if (requete != null) {
            	requete.close();
            }
            BaseDeDonnee.DeconnexionBDD();
        }
        return listePersonne;
    }

    public static void modifierPersonne(int id_personne, String nom, String prenom, int nombre_de_todo_fait) throws SQLException, ClassNotFoundException {

        String SQL_MODIFIER_PERSONNE =
                "UPDATE personnes" +
                        "      SET nom = ?, prenom = ?,nombre_de_todo_fait = ?" + 
                        "    WHERE id_personne = ?";
        
        PreparedStatement requete = null;
        try {
        	BaseDeDonnee.ConnexionBDD();
        	requete = BaseDeDonnee.connexion.prepareStatement(SQL_MODIFIER_PERSONNE);
        	requete.setString(1, nom);
        	requete.setString(2, prenom);
        	requete.setInt(3, nombre_de_todo_fait);
        	requete.setInt(4, id_personne);
        	requete.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erreur : " + e);
            throw e;
        } finally {
            if (requete != null) {
            	requete.close();
            }
            BaseDeDonnee.DeconnexionBDD();
        }
    }

    public static void effacerPersonne(int id_personne) throws SQLException, ClassNotFoundException {
    	String SQL_EFFACER_PERSONNE =
    			 "DELETE FROM personnes WHERE id_personne = ?";
        PreparedStatement requete = null;
        try {
        	BaseDeDonnee.ConnexionBDD();
        	requete = BaseDeDonnee.connexion.prepareStatement(SQL_EFFACER_PERSONNE);
 
        	requete.setInt(1, id_personne);
        	requete.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erreur : " + e);
            throw e;
        } finally {
            if (requete != null) {
            	requete.close();
            }
            BaseDeDonnee.DeconnexionBDD();
        }
    }

    public static void ajouterPersonne(String nom, String prenom, int nombre_de_todo_fait) throws SQLException, ClassNotFoundException {
    	String SQL_AJOUTER_PERSONNE =
                "INSERT INTO personnes" +
                        "(nom, prenom, nombre_de_todo_fait) " +
                        "VALUES(?,?,?)" ;
        
        PreparedStatement requete = null;
        try {
        	BaseDeDonnee.ConnexionBDD();
        	requete = BaseDeDonnee.connexion.prepareStatement(SQL_AJOUTER_PERSONNE);
        	requete.setString(1, nom);
        	requete.setString(2, prenom);
        	requete.setInt(3, nombre_de_todo_fait);
        	requete.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erreur : " + e);
            throw e;
        } finally {
            if (requete != null) {
            	requete.close();
            }
            BaseDeDonnee.DeconnexionBDD();
        }
    }
    public static String listerPersonneTodo(int id) throws ClassNotFoundException, SQLException
    {
    	String Chaine;
        //TEST donnée liée
        Personne personne = lirePersonne(id);
        ObservableList<Todo> listeTodo = TodoDAO.listerTousLesTodos();
        Chaine = "Personne: " + personne.getPersonneNom();
        for(Todo todo : listeTodo)
        {
        	if(todo.getIdPersonne() == personne.getPersonneId())
        	{
            	Chaine = Chaine + "\nTodo: " + todo.getTitre();
            }
        }
		return Chaine;
    }
}
