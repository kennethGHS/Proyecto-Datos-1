package interfaz;

import java.util.List;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	
	branch.getChildren().clear();
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

public static void createBranch(TreeItem<String> branch, String name) {
	TreeItem<String> nuevo = new TreeItem<String>("Galeria de " + name);
	
	branch.getChildren().add(nuevo);
	TreeItem<String> child = new TreeItem<String>(name);
	TreeCreator a = new TreeCreator();
	nuevo.setGraphic(new ImageView(a.generatepicture(1)));
	Image image13 = a.generatepicture(0);
	child.setGraphic(new ImageView(image13));
	nuevo.getChildren().add(child);
	
}
private Image generatepicture(int imagen) {
	// TODO Auto-generated method stub
	if (imagen==0) {
	return new Image(getClass().getResourceAsStream("TableIcon.png"));}
	else {
		return  new Image(getClass().getResourceAsStream("GalleryIcon.png"));
	}
}
public static void eliminatebranch() {
	List<TreeItem<String>> a =Interfaz2.padre.getChildren();
	int indice = 0;
	while (indice<a.size()) {
		if(a.get(indice).getValue().equals("Galeria de " +Interfaz2.actual)) {
			a.remove(a.get(indice));
			return;
		}
		indice++;
	}
}
}
