package jsonreader;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import listas.Doble_enlazada;
import listas.Nodos;

public class JsonReader {

	public static void Read() throws JsonParseException, JsonMappingException, IOException  {
		ObjectMapper mapper = new ObjectMapper();
        List<Meta> myObjects = mapper.readValue(jsonFile(), new TypeReference<List<Meta>>(){});
        JsonReader.convert(myObjects);//despues de leer el meta data me lo escribe en una lista

    }

    private static File jsonFile() {
        return new File("src\\Data");
    }
    
    
    public static void filewriter(List<Meta> lista) throws JsonGenerationException, JsonMappingException, IOException{//crea un json de meta data
    	DefaultPrettyPrinter.Indenter indenter = 
		        new DefaultIndenter("    ", DefaultIndenter.SYS_LF);
		DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
		printer.indentObjectsWith(indenter);
		printer.indentArraysWith(indenter);
		
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.enable(SerializationFeature.INDENT_OUTPUT);
    	mapper.setDefaultPrettyPrinter(printer);
    	mapper.writeValue(new File("src\\Data"), lista);
    	
    }
    
    
		public static void convert(List<Meta> lista) {//me mete los datos en el estatic metadata
			Doble_enlazada<Meta> variable= DataLists.metadata;
			int limite = lista.size();
			int indice= 0;
			while(limite!=indice) {
				System.out.println(lista.get(indice));
				Meta variable2 = lista.get(indice);
				variable.append(variable2);
				indice++;
			}
		}
		
		
		public static List<Meta> convertMeta(){//me convierte el meta data en un list
			Doble_enlazada<Meta> metadata = DataLists.metadata;
			Nodos<Meta> actual = metadata.gethead();
			List<Meta> retornable = new ArrayList<Meta>();
			while(actual!=null) {
				retornable.add(actual.get_objeto());
				actual= actual.get_next();
			}
			System.out.println(retornable);
			return retornable;
		}

		public static void escribemeta() throws JsonGenerationException, JsonMappingException, IOException {
		       JsonReader.filewriter( JsonReader.convertMeta());//me escribe el documento
		}
		
	}


