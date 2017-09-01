package listas;

import interfaz.Interfaz;
import javafx.application.Application;

public class Main {

	public static void main(String[] args) {
System.out.println("Hello");
Doble_enlazada<String> hello= new Doble_enlazada<String>();
hello.append("Hello");
System.out.println(hello.head.objeto);
System.out.println(hello.head);
System.out.println(hello.buscar(1).get_objeto());
Interfaz.inicia(args);
	}

}
