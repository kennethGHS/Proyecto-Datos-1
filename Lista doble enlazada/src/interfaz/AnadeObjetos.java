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

public class AnadeObjetos {
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
	boolean admin = AnadeObjetos.crearnuevaisntancia(galeria, objetos.get(indice) , lista);
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
			return false;
		}
		else {
	    lista.append(result.get());
	    return true;}
	}
	else{return false; }
}
static public void setclaseactual(String clase) {
	claseactual = clase;
}


}
