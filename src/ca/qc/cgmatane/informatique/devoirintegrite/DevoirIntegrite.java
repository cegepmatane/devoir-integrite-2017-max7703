package ca.qc.cgmatane.informatique.devoirintegrite;

import java.util.Iterator;
import java.util.List;

import ca.qc.cgmatane.informatique.devoirintegrite.accesseur.PersonnesDAO;
import ca.qc.cgmatane.informatique.devoirintegrite.accesseur.TodoDAO;
import ca.qc.cgmatane.informatique.devoirintegrite.modele.Personne;
import ca.qc.cgmatane.informatique.devoirintegrite.modele.Todo;

public class DevoirIntegrite {

	public static void main(String[] args) {
		// TODO Stub de la méthode généré automatiquement
		
		TodoDAO accesseurTodo = new TodoDAO();
		List<Todo> listeTodos = accesseurTodo.listerTousLesTodos();
		System.out.println("Nombre de Todos " + listeTodos.size());
		
		for(Iterator<Todo> visiteurTodos = listeTodos.iterator(); visiteurTodos.hasNext(); )
		{
			Todo todo = visiteurTodos.next();
			System.out.println("todo " + todo.getTitre() + " " + todo.getDescription() );
		}	
		
		Todo todo = accesseurTodo.lireTodo(2);
		//accesseurTodo.ajouterTodo(todo);
		System.out.println("Le todo 2 est " + todo.getTitre());
		
		
		
		PersonnesDAO accesseurPersonnes = new PersonnesDAO();
		List<Personne> listePersonnes = accesseurPersonnes.listerToutesLesPersonnes();
		System.out.println("Nombre de Personnes " + listePersonnes.size());
		
		for(Iterator<Personne> visiteurPersonnes = listePersonnes.iterator(); visiteurPersonnes.hasNext(); )
		{
			Personne personne = visiteurPersonnes.next();
			System.out.println("personne " + personne.getNom() + " " + personne.getPrenom() );
		}	
		
		Personne personne = accesseurPersonnes.lirePersonne(2);
		//accesseurTodo.ajouterTodo(todo);
		System.out.println("La personne 2 est " + personne.getNom());
	}

}
