package listas;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import interfaz.Interfaz2;
import javafx.application.Application;
import jsonreader.DataLists;
import jsonreader.Instanciador;
import jsonreader.JsonReader;
import jsonreader.ListstoJson;
import jsonreader.Meta;

public class Main {

	public static void main(String[] args) throws IOException {
JsonReader.Read();
Instanciador.instanciador();
System.out.println("_____________");
Interfaz2.Arrancar(args);
JsonReader.escribemeta();
ListstoJson.galleriesTojson();

}
}
  
