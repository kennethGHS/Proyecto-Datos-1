package listas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import jsonreader.DataLists;
import jsonreader.Meta;
import jsonreader.searcherLists;

public class ForeanDelete {

	public static boolean comprobante(Nodos<ListaSimple<String>> ListadeClase2, String clase) {
		ArrayList<HashMap<String, String>> hashmapsClase = searcherLists.buscaClaseHashmaps(clase);
		ListaSimple<String> ListadeClase = ListadeClase2.get_objeto();
		int indice= 0;
		List<Boolean> lista = new ArrayList<>();
		while(indice<hashmapsClase.size()) {
			lista.add(Comprueba(hashmapsClase.get(indice).get("nombre"),clase,ListadeClase.getvalue(indice)));
		indice++;
		}
		if(lista.contains(false)) {
			return false;
		}
		else{
			return true;
		}
	}

	public static boolean Comprueba(String nombreatributo, String clase, String valor) {
		System.out.println("Entrada");
		List<Boolean> lista = new ArrayList<>();
		Doble_enlazada<Meta> metadata = DataLists.metadata;
		Nodos<Meta> nodo = metadata.gethead();
		while(nodo!=null) {
		
			if(!nodo.get_objeto().name.equals(clase)) {
			Comprobante(nombreatributo,clase,valor,nodo.get_objeto(),lista);
			}
			nodo=nodo.next;
		}
		if(lista.isEmpty()) {
			return true;
		}
		
		System.out.println(lista );
		return !lista.contains(false);
	}

	private static void Comprobante(String nombreatributo, String clase, String valor, Meta clasereceptora,
			List<Boolean> lista) {
		
		int indice = 0;
		while(indice<clasereceptora.colums.size()) {
			System.out.println(nombreatributo + "ALGO MAL PAPUS");
			if(clasereceptora.colums.get(indice).get("Origen").equals(nombreatributo)&&clasereceptora.colums.get(indice).get("Forean").equals(clase)) {
				System.out.println(searcherLists.optieneCircular(clasereceptora.name));
				
			comprobanteFinal(indice,valor,searcherLists.optieneCircular(clasereceptora.name),lista);	
		
			}
			indice++;
		}
	}

	private static void comprobanteFinal(int indice, String valor,
			ListaDobleCircular<ListaSimple<String>> Circular, List<Boolean> lista) {
		System.out.println("___________________");
		
		Nodos<ListaSimple<String>> nodo = Circular.gethead();
		boolean repetido = true;
		int aux = 0;
		if(nodo==null) {
			return;
		}
		while(nodo!= Circular.head || repetido) {
			
			if(Circular.head.equals(null)) {
				
				break;
			}
			repetido=false;
			if(nodo.get_objeto().getvalue(indice).equals(valor)) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText( "La instancia nuemero " +aux+ " de la clase " +Circular.id +" usa el atributo " + valor);
				alert.showAndWait(); 
				lista.add(false);
			}
			else {
				lista.add(true);
			}
			nodo = nodo.next;
			aux++;
		}
		
	}

}


