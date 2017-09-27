package interfaz;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import jsonreader.ListstoJson;

public class Menuitems {

	public static void Crearitems(MenuBar menuBar) {
		Menuitems.edit(menuBar);
		Menuitems.delete(menuBar);
		Menuitems.anadir(menuBar);
		Menuitems.commit(menuBar);
		
	}

	private static void commit(MenuBar menuBar) {
		Menu commit = new Menu("Commit");
		commit.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	try {
	        		ListstoJson.galleriesTojson();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
	    });
		menuBar.getMenus().add(commit);
	}

	private static void anadir(MenuBar menuBar) {
		Menu anadir = new Menu("Anandir");
		anadir.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	try {
					ManejaObjetos.nuevoObjeto();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }
	    });
		menuBar.getMenus().add(anadir);
	}

	private static void delete(MenuBar menuBar) {
		Menu delete = new Menu("Delete");
		delete.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Eliminar");
				dialog.setHeaderText("Escribe ID del objeto a eliminar");
				if(ManejaObjetos.claseactual=="") {
					return;
				}
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
					try {
				    ManejaObjetos.eliminarobjetos(Integer.parseInt(result.get()));
				    
				    }
					catch(Exception e) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error al insertar Id");
						alert.setHeaderText("ID tiene que ser un numero entero" );
						alert.showAndWait();
						
					}
				}
	        }
	    });
		menuBar.getMenus().add(delete);
		
	}

	private static void edit(MenuBar menuBar) {
		Menu editor = new Menu("Edit");
		editor.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Editar");
				dialog.setHeaderText("Escribe ID del objeto a editar");
				if(ManejaObjetos.claseactual=="") {
					return;
				}
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
					try {
				    ManejaObjetos.editarobjetos(Integer.parseInt(result.get()));
				    
				    }
					catch(Exception e) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error al insertar Id");
						alert.setHeaderText("ID tiene que ser un numero entero" );
						alert.showAndWait();
						
					}
				}
	        }
	    });
		menuBar.getMenus().add(editor);
	}

}
