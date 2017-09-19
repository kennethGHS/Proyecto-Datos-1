package interfaz;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class SetActions {
public static void setaddButton(Button boton) {
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
public static void setdeleteButton(Button boton) {
	boton.setOnAction(new EventHandler<ActionEvent>() {
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
}
public static void seteditbutton(Button boton) {
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
}
