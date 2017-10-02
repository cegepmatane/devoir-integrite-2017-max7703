package ca.qc.cgmatane.informatique.devoirintegrite.accesseur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ca.qc.cgmatane.informatique.devoirintegrite.modele.Todo;

public class TodoDAO {

    public static Todo lireTodo (int todo_id) throws SQLException, ClassNotFoundException {
        String SQL_UN_TODO = "SELECT * FROM todo WHERE id_todo = ?";

        PreparedStatement requete = null;
        ResultSet resultat = null;
        Todo todo;
        try {
        	BaseDeDonnee.ConnexionBDD();
        	requete = BaseDeDonnee.connexion.prepareStatement(SQL_UN_TODO);
        	requete.setInt(1, todo_id);
        	resultat = requete.executeQuery();
            todo = null;
            if (resultat.next()) {
                todo = new Todo();
                todo.setTodoId(resultat.getInt("id_todo"));
                todo.setTitre(resultat.getString("titre"));
                todo.setDescription(resultat.getString("description"));
                todo.setIdPersonne(resultat.getInt("id_personne"));
                todo.setDate(resultat.getString("date"));
                todo.setFini(resultat.getString("fini"));
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
        return todo;
    }
    public static ObservableList<Todo> listerTousLesTodos() throws SQLException, ClassNotFoundException {
        String SQL_LISTER_TODO = "SELECT * FROM todo";
        
        ObservableList<Todo> listeTodo = FXCollections.observableArrayList();
        
        PreparedStatement requete = null;
        ResultSet resultat = null;
        try {
        	BaseDeDonnee.ConnexionBDD();
        	requete = BaseDeDonnee.connexion.prepareStatement(SQL_LISTER_TODO);
        	resultat = requete.executeQuery();
            while (resultat.next()) {
            	Todo todo = new Todo();
                todo.setTodoId(resultat.getInt("id_todo"));
                todo.setTitre(resultat.getString("titre"));
                todo.setDescription(resultat.getString("description"));
                todo.setIdPersonne(resultat.getInt("id_personne"));
                todo.setDate(resultat.getString("date"));
                todo.setFini(resultat.getString("fini"));

                listeTodo.add(todo);
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
        return listeTodo;
    }

    public static void modifierTodo (int todo_id, String titre, String description, String date, int id_personne) throws SQLException, ClassNotFoundException {

        String SQL_MODIFIER_TODO =
                "UPDATE todo " +
                        "      SET titre = ?, description = ?, date = ?, id_personne = ?" + 
                        "    WHERE id_todo = ?";
        
        PreparedStatement requete = null;
        try {
        	BaseDeDonnee.ConnexionBDD();
        	requete = BaseDeDonnee.connexion.prepareStatement(SQL_MODIFIER_TODO);
        	requete.setString(1, titre);
        	requete.setString(2, description);
        	requete.setString(3, date);
        	requete.setInt(4, id_personne);
        	requete.setInt(5, todo_id);
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

    public static void effacerTodo (int todo_id) throws SQLException, ClassNotFoundException {
        String SQL_EFFACER_TODO =
                "DELETE FROM todo WHERE id_todo = ?";
        PreparedStatement requete = null;
        try {
        	BaseDeDonnee.ConnexionBDD();
        	requete = BaseDeDonnee.connexion.prepareStatement(SQL_EFFACER_TODO);
 
        	requete.setInt(1, todo_id);
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

    public static void ajouterTodo (String titre, String description, String date, int id_personne) throws SQLException, ClassNotFoundException {
        String SQL_AJOUTER_TODO =
                "INSERT INTO todo" +
                        "(titre, description, date, id_personne, fini) " +
                        "VALUES(?,?,?,?,'0')" ;
        
        PreparedStatement requete = null;
        try {
        	BaseDeDonnee.ConnexionBDD();
        	requete = BaseDeDonnee.connexion.prepareStatement(SQL_AJOUTER_TODO);
        	requete.setString(1, titre);
        	requete.setString(2, description);
        	requete.setString(3, date);
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
}
