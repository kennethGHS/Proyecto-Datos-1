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
/**
 * Clase que convierte galerias en json
 * @author kenneth
 *
 */
public class ListstoJson {
	/**
	 * Itera las galerias para llamar a otro metodo e ir escribiendolas
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
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
/**
 * Me va llamando un metodo para crear hashmaps por cada lista y meterlos en una lista que finalmente sera escrita
 * @param objeto Lista circular con listas simple dentro
 * @param id la id de esa lista circular
 * @param colums hashmap con atributos
 * @throws JsonGenerationException
 * @throws JsonMappingException
 * @throws IOException
 */
	private static void agarracircular(ListaDobleCircular<ListaSimple<String>> objeto, String id, ArrayList<HashMap<String, String>> colums) throws JsonGenerationException, JsonMappingException, IOException {
		 List<HashMap<String,String>> myObjects = new ArrayList<HashMap<String,String>>();
		 ArrayList<String> buscadores = Meta.metalist(colums);
		 Nodos<ListaSimple<String>> nodo = objeto.gethead();
		 Nodos<ListaSimple<String>> nodo2 = objeto.gethead();
		 int condicion = 0;
		 if(nodo==null) {
			 ListstoJson.filewriter(id,myObjects);
		 }
		 if (nodo!= null) {
		 while(nodo!=nodo2 || condicion==0 ) {
			 if (condicion ==0) {
				 condicion++;
			 }
			 ListstoJson.meterhashmap(myObjects,nodo.get_objeto(),buscadores);
			 
			 nodo=nodo.get_next();
		 }
		 ListstoJson.filewriter(id,myObjects);}
		 
	}
/**
 * 
 * @param id nombre de la clase
 * @param myObjects listas de hashmap que tiene las instancias dentro
 * @throws JsonGenerationException
 * @throws JsonMappingException
 * @throws IOException
 */
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
/**
 * Crea un hashmap con los atributos y los nombres de los atributos de una lista
 * @param myObjects Hashmap con clases
 * @param variable
 * @param buscadores lista con hashmaps de la cual salen atributos
 */
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
	/**
	 * Metodo para darle la vuelta a un hashmap
	 * @param mapa
	 * @return
	 */
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
