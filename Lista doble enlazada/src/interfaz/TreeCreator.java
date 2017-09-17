package interfaz;

import javafx.scene.control.TreeItem;
import jsonreader.Meta;
import listas.Doble_enlazada;
import listas.Nodos;
import jsonreader.DataLists;
public class TreeCreator {
static public void CrearArbol(TreeItem<String> branch) {
	Doble_enlazada<Meta> metadata = DataLists.metadata;
	Nodos<Meta> nodo = metadata.gethead();
	while(nodo!=null) {
		TreeCreator.createBranch( branch , nodo.get_objeto().name);
		nodo= nodo.next;
	}
}

private static void createBranch(TreeItem<String> branch, String name) {
	TreeItem<String> nuevo = new TreeItem<String>(name+"galleries");
	branch.getChildren().add(nuevo);
	TreeItem<String> child = new TreeItem<String>(name);
	nuevo.getChildren().add(child);
	
}
}
