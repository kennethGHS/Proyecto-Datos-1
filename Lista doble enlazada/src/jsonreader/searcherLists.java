package jsonreader;

import java.util.List;

import listas.Doble_enlazada;
import listas.ListaDobleCircular;
import listas.ListaSimple;
import listas.Nodos;

/**
 * Buscar atributos o clases
 * @author kenne
 *
 */
public class searcherLists {
/**
 * Se busca una clase de la lista meta
 * @param clase clase a buscar
 * @return
 */
	static public boolean buscaClase(String clase) {
		Doble_enlazada<Meta> meta = DataLists.metadata;
		Nodos<Meta> clases = meta.gethead();
		while(clases!=null) {
			if(clases.get_objeto().name.equals(clase)) {
				return true;
			}
			clases=clases.next;
		}
		return false;
	}
	/**
	 * Busca el int al que peretenece un atributo, es usado para ver si los atributos existen
	 * @param atributo atributo a buscar
	 * @param clase clase a la que pertenece
	 * @return
	 */
	static public int sacaindice(String atributo,String clase) {
		Nodos<Meta> meta = DataLists.metadata.gethead();
		while(meta!= null) {
			if(meta.get_objeto().name.equals(clase)) {
				break;
			}
			meta=meta.next;
		}
		if(meta==null) {
			return 1000;
		}
		List<String> atributos = Meta.metalist(meta.get_objeto().colums);
		int indice=0;
		while(indice!= atributos.size()) {
			if(atributos.get(indice).equals(atributo)) {
				return indice;
			}
			indice++;
		}
		return 1000;
	}
	/**
	 * 
	 * @param atributo atributo a buscar
	 * @param clase
	 * @return
	 */
	static public boolean validaAtributo(String atributo, String clase) {
		Nodos<Meta> meta = DataLists.metadata.gethead();
		Meta metadata = null;
		while(meta!= null) {
			if(meta.get_objeto().name.equals(clase)) {
				metadata = meta.get_objeto();
				break;
			}
			meta=meta.next;
		}
		List<String> atributos = Meta.metalist(meta.get_objeto().colums);
		if(atributos.contains(atributo)) {
			return true;
		}
		else {
			return false;
		}
	}
	static public ListaDobleCircular<ListaSimple<String>> optieneCircular(String Nombre){
		Nodos<ListaDobleCircular<ListaSimple<String>>> nodo = DataLists.galerias.gethead();
		while(nodo!= null) {
			if(nodo.objeto.id.equals(Nombre)) {
				return nodo.get_objeto();
			}
			nodo = nodo.next;
		}
		return null;
	}
	static public int optieneCircular2(String Nombre){
		Nodos<ListaDobleCircular<ListaSimple<String>>> nodo = DataLists.galerias.gethead();
		int indice = 0;
		while(nodo!= null) {
			if(nodo.objeto.id.equals(Nombre)) {
				
				return indice;
			}
			indice++;
			nodo = nodo.next;
		}
		return 4;
	}
}
