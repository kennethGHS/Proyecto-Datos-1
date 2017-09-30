package jsonreader;

import listas.Doble_enlazada;
import listas.ListaDobleCircular;
import listas.ListaSimple;
import listas.Nodos;
/**
 * Clase que contiene listas, la metadata
 * @author kenneth
 *
 */
public class DataLists {
	public static Doble_enlazada<Meta> metadata= new Doble_enlazada<>();
 public static Doble_enlazada<ListaDobleCircular<ListaSimple<String>>> galerias = new Doble_enlazada<>();
 /**
  * Me saca una lista doble circular de una clase en especifico
  * @param nombre
  * @return Lista doble circular segun su ID
  */
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
