package interfaz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jsonreader.searcherLists;
import listas.ListaDobleCircular;
import listas.ListaSimple;
import listas.Nodos;

public class Validadoratributos {
	public static boolean validadortipo(String dato , String tipo) {
		
		if(tipo.equals("Int")) {
			return Validadoratributos.validadorint(dato);
		}
		if(tipo.equals("String")) {
			return Validadoratributos.validarString(dato);
	
		}
		if(tipo.equals("Float")) {
			if (dato.contains(".")) {
			return Validadoratributos.validarFloat(dato);}
			else {
				return false;
			}
		}
		if(tipo.equals("Fecha")) {
			try {
			List<Boolean> a = new ArrayList<>();
			Integer.parseInt(dato.substring(0, 1));
			Integer.parseInt(dato.substring(1, 2));
			Integer.parseInt(dato.substring(3, 4));
			Integer.parseInt(dato.substring(4, 5));
			Integer.parseInt(dato.substring(6, 7));
			Integer.parseInt(dato.substring(7,8));
			a.add(dato.substring(2, 3).equals("/"));
			a.add(dato.substring(5, 6).equals("/"));
			a.add(dato.length()== 8);
			return !a.contains(false);}
			catch(Exception e) {
				return false;
			}
		}
		if(tipo.equals("Hora") && dato.length()== 5) {
			try {
			List<Boolean> a = new ArrayList<>();
			Integer.parseInt(dato.substring(0, 1));
			Integer.parseInt(dato.substring(1, 2));
			Integer.parseInt(dato.substring(3, 4));
			Integer.parseInt(dato.substring(4, 5));
			a.add(dato.substring(2, 3).equals(":"));
			a.add(dato.length()==5);
			return !a.contains(false);}
			catch(Exception a) {
				return false;
			}
		}
		else {return false;}
	}
/**
 * Valida si el dato es un float
 * @param dato
 * @return
 */
	private static boolean validarFloat(String dato) {
		try {
			Float.parseFloat(dato);
			return true;
		}catch(Exception x) {
			return false;
		}
	}
/**
 * Valida que sea un string
 * @param dato
 * @return
 */
	private static boolean validarString(String dato) {
		try {
			if(dato.contains("/") || dato.contains(":")) {
				return false;
			}
			Integer.parseInt(dato);
			return false;
		}catch(Exception e) {return true;}
		
	}
/**
 * Validador de String a int
 * @param dato
 * @return
 */
	private static boolean validadorint(String dato) {
		try {
			Integer.parseInt(dato);
			return true; 
		}catch(NumberFormatException e) {
			return false;
		}
	}
public static boolean comprobarForaneo(String atributo, HashMap<String, String> hashMap) {
	ListaDobleCircular<ListaSimple<String>> listasforean = searcherLists.optieneCircular(hashMap.get("Forean"));
	ArrayList<HashMap<String, String>> hashmapsforean = searcherLists.buscaClaseHashmaps(hashMap.get("Forean"));
	int indiceForean = sacaIndiceForeano(hashmapsforean,hashMap.get("Origen"));
	List<String> lista = sacaListasAtributosForeanos(listasforean,indiceForean);
	if(lista.contains(atributo)) {
		return true;
	}
	else {
		return false;
	}
}
private static List<String> sacaListasAtributosForeanos(ListaDobleCircular<ListaSimple<String>> listasforean,
		int indiceForean) {
	List<String> listaretornable = new ArrayList<>();
	Nodos<ListaSimple<String>> nodo = listasforean.gethead();
	boolean repetido = true;
	if(nodo==null) {
		return listaretornable;
	}
	while(nodo != listasforean.gethead()|| repetido) {
		repetido = false;
		listaretornable.add(nodo.get_objeto().getvalue(indiceForean));
		nodo= nodo.next;
	}
	return listaretornable;
}
private static int sacaIndiceForeano(ArrayList<HashMap<String, String>> hashmapsforean, String Atributo) {
	int indice = 0;
	while(indice<hashmapsforean.size()) {
		if(hashmapsforean.get(indice).get("nombre").equals(Atributo)) {
			return indice;
		}
		indice++;
	}
	return 0;
}
public static boolean comprobarunico(ListaDobleCircular<ListaSimple<String>> galeria, HashMap<String, String> hashMap,
		int indice, String result) {
	if(galeria == null) {
		return true;
	}
	if (galeria.gethead()==null) { return true;}
	if(hashMap.get("primaria").equals("si")) {
		List<String> lista = Validadoratributos.sacaListas(galeria,indice);
		return !lista.contains(result);
	}
	else {
		return true;
	}
}
private static List<String> sacaListas(ListaDobleCircular<ListaSimple<String>> galeria, int indice) {
	Nodos<ListaSimple<String>> nodo = galeria.gethead();
	List<String> lista = new ArrayList<>();
	boolean repetido = true;
	while(nodo!= galeria.gethead() || repetido==true) {
		repetido = false;
		lista.add(nodo.get_objeto().getvalue(indice));
		nodo = nodo.next;
	}
	return lista;
}

}
