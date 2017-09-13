package listas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import interfaz.Interfaz;
import javafx.application.Application;
import jsonreader.DataLists;
import jsonreader.Instanciador;
import jsonreader.JsonReader;
import jsonreader.Meta;

public class Main {

	public static void main(String[] args) throws IOException {
//System.out.println("Hello");
//Doble_enlazada<String> hello= new Doble_enlazada<String>();
//hello.append("Hello");
Interfaz.inicia(null);
JsonReader.Read();
Interfaz.creartabla();
//ListaDobleCircular<String> a = new ListaDobleCircular<String>();
//a.append("hola");
//a.append("cosas");
//System.out.println(a.buscar(2).objeto);
//ListaSimple<String> x = new ListaSimple<>();
//x.append("hola");
//x.append("Holas");
//x.append("mas");
//x.eliminar(5);
//x.eliminar(1);
//System.out.println(x.head.get_objeto());
//
//System.out.println("_______________");
//x.print();
//HashMap<String,String> a = new HashMap<>();
//a.put("hola", "algo");
//a.put("hola2", "algo");
//a.put("hola3", "algo");
//List<String> l = new ArrayList<String>(a.keySet());
//Collections.reverse(l);
//System.out.println(l.get(0));
Instanciador.instanciador();
System.out.println("_____________");
DataLists.galerias.gethead().next.get_objeto().gethead().objeto.print();
	}

}
