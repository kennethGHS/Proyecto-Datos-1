package interfaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.control.Alert.AlertType;
import jsonreader.ListstoJson;
/**
 * Clase que genera las acciones de los botones
 * @author kenneth
 *
 */

public class SetActions {
	public static List<ImageView> ListaImages;
	/**
	 * Clase que me carga todas las imagenes
	 */
public void setimages() {
	ListaImages = new ArrayList<ImageView>();
	Image image = new Image(getClass().getResourceAsStream("Add.png"));//0
	ListaImages.add(new ImageView(image));
	
	Image image2 = new Image(getClass().getResourceAsStream("AddIcon.png"));//1
	ListaImages.add(new ImageView(image2));
	
	Image image3 = new Image(getClass().getResourceAsStream("DeleteFile.png"));//2
	ListaImages.add(new ImageView(image3));
	
	Image image4 = new Image(getClass().getResourceAsStream("DeleteFileIcon.png"));//3
	ListaImages.add(new ImageView(image4));
	
	Image image5 = new Image(getClass().getResourceAsStream("Deletegallery.png"));//4
	ListaImages.add(new ImageView(image5));
	
	Image image6 = new Image(getClass().getResourceAsStream("DeletegalleryIcon.png"));//5
	ListaImages.add(new ImageView(image6));
	
	Image image7 = new Image(getClass().getResourceAsStream("EditButton.png"));//6
	ListaImages.add(new ImageView(image7));
	
	Image image8 = new Image(getClass().getResourceAsStream("EditButtonIcon.png"));//7
	ListaImages.add(new ImageView(image8));
	
	Image image9 = new Image(getClass().getResourceAsStream("GalleryAdd.png"));//8
	ListaImages.add(new ImageView(image9));
	
	Image image10 = new Image(getClass().getResourceAsStream("GalleryAddIcon.png"));//9
	ListaImages.add(new ImageView(image10));
	
	Image image11 = new Image(getClass().getResourceAsStream("Save.png"));//10
	ListaImages.add(new ImageView(image11));
}
public  void setaddButton(Button boton) {
	Image image = new Image(getClass().getResourceAsStream("Add.png"));
	boton.setStyle("-fx-focus-color: transparent;");
	boton.setGraphic(new ImageView(image));
	boton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        	try {
        		
				ManejaObjetos.nuevoObjeto();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    });
}
/**
 * Da el evento de eliminar a un boton
 * @param boton
 */
public  void setdeleteButton(Button boton) {
	Image image = new Image(getClass().getResourceAsStream("DeleteFile.png"));
	boton.setStyle("-fx-focus-color: transparent;");
	boton.setGraphic(ListaImages.get(2));
	boton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        	TextInputDialog dialog = new TextInputDialog();
        	dialog.setGraphic(new ImageView(image));
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
}
/**
 * Da evento de edicion a un boton
 * @param boton
 */
public  void seteditbutton(Button boton) {
	Image image = new Image(getClass().getResourceAsStream("EditButton.png"));
	boton.setStyle("-fx-focus-color: transparent;");
	boton.setGraphic(new ImageView(image));
	boton.setOnAction(new EventHandler<ActionEvent>() {
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
	
}
public  void setaddClassAction(Button boton) {
	Image image = new Image(getClass().getResourceAsStream("GalleryAdd.png"));
	boton.setStyle("-fx-focus-color: transparent;");
	boton.setGraphic(new ImageView(image));
	boton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        	try {
        		CreaInstancias.crearClase();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    });
	//
}
@SuppressWarnings("unchecked")
public static void SetTree() {
	TreeView<String> variable = Interfaz2.tree;
	variable.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
        @Override
        public void changed(@SuppressWarnings("rawtypes") ObservableValue observable, Object oldValue,
                Object newValue) {

            TreeItem<String> selectedItem = (TreeItem<String>) newValue;
            try {
            Interfaz2.actual = selectedItem.getValue();
            try {
    			CreadorTablas.creartabladesdeArbol(selectedItem.getValue());
    			
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}}
            catch(Exception e) {
            	Interfaz2.actual = "";
            }
          
           variable.setContextMenu(Contextmenus.bookContext());
           variable.refresh();
          
           // selectedItem.getValue()
        }
      });
}
public  void commitbutton(Button boton) {
	Image image = new Image(getClass().getResourceAsStream("Save.png"));
	boton.setStyle("-fx-focus-color: transparent;");
	boton.setGraphic(new ImageView(image));
	boton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        	try {
        		ListstoJson.galleriesTojson();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    });
	
}
}
