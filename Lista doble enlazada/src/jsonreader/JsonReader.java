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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonReader {

	@SuppressWarnings("unchecked")
	public static void Read() throws IOException  {
		
		Path lugar=Paths.get("src\\Data");
		byte[] mapData = Files.readAllBytes(lugar);
		Map<String, HashMap<String, String>> myMap = new HashMap<String,HashMap<String, String>>();
		
		ObjectMapper objectMapper = new ObjectMapper();
		myMap = objectMapper.readValue(mapData, HashMap.class);
		
		
		System.out.println(myMap.get("cosa"));


	}
	public static void read2() {
		ObjectMapper mapper = new ObjectMapper();
	}
}
