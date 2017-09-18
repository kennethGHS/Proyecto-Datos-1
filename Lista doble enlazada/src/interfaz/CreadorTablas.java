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
 
	public static void creartabla(String nombre) throws InterruptedException {
	
	TableView<GenericObject> tabla = Interfaz2.nodo.get_objeto();
	tabla.getColumns().clear();
	ListaDobleCircular<ListaSimple<String>> lista = CreadorTablas.buscarlista(nombre);
	List<String> colums =  CreadorTablas.atributelist(nombre);
	CreadorTablas.creartabla2(colums,lista,tabla);
	
			}
	public static void creartabladesdeArbol(String nombre) throws InterruptedException {
		if(CreadorTablas.Validar(nombre)) {
			AnadeObjetos.setclaseactual(nombre);
		TableView<GenericObject> tabla = Interfaz2.nodo.get_objeto();
		tabla.getColumns().clear();
		ListaDobleCircular<ListaSimple<String>> lista = CreadorTablas.buscarlista(nombre);
		List<String> colums =  CreadorTablas.atributelist(nombre);
		CreadorTablas.creartabla2(colums,lista,tabla);
		}
		else {return;}
				}

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

	private static void creartabla2(List<String> colums, ListaDobleCircular<ListaSimple<String>> lista,
		TableView<GenericObject> tabla) throws InterruptedException {
		
		int indice=0;
		List<ArrayList<String>> list2 = new ArrayList<>();
				while(indice<colums.size() ) {
					ArrayList<String> list = new ArrayList<>();
					Nodos<ListaSimple<String>> nodo = lista.gethead();
					boolean repetido = true;
					if(nodo==null) {//revisar papus
						
						return;
					}
					while( !nodo.equals(lista.gethead())  || repetido == true) {
						repetido = false;
						System.out.println(nodo.get_objeto().getvalue(indice));
						list.add(nodo.get_objeto().getvalue(indice));
						nodo= nodo.get_next();
						System.out.println(list);
					}
					list2.add(list);
					
					indice++;
				}
				CreadorTablas.metertablas(tabla , colums,CreadorTablas(list2));
				
	}

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

	@SuppressWarnings("unchecked")
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
