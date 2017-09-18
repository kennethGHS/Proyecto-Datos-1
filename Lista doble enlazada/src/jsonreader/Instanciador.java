package jsonreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import listas.Doble_enlazada;
import listas.ListaDobleCircular;
import listas.ListaSimple;
import listas.Nodos;

public class Instanciador {
	public static void instanciador() throws JsonParseException, JsonMappingException, IOException {
		Doble_enlazada<Meta> metadata = DataLists.metadata;
		Nodos<Meta> nodo = metadata.gethead();
		while (nodo!=null) {
		List<String> L = Meta.metalist(nodo.objeto.colums);
		
		Instanciador.ReadInstances(nodo.get_objeto().name, L);
		System.out.println("my objeto es"+ nodo.get_objeto().colums);
		nodo=nodo.get_next();
		}
	}
	private static void ReadInstances( String id, List<String> keys) throws JsonParseException, JsonMappingException, IOException  {
		ObjectMapper mapper = new ObjectMapper();
		String dato = "src\\"+ id;
		try {
		List<HashMap<String,String>> myObjects = mapper.readValue(new File(dato), new TypeReference<List<HashMap<String,String>>>(){});
		Instanciador.ReadInstances2(id, keys, myObjects);}
		catch(FileNotFoundException excepcion ) {
			Instanciador.documentovacio(id);
			return;
		}
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
private static void documentovacio(String documento) throws JsonGenerationException, JsonMappingException, IOException {
	DefaultPrettyPrinter.Indenter indenter = 
	        new DefaultIndenter("    ", DefaultIndenter.SYS_LF);
	DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
	printer.indentObjectsWith(indenter);
	printer.indentArraysWith(indenter);
	    	ObjectMapper mapper = new ObjectMapper();
	    	mapper.enable(SerializationFeature.INDENT_OUTPUT);
	    	mapper.setDefaultPrettyPrinter(printer);
	    	List<HashMap<String,String>> myObjects = new ArrayList<>();
			mapper.writeValue(new File("src\\"+documento), myObjects);
	
}
}
