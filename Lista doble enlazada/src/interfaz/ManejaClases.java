package interfaz;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jsonreader.DataLists;
import jsonreader.JsonReader;
import jsonreader.Meta;
import jsonreader.searcherLists;
import listas.Doble_enlazada;
import listas.ListaDobleCircular;
import listas.ListaSimple;
import listas.Nodos;

public class ManejaClases {
public static void clear() throws InterruptedException {
	String claseactual = Interfaz2.actual;
	ListaDobleCircular<ListaSimple<String>> elimino =searcherLists.optieneCircular(claseactual);
	elimino.deleteAll();
	CreadorTablas.creartabla(claseactual);
}
/**
 * Eliminador de clases
 * @throws JsonGenerationException
 * @throws JsonMappingException
 * @throws IOException
 */
public static void eliminar() throws JsonGenerationException, JsonMappingException, IOException{

	System.out.println("LOL");
	int nodo = searcherLists.optieneCircular2(Interfaz2.actual);
	DataLists.galerias.eliminar(nodo);
	Doble_enlazada<Meta> metadata = DataLists.metadata;
	Nodos<Meta> nodos= metadata.gethead();
	int indice = 0;

	while(!nodos.get_objeto().name.equals(Interfaz2.actual)) {
		nodos = nodos.next;
		indice++;
	}
	System.out.println(nodos.get_objeto().name);
File file = new File("src\\"+Interfaz2.actual);
file.delete();
System.out.println("ERROR FATAL");
metadata.eliminar(indice);
JsonReader.escribemeta();
TreeCreator.eliminatebranch();
}
}
