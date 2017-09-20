package interfaz;

public class Validadoratributos {
	public static boolean validadortipo(String dato , String tipo) {
		
		if(tipo.equals("Int")) {
			return Validadoratributos.validadorint(dato);
		}
		if(tipo.equals("String")) {
			return Validadoratributos.validarString(dato);
		}
		if(tipo.equals("Float")) {
			return Validadoratributos.validarFloat(dato);
		}
		if(tipo.equals("Fecha")) {
			return dato.contains("/");
		}
		if(tipo.equals("Hora") && dato.length()== 4) {
			return dato.contains(":");
		}
		else {return false;}
	}
/**
 * Valida si el dato es un float
 * @param dato
 * @return
 */
	private static boolean validarFloat(String dato) {
		try {
			Float.parseFloat(dato);
			return true;
		}catch(Exception x) {
			return false;
		}
	}
/**
 * Valida que sea un string
 * @param dato
 * @return
 */
	private static boolean validarString(String dato) {
		try {
			if(dato.contains("/") || dato.contains(":")) {
				return false;
			}
			Integer.parseInt(dato);
			return false;
		}catch(Exception e) {return true;}
		
	}
/**
 * Validador de String a int
 * @param dato
 * @return
 */
	private static boolean validadorint(String dato) {
		try {
			Integer.parseInt(dato);
			return true; 
		}catch(NumberFormatException e) {
			return false;
		}
	}

}
