package interfaz;

import java.util.ArrayList;
import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import listas.GenericObject;

public class TableSearch {
public static void buscarLista(String objeto) {
	ObservableList<GenericObject> objetos = Interfaz2.nodo.get_objeto().getItems();
	ObservableList<GenericObject> coincidencias = TableSearch.crearlista(objetos,objeto);
	if(coincidencias.isEmpty()) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("No encontrado");
		alert.setHeaderText("No se encontro el objeto con esa caracteristica" );
		alert.showAndWait(); 
		return;
	}
	Interfaz2.nodo.get_objeto().getItems().clear();
	Interfaz2.nodo.get_objeto().getItems().addAll(coincidencias);
}

private static ObservableList<GenericObject> crearlista(ObservableList<GenericObject> objetos, String objeto) {
	 ArrayList<GenericObject> coincidencia= new ArrayList<GenericObject>();
	int indice = 0;
	 while (indice!= objetos.size()) {
		 if(objetos.get(indice).toList().contains(objeto)) {
			 coincidencia.add(objetos.get(indice));
		 }
		 indice++;
		 
	 }
	 Collection<GenericObject> lista = coincidencia;
	 ObservableList<GenericObject> retornable = FXCollections.observableArrayList(lista);
	 return retornable;
}
}
