package interfaz;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class AlertChanges {
public static void noForean() {
	Alert alert = new Alert(AlertType.WARNING);
	alert.setTitle("Error");
	alert.setHeaderText("La clase no se encuentra en la base de datos");
	alert.setContentText("");

	alert.showAndWait();
}
public static void  noAtribute() {
	Alert alert = new Alert(AlertType.WARNING);
	alert.setTitle("Error");
	alert.setHeaderText("El atributo no pertenece a esa clase");
	alert.setContentText("");
}
public static boolean Option() {
	try {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Quieres crear una nueva clase?");
	alert.setHeaderText("");
	alert.setContentText("");

	ButtonType buttonTypeOne = new ButtonType("Si");
	ButtonType buttonTypeTwo = new ButtonType("No");



	alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == buttonTypeOne){
	    return true;
	} else if (result.get() == buttonTypeTwo) {
	    return false;
	}
	return false; }catch(Exception e) {
		return false;
	}
}
}
