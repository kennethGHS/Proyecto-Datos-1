package interfaz;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import listas.Doble_enlazada;
import listas.Nodos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class Interfaz extends Application {
	public static Scene scene;
	private static Stage primaryStage;
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) {
		try {
			scene = new Scene(new Group());
			
			this.primaryStage=primaryStage;
			this.primaryStage.setTitle("Table View Sample");
			Doble_enlazada<String> strings= new Doble_enlazada<String>();
			strings.append("hola");
			strings.append("hola2");
			strings.append("lucyrara");
			this.primaryStage.setWidth(900);
			this.primaryStage.setHeight(900);
			System.out.println("hola");
			this.creartabla(strings);
			
			this.primaryStage.setScene(scene);
			this.primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void inicia(String[] args) {
		launch(args);
	}
	public static void creartabla(Doble_enlazada<String> lista) {
		TableView<String> table=new TableView<String>();
		table.setEditable(true);
		primaryStage.setTitle("lol");
	Interfaz.generarTabla( table, lista);
	
	final VBox vbox = new VBox();
       vbox.setSpacing(5);
       vbox.setPadding(new Insets(10, 0, 0, 10));
       vbox.getChildren().addAll( table);
       ((Group)scene.getRoot()).getChildren().addAll(vbox);
      
       return;        
	}
	@SuppressWarnings("unchecked")
	private static void generarTabla(TableView table, Doble_enlazada<String> lista) {
		
		Nodos<String> actual = lista.gethead();
		
		if(actual==null) {
			
			return;
		}
		else {
			System.out.println(lista.gethead().get_next().get_objeto());
			
		while(actual != null) {
		
		System.out.println(actual.get_objeto());
		TableColumn nueva = new TableColumn(actual.get_objeto());
		table.getColumns().addAll(nueva);
		actual= actual.get_next();
		}
		
		}
	}
	 public static  void creartabla() {
		
        
	}
	
	}

