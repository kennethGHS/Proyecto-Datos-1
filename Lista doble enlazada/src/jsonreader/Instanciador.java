package jsonreader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import listas.Doble_enlazada;
import listas.ListaDobleCircular;
import listas.ListaSimple;
import listas.Nodos;

public class Instanciador {
	public static void instanciador() throws JsonParseException, JsonMappingException, IOException {
		Doble_enlazada<Meta> metadata = DataLists.metadata;
		Nodos<Meta> nodo = metadata.gethead();
		while (nodo!=null) {
		List<String> L = new ArrayList<String>(nodo.get_objeto().colums.get(0).values());
		Collections.reverse(L);
		Instanciador.ReadInstances(nodo.get_objeto().name, L);
		System.out.println("my objeto es"+ nodo.get_objeto().colums);
		nodo=nodo.get_next();
		}
	}
	private static void ReadInstances( String id, List<String> keys) throws JsonParseException, JsonMappingException, IOException  {
		ObjectMapper mapper = new ObjectMapper();
		String dato = "src\\"+ id;
		List<HashMap<String,String>> myObjects = mapper.readValue(new File(dato), new TypeReference<List<HashMap<String,String>>>(){});
		Instanciador.ReadInstances2(id, keys, myObjects);
    }
	private static void ReadInstances2(String id, List<String> keys,List<HashMap<String,String>> instancias) {
		ListaDobleCircular<ListaSimple<String>> lista = new ListaDobleCircular<>(id);
		int indice = 0;
		while(indice<instancias.size()) {

			Instanciador.intanciaFinal(instancias.get(indice), keys,lista);
			indice++;
		}
	 DataLists.galerias.append(lista);;
	}
	private static void intanciaFinal(HashMap<String, String> hashMap, List<String> keys, ListaDobleCircular<ListaSimple<String>> lista) {
		ListaSimple<String> instancia= new ListaSimple<>();
		int indice=0;
		while( indice< keys.size()) {
			
			instancia.append(hashMap.get(keys.get(indice)));
			indice++;
		}
		lista.append(instancia);
	}

}
