package listas;

import java.io.IOException;

import interfaz.Interfaz;
import javafx.application.Application;
import jsonreader.JsonReader;

public class Main {

	public static void main(String[] args) throws IOException {
System.out.println("Hello");
Doble_enlazada<String> hello= new Doble_enlazada<String>();
hello.append("Hello");
Interfaz.inicia(null);
JsonReader.Read();
Interfaz.creartabla();
	}

}
