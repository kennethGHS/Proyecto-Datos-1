package interfaz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import jsonreader.DataLists;
import jsonreader.Meta;
import listas.Doble_enlazada;
import listas.ListaDobleCircular;
import listas.ListaSimple;
import listas.Nodos;

public class ManejaObjetos {
	public static String claseactual ="";
public static void nuevoObjeto() throws InterruptedException {
	if(claseactual=="") {
		return;
	}
	ArrayList<HashMap<String, String>> objetos = Meta.sacaListaMeta(claseactual);
	ListaDobleCircular<ListaSimple<String>> galeria = CreadorTablas.buscarlista(claseactual);
	ListaSimple<String> lista = new ListaSimple<>();
	int indice = 0;
	while(indice< objetos.size() ) {
	boolean admin = ManejaObjetos.crearnuevaisntancia(galeria, objetos.get(indice) , lista);
	if(admin == false) {
		return;
	}
	System.out.println(objetos.get(indice));
	indice++;
	}
	galeria.append(lista);
	CreadorTablas.creartabla(claseactual);
}
private static boolean crearnuevaisntancia(ListaDobleCircular<ListaSimple<String>> galeria,
		HashMap<String, String> hashMap, ListaSimple<String> lista) {
	boolean repetido = true;
	while(repetido) {
	TextInputDialog dialog = new TextInputDialog();
	dialog.setTitle(hashMap.get("nombre"));
	dialog.setHeaderText(hashMap.get("nombre"));
	dialog.setContentText("Por favor introduce:"+hashMap.get("nombre"));
	System.out.println(hashMap.get("Tipo"));
	Optional<String> result = dialog.showAndWait();
	if (result.isPresent()){
		if ( Validadoratributos.validadortipo(result.get(), hashMap.get("Tipo")) == false ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al insertar dato");
			alert.setHeaderText("Atributo " + hashMap.get("nombre") + " debe ser de tipo " + hashMap.get("Tipo") );
			alert.showAndWait(); 
		}
		else {
	    lista.append(result.get());
	    repetido = false;
	    return true;}
	}
	else{return false; }
	}
	return repetido;
}
static public void setclaseactual(String clase) {
	claseactual = clase;
}
static public void eliminarobjetos(int id) throws InterruptedException {
	ListaDobleCircular<ListaSimple<String>> lista =DataLists.sacaCircular(claseactual);
	lista.borrar(id);
	CreadorTablas.creartabla(claseactual);
}
static public void editarobjetos(int id) throws InterruptedException {
	ListaDobleCircular<ListaSimple<String>> lista =DataLists.sacaCircular(claseactual);
	ListaSimple<String> objetos = lista.buscar(id).get_objeto();
	ArrayList<HashMap<String, String>> atributos = Meta.sacaListaMeta(claseactual);
	ManejaObjetos.editor(objetos,atributos);
}
private static void editor(ListaSimple<String> objetos, ArrayList<HashMap<String, String>> atributos) throws InterruptedException {
	int indice = 0;
	Nodos<String> nodo = objetos.head;
	while (indice!= atributos.size() ) {
		try {
		TextInputDialog dialog = new TextInputDialog(objetos.getvalue(indice));
		dialog.setTitle("Editor de atributos");
		dialog.setHeaderText("Edita El atributo");
		//dialog.setContentText();

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    if(Validadoratributos.validadortipo(result.get(), atributos.get(indice).get("Tipo")) == false) {
		    	Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error al insertar dato");
				alert.setHeaderText("Atributo " + atributos.get(indice).get("nombre") + " debe ser de tipo " + atributos.get(indice).get("Tipo") );
				alert.showAndWait(); 
		    	return;
		    }
		    else {
		    	System.out.println("algomal");
		    	nodo.set_objeto(result.get());
		    	nodo = nodo.next;
		    	indice++;
		    }
		    }
		else {return;}
		}
		catch(Exception x) {return;}
		}
	
	CreadorTablas.creartabla(claseactual);
	}
	
}


