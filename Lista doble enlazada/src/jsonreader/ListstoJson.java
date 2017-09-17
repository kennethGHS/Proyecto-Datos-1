package jsonreader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import listas.Doble_enlazada;
import listas.ListaDobleCircular;
import listas.ListaSimple;
import listas.Nodos;

public class ListstoJson {
	public static void galleriesTojson() throws JsonGenerationException, JsonMappingException, IOException {
		Doble_enlazada<ListaDobleCircular<ListaSimple<String>>> galeria = DataLists.galerias;
		Nodos<ListaDobleCircular<ListaSimple<String>>> nodo= galeria.gethead();
		Doble_enlazada<Meta> metadata = DataLists.metadata;
		int indice = 0;
		while(nodo!= null) {
			
			ListstoJson.agarracircular(nodo.objeto,nodo.objeto.id,metadata.buscar(indice).objeto.colums);
			nodo= nodo.get_next();
			
			indice++;
		}
	}

	private static void agarracircular(ListaDobleCircular<ListaSimple<String>> objeto, String id, ArrayList<HashMap<String, String>> colums) throws JsonGenerationException, JsonMappingException, IOException {
		 List<HashMap<String,String>> myObjects = new ArrayList<HashMap<String,String>>();
		 ArrayList<String> buscadores = Meta.metalist(colums);
		 Nodos<ListaSimple<String>> nodo = objeto.gethead();
		 Nodos<ListaSimple<String>> nodo2 = objeto.gethead();
		 int condicion = 0;
		 while(nodo!=nodo2 || condicion==0 ) {
			 if (condicion ==0) {
				 condicion++;
			 }
			 System.out.println("holaaquinoes");
			 ListstoJson.meterhashmap(myObjects,nodo.get_objeto(),buscadores);
			 nodo=nodo.get_next();
		 }
		 ListstoJson.filewriter(id,myObjects);
	}

	private static void filewriter(String id, List<HashMap<String, String>> myObjects) throws JsonGenerationException, JsonMappingException, IOException {
		DefaultPrettyPrinter.Indenter indenter = 
		        new DefaultIndenter("    ", DefaultIndenter.SYS_LF);
		DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
		printer.indentObjectsWith(indenter);
		printer.indentArraysWith(indenter);
		    	ObjectMapper mapper = new ObjectMapper();
		    	mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	mapper.setDefaultPrettyPrinter(printer);
		    	mapper.writeValue(new File("src\\"+id), myObjects);
		    	
		
	}

	private static void meterhashmap(List<HashMap<String, String>> myObjects, ListaSimple<String> variable,
			ArrayList<String> buscadores) {
		LinkedHashMap<String,String> hashmaps = new LinkedHashMap<>();
		int indice = variable.largo -1 ;
		
		while(indice!=-1) {
			hashmaps.put(buscadores.get(indice), variable.getvalue(indice));
			indice--;
		}
		myObjects.add(ListstoJson.reversehashmap(hashmaps));
		System.out.println(hashmaps);
	}
	private static LinkedHashMap<String,String> reversehashmap(LinkedHashMap<String,String> mapa) {
		List<String> keys = new ArrayList<String>(mapa.keySet());
		int indiceinverso = keys.size() -1;
		LinkedHashMap<String,String> retornable = new LinkedHashMap<String,String>() ;
		while(indiceinverso!=-1){
			retornable.put(keys.get(indiceinverso), mapa.get(keys.get(indiceinverso)));
			indiceinverso--;
		}
		return retornable;
	}

}
