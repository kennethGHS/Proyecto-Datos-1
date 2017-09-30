package interfaz;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import jsonreader.DataLists;
import jsonreader.Meta;
import jsonreader.searcherLists;
import listas.ListaDobleCircular;
import listas.ListaSimple;

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
			if(searcherLists.buscaClase(result.get())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(Interfaz2.primaryStage);
				alert.setTitle("Error al insertar dato");
				alert.setHeaderText( "No se pueden tener dos clases con el mismo nombre" );
				alert.showAndWait(); 
				 crearClase();
				 return;
			}
		    nuevaclase.setName(result.get());
		   boolean creacion = CreaInstancias.crearAtributos(nuevaclase);
		 if(creacion) {
			 DataLists.metadata.append(nuevaclase);
			 ObjectMapper mapper = new ObjectMapper();
		    	mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
		    	mapper.writeValue(new File("src\\"+nuevaclase.name), new ArrayList<>());
			 DataLists.galerias.append(new ListaDobleCircular<ListaSimple<String>>(nuevaclase.name));
			 TreeCreator.createBranch(Interfaz2.padre, nuevaclase.name);;
			 SetActions.SetTree();
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
			condicion = CreaInstancias.setnombre( hashmap,nuevaclase.name,atributos);
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
				condicion = CreaInstancias.setUnico(hashmap);
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
	private static boolean setUnico(HashMap<String, String> hashmap) {
try {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Unico");
	alert.setHeaderText("Es unico?");
	alert.setContentText("");
	ButtonType buttonTypeOne = new ButtonType("Si");
	ButtonType buttonTypeTwo = new ButtonType("No");
	alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == buttonTypeOne){
		hashmap.put("primaria", "si");
	    return true;
	} else if (result.get() == buttonTypeTwo) {
		hashmap.put("primaria", "no");
	    return true;
	}
	else {
		return false;
	}
	
}catch(Exception e) {
	return false;
}
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
				if(!CreaInstancias.validadortipo(result.get(),clase,hashmap.get("Tipo"))) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error al insertar dato");
					alert.setHeaderText( "El atributo debe de ser del mismo tipo que la llave foranea su tipo actual es "+ hashmap.get("Tipo") + " cambialo" );
					alert.showAndWait(); 
					CreaInstancias.settipe(hashmap);
					return setForeanAtribute( clase,hashmap);
				}
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
	choices.add("String");
	choices.add("Fecha");
	choices.add("Hora");
	choices.add("Float");
	ChoiceDialog<String> dialog = new ChoiceDialog<>("Int", choices);
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
 * @param name 
 * @param atributos 
 * @return
 */
private static boolean setnombre(HashMap<String, String> hashmap, String name, List<HashMap<String, String>> atributos) {
	try {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Nombre atributo");
		dialog.setHeaderText("Introduce el nombre del atributo");
		dialog.setContentText("");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			if(CreaInstancias.Sacanombres(atributos).contains(result.get())){
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(Interfaz2.primaryStage);
				alert.setTitle("Error al insertar dato");
				alert.setHeaderText( "No se pueden tener dos atributos de igual nombre" );
				alert.showAndWait(); 
				return setnombre(hashmap,name, atributos);
			}
			if(name.equals(result.get())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(Interfaz2.primaryStage);
				alert.setTitle("Error al insertar dato");
				alert.setHeaderText( "No se puede tener un atributo con el mismo nombre de la clase" );
				alert.showAndWait(); 
				return setnombre(hashmap,name, atributos);
			}
			if(result.get().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(Interfaz2.primaryStage);
				alert.setTitle("Error al insertar dato");
				alert.setHeaderText( "Este espacio es obligatorio" );
				alert.showAndWait(); 
				return setnombre(hashmap,name, atributos);
			}
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
//Saca una lista con nombres de atributos
private static List<String> Sacanombres(List<HashMap<String, String>> atributos) {
	int indice = 0;
	List<String> a = new ArrayList<>();
	while(indice<atributos.size()) {
		a.add(atributos.get(indice).get("nombre"));
		indice++;
		
	}
	return a;
	
}
private static boolean validadortipo(String atributo, String clase, String tipo) {
	ArrayList<HashMap<String, String>> mapas = searcherLists.buscaClaseHashmaps(clase);
	int indice = 0;
	while(indice<mapas.size()) {
		if(mapas.get(indice).get("nombre").equals(atributo)) {
			if(mapas.get(indice).get("Tipo").equals(tipo)) {
				return true;
			}
			else {
				
				return false;
				}
		}
		indice++;
	}
	return false;
	
}

}
