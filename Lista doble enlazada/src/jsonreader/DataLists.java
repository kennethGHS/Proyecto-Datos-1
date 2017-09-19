package jsonreader;

import listas.Doble_enlazada;
import listas.ListaDobleCircular;
import listas.ListaSimple;
import listas.Nodos;

public class DataLists {
	public static Doble_enlazada<Meta> metadata= new Doble_enlazada<>();
 public static Doble_enlazada<ListaDobleCircular<ListaSimple<String>>> galerias = new Doble_enlazada<>();
 
 public static ListaDobleCircular<ListaSimple<String>> sacaCircular(String nombre) {
	 Nodos<ListaDobleCircular<ListaSimple<String>>> nodo = galerias.gethead();
	 while (nodo!= null) {
		 if(nodo.get_objeto().id.equals(nombre)) {
			 return nodo.get_objeto();
		 }
		 nodo = nodo.next;
	 }
	 if (nodo==null) {
return null;
	 }
	return null;
	 }
}
