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
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import listas.Doble_enlazada;

public class JsonReader {

	public static void Read() throws JsonParseException, JsonMappingException, IOException  {
		ObjectMapper mapper = new ObjectMapper();
        List<Meta> myObjects = mapper.readValue(jsonFile(), new TypeReference<List<Meta>>(){});
        System.out.println(myObjects.get(1).colums.get(0));
        JsonReader.filewriter(myObjects);
        List<String> list = new ArrayList<String>(myObjects.get(1).colums.get(0).values());
        System.out.println(list.get(1));
        JsonReader.convert(myObjects);
        
    }

    private static File jsonFile() {
        return new File("src\\Data");
    }
    public static void filewriter(List<Meta> lista) throws JsonGenerationException, JsonMappingException, IOException{
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.writeValue(new File("src\\Data2"), lista);
    	
    }
		public static void convert(List<Meta> lista) {
			Doble_enlazada<Meta> variable= DataLists.metadata;
			int limite = lista.size();
			int indice= 0;
			while(limite!=indice) {
				System.out.println(lista.get(indice));
				Meta variable2 = lista.get(indice);
				variable.append(variable2);
				indice++;
				
			}
			System.out.println(DataLists.metadata.buscar(1).objeto.colums);
			System.out.println("____________________________");
		}

		
	}


