package interfaz;

import com.sun.glass.events.MouseEvent;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import listas.GenericObject;
import listas.ListaSimple;
import listas.Nodos;

public class Interfaz2 extends Application {
	public static TreeItem<String> padre = new TreeItem<>("Galleries");
	public static Scene scene;
	private static Stage primaryStage;
	public  static Nodos<TableView<GenericObject>> nodo = new Nodos();
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage2) throws InterruptedException {
		
		nodo.objeto = new TableView<GenericObject>();//genera una tabla que contiene objeto generico
        
		BorderPane borderpane = new BorderPane(); // panel
		scene = new Scene(borderpane, 300, 200);
		CreadorTablas.creartabla("Estudiantes");
		borderpane.setCenter(nodo.objeto);//pone la tabla en el centro
		CreadorTablas.creartabla("Profes");
		

		TreeCreator.CrearArbol(padre);
		TreeView<String> tree = new TreeView<>(padre);// inicia el treeview
		tree.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

	        @Override
	        public void changed(@SuppressWarnings("rawtypes") ObservableValue observable, Object oldValue,
	                Object newValue) {

	            TreeItem<String> selectedItem = (TreeItem<String>) newValue;
	            //meter accion
	            
	        }
	      });
		borderpane.setLeft(tree);
		primaryStage=primaryStage2;
		primaryStage.setResizable(false);
		primaryStage.setTitle("LinkedDB");
		primaryStage.setWidth(1200);
		primaryStage.setHeight(1000);
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}


	public static void Arrancar(String[] args) {
		launch(args);
	}
}
