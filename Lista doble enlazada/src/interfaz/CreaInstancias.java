package interfaz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import jsonreader.DataLists;
import jsonreader.Meta;
import jsonreader.searcherLists;

/**
 * Crea nuevas clases
 * @author kenne
 *
 */
public class CreaInstancias {
	public static void crearClase(){
		try {
		Meta nuevaclase = new Meta(); 
		nuevaclase.colums = new ArrayList<HashMap<String,String>>();
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Nombre");
		dialog.setHeaderText("Nueva clase");
		dialog.setContentText("Escribe el nombre de tu nueva clase:");

		// Traditional way to get the response value.
	Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    nuevaclase.setName(result.get());
		   boolean creacion = CreaInstancias.crearAtributos(nuevaclase);
		 if(creacion) {
			 DataLists.metadata.append(nuevaclase);
		 }
		 else {
			 Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Operacion interrumpida, se cerro alguna ventana en la elaboracion de una nueva clase" );
				alert.showAndWait(); 
		    	return;
		 }
	}
		
	}
		catch( Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Operacion interrumpida, se cerro alguna ventana en la elaboracion de una nueva clase" );
			alert.showAndWait(); 
	    	return;
		}
	}
/**
 * clase que vera que todo lo que pasa en la creacion de atributos este bien.
 * @param nuevaclase
 * @return
 */
	private static boolean crearAtributos(Meta nuevaclase) {
	 boolean condicion = true;
	 boolean termino = true;
	 List<HashMap<String,String>> atributos = nuevaclase.colums;
	 //LOOP INFINITO
		while (termino) {
			HashMap<String,String> hashmap = new HashMap<>();
			condicion = CreaInstancias.setnombre( hashmap);
			if (condicion) {
			condicion = CreaInstancias.settipe(hashmap);	
		}
			if (condicion) {
				condicion = CreaInstancias.setForean(hashmap);
			}
			if(condicion) {
				condicion = CreaInstancias.setForeanAtribute( hashmap.get("Forean"),hashmap);		
			}
			if(condicion ) {
				condicion = CreaInstancias.setgeneric(hashmap);
			}
			if(condicion) {
				atributos.add(hashmap);
			}
			if(condicion ==false) {
				return false;
			}
			if(!AlertChanges.Option()) {
				return true;
			}
			
		}
		return false;
		
	}
	private static boolean setgeneric(HashMap<String, String> hashmap) {
		boolean atributo = true;
		while (atributo) {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Atributo generico");
		dialog.setHeaderText("Introduce el atributo generico");
		dialog.setContentText("");
		Optional<String> result = dialog.showAndWait();
		
		if (result.isPresent()){
			if(result.get().equals("")) {
				hashmap.put("generico", "");
				return true;
			}
			if( Validadoratributos.validadortipo(result.get(), hashmap.get("Tipo")) == false) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error al insertar dato");
				alert.setHeaderText("Atributo " + hashmap.get("nombre") + " debe ser de tipo " + hashmap.get("Tipo") );
				alert.showAndWait(); 
			}
			
			else {
		    hashmap.put("generico", result.get());
		    return true;
		}
		}
		else {return false;}
	
}
		return false;}
	/**
	 * Define un atributo foraneo
	 * @param clase a la que pertenece
	 * @param hashmap hashmap al cual se le apendera
	 * @return
	 */
private static boolean setForeanAtribute(String clase, HashMap<String, String> hashmap) {
	try{
		if(clase.equals("")) {
			hashmap.put("Origen", "");
			return true;
	}
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Atributo pertenece");
		dialog.setHeaderText("Inserta el atributo al que pertenece");
		dialog.setContentText("");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			if(searcherLists.validaAtributo(result.get(), clase)) {
		    hashmap.put("Origen", result.get());
		    return true;}
			else {
				AlertChanges.noAtribute();
				return setForeanAtribute(clase,hashmap);
			}
		}
		else {
			return false;
		}
		
	}catch(Exception e) {
		return false;
}
	
}
/**
 * Define el atributo al que pertenece el foraneo
 * @param hashmap
 * @return
 */
private static boolean setForean(HashMap<String, String> hashmap) {
	try {
	TextInputDialog dialog = new TextInputDialog("");
	dialog.setTitle("Nombre clase");
	dialog.setHeaderText("Introduce el nombre de la clase a la que pertenece el atributo");
	dialog.setContentText("");
	Optional<String> result = dialog.showAndWait();
	if (result.isPresent()){
		if(result.get().equals("")) {
			hashmap.put("Forean", "");
		    return true;
		}
		if(!searcherLists.buscaClase(result.get())) {
			AlertChanges.noForean();
			return setForean(hashmap);
		}
	    hashmap.put("Forean", result.get());
	    return true;
	}
	else {
		return false;
	}}
	catch(Exception e) {
		return false;
	}
	
}
private static boolean settipe(HashMap<String, String> hashmap) {
	try {
	List<String> choices = new ArrayList<>();
	choices.add("Int");
	choices.add("String");
	choices.add("Fecha");
	choices.add("Hora");
	choices.add("Float");
	ChoiceDialog<String> dialog = new ChoiceDialog<>("b", choices);
	dialog.setTitle("Tipo del atributo");
	dialog.setHeaderText("");
	dialog.setContentText("Escoge el tipo:");

	// Traditional way to get the response value.
	Optional<String> result = dialog.showAndWait();
	if (result.isPresent()){
	     hashmap.put("Tipo", result.get());
	     return true;
	}
	else {
	return false;}}
	catch(Exception e) {return false;}
}
/**
 * Setea el nombre del atributo
 * @param hashmap
 * @return
 */
private static boolean setnombre(HashMap<String, String> hashmap) {
	try {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Nombre atributo");
		dialog.setHeaderText("Introduce el nombre del atributo");
		dialog.setContentText("");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    hashmap.put("nombre", result.get());
		    return true;
		}
		else {
			return false;
		}
	}
	catch(Exception e) {
		return false;
	}
}
}
