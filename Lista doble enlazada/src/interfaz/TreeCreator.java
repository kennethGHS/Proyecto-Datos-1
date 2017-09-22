package interfaz;

import javafx.scene.control.TreeItem;

import jsonreader.Meta;
import listas.Doble_enlazada;
import listas.Nodos;
import jsonreader.DataLists;
/**
 * Crea el arbol mediante atributos
 * @author kenne
 *
 */
public class TreeCreator {
static public void CrearArbol(TreeItem<String> branch) {
	Doble_enlazada<Meta> metadata = DataLists.metadata;
	Nodos<Meta> nodo = metadata.gethead();
	while(nodo!=null) {
		TreeCreator.createBranch( branch , nodo.get_objeto().name);
		nodo= nodo.next;
	}
}
/**
 * Crea branches del arbol
 * @param branch
 * @param name
 */

private static void createBranch(TreeItem<String> branch, String name) {
	TreeItem<String> nuevo = new TreeItem<String>("Galeria de " + name);
	branch.getChildren().add(nuevo);
	TreeItem<String> child = new TreeItem<String>(name);
	nuevo.getChildren().add(child);
	
}
}
