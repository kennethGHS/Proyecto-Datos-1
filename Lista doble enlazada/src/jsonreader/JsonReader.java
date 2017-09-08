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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonReader {

	@SuppressWarnings("unchecked")
	public static void Read() throws JsonParseException, JsonMappingException, IOException  {
		ObjectMapper mapper = new ObjectMapper();
        List<Meta> myObjects = mapper.readValue(jsonFile(), new TypeReference<List<Meta>>(){});
        System.out.println(myObjects.get(1).name);
    }

    private static File jsonFile() {
        return new File("src\\Data");
    }
		
		/*Path lugar=Paths.get("src\\Data");
		byte[] mapData = Files.readAllBytes(lugar);
		Map<String, ArrayList<byte[]>> myMap = new HashMap<String, ArrayList<byte[]>>();
		ObjectMapper objectMapper = new ObjectMapper();
		myMap = objectMapper.readValue(mapData, HashMap.class);
		System.out.println(myMap);
		Meta meta= objectMapper.readValues(myMap.get(0), Meta.class);*/
		
	}


