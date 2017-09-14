package jsonreader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Meta {
	public String name;
	public ArrayList<HashMap<String,String>> colums;
public Meta() {
	this.name=null;
	this.colums=null;
}
public static  ArrayList<String> metalist(ArrayList<HashMap<String,String>> hashmap){
	ArrayList<String> retornable = new ArrayList<String>();
	int indice = 0;
	while(indice!=hashmap.size()) {
		retornable.add(hashmap.get(indice).get("nombre"));
		indice++;
	}
	return retornable;
}
}
