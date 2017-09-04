package interfaz;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class Interfaz extends Application {
	Scene scene;
	Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			scene = new Scene(new Group());
			this.primaryStage=primaryStage;
			this.primaryStage.setTitle("Table View Sample");
			this.primaryStage.setWidth(900);
			this.primaryStage.setHeight(900);
			this.primaryStage.setScene(scene);
			this.primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void inicia(String[] args) {
		launch(args);
	}
	public void creartabla() {
		TableView<String> table=new TableView<String>();
		table.setEditable(true);
		
		
	}
	
	}

