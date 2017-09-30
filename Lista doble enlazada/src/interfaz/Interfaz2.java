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
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
	public static String actual;
	public static TreeItem<String> padre = new TreeItem<>("Galleries");
	public static Scene scene;
	public static MenuBar menuBar ;
	public static Stage primaryStage;
	public  static Nodos<TableView<GenericObject>> nodo = new Nodos<>();
	public static TreeView<String> tree;
	@Override
	/**
	 * Metodo que inicializa la interfaz
	 */
	public void start(Stage primaryStage2) throws InterruptedException {
		SetActions a = new SetActions();
		a.setimages();
		nodo.objeto = new TableView<GenericObject>();//genera una tabla que contiene objeto generico
		nodo.objeto.setContextMenu(Contextmenus.contextoTabla());
		BorderPane borderpane = new BorderPane(); // panel
		scene = new Scene(borderpane, 300, 200);
		borderpane.setCenter(nodo.objeto);//pone la tabla en el centro
		TreeCreator.CrearArbol(padre);
		 tree = new TreeView<>(padre);// inicia el treeview
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
		 SetActions accionador = new SetActions();
		Button boton2 = new Button();
		accionador.seteditbutton(boton2);
		Button boton3 = new Button();
		accionador.setaddButton(boton3);
		Button boton = new Button();
		accionador.setdeleteButton(boton);
		Button boton4 = new Button();
		accionador.setaddClassAction(boton4);
		Button boton5 = new Button();
		accionador.commitbutton(boton5);
		Button boton6 = new Button();
		accionador.search(boton6);
		HBox right = new HBox();
		right.getChildren().addAll(boton3,boton,boton2,boton4,boton5,boton6);
		borderpane.setTop(right);
		SetActions.SetTree();
		borderpane.setLeft(tree);
//		padre.getChildren().clear();
//		TreeCreator.CrearArbol(padre);
		
		primaryStage=primaryStage2;
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("WindowIcon.png")));
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
