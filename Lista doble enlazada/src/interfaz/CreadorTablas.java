package interfaz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jsonreader.DataLists;
import jsonreader.Meta;
import listas.GenericObject;
import listas.ListaDobleCircular;
import listas.ListaSimple;
import listas.Nodos;

public class CreadorTablas {
 /**
  * Lo que hace esta es limpiar toda las tablas y genera las columnas y todo lo necesario para crear
  * una tabla
  * @param nombre es el nombre de alguna lista que tiene ID
  * @throws InterruptedException
  */
	public static void creartabla(String nombre) throws InterruptedException {
	
	TableView<GenericObject> tabla = Interfaz2.nodo.get_objeto();
	tabla.getColumns().clear();
	ListaDobleCircular<ListaSimple<String>> lista = CreadorTablas.buscarlista(nombre);
	List<String> colums =  CreadorTablas.atributelist(nombre);
	CreadorTablas.creartabla2(colums,lista,tabla);
	
			}
	/**
	 * Este metedo es espacial para recibir los datos del arbol y validar si este existe
	 * @param nombre nombre de la gallerie que se quiere ver
	 * @throws InterruptedException
	 */
	public static void creartabladesdeArbol(String nombre) throws InterruptedException {
		if(CreadorTablas.Validar(nombre)) {
			ManejaObjetos.setclaseactual(nombre);
		TableView<GenericObject> tabla = Interfaz2.nodo.get_objeto();
		tabla.getColumns().clear();
		ListaDobleCircular<ListaSimple<String>> lista = CreadorTablas.buscarlista(nombre);
		List<String> colums =  CreadorTablas.atributelist(nombre);
		CreadorTablas.creartabla2(colums,lista,tabla);
		}
		else {return;}
				}
/**
 * Validacion del nombre recivido en creartablasdearbol
 * @param nombre
 * @return
 */
	private static boolean Validar(String nombre) {
	Nodos<Meta> nodo = DataLists.metadata.gethead();
	
	while(nodo!=null) {
		if(nodo.get_objeto().name== nombre) {
			return true;
		}
		nodo=nodo.next;
	}
	return false;
	}
/**
 * uno de los metodos que se usa para la creacion de tablas, convierte listas simples dentro de una circular a una simple
 * @param colums columnas de atributos
 * @param lista lista de la cual salen los datos
 * @param tabla es la tabla a la cual se anadira
 * @throws InterruptedException
 */
	private static void creartabla2(List<String> colums, ListaDobleCircular<ListaSimple<String>> lista,
		TableView<GenericObject> tabla) throws InterruptedException {
		
		int indice=0;
		List<ArrayList<String>> list2 = new ArrayList<>();
				while(indice<colums.size() ) {
					ArrayList<String> list = new ArrayList<>();
					Nodos<ListaSimple<String>> nodo = lista.gethead();
					boolean repetido = true;
					if(nodo==null) {
						
						return;
					}
					while( !nodo.equals(lista.gethead())  || repetido == true) {
						repetido = false;
//						System.out.println(nodo.get_objeto().getvalue(indice));
						list.add(nodo.get_objeto().getvalue(indice));
						nodo= nodo.get_next();
//						System.out.println(list);
					}
					list2.add(list);
					
					indice++;
				}
				CreadorTablas.metertablas(tabla , colums,CreadorTablas(list2));
				
	}
/**
 * Metodo especial que convierte listas simples a un objeto generico cada una
 * @param list2 lista con listas dentro
 * @return retorna la lista con objetos genericos
 */
	private static Collection<GenericObject> CreadorTablas(List<ArrayList<String>> list2) {
		Collection<GenericObject> lista = new ArrayList<>();
		int indicesuperior = 0 ;
		while(indicesuperior!= list2.get(0).size()) {
			int indice = 0 ;
			GenericObject objeto= new GenericObject(indicesuperior);
			while(indice!= list2.size()) {
				objeto.setatributo(list2.get(indice).get(indicesuperior), indice);
				indice++;
			}
			lista.add(objeto);
			indicesuperior++;
		}
		return lista;
		
	}

/**
 * Crea las columnas segun el objeto que se esta poniendo
 * @param tabla tabla a la cual se le pondran los atributos
 * @param colums lista columnas con string de las columnas
 * @param collection lista de generic objects
 * @throws InterruptedException
 */
	private static void metertablas(TableView<GenericObject> tabla, List<String> colums, Collection<GenericObject> collection) throws InterruptedException {
		final ObservableList<GenericObject> details = FXCollections.observableArrayList(collection);
		int indice=0;
		if(tabla.getColumns().size()==0) {
			TableColumn<GenericObject, String> col1 = new TableColumn<>("id");
			
			col1.setCellValueFactory(new PropertyValueFactory<>("id"));
			tabla.getColumns().addAll(col1);
		}
	while(indice!= colums.size()) {
		 TableColumn<GenericObject, String> col1 = new TableColumn<>(colums.get(indice));
		
		col1.setCellValueFactory(new PropertyValueFactory<>(GenericObject.get(indice)));
		tabla.getColumns().addAll(col1);
		indice++;
		
	}
        tabla.setItems(details);
       
	}
	@SuppressWarnings("unchecked")
	/**
	 * Metodo para la creacion de una tabla vacia;
	 * @param tabla
	 * @param colums
	 * @throws InterruptedException
	 */
	private static void metertablasvacia(TableView<GenericObject> tabla, List<String> colums) throws InterruptedException {
		
		int indice=0;
		if(tabla.getColumns().size()==0) {
			TableColumn<GenericObject, String> col1 = new TableColumn<>("id");
			
			col1.setCellValueFactory(new PropertyValueFactory<>("id"));
			tabla.getColumns().addAll(col1);
		}
	while(indice!= colums.size()) {
		 TableColumn<GenericObject, String> col1 = new TableColumn<>(colums.get(indice));
		
		col1.setCellValueFactory(new PropertyValueFactory<>(GenericObject.get(indice)));
		tabla.getColumns().addAll(col1);
		indice++;
		
	}
       
	}
/**
 * Busca una lista cicular segun su id en el metadata
 * @param nombre
 * @return
 */
	@SuppressWarnings("unused")
	public static ListaDobleCircular<ListaSimple<String>> buscarlista(String nombre) {
		Nodos<ListaDobleCircular<ListaSimple<String>>> nodo = DataLists.galerias.gethead();
		while(nodo!=null) {
			if(nodo.get_objeto().id.equals(nombre) ) {
				return nodo.get_objeto();
			}
			nodo= nodo.next;
		}
		if (nodo == null) {
			return new ListaDobleCircular<ListaSimple<String>>();
		}
		return null;
	}
	/**
	 * Busca si un elemento esta en una lista simple
	 * @param nombre
	 * @return
	 */
	public static List<String> atributelist(String nombre){
		
		Nodos<Meta> nodo  =DataLists.metadata.gethead();
		if(nodo.get_objeto().name.equals(nombre)) {
		}
		while(nodo!=null) {
			
			if(nodo.get_objeto().name.equals(nombre) ) {
				System.out.println(nombre + "lol");
				return Meta.metalist(nodo.get_objeto().colums);
			}
			nodo=nodo.next;
		}
		return null;
		
		
	
	}
}
