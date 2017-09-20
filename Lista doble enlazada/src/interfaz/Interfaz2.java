package interfaz;

import java.util.Optional;

import com.sun.glass.events.MouseEvent;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import listas.GenericObject;
import listas.ListaSimple;
import listas.Nodos;
/**
 * Ventana
 * @author kenneth
 *
 */
public class Interfaz2 extends Application {
	public static TreeItem<String> padre = new TreeItem<>("Galleries");
	public static Scene scene;
	private static Stage primaryStage;
	public  static Nodos<TableView<GenericObject>> nodo = new Nodos();
	
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Metodo que inicializa la interfaz
	 */
	public void start(Stage primaryStage2) throws InterruptedException {
		
		nodo.objeto = new TableView<GenericObject>();//genera una tabla que contiene objeto generico
		BorderPane borderpane = new BorderPane(); // panel
		scene = new Scene(borderpane, 300, 200);
		borderpane.setCenter(nodo.objeto);//pone la tabla en el centro
		TreeCreator.CrearArbol(padre);
		TreeView<String> tree = new TreeView<>(padre);// inicia el treeview
//		TextInputDialog dialog = new TextInputDialog("walter");
//		dialog.setTitle("Text Input Dialog");
//		dialog.setHeaderText("Look, a Text Input Dialog");
//		dialog.setContentText("Please enter your name:");
//
//		// Traditional way to get the response value.
//		Optional<String> result = dialog.showAndWait();
//		if (result.isPresent()){
//		    System.out.println("Your name: " + result.get());
//		}
		Button boton2 = new Button("Editar");
		SetActions.seteditbutton(boton2);
		Button boton3 = new Button("Anadir");
		SetActions.setaddButton(boton3);
		
		Button boton = new Button("Eliminar");
		SetActions.setdeleteButton(boton);
		VBox right = new VBox();
		right.getChildren().addAll(boton3,boton,boton2);
		
		borderpane.setTop(right);
		tree.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
	        @Override
	        public void changed(@SuppressWarnings("rawtypes") ObservableValue observable, Object oldValue,
	                Object newValue) {

	            TreeItem<String> selectedItem = (TreeItem<String>) newValue;
	          try {
				CreadorTablas.creartabladesdeArbol(selectedItem.getValue());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           // selectedItem.getValue()
	        }
	      });
		borderpane.setLeft(tree);
		primaryStage=primaryStage2;
		primaryStage.setResizable(false);
		primaryStage.setTitle("LinkedDB");
		primaryStage.setWidth(1000);
		primaryStage.setHeight(900);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public static void Arrancar(String[] args) {
		launch(args);
	}
}
