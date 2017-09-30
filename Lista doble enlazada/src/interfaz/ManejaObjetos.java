package interfaz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import jsonreader.DataLists;
import jsonreader.Meta;
import jsonreader.searcherLists;
import listas.Doble_enlazada;
import listas.ForeanDelete;
import listas.ListaDobleCircular;
import listas.ListaSimple;
import listas.Nodos;
/**
 * Maneja las acciones que se ejecutan en la interfaz y el database, las conecciones y mantener actualizado
 * @author kenneth
 *
 */
public class ManejaObjetos {
	public static String claseactual ="";
	/**
	 * Metodo que actualiza la lista cada vez que se cambia de objeto
	 * @throws InterruptedException
	 */
public static void nuevoObjeto() throws InterruptedException {
	if(claseactual=="") {
		return;
	}
	ArrayList<HashMap<String, String>> objetos = Meta.sacaListaMeta(claseactual);
	ListaDobleCircular<ListaSimple<String>> galeria = CreadorTablas.buscarlista(claseactual);
	ListaSimple<String> lista = new ListaSimple<>();
	int indice = 0;
	if(objetos==null) {
		return;
	}
	while(indice< objetos.size() ) {
	boolean admin = ManejaObjetos.crearnuevaisntancia(galeria, objetos.get(indice) , lista,indice);
	if(admin == false) {
		return;
	}
	System.out.println(objetos.get(indice));
	indice++;
	}
	galeria.append(lista);
	CreadorTablas.creartabla(claseactual);
}

/**
 * Menu para crear una nueva instancia de un objeto en especifico desde la interfaz
 * @param galeria
 * @param hashMap del cual saldran los nombres
 * @param lista simple la cual sera andadida al final
 * @param indice 
 * @return
 */
private static boolean crearnuevaisntancia(ListaDobleCircular<ListaSimple<String>> galeria,
		HashMap<String, String> hashMap, ListaSimple<String> lista, int indice) {
	boolean repetido = true;
	while(repetido) {
	TextInputDialog dialog = new TextInputDialog();
	dialog.setGraphic(SetActions.ListaImages.get(0));
	dialog.setTitle(hashMap.get("nombre"));
	dialog.setHeaderText(hashMap.get("nombre"));
	dialog.setContentText("Por favor introduce:"+hashMap.get("nombre"));
//	System.out.println(hashMap.get("Tipo"));
	Optional<String> result = dialog.showAndWait();
	if (result.isPresent()){
		if(result.get().equals("")) {
			lista.append(hashMap.get("generico"));
		    repetido = false;
		    return true;}
		if ( Validadoratributos.validadortipo(result.get(), hashMap.get("Tipo")) == false ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al insertar dato");
			alert.setHeaderText("Atributo " + hashMap.get("nombre") + " debe ser de tipo " + hashMap.get("Tipo") );
			alert.showAndWait(); 
		}
		else {
			if(Validadoratributos.comprobarunico(galeria,hashMap,indice,result.get())) {
			if(hashMap.get("Forean").equals("")) {
				lista.append(result.get());
			    repetido = false;
			    return true;
			}
			else{
				
			if(Validadoratributos.comprobarForaneo(result.get(),hashMap)) {
				lista.append(result.get());
			    repetido = false;
			    return true;}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error al insertar dato");
				alert.setHeaderText("No se encontro el atributo en "+hashMap.get("Forean"));
				alert.showAndWait(); }
				
			}
			
			}
			else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error al insertar dato");
				alert.setHeaderText("El atributo ya esta siendo usado");
				alert.showAndWait(); 
			}
		}
		
	    
	}
	else{return false; }
	}
	return repetido;
}
static public void setclaseactual(String clase) {
	claseactual = clase;
}
/**
 * Elimina el objeto por el id del cual se encuentra el objeto en la lista
 * @param id
 * @throws InterruptedException
 */
static public void eliminarobjetos(int id) throws InterruptedException {
	ListaDobleCircular<ListaSimple<String>> lista =DataLists.sacaCircular(claseactual);
	if(ForeanDelete.comprobante(lista.buscar(id),lista.id)) {
	lista.borrar(id);
	CreadorTablas.creartabla(claseactual);}
}
/**
 * Recibe el id de un objeto en tabla y lo edita.
 * @param id
 * @throws InterruptedException
 */
static public void editarobjetos(int id) throws InterruptedException {
	ListaDobleCircular<ListaSimple<String>> lista =DataLists.sacaCircular(claseactual);
	if(lista.largo<=id) {
		return;
	}
	ListaSimple<String> objetos = lista.buscar(id).get_objeto();
	ArrayList<HashMap<String, String>> atributos = Meta.sacaListaMeta(claseactual);
	ManejaObjetos.editor(objetos,atributos,lista.id);
}
/**
 * Edita una instancia
 * @param objetos
 * @param atributos
 * @param id 
 * @throws InterruptedException
 */
private static void editor(ListaSimple<String> objetos, ArrayList<HashMap<String, String>> atributos, String id) throws InterruptedException {
	int indice = 0;
	Nodos<String> nodo = objetos.head;
	while (indice!= atributos.size() ) {
		try {
		TextInputDialog dialog = new TextInputDialog(objetos.getvalue(indice));
		dialog.setGraphic(SetActions.ListaImages.get(6));
		//stage.getIcons().add(new Image("/path/to/javaicon.png"));
		//Imagen a dialosgos dialog.initOwner(window);
		dialog.initOwner(Interfaz2.primaryStage);
		dialog.setTitle("Editor de atributos");
		dialog.setHeaderText("Edita El atributo");

		//Anadir validaciones

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){

			
		    if(Validadoratributos.validadortipo(result.get(), atributos.get(indice).get("Tipo")) == false) {
		    	Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error al insertar dato");
				alert.setHeaderText("Atributo " + atributos.get(indice).get("nombre") + " debe ser de tipo " + atributos.get(indice).get("Tipo") );
				alert.showAndWait(); 
				
		    }
		    else {
		    	System.out.println(atributos.get(indice).get("nombre"));
		    	System.out.println(id);
		    	System.out.println(nodo.objeto);
		    	if(!result.get().equals(nodo.objeto)) {
		    		if(Validadoratributos.comprobarunico(searcherLists.optieneCircular(claseactual), atributos.get(indice), indice, result.get())) {
		    	if(ForeanDelete.Comprueba(atributos.get(indice).get("nombre"), claseactual, nodo.objeto)){
		    	if(atributos.get(indice).get("Forean").equals("")) {
		    	System.out.println("algomal");
		    	nodo.set_objeto(result.get());
		    	nodo = nodo.next;
		    	indice++;}
		    	else {
		    		if(result.get().equals(nodo.objeto)) {
				    	nodo.set_objeto(result.get());
				    	nodo = nodo.next;
				    	indice++;
		    		}
		    		if(Validadoratributos.comprobarForaneo(result.get(),atributos.get(indice))) {
		    			System.out.println("algomal");
				    	nodo.set_objeto(result.get());
				    	nodo = nodo.next;
				    	indice++;
		    		}
		    		else {
		    			Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error al insertar dato");
						alert.setHeaderText("No se encontro el atributo en "+atributos.get(indice).get("Forean") );
						alert.showAndWait(); 
		    		}
		    	
		    	}
		    	
		    	}
		    	}
		    	else {
		    		Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error al insertar dato");
					alert.setHeaderText("Atributo esta siendo usado");
					alert.showAndWait(); 
		    	}
		    }
		    	else {
		    		nodo.set_objeto(result.get());
			    	nodo = nodo.next;
			    	indice++;
		    	}
		    }
		    }
		else {
			return;}
		}
		catch(Exception x) {return;}
		}
	System.out.println("LOLOLOL_________");
	CreadorTablas.creartabla(claseactual);
	}
	
public static boolean foreanUsed() {
	Doble_enlazada<Meta> a = DataLists.metadata;
	List<Boolean> comprobante = new ArrayList<>();
	Nodos<Meta> nodo = a.gethead();
	while(nodo!=null) {
		CompruebaForaneano(nodo.get_objeto().name,comprobante,nodo.get_objeto().colums,Interfaz2.actual);
		nodo = nodo.next;
	}
	if(comprobante.contains(false)) {
		return false;
	}
	else {
		return true;
	}
}

private static void CompruebaForaneano(String claseorigen, List<Boolean> comprobantes, ArrayList<HashMap<String, String>> colums, String referencia) {
	int indice=0;
	while(indice<colums.size()) {
		if(colums.get(indice).get("Forean").equals(referencia)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(Interfaz2.primaryStage);
			alert.setTitle("Error de eliminacion");
			alert.setHeaderText("El atributo " + colums.get(indice).get("nombre")+ " de la clase "+ claseorigen + " utiliza" + referencia );
			alert.showAndWait();
			comprobantes.add(false);
		}
		else {
			comprobantes.add(true);
		}
		indice++;
	}
	
}
}


