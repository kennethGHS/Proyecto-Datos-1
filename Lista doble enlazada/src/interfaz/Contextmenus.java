package interfaz;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;
import jsonreader.searcherLists;
import listas.GenericObject;

public class Contextmenus {
public static ContextMenu bookContext() {
	Contextmenus x = new Contextmenus();
if(Interfaz2.actual.equals("Galleries")) {
	return x.contextGallerie();		
}
if (searcherLists.buscaClase(Interfaz2.actual)) {
	return x.itemgallery();
}
else {
	return null;
}
}
private ContextMenu itemgallery() {
	final ContextMenu contextMenu = new ContextMenu();
	contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
	    public void handle(WindowEvent e) {
	        System.out.println("showing");
	    }
	});
	contextMenu.setOnShown(new EventHandler<WindowEvent>() {
	    public void handle(WindowEvent e) {
	        System.out.println("shown");
	    }
	});

	MenuItem item1 = new MenuItem("Anadir objeto");
	Image image1 = new Image(getClass().getResourceAsStream("AddIcon.png"));

	item1.setGraphic(new ImageView(image1));
	item1.setOnAction(new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
	        try {
				ManejaObjetos.nuevoObjeto();
			} catch (InterruptedException e1) {
				
				e1.printStackTrace();
			}
	    }
	});
	MenuItem item2 = new MenuItem("Eliminar objeto");
	Image image4 = new Image(getClass().getResourceAsStream("DeleteFileIcon.png"));

	item2.setGraphic(new ImageView(image4));
	item2.setOnAction(new EventHandler<ActionEvent>() {
	
	    public void handle(ActionEvent e) {
	    	System.out.println("algo mal");
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
				catch(Exception e3) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error al insertar Id");
					alert.setHeaderText("ID tiene que ser un numero entero" );
					alert.showAndWait();
					
				}
			}
	        
	    }
	});
	
	MenuItem item3 = new MenuItem("Editar Objeto");
	Image image = new Image(getClass().getResourceAsStream("EditButtonIcon.png"));

	item3.setGraphic(new ImageView(image));
	item3.setOnAction(new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
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
				catch(Exception e4) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error al insertar Id");
					alert.setHeaderText("ID tiene que ser un numero entero" );
					alert.showAndWait();
					
				}
			}
	        
	    }
	});
	MenuItem item4 = new MenuItem("Eliminar Todo");
	Image image41 = new Image(getClass().getResourceAsStream("DeletegalleryIcon.png"));

	item4.setGraphic(new ImageView(image41));
	item4.setOnAction(new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
	        try {
	        	if(ManejaObjetos.foreanUsed()) {
				ManejaClases.clear();}
			} catch (InterruptedException e1) {
				
				e1.printStackTrace();
			}
	    }
	});
	MenuItem item5= new MenuItem("Eliminar Galeria");
	Image image5 = new Image(getClass().getResourceAsStream("DeletegalleryIcon.png"));

	item5.setGraphic(new ImageView(image5));
	item5.setOnAction(new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
	        try {
	        	if(!ManejaObjetos.foreanUsed()) {
	        		return;
	        	}
				ManejaClases.eliminar();
				Interfaz2.nodo.get_objeto().getColumns().clear();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
	    }
	});
	contextMenu.getItems().addAll(item1, item2,item3,item4,item5);
	return contextMenu;
}
/**
 * Context para galerias
 * @return
 */
private ContextMenu contextGallerie() {
	final ContextMenu contextMenu = new ContextMenu();
	contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
	    public void handle(WindowEvent e) {
	        System.out.println("showing");
	    }
	});
	contextMenu.setOnShown(new EventHandler<WindowEvent>() {
	    public void handle(WindowEvent e) {
	        System.out.println("shown");
	    }
	});

	MenuItem item1 = new MenuItem("Anadir Clase");
	item1.setGraphic(SetActions.ListaImages.get(9));
	item1.setOnAction(new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
	        CreaInstancias.crearClase();
	    }
	});
	contextMenu.getItems().addAll(item1);
	return contextMenu;
}
/**
 * Context de la tabla GenericObject w = nodo.get_objeto().getSelectionModel().getSelectedItem();
 */
@SuppressWarnings("unused")
public static ContextMenu contextoTabla() {
	final ContextMenu contextMenu = new ContextMenu();
	contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
	    public void handle(WindowEvent e) {
	        System.out.println("showing");
	    }
	});
	contextMenu.setOnShown(new EventHandler<WindowEvent>() {
	    public void handle(WindowEvent e) {
	        System.out.println("shown");
	    }
	});

	MenuItem item1 = new MenuItem("Editar");
	item1.setGraphic(SetActions.ListaImages.get(7));
	item1.setOnAction(new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
	    	try {
	    	GenericObject w = Interfaz2.nodo.get_objeto().getSelectionModel().getSelectedItem();
				ManejaObjetos.editarobjetos(w.getId());
			} catch (Exception e1) {
				
			}
	    }
	});
	MenuItem item2 = new MenuItem("Eliminar");
	item2.setGraphic(SetActions.ListaImages.get(3));
	item2.setOnAction(new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
	    	try {
	    		GenericObject w = Interfaz2.nodo.get_objeto().getSelectionModel().getSelectedItem();
	    	ManejaObjetos.eliminarobjetos(w.getId());
	    	}
	    	catch(Exception e1) {}
	        
	    }
	});
	MenuItem item3 = new MenuItem("Refrescar");
	item3.setGraphic(SetActions.ListaImages.get(11));
	item3.setOnAction(new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
	    	try {
	    		CreadorTablas.creartabla(Interfaz2.actual);
	    	}
	    	catch(Exception e1) {}
	        
	    }
	});
	contextMenu.getItems().addAll(item1, item2,item3);
	return contextMenu;
}
}
